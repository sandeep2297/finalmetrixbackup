import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray, FormControl, FormGroupDirective } from '@angular/forms';
import { RuleService } from '../rule.service';
import { LoggerService } from 'src/app/logger.service';
import { PipelineDetailsService } from '../pipeline-details.service';
import { Router } from '@angular/router';
import { ConfigRuleHelpComponent } from './config-rule-help/config-rule-help.component';
import { MatDialogConfig, MatDialog } from '@angular/material';
import { ConfirmComponent } from '../confirm/confirm.component';
import { IssueAwardsService } from '../issue-awards.service';
import { ViewIssuerBadgesComponent } from './view-issuer-badges/view-issuer-badges.component';
import { AuthenticationService } from 'src/app/app-home/authentication.service';

@Component({
  selector: 'app-rules',
  templateUrl: './rules.component.html',
  styleUrls: ['./rules.component.css']
})

export class RulesComponent implements OnInit {

  public types: string[] = ['ACTOR', 'TYPE', 'OBJECT', 'TARGET'];
  public operators: string[] = ['='];
  // public awards: any[] = ['POINT', 'BADGE'];
  public complete: string;
  ruleForm: FormGroup;
  activityPipeline: string;
  response: string;
  issuerAward: any;
  issuerName = '';
  display = false;
  urlSplit: any[];
  selectedAwardType: any;
  // dropDown = false;
  clientDetails: any;
  createdBy: string;
  ruleIdEdit: string;
  editButton = false;
  // notAward = true;
  constructor(
    private fb: FormBuilder,
    private ruleInstance: RuleService,
    private logger: LoggerService,
    private router: Router,
    private pipeline: PipelineDetailsService,
    private dialog: MatDialog,
    private auth: AuthenticationService
  ) { }

  ngOnInit() {
    this.urlSplit = this.router.url.split('/');
    this.ruleIdEdit = this.urlSplit[6];
    this.auth.getUserProfile().subscribe((clientData) => {
      this.clientDetails = clientData;
      this.createdBy = this.clientDetails.name;
    });
    if (this.ruleIdEdit !== undefined) {
      this.editButton = true;
      this.editRulesForm(this.ruleIdEdit);
    }
    this.pipeline.currentMessage().subscribe(data => {
      // this.logger.log(data);
      this.activityPipeline = data.activityPipelineId;
    });
    this.ruleForm = this.fb.group({
      ruleName: new FormControl('', [Validators.required, Validators.maxLength(15)]),
      ruleDescription: new FormControl('', [Validators.required]),
      point: new FormControl('', [Validators.pattern('^([1-9])([0-9]*)$')]),
      badge: new FormControl(''),
      createdBy: new FormControl(''),
      expression: this.fb.array([this.addFormGroupExpression('')]),
    });
  }

  onAwardTypeSelection() {
    const awardConfig = new MatDialogConfig();
    awardConfig.disableClose = true;
    awardConfig.autoFocus = true;
    awardConfig.width = '620px';
    awardConfig.height = '600px';
    awardConfig.data = this.urlSplit[2];
    this.dialog.open(ViewIssuerBadgesComponent, awardConfig)
      .afterClosed()
      .subscribe((award) => {
        if (award === '') {
          this.issuerName = '';
        } else {
          this.issuerAward = award;
          this.issuerName = this.issuerAward.name;
        }
      });
  }
  addFormGroupExpression(addConjunction) {
    return this.fb.group({
      type: new FormControl('', [Validators.required]),
      operator: new FormControl('', [Validators.required]),
      expressionValue: new FormControl('', [Validators.required]),
      conjunction: new FormControl(addConjunction)
    });
  }

  // addFormGroupAward() {
  //   return this.fb.group({
  //     awardType: new FormControl('', [Validators.required]),
  //     awardValue: new FormControl('')
  //   });
  // }

  get expressionArray() {
    return this.ruleForm.get('expression') as FormArray;
  }

  // get awardArray() {
  //   return this.ruleForm.get('award') as FormArray;
  // }

  addExpression(index) {
    this.expressionArray.insert(index + 1, this.addFormGroupExpression('AND'));
  }

  removeExpression(index) {
    if (this.expressionArray.length > 1) {
      this.expressionArray.removeAt(index);
    }
  }

  // addAward(index) {
  //   this.awardArray.insert(index + 1, this.addFormGroupAward());
  // }

  // removeAward(index) {
  //   if (this.awardArray.length > 1) {
  //     this.awardArray.removeAt(index);
  //   }
  // }

  hideFirstField(index) {
    if (index === 0) { return false; }
    return true;
  }

  clearResponse() {
    this.response = '';
    this.display = false;
  }

  submitRule(formDirective: FormGroupDirective) {
    this.ruleForm.value.createdBy = this.createdBy;
    if (this.issuerName !== '') {
      this.ruleForm.value.badge = this.issuerAward.badgeClassId;
    }
    if (this.ruleIdEdit !== undefined) {
      this.patchPreviousRule(formDirective);
    } else {
      // this.logger.log(this.ruleForm.value);
      this.ruleInstance.addRuleData(this.urlSplit[2], this.urlSplit[4], this.ruleForm.value).subscribe(value => {
        this.ruleInstance.changeMessage(value);
        formDirective.resetForm();
        this.ruleForm.reset();
        if (this.ruleIdEdit === undefined) {
          this.responseRule();
        }
        this.expressionArray.clear();
        this.addExpression(-1);
        // this.awardArray.clear();
        // this.addAward(-1);
        this.issuerName = '';
      }, error => {
        // this.logger.error('Failed to create Rule');
        this.response = 'Failed to create Rule';
        this.display = true;
      },
        () => {
          // this.logger.log('Rule Saved Successfully');
          this.response = 'Rule Saved Successfully';
          this.display = true;
        });
    }
  }

  configRule() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '680px';
    dialogConfig.height = '600px';
    this.dialog.open(ConfigRuleHelpComponent, dialogConfig);
  }

  responseRule() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '300px';
    dialogConfig.height = '165px';
    dialogConfig.data = 'Rule saved successfully.';
    this.dialog.open(ConfirmComponent, dialogConfig);
  }

  editRulesForm(ruleIdPatch) {
    this.ruleInstance.getRuleById(this.urlSplit[2], this.urlSplit[4], ruleIdPatch).subscribe((dataOfRules) => {
      this.patchForm(dataOfRules);
    });
  }

  patchForm(dataOfRules) {
    this.ruleForm.patchValue({
      ruleName: dataOfRules.ruleName,
      ruleDescription: dataOfRules.ruleDescription,
    });
  }

  patchPreviousRule(formDirective: FormGroupDirective) {
    const updateConfig = new MatDialogConfig();
    updateConfig.disableClose = true;
    updateConfig.autoFocus = true;
    updateConfig.width = '300px';
    updateConfig.height = '165px';
    updateConfig.data = 'Sure to update?';
    this.dialog.open(ConfirmComponent, updateConfig)
      .afterClosed()
      .subscribe((dataUpdate) => {
        if (dataUpdate !== 'cancel') {
          this.ruleForm.value.createdBy = this.createdBy;
          if (this.issuerName !== '') {
            this.ruleForm.value.badge = this.issuerAward.badgeClassId;
          }
          this.ruleInstance.archiveRule(this.urlSplit[2], this.urlSplit[4], this.urlSplit[6], true).subscribe((ruleData) => {
            this.ruleInstance.changeMessage(ruleData);
            // this.logger.log(this.ruleForm.value);
            this.ruleInstance.addRuleData(this.urlSplit[2], this.urlSplit[4], this.ruleForm.value).subscribe(value => {
              this.ruleInstance.changeMessage(value);
              formDirective.resetForm();
              this.ruleForm.reset();
              if (this.ruleIdEdit === undefined) {
                this.responseRule();
              }
              this.expressionArray.clear();
              this.addExpression(-1);
              // this.awardArray.clear();
              // this.addAward(-1);
              this.issuerName = '';
            }, error => {
              // this.logger.error('Failed to create Rule');
              this.response = 'Failed to create Rule';
              this.display = true;
            },
              () => {
                // this.logger.log('Rule Saved Successfully');
                this.response = 'Rule Saved Successfully';
                this.display = true;
              });
          });
        }
      });
  }
}
