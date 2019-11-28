import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormControl, Validators } from '@angular/forms';
import { MatDialog, MatDialogConfig } from '@angular/material';
import { RevocationDialogBoxComponent } from '../revocation-dialog-box/revocation-dialog-box.component';
import { AwardsService } from '../awards.service';
import { AuthenticationService } from 'src/app/app-home/authentication.service';

export interface Issuer {
  value: string;
  viewValue: string;
}

@Component({
  selector: 'app-awardees',
  templateUrl: './awardees.component.html',
  styleUrls: ['./awardees.component.css']
})
export class AwardeesComponent implements OnInit {

  issuerList = new FormControl('', [Validators.required]);
  displayedColumns: string[] = ['recipientName', 'issuedOn', 'assertionNarrative'];

  clientId: any;
  record: any;
  issuers: Issuer[];
  selected: any;
  badge: any;
  tagsColln = [];
  badgeImage: any;
  badgeName: any;
  id: string;
  selectedIssuer: any;
  disabledAwardBtn = true;
  showTable = false;
  dataSource: any;
  assertionRevoke: any;
  constructor(private router: Router, private service: AwardsService, private routes: ActivatedRoute, private auth: AuthenticationService, private dailog: MatDialog) { }

ngOnInit() {

    const id = this.routes.snapshot.paramMap.get('id');
    this.service.getBadgeById(id).subscribe((badgeData) => {
          this.badge = badgeData;
          this.tagsColln = this.badge.tags;
          this.badgeImage = this.badge.image;
          this.badgeName = this.badge.name;
    });
    this.auth.getUserProfile().subscribe((data) => {
      this.record = data;
      this.clientId = this.record.userName;
      this.service.getIssuers(this.clientId).subscribe(issuerData => {
        this.issuers = issuerData;
      });
    });
  }
onIssuerSelection() {
    // this.disabledAwardBtn = false;
    // this.service.updateIssuerId(this.selectedIssuer);
    // this.service.getAssertionByIssuerId(this.selectedIssuer).subscribe((assertionRecordData) => {
    //   this.assertionRecordByIssuer = assertionRecordData;
    //   console.log('assertion record by issuer', this.assertionRecordByIssuer);
    //   // this.dataSource = new MatTableDataSource<AssertionRecord>(this.assertionRecordByIssuer);
    //   this.dataSource = this.assertionRecordByIssuer;
    //   console.log('table data', this.dataSource);
    //   if (this.assertionRecordByIssuer.length === 0) {
    //       this.showTable = false;
    //   }
    //   console.log('dataSource data values :', this.dataSource.data.values);
    //   console.log('dataSource data values :', this.dataSource.data);
    //   console.log('assertionRecordData :', assertionRecordData);

    // });
  }
getIssuerName() {
    return this.selected;
  }

awardForm() {
    const id = this.badge.badgeId;
    this.router.navigate(['awards/badge', id, 'award']);
  }

revoke(assertion) {
    this.assertionRevoke = assertion;
    // console.log('assertion in ts', this.assertionRevoke);
    // console.log('assertion from table', assertion);
    const dailogConfig = new MatDialogConfig();
    dailogConfig.disableClose = true;
    dailogConfig.autoFocus = true;
    dailogConfig.width = '40%';
    dailogConfig.height = '50%';
    dailogConfig.data = assertion;
    this.dailog.open(RevocationDialogBoxComponent, dailogConfig);
    }
}


