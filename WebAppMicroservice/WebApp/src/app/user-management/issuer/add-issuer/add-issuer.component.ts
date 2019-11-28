import { Component, OnInit, Injectable, Inject, ChangeDetectionStrategy } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { IssuerDetailsInputService } from '../issuer-details-input.service';
import { MatSnackBar, MatDialogRef, MAT_DIALOG_DATA, MatDialog } from '@angular/material';
import { Router, ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { IssuerDetails } from '../entity/IssuerDetails';
import { AuthenticationService } from 'src/app/app-home/authentication.service';
import { LoggerService } from 'src/app/logger.service';
import { ClientProfileService } from '../../client/client-profile.service';
import { IssueAwardsService } from '../issue-awards.service';

@Component({
  selector: 'app-add-issuer',
  templateUrl: './add-issuer.component.html',
  styleUrls: ['./add-issuer.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
@Injectable()
export class AddIssuerComponent implements OnInit {
  errorMsg: string;
  data: IssuerDetails;
  response: any;
  issuerForm: FormGroup;
  records: any;
  email: string;
  detailsOfClient: any;
  clientOrg: string;
  clientDesc: string;
  description: string;
  organisationURL: string;

  updateData = false;
  issuerid: any;

  imgURL: any;
  imageToShow: any;
  myURL: any;
  clientId: any;
  private httpOptions = {
    headers: new HttpHeaders({
      responseType: 'blob'
    })
  };
  // tslint:disable-next-line:max-line-length
  constructor(
    private add: IssuerDetailsInputService,
    private fb: FormBuilder,
    private http: HttpClient,
    private router: Router, snackbar: MatSnackBar,
    private auth: AuthenticationService,
    private logger: LoggerService,
    private dialog: MatDialog,
    private clientService: ClientProfileService,
    private issueAwards: IssueAwardsService,
    public dailogRef: MatDialogRef<AddIssuerComponent>,
    @Inject(MAT_DIALOG_DATA) public issuerData: any,
    private routes: ActivatedRoute
  ) { }

  ngOnInit() {
    this.issuerid = this.issuerData;
    if (this.issuerData) {
      this.updateData = true;
      // console.log(this.updateData);
    }
    if (this.issuerid) {
      this.getIssuerData(this.issuerid);
    }
    this.auth.getUserProfile().subscribe((data) => {
      this.records = data;
      this.email = this.records.email;
      // this.clientId = this.data.userName;
      this.clientService.getCurrentClient(this.email).subscribe((clientData) => {
        this.detailsOfClient = clientData;
        // console.log('details of client', this.detailsOfClient);
        this.clientDesc = this.detailsOfClient.description;
        // console.log('client Desc===', this.clientDesc);
        this.clientOrg = this.detailsOfClient.organisationURL;
        // console.log('client Org===', this.clientOrg);
        this.formDisplay();
      });
    });




  }
  formDisplay() {
    this.issuerForm = this.fb.group({
      clientId: new FormControl(''),
      issuerName: new FormControl('', [
        Validators.required,
        Validators.minLength(2),
        Validators.maxLength(100),
        Validators.pattern('^([A-Za-z \s]+)([A-Za-z]+)$')
      ]),
      issuerOrganizationURL: new FormControl(this.clientOrg),
      //  [
      //   Validators.required,
      //   Validators.minLength(2),
      //   Validators.maxLength(100),
      // ]),
      issuerAvatarURL: new FormControl(''),
      issuerDescription: new FormControl(this.clientDesc),
      //  [
      //   Validators.required,
      //   Validators.minLength(2),
      //   Validators.maxLength(300),
      // ]),
      issuerEmail: new FormControl('', [
        Validators.required,
        Validators.pattern(/^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$/)
      ]),
      entityType: new FormControl('', [
        Validators.minLength(2),
        Validators.maxLength(100)]),
      profileObjective: new FormControl('', [
        Validators.required,
        Validators.minLength(2),
        Validators.maxLength(300),
      ])
    });
  }
  onClose() {
    this.dailogRef.close();
  }
  getIssuerData(id: string) {
    this.add.getSelectedIssuer(id).subscribe((issuer) =>
      this.editIssuer(issuer));
  }
  editIssuer(issuer) {
    this.issuerForm.patchValue({
      issuerName: issuer.issuerName,
      issuerAvatarURL: issuer.issuerAvatarURL,
      // issuerDescription: issuer.issuerDescription,
      issuerEmail: issuer.issuerEmail,
      // issuerOrganizationURL: issuer.issuerOrganizationURL,
      profileObjective: issuer.profileObjective
    });
  }

  onURLinserted() {
    this.getImage(this.myURL).subscribe(data => {
      this.createImageFromBlob(data);
    }, error => {
      // this.logger.log('Error occured', error);
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
      this.imageToShow = reader.result; // here is the result you got from reader
    }, false);

    if (image) {
      reader.readAsDataURL(image);
    }
  }
  formSubmit(): void {
    this.issuerForm.value.clientId = this.email;
    this.data = this.issuerForm.value;
    // this.logger.log(this.data);
    this.add.addIssuerData(this.data).subscribe(value => {
      this.add.changeMessage(value);
      this.response = value;
    }, error => {
      this.errorMsg = 'Failed to Submit data';
    });
    this.issueAwards.saveIssuerInAwards(this.email).subscribe(value => {
    });
    this.onClose();
  }
  // onCancel() {
  //   this.dailogRef.close();
  // }
  display() {
    // this.logger.log('this.issuerForm.value.issuerAvatarURL:: ', this.issuerForm.value.issuerAvatarURL);
    if (this.issuerForm.value.issuerAvatarURL) {
      // this.logger.log('if condin');
      return true;
    } else {
      // this.logger.log('else condin');
      return false;
    }
  }
  formUpdate() {
      this.add.updateIssuer(this.issuerid, this.issuerForm.value).subscribe(
      data => {
          this.router.navigate(['issuer', this.issuerid , 'profile']);
        },
      error => (this.errorMsg = 'Server Not Found')
    );
  }
}
