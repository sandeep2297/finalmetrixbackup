import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA, MatSnackBar } from '@angular/material';
import { Router } from '@angular/router';
import { AwardsService } from '../awards.service';

@Component({
  selector: 'app-revocation-dialog-box',
  templateUrl: './revocation-dialog-box.component.html',
  styleUrls: ['./revocation-dialog-box.component.css']
})
export class RevocationDialogBoxComponent implements OnInit {


  selectedRevokedReason: string;
  revokedReasons: string[] = ['Issued in error', 'Award reconsidered', 'Violation of conditions',
   'Duplicate badge issued', 'Wrong email', 'No evidence attached', 'Issued during testing',
  'Wrong issued date'];

  constructor(public dailogRef: MatDialogRef<RevocationDialogBoxComponent>,
              @Inject(MAT_DIALOG_DATA) public assertionData: any,
              private router: Router,
              private service: AwardsService,
              private snackBar: MatSnackBar) { }

  ngOnInit() {
  }

  onClose() {
    this.dailogRef.close();
  }
  onCancel() {
    this.onClose();
  }
  revoke() {
    // console.log('selected revoked reason', this.selectedRevokedReason);
    // console.log('assertion data', this.assertionData);
    this.openSnackBar(' Badge Revoked For ', this.assertionData.recipientName);
    this.service.UpdateAssertionAfterRevocation(this.assertionData.assertionId, this.selectedRevokedReason)
        .subscribe((assertion) => {
          //  console.log('assertion after revocation in revocation dailog ', assertion);
        });
    this.onClose();
  }
  openSnackBar(message: string, action: string) {
    this.snackBar.open(message, action, {
      duration: 2000,
    });
  }
}
