import { Component, OnInit, Input } from '@angular/core';
import { PipelineService } from '../pipeline.service';
import { ActivatedRoute, Router } from '@angular/router';
import { LoggerService } from 'src/app/logger.service';
import { headersToString } from 'selenium-webdriver/http';
import { AuthenticationService } from 'src/app/app-home/authentication.service';
import * as moment from 'moment';


export class Headers {
  headerField: string;
  headerValue: string;
}
@Component({
  selector: 'app-view-pipeline',
  templateUrl: './view-pipeline.component.html',
  styleUrls: ['./view-pipeline.component.css']
})
export class ViewPipelineComponent implements OnInit {
  pipelines: any;
  urlSplit: any[];
  header: string;
  dataSource: any[];
  transformerValue: string;
  detailsOfClient: any;
  created: string;
  name: string;
  viewHeader = false;
  pDesc: string;
  viewPipeline = false;
  viewEmpty = true;
  clientId: string;
  clientEndPoint: string;
  clientName: string;
  constructor(
    private service: PipelineService,
    public route: ActivatedRoute,
    private logger: LoggerService,
    private router: Router,
    public auth: AuthenticationService) { }

  ngOnInit() {
    this.urlSplit = this.router.url.split('/');
    this.auth.getUserProfile().subscribe((clientDetails) => {
      this.detailsOfClient = clientDetails;
      this.clientId = this.detailsOfClient.userName;
      this.clientName = this.detailsOfClient.name;
      this.logger.log('client name===>', this.clientName);
      this.logger.log(this.clientId);
      this.service.getSelectedPipeline(this.urlSplit[2], this.urlSplit[4]).subscribe((pipelinedata) => {
        this.logger.log('pipelinedata ', pipelinedata);
        this.pipelines = pipelinedata;
        this.logger.log('pipelines===>', this.pipelines);
        this.name = this.pipelines.pipelineName;
        this.pDesc = this.pipelines.pipelineDescription;
        if (this.pipelines.transformer.length === 0) {
          this.viewPipeline = false;
          this.viewEmpty = true;
          //  this.transformerValue = 'Input data is in Activity Stream 2.0 format';
        } else {
            this.viewPipeline = true;
            this.viewEmpty = false;
            this.transformerValue = ' ' + this.pipelines.transformer + ' transformer';
        }
        this.dataSource = this.pipelines.headers;
        this.logger.log('pipeline headers', this.pipelines.headers);
        if (this.dataSource[0].headerField === '') {
          this.viewHeader = false;
        } else {
          this.viewHeader = true;
        }
        this.clientEndPoint = this.pipelines.clientEndpointUrl;
        this.logger.log('client end point===>', this.clientEndPoint);
        this.created = moment.utc(this.pipelines.createdOn).format('LL');
        this.logger.log('created On===>', this.created);
        });
    });
  }
  copyInputMessage(inputElement) {
    inputElement.select();
    document.execCommand('copy');
    inputElement.setSelectionRange(0, 0);
  }
}
