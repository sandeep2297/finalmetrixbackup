import { Component, OnInit } from '@angular/core';
import { MatDialogConfig, MatDialog } from '@angular/material';
import { RevocationDialogComponent } from '../revocation-dialog/revocation-dialog.component';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthenticationService } from 'src/app/app-home/authentication.service';
import { IssueAwardsService } from '../issue-awards.service';
import { ViewAndVerifyComponent } from '../view-and-verify/view-and-verify.component';
import { MatTableDataSource } from '@angular/material/table';
import * as moment from 'moment';

// export interface Assertion {
//   recipientId: string;
//   issuedOn: any;
//   target: string;
//   verb: string;
//   object: string;
//   badge: string;
// }
@Component({
  selector: 'app-awardees-list',
  templateUrl: './awardees-list.component.html',
  styleUrls: ['./awardees-list.component.css']
})
export class AwardeesListComponent implements OnInit {

  // elementdata: Assertion[];
  displayedColumns = ['recipient', 'badgeName', 'issuedOn', 'verb', 'target', 'object', 'type'];
  dataSource;
  assertionRevoke: any;
  issuerId: any;
  assertionRecordByIssuer = [];
  URLsplit: any[];
  revokedReason: string;
  revokedAssertionId: any;
  newCreatedOn: any;
  newAssertion: any;

  constructor(
    private router: Router,
    private service: IssueAwardsService,
    private routes: ActivatedRoute,
    private auth: AuthenticationService,
    private dailog: MatDialog) { }

  ngOnInit() {
    this.showAssertion();
    this.dataSource.filterPredicate = (data: any, filter: string) => {
      return data.recipient.recipientId === filter;
     };
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue;
  }
  showAssertion() {
    this.URLsplit = this.router.url.split('/');
    this.issuerId = this.URLsplit[2];
    this.service.getAssertionByIssuerId(this.issuerId).subscribe((assertionArray) => {
      this.assertionRecordByIssuer = [];
      assertionArray.forEach((element: any) => {
        if (element.revoked === false) {
          element.issuedOn =  moment(element.issuedOn).format('LLL');
          element.recipientId = element.recipient.recipientId;
          this.assertionRecordByIssuer.push(element);
        }
      });
      this.dataSource = new MatTableDataSource(this.assertionRecordByIssuer);
    });
    this.dataSource.filterPredicate = (data: any, filter: string) => {
      return data.recipient.recipientId === filter;
    };
  }
  revoke(assertion) {
    this.assertionRevoke = assertion;
    const dailogConfig = new MatDialogConfig();
    dailogConfig.disableClose = true;
    dailogConfig.autoFocus = true;
    dailogConfig.width = '50%';
    dailogConfig.height = '50%';
    dailogConfig.data = assertion;
    this.dailog.open(RevocationDialogComponent, dailogConfig)
      .afterClosed()
      .subscribe(revokedReason => {
        if (revokedReason !== 'dontDelete') {
          this.revokedReason = revokedReason;
          this.service.UpdateAssertionAfterRevocation(this.assertionRevoke.assertionId, this.revokedReason).subscribe((latestAssertionRevoked) => {
            this.revokedAssertionId = latestAssertionRevoked.assertionId;
            this.showAssertion();
          });
        }
      });

  }
  view(assertion) {
    const convertedAssertion = Object.assign({}, assertion, {issuedOn: moment(assertion.issuedOn).utc().format()});
    this.assertionRevoke = assertion;
    const dailogConfig = new MatDialogConfig();
    dailogConfig.disableClose = true;
    dailogConfig.autoFocus = true;
    dailogConfig.width = '50%';
    dailogConfig.height = '70%';
    dailogConfig.data = convertedAssertion;
    this.dailog.open(ViewAndVerifyComponent, dailogConfig);
  }
}



