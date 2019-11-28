import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material';

@Component({
  selector: 'app-rule-modal',
  templateUrl: './rule-modal.component.html',
  styleUrls: ['./rule-modal.component.css']
})
export class RuleModalComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<RuleModalComponent>) { }

  ngOnInit() {
  }
  ok() {
    this.dialogRef.close();
  }

}
