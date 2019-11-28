import { Component, OnInit, ViewChild, Inject, Input } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators, FormGroupDirective } from '@angular/forms';
import { PipelineFilterService } from '../pipeline-filter/pipeline-filter.service';
import { MatTableDataSource } from '@angular/material/table';
import { WebsocketService } from '../pipeline-filter/websocket.service';
import { WebsocketDataResponseService } from './websocket-data-response.service';
import { AuthenticationService } from 'src/app/app-home/authentication.service';
import {MAT_DIALOG_DATA} from '@angular/material';

import * as moment from 'moment';

export interface IssuerList {
  issuerid: number;
  issuername: string;
  issuermail: string;
}

export interface PipelineList {
  activityPipelineId: number;
  issuerProfileID: string;
  pipelineName: string;
  pipelineDescription: string;
  transformer: boolean;
  endpointUrl: string;
}

export interface PeriodicElement {
  activitytime: string;
  rulename: string;
  result: string;
  actor: string;
  object: string;
  duration: string;
  evaluvation_result: object;
}
@Component({
  selector: 'app-pipeline-filter',
  templateUrl: './pipeline-filter.component.html',
  styleUrls: ['./pipeline-filter.component.css']
})

export class PipelineFilterComponent implements OnInit {
  public issuerList: any[] = [];
  public pipelineList: PipelineList[] = [];
  public disableSelect = true;
  public currentIssuerID = '';
  public dataSourceEmpty = false;
  public dataSourceResult: any = [];
  public notifications = 0;
  public behavioursubscription;
  public display = false;

  pipelinefilter: FormGroup;
  headerDisplay = ['activitytime', 'rulename', 'result', 'actor', 'object', 'duration'];
  expanddisplay = ['expid'];
  expandedElementItem: PeriodicElement;
  clientId: string;
  clientDetails: any;
  constructor(private auth: AuthenticationService, private fb: FormBuilder, private issuerListItems: PipelineFilterService, private pipeLineListItems: PipelineFilterService, private websocketConnection: WebsocketService, private webSocketResponse: WebsocketDataResponseService) {
    this.pipelinefilter = this.fb.group({
      issuerListControl: new FormControl('', [Validators.required]),
      pipelineListControl: new FormControl('', [Validators.required])
    });
  }

  ngOnInit() {
    this.auth.currentMessage().subscribe((data) => {
      this.clientId = data;
      // this.clientId = this.clientDetails.clientEmail;
      // if(this.clientId != "metrixdev") this.clientId = this.cliendIdFromMatDialog;
      // console.log(this.clientId);
      this.issuerListItems.getAllIssuerListDetails(this.clientId).subscribe((dataIssuer) => {
        this.issuerList = dataIssuer;
        this.disableSelect = true;
      });
    });
  }

  selectIssuerItemListIndex(event) {
    const target = event.source.selected._element.nativeElement;
    const selectedDataIssuer = {
      value: event.value,
      text: target.innerText.trim()
    };
    this.disableSelect = false;
    this.currentIssuerID = selectedDataIssuer.value;
    this.getPipelineDetails(this.currentIssuerID);
  }

  getPipelineDetails(issuerID) {
    this.pipeLineListItems.getAllPipelineListDetails(issuerID).subscribe((data) => {
      this.pipelineList = data;
    });
  }
  submitData(formDirective: FormGroupDirective) {
    const issuerID: any[] = this.pipelinefilter.value.issuerListControl;
    const pipelineID: any[] = this.pipelinefilter.value.pipelineListControl;
    this.showRulesetResults(issuerID, pipelineID);
  }

  showNodetailsfoundDecision(dataSource) {
    if ((dataSource !== null && dataSource.length !== 0 && dataSource !== undefined)) {
      this.dataSourceEmpty = true;
    } else {
      this.dataSourceEmpty = false;
    }
    return this.dataSourceEmpty;
  }
  showRulesetResults(issuerID, pipelineID) {
    this.pipeLineListItems.getAllRulesetResultListDetails(issuerID, pipelineID).subscribe((data) => {
      this.dataSourceResult = data;
      this.showNodetailsfoundDecision(this.dataSourceResult);
      this.websocketConnection.initializeWebSocketConnection(issuerID, pipelineID);
      // console.log('form pipeline filter ', this.dataSourceResult);
      this.display = !this.display;
      this.dataSourceResult.forEach((element) => {
        element.activitytime = moment(element.activitytime).format('LLL');
      });
    });
  }
}
