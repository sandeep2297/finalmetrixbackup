import { Component, OnInit } from '@angular/core';
import { ClientProfileService } from '../client-profile.service';
import { ActivatedRoute } from '@angular/router';
import { LoggerService } from 'src/app/logger.service';
import { AuthenticationService } from 'src/app/app-home/authentication.service';
import { ChartOptions, ChartType, ChartDataSets } from 'chart.js';
import { Label } from 'ng2-charts';
import { IssuerDetailsInputService } from '../../issuer/issuer-details-input.service';
import { PipelineService } from '../../issuer/pipeline.service';
import * as moment from 'moment';
import * as lodash from 'lodash';
import { IssueAwardsService } from '../../issuer/issue-awards.service';
import { TermsDialogBoxComponent } from '../terms-dialog-box/terms-dialog-box.component';
import { MatDialog } from '@angular/material';

@Component({
  selector: 'app-client-dashboard',
  templateUrl: './client-dashboard.component.html',
  styleUrls: ['./client-dashboard.component.css']
})
export class ClientDashboardComponent implements OnInit {
  public records: any[];
  private nameOfClient: any;
  public currentUser: any;
  public currentProfileEmail: string;
  public accountProfile = true;
  issuerArray: any = [];
  clientArray: any = [];
  dataPassedArray: any = [];
  dataFailedArray: any = [];
  errorMessage: any;
  errorObserve = false;
  issuerDetails: any;
  issuerIdArray: any = [];
  pipelineIdArray: any = [];
  pipelineNameArray: any = [];
  pipelinedatacount = 0;
  issuercount = 0;
  resultArray: any = [];
  timeArray: any = [];
  datacount = 0;
  isActive = true;
  issuerDetailsArray: any = [];
  graphDisplay = false;
  badgecount = 0;


  public lineChartOptions: ChartOptions = {
    responsive: true,
    // We use these empty structures as placeholders for dynamic theming.
    scales: {
      xAxes: [{
        stacked: false,
        ticks: {
          fontColor: 'black',  // x axe labels (can be hexadecimal too)
        },
        gridLines: {
          display: false,
          color: '#5f5e5e9e'  // grid line color (can be removed or changed)
        },
        scaleLabel: {
          display: true,
          labelString: 'Issuers',
          fontColor: 'black',  // x axe label color (can be hexadecimal too)
          fontSize: 20
        }
      }], yAxes: [{
        stacked: false,
        ticks: {
          fontColor: 'black',  // y axes numbers color (can be hexadecimal too)
          min: 0,
          beginAtZero: true,
        },
        gridLines: {
          display: false,
          color: '#5f5e5e9e'  // grid line color (can be removed or changed)
        },
        scaleLabel: {
          display: true,
          labelString: 'User Activity',
          fontColor: 'black',
          fontSize: 20           // y axe label color (can be hexadecimal too)
        }
      }]
    },
    plugins: {
      datalabels: {
        anchor: 'end',
        align: 'end',
      }
    }
  };
  public lineChartLabels: Label[] = this.issuerArray;
  public lineChartType: ChartType = 'line';
  public lineChartLegend = true;
  // public barChartPlugins = [pluginDataLabels];

  public lineChartData: ChartDataSets[] = [
    { data: this.dataPassedArray, label: 'Data Passed' },
    { data: this.dataFailedArray, label: 'Data Failed ' }
  ];

  public chartColors: Array<any> = [
    { // first color
      backgroundColor: 'rgba(148,159,177,0.2)',
      borderColor: 'rgba(148,159,177,1)',
      pointBackgroundColor: 'rgba(148,159,177,1)',
      pointBorderColor: '#fff',
      pointHoverBackgroundColor: '#fff',
      pointHoverBorderColor: 'rgba(148,159,177,0.8)'
    },
    { // second color
      backgroundColor: 'rgba(255,0,0,0.3)',
      borderColor: 'red',
      pointBackgroundColor: 'rgba(148,159,177,1)',
      pointBorderColor: '#fff',
      pointHoverBackgroundColor: '#fff',
      pointHoverBorderColor: 'rgba(148,159,177,0.8)'
    }];


  constructor(public dialog: MatDialog, private getBadge: IssueAwardsService, private logger: LoggerService, private pipeline: PipelineService, private issuer: IssuerDetailsInputService, private auth: AuthenticationService, private getClient: ClientProfileService, private route: ActivatedRoute) { }

  ngOnInit() {
    // this.logger.log('24 hrs data ', result[0]);
    // let time = moment().utc();
    // time.set({ hour: 0, minute: 0, second: 0, millisecond: 0 });
    // // this.logger.log('time is: ', time.format('HH:mm'));
    // this.timeArray.push(time.format('HH:mm'));
    // for (let i = 2; i <= 24; i++) {
    //   time = time.add(1, 'hours');
    //   // const time = moment.utc(00.).format('HH:mm');
    //   // this.logger.log('time is: ', time.format('HH:mm'));
    //   this.timeArray.push(time.format('HH:mm'));
    // }


    this.getClient.getAllClients().subscribe((data) => {
      this.records = data;
    });



    const tempClientName = this.route.snapshot.paramMap.get('profileName');
    this.nameOfClient = tempClientName;

    this.getUserProfile();

  }

  public chartClicked({ event, active }: { event: MouseEvent, active: {}[] }): void {
    // this.logger.log(event, active);
  }

  public chartHovered({ event, active }: { event: MouseEvent, active: {}[] }): void {
    // this.logger.log(event, active);
  }

  public randomize(): void {
    this.lineChartType = this.lineChartType === 'line' ? 'bar' : 'line';
  }

  getUserProfile() {
    this.auth.getUserProfile().subscribe((response) => {
      this.currentUser = response;
      this.currentProfileEmail = this.currentUser.email;
      // this.isActive = false;
      if (this.currentUser.read === false) {

        this.dialog.open(TermsDialogBoxComponent, {
          width: '1000px', height: '600px',
        });
      }
      this.checkClient();
    });
  }

  checkClient() {

    this.getClient.getCurrentClient(this.currentProfileEmail).subscribe((data) => {
      if (data == null) {
        this.accountProfile = false;
      }
      this.isActive = false;
    });

    // this.issuer.getAllIssuers(this.currentProfileEmail).subscribe((response) => {
    //   this.logger.log('issuer Details', response);
    //   this.logger.log('before saving issuers: ' + this.issuerDetails);
    //   this.issuerDetailsArray = response;
    //   this.issuercount = this.issuerDetailsArray.length;
    //   this.issuerDetailsArray.forEach((item) => {
    //     this.pipeline.getPipelineDetails(item.issuerProfileId).subscribe((pipelineDetails) => {
    //       this.pipelinedatacount += pipelineDetails.length;
    //     });
    //     this.getClient.getData(item.issuerProfileId).subscribe((result) => {
    //       this.datacount += result.totalcount;
    //       this.logger.log('total count= ' + result.totalcount);
    //     });
    //   });
    // });
    this.issuer.getAllIssuers(this.currentProfileEmail).subscribe((response) => {
      // this.logger.log('issuer Details', response);
      // this.logger.log('before saving issuers: ' + this.issuerDetails);
      this.issuerDetails = response;
      this.issuercount = this.issuerDetails.length;
      this.issuerDetails.forEach((item) => {
        this.pipeline.getPipelineDetails(item.issuerProfileId).subscribe((pipelineDetails) => {
          this.pipelinedatacount += pipelineDetails.length;
        });
        // this.logger.log('issuer: ', item);
        // this.issuerArray.push(item.issuerName);
        this.getClient.getData(item.issuerProfileId).subscribe((result) => {
          // this.barChartLabels.(item.issuerName);
          this.issuerArray.push(item.issuerName);
          this.dataPassedArray.push(result.passcount);
          // this.logger.log('passcount ', result.passcount);
          this.dataFailedArray.push(result.failcount);
          // this.logger.log('failcount', result.failcount);
          // this.logger.log('result of each issuer', result);
          this.datacount += result.totalcount;
          // this.logger.log('total count= ' + result.totalcount);
        });
        this.getBadge.getAssertionByIssuerId(item.issuerProfileId).subscribe((badge) => {
          this.badgecount += badge.length;
        });
      });
    });

  }

  // groupDateTimeRangedResult(datatimeRangeEvalResult) {
  //   console.log('CHECK input array::', datatimeRangeEvalResult);
  //   const updateResultWithHour = datatimeRangeEvalResult.map(result => Object.assign({}, result, { hourOfActivity: moment(result.activityTime).get('hour') }));
  //   console.log('CHeck hour updated result::', updateResultWithHour);
  //   const groupByDate = lodash.groupBy(updateResultWithHour, 'hourOfActivity');
  //   console.log('check group::', groupByDate);
  //   const hourlyCount = Object.keys(groupByDate).map(hour => {
  //     return {
  //       hour,
  //       count: groupByDate[hour].length
  //     };
  //   });
  //   return hourlyCount;
  // }

  // submitGraph(date) {

  //   this.graphDisplay = true;
  //   this.logger.log(date);
  //   const startdate = moment.utc(date).format('YYYY-MM-DDTHH:mm:ss');
  //   const enddate = moment.utc(date).add(1, 'days').format('YYYY-MM-DDTHH:mm:ss');
  //   this.logger.log('startdate', startdate);
  //   this.logger.log('endddate', enddate);
  //   this.issuer.getAllIssuers(this.currentProfileEmail).subscribe((response) => {
  //     // this.dataPassedArray = new Array(24).fill(0);
  //     // this.dataFailedArray = new Array(24).fill(0);
  //     this.logger.log('issuer Details', response);
  //     this.logger.log('before saving issuers: ' + this.issuerDetails);
  //     this.issuerDetails = response;
  //     this.issuerDetails.forEach((item) => {
  //       this.logger.log('issuer: ', item);
  //       // const dataCollection = [];
  //       // this.issuerArray.push(item.issuerName);
  //       this.getClient.getDateData(item.issuerProfileId, startdate, enddate).subscribe((result) => {
  //         // const resultDataSource = this.groupDateTimeRangedResult(result);
  //         // dataCollection.push(...resultDataSource);
  //         // this.logger.log('24 hrs data ', result[0]);
  //         // const time = moment.utc(item.activityTime).format('HH:mm');
  //         // this.logger.log('time is: ', time);
  //         // this.timeArray.push(time);
  //         this.logger.log('result data ', result);
  //         for (let i = 0; i < 24; i++) {
  //           console.log(this.dataPassedArray[i], '   ', result[i].passed);
  //           this.dataPassedArray[i] = this.dataPassedArray[i] + result[i].passed;
  //           this.dataFailedArray[i] = this.dataFailedArray[i] + result[i].failed;
  //         }
  //         // result.forEach((items, i) => {
  //         //   this.dataPassedArray[i] += (items.passed);
  //         //   // this.logger.log('passcount ', items.passcount);
  //         //   this.dataFailedArray[i] += (items.failed);
  //         //   // this.logger.log('failcount', items.failcount);
  //         // });
  //         console.log('totat data passed', this.dataPassedArray);
  //         console.log('totat failed passed', this.dataFailedArray);
  //       });
  //     });

  //   });

  // }
}
