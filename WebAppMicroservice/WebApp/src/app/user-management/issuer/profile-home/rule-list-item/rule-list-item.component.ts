import { Component, OnInit, HostBinding, Input } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { trigger, state, style, transition, animate } from '@angular/animations';
import { RuleService } from '../../rule.service';
import { PipelineService } from '../../pipeline.service';
import { MatDialog, MatDialogConfig } from '@angular/material';
import { RuleModalComponent } from './rule-modal/rule-modal.component';

@Component({
  selector: 'app-rule-list-item',
  templateUrl: './rule-list-item.component.html',
  styleUrls: ['./rule-list-item.component.css'],
  animations: [
    trigger('indicatorRotate', [
      state('collapsed', style({ transform: 'rotate(0deg)' })),
      state('expanded', style({ transform: 'rotate(180deg)' })),
      transition('expanded <=> collapsed',
        animate('225ms cubic-bezier(0.4,0.0,0.2,1)')
      ),
    ])
  ]
})
export class RuleListItemComponent implements OnInit {

  addRule: any = {
    ruleName: 'Add Rules',
  };
  expanded: boolean;
  pipelineDetails: any;
  urlSplit: string[];
  @HostBinding('attr.aria-expanded') ariaExpanded = this.expanded;
  @Input() item: any;
  @Input() depth: number;
  @Input() selectedPipelineId: string;

  constructor(private dialog: MatDialog, public router: Router, private service: RuleService, private route: ActivatedRoute, private pipelineService: PipelineService) {
    if (this.depth === undefined) {
      this.depth = 0;
    }
  }

  onItemSelected(item: any) {
    if (!item.children || !item.children.length) {
      if (item.ruleId === 'RuleAdd') {
        this.pipelineService.getSelectedPipeline(this.urlSplit[2], this.selectedPipelineId).subscribe((pipelineData) => {
          this.pipelineDetails = pipelineData;
          if (this.pipelineDetails.pipelineStatus === 'ACTIVE') {
            const dialogRule = new MatDialogConfig();
            dialogRule.height = '200px';
            dialogRule.width = '400px';
            this.dialog.open(RuleModalComponent, dialogRule);
          } else {
            this.router.navigate(['pipeline', this.selectedPipelineId, 'rules'], { relativeTo: this.route });
          }
        });
      } else {
        // this.service.changeMessage(item.activityRuleId);
        this.router.navigateByUrl(`issuer/${this.urlSplit[2]}/pipeline/${this.selectedPipelineId}/rules`, { skipLocationChange: true }).then(() => {
          this.router.navigate(['pipeline', this.selectedPipelineId, 'rules', item.activityRuleId], { relativeTo: this.route });
        });
      }
    }
    if (item.children && item.children.length) {
      this.expanded = !this.expanded;
    }
  }


  ngOnInit() {
    this.urlSplit = this.router.url.split('/');
  }

}
