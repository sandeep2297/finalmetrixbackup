import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { IssueAwardsService } from '../issue-awards.service';
import { Router } from '@angular/router';

export interface Badge {
  name: any;
  description: any;
  image: any;
  criteria: any;
  issuer: any;
  alignment: any[];
  tags: any[];
}

@Component({
  selector: 'app-confirmation-and-convert',
  templateUrl: './confirmation-and-convert.component.html',
  styleUrls: ['./confirmation-and-convert.component.css']
})

export class ConfirmationAndConvertComponent implements OnInit {

  issuerId: any;
  urlSplit: any;
  badgeClass = {};

  constructor(public dailogRef: MatDialogRef<ConfirmationAndConvertComponent>,
              @Inject(MAT_DIALOG_DATA) public badgeData: any,
              private issueAwards: IssueAwardsService,
              private router: Router) {
              }

  ngOnInit() {
    this.urlSplit = this.router.url.split('/');
    this.issuerId = this.urlSplit[2];
  }

  confirmed() {
    // console.log(this.badgeData.clientId);
    // tslint:disable-next-line: no-string-literal
    this.badgeClass['name'] = this.badgeData.name;
    // tslint:disable-next-line: no-string-literal
    this.badgeClass['issuer'] = this.issuerId;
    // tslint:disable-next-line: no-string-literal
    this.badgeClass['description'] = this.badgeData.description;
    // tslint:disable-next-line: no-string-literal
    this.badgeClass['criteria'] = this.badgeData.criteria;
    // tslint:disable-next-line: no-string-literal
    this.badgeClass['alignment'] = this.badgeData.alignment;
    // tslint:disable-next-line: no-string-literal
    this.badgeClass['tags'] = this.badgeData.tags;
    // tslint:disable-next-line: no-string-literal
    this.badgeClass['image'] = this.badgeData.image;
    // tslint:disable-next-line: no-string-literal
    // console.log(this.badgeClass);
    this.issueAwards.postBadgeClass(this.badgeClass).subscribe((data) => {
    });
    this.dailogRef.close(this.badgeClass);
  }

  cancel() {
    this.dailogRef.close();
  }

}
