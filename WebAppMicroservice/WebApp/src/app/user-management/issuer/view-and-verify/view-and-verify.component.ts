import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA, MatSnackBar } from '@angular/material';
import { Router } from '@angular/router';
import { IssueAwardsService } from '../issue-awards.service';

@Component({
  selector: 'app-view-and-verify',
  templateUrl: './view-and-verify.component.html',
  styleUrls: ['./view-and-verify.component.css']
})
export class ViewAndVerifyComponent implements OnInit {

  panelOpenState = false;
  badge = {};
  recipient = {};
  evidence = [{}];
  displayAlignment = true;
  valid: boolean;
  constructor(public dailogRef: MatDialogRef<ViewAndVerifyComponent>,
              @Inject(MAT_DIALOG_DATA) public assertionData: any,
              private router: Router,
              private service: IssueAwardsService,
              private snackBar: MatSnackBar) { }

  ngOnInit() {

  }

  onClose() {
    this.dailogRef.close();
  }

  verify() {
    this.service.verifyAssertion(this.assertionData).subscribe((status) => {
           this.valid = status;
    });
  }
}
