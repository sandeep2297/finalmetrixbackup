import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RuleService } from '../../rule.service';
import { LoggerService } from 'src/app/logger.service';
import { MatDialog, MatDialogConfig } from '@angular/material';
import { ConfirmComponent } from '../../confirm/confirm.component';
import { PipelineService } from '../../pipeline.service';
import { RuleModalComponent } from '../rule-list-item/rule-modal/rule-modal.component';

export class Expression {
  lhs: string;
  operator: string;
  rhs: string;
}

@Component({
  selector: 'app-view-rule',
  templateUrl: './view-rule.component.html',
  styleUrls: ['./view-rule.component.css']
})
export class ViewRuleComponent implements OnInit {
  ruleId: string;
  rule: any;
  displayedColumns: string[] = ['lhs', 'operator', 'rhs'];
  dataSource: Expression[];
  urlSplit: any[];
  pipelineDetails: any;
  award: any;
  point: any;
  displayBadge = false;
  displayPoints = false;

  constructor(
    private pipeline: PipelineService,
    private dialog: MatDialog,
    private ruleService: RuleService,
    private router: Router,
    private route: ActivatedRoute,
    private service: RuleService,
    private logger: LoggerService) { }

  ngOnInit() {
    this.urlSplit = this.router.url.split('/');
    this.service.getRuleById(this.urlSplit[2], this.urlSplit[4], this.urlSplit[6]).subscribe((datas) => {
      this.rule = datas;
      // this.logger.log(this.rule);
      if (this.rule.award.length === 1) {
        if (this.rule.award[0].awardType === 'BADGE') {
          this.service.getBadgeByBadgeId(this.rule.award[0].awardValue).subscribe(awards => {
            this.award = awards;
            // console.log(awards);
            this.displayBadge = true;
            this.displayPoints = false;
          });
        } else {
          this.point = this.rule.award[0];
          this.displayBadge = false;
          this.displayPoints = true;
        }
      } else {
        this.displayBadge = true;
        this.displayPoints = true;
        this.service.getBadgeByBadgeId(this.rule.award[1].awardValue).subscribe(awards => {
          this.award = awards;
        });
        this.point = this.rule.award[0];
      }
    });

    this.service.getExpressionByRuleId(this.urlSplit[6]).subscribe((expressionData) => {
      this.dataSource = expressionData;
    });

  }

  editRule() {
    this.pipeline.getSelectedPipeline(this.urlSplit[2], this.urlSplit[4]).subscribe((pipelineData) => {
      this.pipelineDetails = pipelineData;
      if (this.pipelineDetails.pipelineStatus === 'ACTIVE') {
        const dialogRule = new MatDialogConfig();
        dialogRule.height = '200px';
        dialogRule.width = '400px';
        this.dialog.open(RuleModalComponent, dialogRule);
      } else {
        this.router.navigate(['issuer', this.urlSplit[2], 'pipeline', this.urlSplit[4], 'rules', this.urlSplit[6], 'edit']);
      }
    });
  }

  archiveRule() {
    this.pipeline.getSelectedPipeline(this.urlSplit[2], this.urlSplit[4]).subscribe((pipelineData) => {
      this.pipelineDetails = pipelineData;
      if (this.pipelineDetails.pipelineStatus === 'ACTIVE') {
        const dialogRule = new MatDialogConfig();
        dialogRule.height = '200px';
        dialogRule.width = '400px';
        this.dialog.open(RuleModalComponent, dialogRule);
      } else {
        const dialogConfirm = new MatDialogConfig();
        dialogConfirm.autoFocus = true;
        dialogConfirm.width = '400px';
        dialogConfirm.height = '200px';
        dialogConfirm.data = 'Archived Rules cannot be unarchived, Continue?';
        this.dialog.open(ConfirmComponent, dialogConfirm)
          .afterClosed()
          .subscribe(reason => {
            if (reason !== 'cancel') {
              this.ruleService.archiveRule(this.urlSplit[2], this.urlSplit[4], this.urlSplit[6], true).subscribe((ruleData) => {
                const dialogConfig = new MatDialogConfig();
                dialogConfig.disableClose = true;
                dialogConfig.autoFocus = true;
                dialogConfig.width = '300px';
                dialogConfig.height = '165px';
                dialogConfig.data = 'Rule Archived successfully.';
                this.dialog.open(ConfirmComponent, dialogConfig);
                this.ruleService.changeMessage(ruleData);
                this.router.navigate(['issuer', this.urlSplit[2], 'pipeline', this.urlSplit[4], 'rules']);
              });
            }
          });
      }
    });
  }

}
