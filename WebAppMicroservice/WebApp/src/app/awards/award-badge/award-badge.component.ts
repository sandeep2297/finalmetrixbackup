import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormControl, FormArray, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material';
import { AwardsService } from '../awards.service';

export interface Recipient {
  recipientId: string;
  recipientName: string;
  type: string;
  hashed: boolean;
  salt: string;
}
export interface Evidence {
  evidenceURI: string;
  narrative: string;
  name: string;
  description: string;
  genre: string;
  audience: string;
}
@Component({
  selector: 'app-award-badge',
  templateUrl: './award-badge.component.html',
  styleUrls: ['./award-badge.component.css']
})
export class AwardBadgeComponent implements OnInit {

  awardBadgeForm: FormGroup;
  displayEvidence = true;
  badgeName: any;
  badgeImage: any;
  badgeId: string;
  issuerId: string;
  formData: any;
  recipient = {};
  evidence = [{}];
  constructor(private router: Router,
              private fb: FormBuilder,
              private snackBar: MatSnackBar,
              private service: AwardsService,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this.issuerId = this.service.getSelectedIssuerId();
    const id = this.route.snapshot.paramMap.get('id');
    this.service.getBadgeById(id).subscribe((badgeData) => {
      this.badgeName = badgeData.name;
      this.badgeImage = badgeData.image;
      this.badgeId = badgeData.badgeId;
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
    const id = this.badgeId;
    this.router.navigate(['awards/badge', id, 'details']);
  }
  onSubmit() {
    this.recipient[' recipientId '] = this.awardBadgeForm.value.recipientId;
    this.recipient[' recipientName '] = this.awardBadgeForm.value.recipientName;
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
    this.service.awardBadgeToRecipient(this.formData).subscribe(value => {
      this.openSnackBar('Awarded Badge successfully To', this.awardBadgeForm.value.name);
    }, error => {
      this.openSnackBar('Cannot Award This Badge Please Try Another Badge !', '');
    },
      () => {
        this.openSnackBar('Badge Awarded to!', this.awardBadgeForm.value.recipientName);
      });
    // const id = this.badgeId;
    // this.router.navigate(['awards/badge', id, 'details']);
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
