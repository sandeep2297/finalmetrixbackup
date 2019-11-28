import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormArray, FormGroup, FormControl, Validators } from '@angular/forms';
import { COMMA, ENTER } from '@angular/cdk/keycodes';
import { MatChipInputEvent } from '@angular/material/chips';
import { AwardsService } from '../awards.service';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { AuthenticationService } from 'src/app/app-home/authentication.service';
import { MatSnackBar, MatDialog, MatDialogConfig } from '@angular/material';
import { Router } from '@angular/router';
import { ConfirmComponent } from 'src/app/user-management/issuer/confirm/confirm.component';

export interface IAlignment {
  targetName: string;
  targetURL: string;
  targetDescription: string;
  targetCode: string;
  targetFramework: string;
}

@Component({
  selector: 'app-create-badge',
  templateUrl: './create-badge.component.html',
  styleUrls: ['./create-badge.component.css']
})

export class CreateBadgeComponent implements OnInit {
  edit = false;
  errorMsg: any;
  badgeForm: FormGroup;
  badgeArray = [];
  visible = true;
  selectable = true;
  removable = true;
  addOnBlur = true;
  readonly separatorKeysCodes: number[] = [ENTER, COMMA];
  tempTags = [];
  display = true;
  formData: any;
  imgURL: any;
  clientData: any;
  clientId: any;
  urlSplit: any[];
  resetVar: any;

  constructor(private http: HttpClient, private fb: FormBuilder, private awardsInstance: AwardsService, private auth: AuthenticationService, private snackBar: MatSnackBar, private dialog: MatDialog, private router: Router) {
  }

  ngOnInit() {
    this.urlSplit = this.router.url.split('/');
    if (this.urlSplit[4]) {
      this.edit = true;
      this.getBadges(this.urlSplit[4]);
    }
    this.auth.getUserProfile().subscribe((userData: any) => {
      this.clientId = userData.userName;
    });

    this.badgeForm = this.fb.group({
      image: new FormControl(null),
      name: new FormControl('', [
        Validators.required,
        Validators.minLength(3),
        Validators.maxLength(20),
        Validators.pattern('^([A-Za-z \s]+)([A-Za-z]+)$')
      ]),
      description: new FormControl(null, [
        Validators.required,
        Validators.minLength(10),
        Validators.maxLength(250),
      ]),
      criteria: new FormControl(''),
      tags: this.fb.array([this.tempTags]),
      alignment: this.fb.array([this.addFormGroupAlignment()]),
    });
    this.removeAlignment(0);
  }
  addTag(event: MatChipInputEvent): void {
    const input = event.input;
    const value = event.value;

    // Add our Tags
    if ((value || '').trim()) {
      this.tempTags.push(value.trim());
    }

    // Reset the input value of Tag
    if (input) {
      input.value = '';
    }
    // this.badgeForm.value.tags = this.tempTags;
  }
  onCancel() {
    this.router.navigate(['awards/badge']);
  }

  removeTag(tag: any): void {
    const index = this.tempTags.indexOf(tag);

    if (index >= 0) {
      this.tempTags.splice(index, 1);
    }
  }
  getBadges(bId) {
    this.awardsInstance.getBadgeById(bId).subscribe((badgeData: any) => {
      this.editBadge(badgeData);
    });
  }
  editBadge(badgeData) {
    this.badgeForm.patchValue({
      clientId: badgeData.clientId,
      image: badgeData.image,
      name: badgeData.name,
      description: badgeData.description,
      criteria: badgeData.criteria,
    });
    this.badgeForm.setControl('alignment', this.setExistingAlignment(badgeData.alignment));

  }

  setExistingAlignment(alignmentSet: IAlignment[]): FormArray {
           const formArray = new FormArray([]);
           alignmentSet.forEach(s => {
              formArray.push(this.fb.group({
              targetName: s.targetName,
              targetURL:  s.targetURL,
              targetDescription: s.targetDescription,
              targetCode: s.targetCode,
              targetFramework: s.targetFramework
            }));
          });
           return formArray;
  }
  getTags() {
    return this.fb.group({
      targetName: new FormControl(''),
    });
  }

  createAlignmentForm() {
    this.display = false;
    this.addAlignment(-1);
  }

  addFormGroupAlignment() {
    return this.fb.group({
      targetName: new FormControl(''),
      targetURL: new FormControl(''),
      targetDescription: new FormControl(''),
      targetCode: new FormControl(''),
      targetFramework: new FormControl('')
    });
  }

  get alignmentArray() {
    return this.badgeForm.get('alignment') as FormArray;
  }

  addAlignment(index: number) {
    this.alignmentArray.insert(index + 1, this.addFormGroupAlignment());
  }
  removeAlignment(index: number) {
    this.alignmentArray.removeAt(index);
    if (this.alignmentArray.length === 0) {
      this.display = true;
    }

  }
  onURLinserted() {
    this.getImage(this.imgURL).subscribe(data => {
      this.createImageFromBlob(data);
    }, error => {
    });
  }

  getImage(imageUrl: any): Observable<any> {
    return this.http
      .get(imageUrl, { responseType: 'blob' })
      .pipe(map((res: any) => res));
  }

  createImageFromBlob(image: Blob) {
    const reader = new FileReader(); // you need file reader for read blob data to base64 image data.
    reader.addEventListener('load', () => {
      this.imgURL = reader.result; // here is the result you got from reader
    }, false);
    if (image) {
      reader.readAsDataURL(image);
    }
  }

  submitBadge() {
    this.formData = this.resetVar;
    // console.log('client id for form data', this.clientId);
    this.formData = { ...this.badgeForm.value, clientId: this.clientId};
    this.formData = { ...this.formData, tags: this.tempTags };
    // console.log('this form data', this.formData);
    this.awardsInstance.addBadgeData(this.formData).subscribe(value => {
      // console.log(this.badgeForm.value);
      this.responseBadge();
      // this.openSnackBar(this.badgeForm.value.name, ' created sucessfully');
      this.router.navigate(['awards/badge'] );
    }, error => {
      this.openSnackBar('Failed to create badge', this.badgeForm.value.name);
    },
      () => {
        // this.openSnackBar(this.badgeForm.value.name, ' created sucessfully');
      });
  }

  openSnackBar(message: string, action: string) {
    this.snackBar.open(message, action, {
      duration: 2000,
    });
  }
  responseBadge() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '300px';
    dialogConfig.height = '165px';
    dialogConfig.data = 'Badge created successfully.';
    this.dialog.open(ConfirmComponent, dialogConfig);
  }
  updateBadge() {
    this.formData = this.resetVar;
    this.formData = { ...this.badgeForm.value, clientId: this.clientId};
    this.formData = { ...this.formData, tags: this.tempTags };
    this.awardsInstance.UpdateBadge(this.urlSplit[4], this.formData).subscribe((newBadge) => {
          // console.log('new Badge Data', newBadge);
    });
    this.router.navigate(['awards/badge'] );
  }
}
