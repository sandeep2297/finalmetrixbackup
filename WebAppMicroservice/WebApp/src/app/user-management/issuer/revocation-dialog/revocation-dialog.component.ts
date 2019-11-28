import { Component, OnInit, Inject, Output, EventEmitter } from '@angular/core';
import { MAT_DIALOG_DATA, MatSnackBar, MatDialogRef } from '@angular/material';
import { Router } from '@angular/router';
import { IssueAwardsService } from '../issue-awards.service';

@Component({
  selector: 'app-revocation-dialog',
  templateUrl: './revocation-dialog.component.html',
  styleUrls: ['./revocation-dialog.component.css']
})
export class RevocationDialogComponent implements OnInit {

  revoked: boolean;
  selectedRevokedReason: string;
  revokedReasons: string[] = ['Issued in error', 'Award reconsidered', 'Violation of conditions',
   'Duplicate badge issued', 'Wrong email', 'No evidence attached', 'Issued during testing',
  'Wrong issued date'];

  constructor(public dailogRef: MatDialogRef<RevocationDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public assertionData: any,
              private router: Router,
              private service: IssueAwardsService,
              private snackBar: MatSnackBar) { }

  ngOnInit() {
  }
  onClose() {
    this.dailogRef.close({data: this.selectedRevokedReason});
  }
  onCancel() {
    this.dailogRef.close('dontDelete');
  }
  revoke() {
    this.revoked = true;
    this.openSnackBar(' Badge Revoked', '');
    this.onClose();
  }
  openSnackBar(message: string, action: string) {
    this.snackBar.open(message, action, {
      duration: 2000,
    });
  }

}
