import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormControl, Validators, FormArray, FormGroup } from '@angular/forms';
import { IssueAwardsService } from '../issue-awards.service';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-award-manually',
  templateUrl: './award-manually.component.html',
  styleUrls: ['./award-manually.component.css']
})
export class AwardManuallyComponent implements OnInit {

  awardBadgeForm: FormGroup;
  displayEvidence = true;
  badgeName: any;
  badgeImage: any;
  badgeId: string;
  issuerId: string;
  formData: any;
  public recipient =  {
    recipientId: undefined,
    recipientName: undefined,
  };
  evidence = [{}];
  urlSplit: string[];
  constructor(private router: Router,
              private fb: FormBuilder,
              private snackBar: MatSnackBar,
              private service: IssueAwardsService,
              private route: ActivatedRoute) { }

  ngOnInit() {

    this.urlSplit = this.router.url.split('/');
    this.badgeId = this.urlSplit[4];
    this.issuerId = this.urlSplit[2];
    this.service.getBadgeClassById(this.urlSplit[4]).subscribe((badgeData: any) => {
      this.badgeName = badgeData.name;
      this.badgeImage = badgeData.image;
    });
    this.awardBadgeForm = this.fb.group({
      recipientName: new FormControl('', Validators.required),
      recipientId: new FormControl('', Validators.required),
      assertionNarrative: new FormControl(''),
      evidence: this.fb.array([this.addFormGroupEvidence()])
    });
    this.removeEvidence(0);
  }
  onCancel() {
    this.router.navigateByUrl(`issuer/${this.issuerId}/badges`);
  }
  onSubmit() {
    this.recipient.recipientId = this.awardBadgeForm.value.recipientId;
    this.recipient.recipientName = this.awardBadgeForm.value.recipientName;
    this.awardBadgeForm.value.evidence.forEach(element => {
      this.evidence.push(element);
    });
    this.formData = { ...this.formData, evidence: this.evidence};
    this.formData = { ...this.formData, badge: this.badgeId };
    this.formData = { ...this.formData, issuerId: this.issuerId };
    this.formData = { ...this.formData, image: this.badgeImage };
    this.formData = { ...this.formData, recipient: this.recipient };
    this.formData = { ...this.formData, assertionNarrative: this.awardBadgeForm.value.assertionNarrative };
    this.formData = { ...this.formData, badgeName: this.badgeName };
    this.service.awardBadgeToRecipient(this.formData).subscribe((value) => {
      this.openSnackBar('Awarded Badge successfully To', this.awardBadgeForm.value.name);
    }, error => {
      this.openSnackBar('Cannot Award This Badge Please Try Another Badge !', '');
    },
      () => {
        this.openSnackBar('Badge Awarded to!', this.awardBadgeForm.value.recipientName);
      });
    this.router.navigateByUrl(`issuer/${this.issuerId}/badges`);
  }
  openSnackBar(message: string, action: string) {
    this.snackBar.open(message, action, {
      duration: 2000,
    });
  }

  createEvidenceForm() {
    this.displayEvidence = false;
    this.addEvidence(-1);
  }

  addFormGroupEvidence() {
    return this.fb.group({
      narrative: new FormControl(''),
      evidenceURI: new FormControl('', Validators.required),
      name: new FormControl(''),
      description: new FormControl(''),
      genre: new FormControl(''),
      audience: new FormControl('')
    });
  }

  get evidenceArray() {
    return this.awardBadgeForm.get('evidence') as FormArray;
  }

  addEvidence(index: number) {
    this.evidenceArray.insert(index + 1, this.addFormGroupEvidence());
  }
  removeEvidence(index: number) {
    this.evidenceArray.removeAt(index);
    if (this.evidenceArray.length === 0) {
      this.displayEvidence = true;
    }
  }

}
