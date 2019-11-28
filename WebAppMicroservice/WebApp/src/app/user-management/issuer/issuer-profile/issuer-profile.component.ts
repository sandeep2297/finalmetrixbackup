import { Component, OnInit, Injectable } from '@angular/core';
import { IssuerDetailsInputService } from '../issuer-details-input.service';
import { LoggerService } from 'src/app/logger.service';
import { ActivatedRoute, Router, NavigationEnd } from '@angular/router';
import { PipelineService } from '../pipeline.service';
import { MatDialog, MatDialogConfig } from '@angular/material';
import { AddIssuerComponent } from '../add-issuer/add-issuer.component';

@Component({
  selector: 'app-issuer-profile',
  templateUrl: './issuer-profile.component.html',
  styleUrls: ['./issuer-profile.component.css']
})

export class IssuerProfileComponent implements OnInit {
  issuers = [];
  errorMsg: string;
  nullResult = false;
  issuerId: string;
  pipeline = [];
  urlSplit: any[];
  constructor(private router: Router,
              private pipelineData: PipelineService,
              private service: IssuerDetailsInputService,
              private logger: LoggerService,
              private routes: ActivatedRoute,
              private dialog: MatDialog) { }

  ngOnInit() {
    this.urlSplit = this.router.url.split('/');
    this.service.getSelectedIssuer(this.urlSplit[2]).subscribe((issuerData) => {
      this.issuers.push(issuerData);
      // this.logger.log(this.issuers);
    });
  }
  editIssuerProfile(issuerId) {
    const dailogConfig = new MatDialogConfig();
    dailogConfig.disableClose = true;
    dailogConfig.autoFocus = true;
    dailogConfig.width = '650px';
    dailogConfig.height = '650px';
    dailogConfig.data = issuerId;
    this.dialog.open(AddIssuerComponent, dailogConfig);

    // this.router.navigate(['/edit', issuerId]);
  }
}
