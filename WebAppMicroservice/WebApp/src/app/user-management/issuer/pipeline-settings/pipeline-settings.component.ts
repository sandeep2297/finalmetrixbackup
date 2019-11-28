import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators, FormGroupDirective, FormArray } from '@angular/forms';
import { PipelineService } from '../pipeline.service';
import { Router } from '@angular/router';
import { PipelineDetailsService } from '../pipeline-details.service';
import { LoggerService } from 'src/app/logger.service';
import { IssuerDetailsInputService } from '../issuer-details-input.service';
import { MatDialog, MatDialogConfig } from '@angular/material';
import { ConfirmComponent } from '../confirm/confirm.component';


export interface TransformerValues {
  value: string;
  viewValue: string;
}
@Component({
  selector: 'app-pipeline-settings',
  templateUrl: './pipeline-settings.component.html',
  styleUrls: ['./pipeline-settings.component.css']
})
export class PipelineSettingsComponent implements OnInit {
  transformers: TransformerValues[] = [
    { value: 'github', viewValue: 'Github' },
    { value: 'gitlab', viewValue: 'Gitlab' }
  ];
  pipeline: FormGroup;
  submitted: boolean;
  issuerProfile: string;
  pipelineId: any;
  response: string;
  display = false;
  show = false;
  issuerid: any;
  filter = false;
  checked: true;
  disabled: false;
  urlsplit: any[];
  endPointUrl: string;
  needed: string;
  radioValues: any[] = ['Yes', 'No, data is already in Activtity Stream 2.0 format'];

  ngOnInit() {
    this.urlsplit = this.router.url.split('/');
  }

  constructor(
    private issuer: IssuerDetailsInputService,
    private fb: FormBuilder,
    private add: PipelineService,
    private router: Router,
    private pipelineIdService: PipelineDetailsService,
    private logger: LoggerService,
    private dialog: MatDialog) {
    this.pipeline = this.fb.group({
      endpointUrl: new FormControl(''),
      isTransformer: new FormControl(''),
      pipelineName: new FormControl('', [Validators.required, Validators.pattern('[a-zA-Z ]*')]),
      pipelineDescription: new FormControl('', [Validators.required]),
      transformer: new FormControl(''),
      clientEndpointUrl: new FormControl('', Validators.required),
      headers: this.fb.array([this.addFormGroupHeader()]),

    });
  }

  addFormGroupHeader() {
    return this.fb.group({
      headerField: new FormControl(''),
      headerValue: new FormControl('')
    });
  }

  get headersArray() {
    return this.pipeline.get('headers') as FormArray;
  }
  addHeader(index) {
    this.headersArray.insert(index + 1, this.addFormGroupHeader());
  }

  removeHeader(index) {
    if (this.headersArray.length > 1) {
      this.headersArray.removeAt(index);
    }
  }

  hideFirstField(index) {
    if (index === 0) { return false; }
    return true;
  }

  clearResponse() {
    this.response = '';
    this.display = false;
  }
  submitData(formDirective: FormGroupDirective) {
    this.endPointUrl = `https://metrix-dev.stackroute.io/stream/api/v1/webhook/issuerid/${this.urlsplit[2]}/pipelineid/`;
    this.pipeline.value.endpointUrl = this.endPointUrl;
    if (this.needed === 'Yes') {
      this.pipeline.value.isTransformer = true;
    } else {
    this.pipeline.value.isTransformer = false;
  }
    // this.logger.log(this.pipeline.value);
    this.add.createPipeline(this.pipeline.value, this.urlsplit[2]).subscribe(
      data => {
        // this.logger.log(data);
        this.pipelineId = data.activityPipelineId;
        this.responsePipeline();
        this.display = true;
        this.issuerid = this.issuerProfile;
        this.show = true;
        this.pipelineIdService.changeMessage(data);
      },
      error => {
        // this.logger.error('Failed to add pipeline');
        this.response = 'Failed to add pipeline';
        this.display = true;
      },
      () => {
        // this.logger.log('Pipeline Saved');
        this.response = 'Pipeline Saved';
        this.display = true;
      }
    );
    // console.log('submitted data');
    this.submitted = true;
    formDirective.resetForm();
    this.pipeline.reset();
  }
  responsePipeline() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '300px';
    dialogConfig.height = '165px';
    dialogConfig.data = 'Pipeline saved successfully.';
    this.dialog.open(ConfirmComponent, dialogConfig);
    //  this.dialog.open(ConfirmComponent , {
    //    width: '300px' , height: '165px'
    //  });

  }
  onSelection(event) {
  // this.logger.log('event==>', event);
  }
}


