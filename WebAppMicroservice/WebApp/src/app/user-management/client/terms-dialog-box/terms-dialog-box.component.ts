import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material';
import { ClientProfileService } from '../client-profile.service';
import { AuthenticationService } from 'src/app/app-home/authentication.service';

@Component({
  selector: 'app-terms-dialog-box',
  templateUrl: './terms-dialog-box.component.html',
  styleUrls: ['./terms-dialog-box.component.css']
})
export class TermsDialogBoxComponent implements OnInit {

  currentUser: any;
  currentProfileEmail: string;
  errorMsg: string;
  count = 0;
  isActive = false;

  constructor(private auth: AuthenticationService, private user: ClientProfileService, private dialogRef: MatDialogRef<TermsDialogBoxComponent>) { }

  ngOnInit() {
    this.getUserProfile();
  }

  getUserProfile() {
    this.auth.getUserProfile().subscribe((response) => {
      this.currentUser = response;
      this.currentProfileEmail = this.currentUser.email;
    });
  }

  closeModalwithPatch() {
    this.user.updateUserData(this.currentProfileEmail).subscribe((data) => {

    },
      error => (this.errorMsg = 'Server Not Found')
    );
    this.dialogRef.close();
  }

  closeModal() {
    this.dialogRef.close();
  }

  onCheck() {
    this.count++;
    if (this.count === 1) {
      this.isActive = true;
    } else {
      this.isActive = false;
      this.count = 0;
    }


  }

}
