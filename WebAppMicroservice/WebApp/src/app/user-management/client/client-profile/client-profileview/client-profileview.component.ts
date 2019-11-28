import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { ClientProfileService } from '../../client-profile.service';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthenticationService } from 'src/app/app-home/authentication.service';
import { LoggerService } from 'src/app/logger.service';
import { MatDialog, MatDialogConfig } from '@angular/material';
import { UpdateclientComponent } from '../updateclient/updateclient.component';
import { AddIssuerComponent } from 'src/app/user-management/issuer/add-issuer/add-issuer.component';


// export interface ClientProfile {
//   profileName: string;
//   avatarURL: string;
//   clientEmail: string;
//   organizationEmail: string;
//   description: string;
//   organisationName: string;
//   organisationURL: string;
//   aboutOrg: string;
// }

@Component({
  selector: 'app-client-profileview',
  templateUrl: './client-profileview.component.html',
  styleUrls: ['./client-profileview.component.css']
})
export class ClientProfileviewComponent implements OnInit {
  profileName: string;
  currentProfileEmail: string;
  currentUser: any;
  public currentprofileurl: string;
  public currentusername: string;
  public userProfileAvatarUrl: string;

  @Output()
  notify: EventEmitter<boolean> = new EventEmitter<boolean>();

  client: any;
  constructor(private auth: AuthenticationService, private add: ClientProfileService, private router: Router, private route: ActivatedRoute, private logger: LoggerService, public dialog: MatDialog) {
  }
  ngOnInit() {
    this.route.snapshot.paramMap.get('name');
    this.route.paramMap.subscribe(parameterMap => {
      const id = +parameterMap.get('id');
      this.client = id;
    });

    this.getUserProfile();
    this.getUrl();
  }
  getUrl() {
    this.auth.getUserProfile().subscribe((response) => {
      this.currentUser = response;
      this.currentprofileurl = this.currentUser.avatarURL;
      this.currentusername = this.currentUser.name;
      // this.logger.log(this.currentprofileurl);
      // this.logger.log(this.currentusername);
      this.userProfileAvatarUrl = 'url(' + this.currentprofileurl + ')';
    });
  }

  getUserProfile() {
    this.auth.getUserProfile().subscribe((response) => {
      this.currentUser = response;
      this.currentProfileEmail = this.currentUser.email;
      this.checkClientAccountProfile();
    });
  }

  checkClientAccountProfile() {
    this.add.getCurrentClient(this.currentProfileEmail).subscribe(data => {
      this.client = data;
      // console.log(data);
    });
  }
  editProfile() {
    this.router.navigate(['/user/profile/edit', this.currentProfileEmail]);
    const dailogConfig = new MatDialogConfig();
    dailogConfig.disableClose = true;
    dailogConfig.autoFocus = true;
    dailogConfig.width = '800px';
    dailogConfig.height = '650px';
    dailogConfig.data = this.currentProfileEmail;
    this.dialog.open(UpdateclientComponent, dailogConfig);
  }
}
