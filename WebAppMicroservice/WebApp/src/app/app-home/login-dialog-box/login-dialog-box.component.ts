import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { ClientProfileComponent } from 'src/app/user-management/client/client-profile/client-profile.component';

@Component({
  selector: 'app-login-dialog-box',
  templateUrl: './login-dialog-box.component.html',
  styleUrls: ['./login-dialog-box.component.css']
})
export class LoginDialogBoxComponent implements OnInit {


  constructor(private dialogRef: MatDialogRef<ClientProfileComponent>) { }

  ngOnInit() {
  }

  closeModal() {
    this.dialogRef.close();
  }




}
