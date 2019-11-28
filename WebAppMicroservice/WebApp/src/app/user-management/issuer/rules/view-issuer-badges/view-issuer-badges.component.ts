import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { IssueAwardsService } from '../../issue-awards.service';

@Component({
  selector: 'app-view-issuer-badges',
  templateUrl: './view-issuer-badges.component.html',
  styleUrls: ['./view-issuer-badges.component.css']
})
export class ViewIssuerBadgesComponent implements OnInit {
  issuerAwards: any[];

  constructor(public dialogRef: MatDialogRef<ViewIssuerBadgesComponent>,
              @Inject(MAT_DIALOG_DATA) public issuerId: string,
              private badgeService: IssueAwardsService) { }

  ngOnInit() {
    this.badgeService.getAllBadgeClass(this.issuerId).subscribe((issuers) => {
      this.issuerAwards = issuers;
    });
  }

  selectedBadge(issuerAward) {
    this.dialogRef.close(issuerAward);
  }

  onCancel() {
    this.dialogRef.close('');
  }
}
