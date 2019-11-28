import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef } from '@angular/material';
import {MAT_DIALOG_DATA} from '@angular/material';


@Component({
  selector: 'app-pipelinelist',
  templateUrl: './pipelinelist.component.html',
  styleUrls: ['./pipelinelist.component.css']
})
export class PipelinelistComponent implements OnInit {

  constructor(public dailogRef: MatDialogRef<PipelinelistComponent>) { }


  ngOnInit() {
  }

  cancel() {
    this.dailogRef.close();
  }

}
