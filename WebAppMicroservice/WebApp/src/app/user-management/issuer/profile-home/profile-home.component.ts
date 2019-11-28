import { Component, OnInit } from '@angular/core';
import { ActivityPipeline } from '../entity/ActivityPipeline';
import { Router, ActivatedRoute } from '@angular/router';
import { FormGroup, FormBuilder, FormControl } from '@angular/forms';
import { RuleService } from '../rule.service';
import { LoggerService } from 'src/app/logger.service';
import { PipelineService } from '../pipeline.service';
import { IssuerDetailsInputService } from '../issuer-details-input.service';
import { AuthenticationService } from 'src/app/app-home/authentication.service';
import { PipelineDetailsService } from '../pipeline-details.service';
import * as _ from 'lodash';


@Component({
  selector: 'app-profile-home',
  templateUrl: './profile-home.component.html',
  styleUrls: ['./profile-home.component.css']
})
export class ProfileHomeComponent implements OnInit {

  rules: any[] = [{
    ruleName: 'Add Rules',
    ruleId: 'RuleAdd'
  }];

  issuerRules: any[];
  pipeId: string;

  navItems: any[] = [{
    ruleName: 'Rules',
    ruleId: 'RuleHeader',
    children: this.rules
  }];
  displayButton: boolean;
  pipelineData: ActivityPipeline;

  pipeline: FormGroup = this.fb.group({
    pipeline: '',
  });
  id: any;
  result: any;
  detailsOfPipeline: any;
  detailsOfIssuers: any;
  issueId: string;
  selectedPipelineId: any;
  searchField = new FormControl();
  issuerId: string;
  urlSplit: string[];
  pipelineArray: any[];

  constructor(
    public router: Router,
    public fb: FormBuilder,
    private ruleService: RuleService,
    private logger: LoggerService,
    private pipelineServ: PipelineService,
    private service: IssuerDetailsInputService,
    private auth: AuthenticationService,
    public route: ActivatedRoute,
    private pipelineIdService: PipelineDetailsService) { }

  ngOnInit() {
    this.onLoadPipeline();
    this.ruleService.currentMessage().subscribe((ruleData) => {
      let index = 0;
      if (ruleData.activityRuleId !== undefined && ruleData.archieved === false) {
        ruleData.ruleName = _.startCase(_.camelCase(ruleData.ruleName));
        this.rules.splice(1, 0, ruleData);
      }
      if (ruleData.archieved === true) {
        this.rules.forEach((elementToDelete) => {
          if (ruleData.activityRuleId === elementToDelete.activityRuleId) {
            this.rules.splice(index, 1);
          } else {
            index++;
          }
        });
      }
    });
  }
  addPipeline() {
    // this.clearValue();
    this.router.navigate(['pipeline'], { relativeTo: this.route });
  }
  onLoadPipeline() {
    this.pipelineIdService.currentMessage().subscribe((resultOfPipeline) => {
      this.urlSplit = this.router.url.split('/');
      this.pipelineServ.getPipelineDetails(this.urlSplit[2]).subscribe((pipelineData) => {
        this.result = pipelineData;
      });
    });
  }
  onSelectionChanged(event) {
    // this.logger.log(event);
    const selectedPipelineId = event.option.value.activityPipelineId;
    this.id = selectedPipelineId;
    this.ruleService.getAllRules(this.urlSplit[2], this.id).subscribe((data) => {
      this.issuerRules = data;
      if (this.issuerRules !== null) {
        this.rules.splice(1, this.rules.length);
        this.issuerRules.forEach((element) => {
          if (element.archieved === false) {
            element.ruleName = _.startCase(_.camelCase(element.ruleName));
            this.rules.splice(1, 0, element);
          }
        });
      }
    });
    this.router.navigateByUrl(`/issuer/${this.urlSplit[2]}`, { skipLocationChange: true }).then(() => {
      this.router.navigate(['pipeline', this.id], { relativeTo: this.route });
    });
  }
  //  clearValue(){
  //    this.pipelineName = " ";
  //  }
  goToProfile() {
    this.router.navigate(['profile'], { relativeTo: this.route });
  }

  goToPipeline() {
    this.router.navigate(['pipeline', this.id], { relativeTo: this.route });

  }
  goToBadges() {
    this.router.navigate(['badges'], { relativeTo: this.route });
  }

  goToPublish() {
    this.router.navigate(['pipeline', this.id, 'publish'], { relativeTo: this.route });
  }
  goToAwards() {
    this.router.navigate(['awards'], { relativeTo: this.route });
  }
  goToMonitor() {
    this.router.navigate(['monitor'], { relativeTo: this.route });
  }

  updatePipeline() {
    this.onLoadPipeline();
  }

  displayFn(subject) {
    return subject ? subject.pipelineName : subject;

    }

  }
