import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material';

@Component({
  selector: 'app-config-rule-help',
  templateUrl: './config-rule-help.component.html',
  styleUrls: ['./config-rule-help.component.css']
})
export class ConfigRuleHelpComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<ConfigRuleHelpComponent>) { }

  ngOnInit() {
  }
  cancelHelp() {
    this.dialogRef.close();
  }
}
