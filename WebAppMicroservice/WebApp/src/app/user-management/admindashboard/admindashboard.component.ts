import { Component, OnInit, ViewChild } from '@angular/core';
import { ClientProfileService } from '../client/client-profile.service';
import { MatPaginator } from '@angular/material/paginator';
import { IssuerDetailsInputService } from '../issuer/issuer-details-input.service';
import { AwardsService } from 'src/app/awards/awards.service';
import { PipelineService } from '../issuer/pipeline.service';
import { MatDialog } from '@angular/material';
import { IssuerlistComponent } from './issuerlist/issuerlist.component';
import { PipelinelistComponent } from './pipelinelist/pipelinelist.component';
import { AuthenticationService } from 'src/app/app-home/authentication.service';


@Component({
  selector: 'app-admindashboard',
  templateUrl: './admindashboard.component.html',
  styleUrls: ['./admindashboard.component.css']
})
export class AdmindashboardComponent implements OnInit {

  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;
  count: any = 0;
  count1: any = 0;
  countArray: any[] = [];
  countArray1: any[] = [];
  countArray2: any[] = [];
  issuerIdArray: any = [];
  allpipelinedata: any = [];
  pipelineIdArray: any = [];
  count2: number;
  issuerArray: any = [];
  clientDetails: any;

  constructor(private pipeline: PipelineService, private add: ClientProfileService, private issuer: IssuerDetailsInputService, private award: AwardsService, public dialog: MatDialog, private auth: AuthenticationService) { }
  clientArray: any;
  issuerDetails: any;
  badgeDetails: any;
  ngOnInit() {
    this.add.getAllClients().subscribe(data => {
      this.clientArray = data;
      // console.log(data);
      this.clientArray.paginator = this.paginator;
      this.clientArray.forEach((element) => {
        this.count = 0;
        this.issuer.getAllIssuers(element.clientEmail).subscribe((Data) => {
          this.issuerDetails = Data;
          this.count = this.issuerDetails.length;
          this.countArray.push(this.count);
        });
      });
      // this.clientArray.forEach((element) => {
      //   this.clientDetails = element;
      //   this.count1 = 0;
      // tslint:disable-next-line: no-shadowed-variable
      // this.award.getAllBadges(this.clientDetails.clientEmail).subscribe((data) => {
      //   this.badgeDetails = data;
      //   this.count1 = this.badgeDetails.length;
      //   this.countArray1.push(this.count1);
      // console.log('count:', this.countArray1.length, this.countArray1);
      //   });
      // });

      // this.clientArray.forEach((element) => {
      //   this.clientDetails = element;
      //   // tslint:disable-next-line: no-shadowed-variable
      //   this.issuer.getAllIssuers(this.clientDetails.clientEmail).subscribe((response) => {
      //        console.log('issuer Details', response);
      //        this.issuerDetails = response;
      //        this.issuerDetails.forEach((item) => {
      //          this.issuerArray.push(item.issuerName);
      //          console.log('issuer array:' + this.issuerArray);
      //          this.issuerIdArray.push(item.issuerProfileId);
      //          console.log('issuer Id array:' + this.issuerIdArray);
      //          this.issuerIdArray.forEach((res) => {
      //            console.log('res is:' + res);
      //            this.pipeline.getPipelineDetails(res).subscribe((pipelinedata) => {
      //              this.allpipelinedata = pipelinedata;
      //              console.log(this.allpipelinedata);
      //              // tslint:disable-next-line: no-shadowed-variable
      //              this.allpipelinedata.forEach((element) => {
      //                this.pipelineIdArray.push(element.activityPipelineId);
      //                console.log('pipeline ID array:' + this.pipelineIdArray);
      //                this.count2 = this.pipelineIdArray.length;
      //                console.log('count:', this.count2);
      //                this.countArray2.push(this.count2);
      //              });
      //            });
      //          });
      //        });
      //      });
      // });
    });
  }

  issuerList(client) {
    this.dialog.open(IssuerlistComponent, {
      width: '880px', height: '500px', data: { email: client.clientEmail }
    });
  }

  pipelineList(client) {
    this.auth.changeMessage(client.clientEmail);
    this.dialog.open(PipelinelistComponent, {
      width: '950px', height: '500px'
    });
  }

}
