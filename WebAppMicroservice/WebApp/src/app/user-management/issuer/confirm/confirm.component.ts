import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-confirm',
  templateUrl: './confirm.component.html',
  styleUrls: ['./confirm.component.css']
})
export class ConfirmComponent implements OnInit {
  cancelDialog = 'cancel';
  showCancel = false;

  constructor(
    public dialogRef: MatDialogRef<ConfirmComponent>,
    @Inject(MAT_DIALOG_DATA) public message: string) { }

  ngOnInit() {
    if (this.message === 'Archived Rules cannot be unarchived, Continue?') {
      this.showCancel = true;
    }
    if (this.message === 'Sure to update?') {
      this.showCancel = true;
    }
  }

  closeOk() {
    this.dialogRef.close();
  }

  cancel() {
    this.dialogRef.close(this.cancelDialog);
  }

}
