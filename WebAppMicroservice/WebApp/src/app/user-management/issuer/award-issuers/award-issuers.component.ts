import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { AuthenticationService } from 'src/app/app-home/authentication.service';
import { IssueAwardsService } from '../issue-awards.service';
import { MatDialogConfig, MatDialog } from '@angular/material/dialog';
import { ConfirmationAndConvertComponent } from '../confirmation-and-convert/confirmation-and-convert.component';

@Component({
  selector: 'app-award-issuers',
  templateUrl: './award-issuers.component.html',
  styleUrls: ['./award-issuers.component.css']
})

export class AwardIssuersComponent implements OnInit {
  badgeArray: any[] = [];
  selectedBadgeArray: any[] = [];
  record: any;
  clientId: any;
  issuerId: any;
  urlSplit: any[];

  constructor(private router: Router,
              private http: HttpClient,
              private auth: AuthenticationService,
              private dailog: MatDialog,
              private issueAwards: IssueAwardsService,
              private route: ActivatedRoute) { }

  getBadgeClass() {
    this.issueAwards.getAllBadgeClass(this.urlSplit[2]).subscribe((badgeClassData) => {
      if (badgeClassData.length > 0) {
        this.selectedBadgeArray = badgeClassData;
      }
      this.getBadges();
    });
  }

  getBadges() {
    this.issueAwards.getAllBadges(this.clientId).subscribe((clientData) => {
      const selectedBadgeArrayNameList = this.selectedBadgeArray.map(badge => badge.name);
      const filteredBadgeSet = clientData.filter(badgetemplate => selectedBadgeArrayNameList.indexOf(badgetemplate.name) === -1);
      this.badgeArray = filteredBadgeSet;
    });
  }

  ngOnInit() {
    this.urlSplit = this.router.url.split('/');
    this.auth.getUserProfile().subscribe((data) => {
      this.record = data;
      this.clientId = this.record.userName;
    });
    this.getBadgeClass();
  }

  confirmationAndConvert(badgeTemplate: any) {
    const dailogConfig = new MatDialogConfig();
    dailogConfig.disableClose = true;
    dailogConfig.autoFocus = true;
    dailogConfig.width = '30%';
    dailogConfig.height = '30%';
    dailogConfig.data = badgeTemplate;
    this.dailog.open(ConfirmationAndConvertComponent, dailogConfig)
      .afterClosed()
      .subscribe(data => {
        this.issueAwards.getAllBadgeClass(this.urlSplit[2]).subscribe((badgeClassData) => {
          this.selectedBadgeArray = badgeClassData;
          // this.getBadgeClass();
          this.getBadges();
          // console.log('badge template after conversion', this.badgeArray.values);
        });
      });
  }
  awardsManually(badgeclass) {
    this.router.navigate([badgeclass.badgeClassId, 'award'], { relativeTo: this.route});
  }


}
