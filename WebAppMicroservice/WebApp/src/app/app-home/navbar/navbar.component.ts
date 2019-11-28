import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { LoginDialogBoxComponent } from '../login-dialog-box/login-dialog-box.component';
import { AuthenticationService } from '../authentication.service';
import { LoggerService } from 'src/app/logger.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
})
export class NavbarComponent implements OnInit {
  public currentUser: any;
  public currentprofileurl: string;
  public currentusername: string;
  public userProfileAvatarUrl: string;
  public statusCode: number;
  public currentUserRole: string;
  public displaymetrix = true;

  constructor(public dialog: MatDialog, public router: Router, private auth: AuthenticationService, private logger: LoggerService) { }
  // function for opening login options as a modal window
  openDialog() {
    this.dialog.open(LoginDialogBoxComponent, {
      width: '400px', height: '250px',
    });
  }
  ngOnInit() {
    this.getUrl();
  }

  getUrl() {
    this.auth.getUserProfile().subscribe((response) => {
      this.currentUser = response;
      this.currentprofileurl = this.currentUser.avatarURL;
      this.currentusername = this.currentUser.name;
      this.currentUserRole = this.currentUser.role;
      this.logger.log(this.currentUserRole);
      if (this.currentUser.role === 'SUPERADMIN') {
        this.displaymetrix = false;
      }
      this.logger.log(this.currentprofileurl);
      this.logger.log(this.currentusername);
      this.userProfileAvatarUrl = 'url(' + this.currentprofileurl + ')';
    });
  }

  logout() {
    this.auth.logout().subscribe(value => {
    }, error => {
    },
      () => {
      });
    this.router.navigate(['/']);
  }

}
