import { Component, OnInit, ViewChild, OnChanges } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { AwardsService } from '../awards.service';
import { MatDialog, MatDialogConfig, MatTableDataSource } from '@angular/material';
import { ViewBadgeComponent } from '../view-badge/view-badge.component';
import { AuthenticationService } from 'src/app/app-home/authentication.service';
import { LoggerService } from 'src/app/logger.service';

@Component({
  selector: 'app-list-badge',
  templateUrl: './list-badge.component.html',
  styleUrls: ['./list-badge.component.css']
})

export class ListBadgeComponent implements OnInit {
  badgeArray: any;
  record: any;
  clientId: any;
  ELEMENT_DATA: any;
  filteredBadges: any;
  result2: any;
  badgeLen = false;
  flipped = false;
  datasource = new MatTableDataSource(this.ELEMENT_DATA);
  private searchTermVariable = '';

  constructor(private router: Router,
              private http: HttpClient,
              private awardsService: AwardsService,
              private dailog: MatDialog,
              private auth: AuthenticationService,
              private logger: LoggerService) {
  }

  ngOnInit() {
    this.auth.getUserProfile().subscribe((data: any) => {
      // console.log('data ', data);
      // console.log('data client Id', data.userName);
      this.clientId = data.userName;
      // console.log('client Id', this.clientId);
      // this.logger.log(this.clientId);
      this.getBadges();
    });
  }

  getBadges() {
    // console.log('client Id', this.clientId);
    this.awardsService.getAllBadges(this.clientId).
    subscribe((clientData: any) => {
      // console.log('client data badges', clientData);
      this.badgeArray = clientData;
      this.ELEMENT_DATA = this.badgeArray;
      this.filteredBadges = this.badgeArray;
      this.badgeLen = (this.ELEMENT_DATA !== undefined) ? true : false;
    });
  }

  createBadge() {
    this.router.navigate(['/awards/badge/create']);
    this.getBadges();
  }

  applyFilter(filterValue: string) {
    this.datasource.filter = filterValue.trim().toLowerCase();
  }
  editBadge(badgeId) {
    this.router.navigate(['awards/badge/edit', badgeId]);
  }

  createViewBadge(badge: any) {
    const dailogConfig = new MatDialogConfig();
    dailogConfig.disableClose = true;
    dailogConfig.autoFocus = true;
    dailogConfig.width = '50%';
    dailogConfig.height = '80%';
    dailogConfig.data = badge;
    this.dailog.open(ViewBadgeComponent, dailogConfig);
  }
  // flipIt() {
  //   this.flipped = !this.flipped;
  // }

  get searchTerm(): string {
    return this.searchTermVariable;
  }
  set searchTerm(value: string) {
    this.searchTermVariable = value;
    this.filteredBadges = this.filterBadges(this.searchTermVariable);
  }
  filterBadges(searchString: string) {
    this.result2 = this.badgeArray;
    return this.result2.filter(badges =>
      badges.name.toLowerCase()
      .indexOf(searchString.toLowerCase()) !== -1);
  }

}
