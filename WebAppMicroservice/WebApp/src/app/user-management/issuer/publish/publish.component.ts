import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { RuleService } from '../rule.service';
import { PipelineService } from '../pipeline.service';

@Component({
  selector: 'app-publish',
  templateUrl: './publish.component.html',
  styleUrls: ['./publish.component.css']
})
export class PublishComponent implements OnInit {

  constructor(private ruleService: RuleService, private router: Router, private pipelineService: PipelineService) { }
  result: any;
  pipelines: any;
  panelOpenState: boolean;
  urlSplit: any[];
  rules: any[];
  activeRules: any[];
  expressionArray: any[];
  publish: string;
  suspend: string;
  showPublish: boolean;
  showEndpoint: boolean;
  isSuspended: boolean;
  ngOnInit() {
    this.panelOpenState = false;
    this.urlSplit = this.router.url.split('/');
    // this.ruleService.getAllRules(this.urlSplit[2], this.urlSplit[4]).subscribe((rulesData) => {
    //   this.rules = rulesData;
    //   console.log("rules", this.rules);
    //   this.rules.forEach((element) => {
    //     if(element.archived === false) {
    //       console.log("per element", element)
    //       this.activeRules.splice(0,0,element);
    //       this.ruleService.getExpressionByRuleId(element.activityRuleId).subscribe((expression) => {
    //         console.log("per expression", expression);
    //         this.expressionArray.splice(0,0,expression)
    //       })
    //       console.log("expressionArray", this.expressionArray);
    //     }
    //   })
    // });
    this.pipelineService.getSelectedPipeline(this.urlSplit[2], this.urlSplit[4]).subscribe((pipelineData) => {
      this.pipelines = pipelineData;
      if (this.pipelines.pipelineStatus === 'ACTIVE') {
        this.isSuspended = false;
        this.showEndpoint = true;
        this.showPublish = false;
      } else if (this.pipelines.pipelineStatus === 'SUSPENDED') {
        this.isSuspended = true;
        this.showPublish = true;
        this.showEndpoint = true;
      } else if (this.pipelines.pipelineStatus === 'DRAFTED') {
        this.showPublish = true;
        this.showEndpoint = false;
        this.isSuspended = false;
      }
    });
  }
  publishPipeline() {
    this.showPublish = false;
    this.showEndpoint = true;
    this.isSuspended = false;
    this.publish = 'ACTIVE';
    this.pipelineService.updateSelectedPipeline(this.urlSplit[2], this.urlSplit[4], this.publish).subscribe((publishData) => {
    });
  }

  copyInputMessage(inputElement) {
    inputElement.select();
    document.execCommand('copy');
    inputElement.setSelectionRange(0, 0);
  }

  suspendPipeline() {
    this.showPublish = true;
    this.isSuspended = true;
    this.suspend = 'SUSPENDED';
    this.pipelineService.updateSelectedPipeline(this.urlSplit[2], this.urlSplit[4], this.suspend).subscribe((suspendData) => {
    });
  }

}
