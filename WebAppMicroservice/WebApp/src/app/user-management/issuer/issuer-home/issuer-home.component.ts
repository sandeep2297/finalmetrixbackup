import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { LoggerService } from 'src/app/logger.service';
import { IssuerDetailsInputService } from '../issuer-details-input.service';
import { AuthenticationService } from 'src/app/app-home/authentication.service';
import { MatDialog, MatDialogConfig } from '@angular/material';
import { AddIssuerComponent } from '../add-issuer/add-issuer.component';
import { IssuerDetails } from '../entity/IssuerDetails';
import * as moment from 'moment';
@Component({
  selector: 'app-issuer-home',
  templateUrl: './issuer-home.component.html',
  styleUrls: ['./issuer-home.component.css']
})

export class IssuerHomeComponent implements OnInit {
  date: any[];
  newDate: any[];
  modifiedDate: any;
  detailsOfClient: any;
  clientId: string;
  result: any;
  issuerData: IssuerDetails;
  filteredIssuers: IssuerDetails;
  viewJoyRide = true;
  viewIssuers = false;
  private searchTermVariable = '';
  get searchTerm(): string {
    return this.searchTermVariable;
  }
  set searchTerm(value: string) {
    this.searchTermVariable = value;
    this.filteredIssuers = this.filterIssuers(this.searchTermVariable);
  }
  filterIssuers(searchString: string) {
    return this.result.filter(issuer => issuer.issuerName.toLowerCase().indexOf(searchString.toLowerCase()) !== -1);
  }

  constructor(
    public router: Router,
    private auth: AuthenticationService,
    private dailog: MatDialog,
    private service: IssuerDetailsInputService,
    private logger: LoggerService,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    this.service.currentMessage().subscribe((data) => {
      this.auth.getUserProfile().subscribe((clientDetails) => {
        this.detailsOfClient = clientDetails;
        this.clientId = this.detailsOfClient.userName;
        // this.logger.log(this.clientId);
        this.onLoad();
      });
    });
  }
  onLoad() {
    this.service.getAllIssuers(this.clientId).subscribe((issuerData) => {
      this.result = issuerData;
      this.filteredIssuers = this.result;
      if (this.result.length === 0) {
        this.viewJoyRide = true;
        this.viewIssuers = false;
      } else {
        this.viewJoyRide = false;
        this.viewIssuers = true;
      }

    });
  }
  addIssuer() {
    const dailogConfig = new MatDialogConfig();
    dailogConfig.disableClose = true;
    dailogConfig.autoFocus = true;
    dailogConfig.width = '650px';
    dailogConfig.height = '650px';
    this.dailog.open(AddIssuerComponent, dailogConfig);
    this.onLoad();
  }
  goToProfile(issuerId) {
    this.router.navigate(['issuer', issuerId, 'profile']);
  }


}
