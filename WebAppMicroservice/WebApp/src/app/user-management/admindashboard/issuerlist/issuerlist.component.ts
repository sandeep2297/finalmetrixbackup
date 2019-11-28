import { Component, OnInit, Inject } from '@angular/core';
import { ClientProfileService } from '../../client/client-profile.service';
import { IssuerDetailsInputService } from '../../issuer/issuer-details-input.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { PipelineFilterService } from 'src/app/activity-pipeline/pipeline-filter/pipeline-filter.service';

@Component({
  selector: 'app-issuerlist',
  templateUrl: './issuerlist.component.html',
  styleUrls: ['./issuerlist.component.css']
})
export class IssuerlistComponent implements OnInit {

  clientArray: any;
  issuerDetails: any = [];
  clientDetails: any;
  totalpipeline = [];
  clientEmail;
  countPipeline = [];

  constructor(public dailogRef: MatDialogRef<IssuerlistComponent>, @Inject(MAT_DIALOG_DATA) data, private add: ClientProfileService, private issuer: IssuerDetailsInputService, private pipeLineListItems: PipelineFilterService) {
    // console.log(data.email);
    this.clientEmail = data.email;
  }

  ngOnInit() {

    // tslint:disable-next-line: no-shadowed-variable
    this.issuer.getAllIssuers(this.clientEmail).subscribe((data: any) => {
      this.issuerDetails = data;
      // console.log(data);
      data.forEach((datavalue, value) => {
        // tslint:disable-next-line: no-shadowed-variable
        this.pipeLineListItems.getAllPipelineListDetails(datavalue.issuerProfileId).subscribe((element) => {
          this.countPipeline.push(element.length);
        });
      });
      this.totalpipeline = this.countPipeline;
      // console.log(this.totalpipeline);
    });
  }

  cancel() {
    this.dailogRef.close();
  }

}
