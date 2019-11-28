import { Component, OnInit, Inject } from '@angular/core';
import { FormControl, Validators, FormGroup, FormBuilder } from '@angular/forms';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA, MatSnackBar } from '@angular/material';
import { AddIssuerComponent } from 'src/app/user-management/issuer/add-issuer/add-issuer.component';
import { ActivatedRoute, Router } from '@angular/router';
import { ClientProfileService } from '../../client-profile.service';
import { AuthenticationService } from 'src/app/app-home/authentication.service';
import { HttpClient } from '@angular/common/http';
import { LoggerService } from 'src/app/logger.service';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-updateclient',
  templateUrl: './updateclient.component.html',
  styleUrls: ['./updateclient.component.css']
})

export class UpdateclientComponent implements OnInit {

  newClient: FormGroup;
  clientId: any;
  imgURL: any;
  imageToShow: any;
  errorMsg = '';
  currentProfileEmail: any;
  display: boolean;

  currentUser: any;
  profileName: never;
  constructor(private iw: FormBuilder, private dialog: MatDialog,
              public dailogRef: MatDialogRef<UpdateclientComponent>,
              @Inject(MAT_DIALOG_DATA) public clientData: any,
              private routes: ActivatedRoute,
              private logger: LoggerService,
              private add: ClientProfileService, private snackbar: MatSnackBar, private auth: AuthenticationService, private router: Router, private http: HttpClient) {
    this.newClient = this.iw.group({
      clientEmail: new FormControl(),
      profileName: new FormControl('', [
        Validators.required,
        Validators.minLength(3),
        Validators.maxLength(30),
        Validators.pattern('^([A-Za-z \s]+)([A-Za-z]+)$')
      ]),
      avatarURL: new FormControl(null, [Validators.nullValidator]),
      orgEmail: new FormControl(null, [Validators.required, Validators.minLength(8),
      Validators.maxLength(30),
      Validators.pattern(/^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$/)]),
      description: new FormControl(null, [Validators.required, Validators.minLength(2),
      Validators.maxLength(250)]),
      organisationName: new FormControl(null, [Validators.required, Validators.minLength(2),
      Validators.maxLength(40), Validators.pattern('^([A-Za-z \s]+)([A-Za-z]+)$')]),
      organisationURL: new FormControl(null, [Validators.nullValidator]),
      aboutOrg: new FormControl(null, [Validators.required,
      Validators.minLength(2),
      Validators.maxLength(300)])
    });
  }

  ngOnInit() {
    this.clientId = this.clientData;
    if (this.clientId) {
      this.getClient(this.clientId);
    }
    this.getUserProfile();
    this.routes.snapshot.paramMap.get('name');
    this.profileName = name;
  }

  getUserProfile() {
    this.auth.getUserProfile().subscribe((response) => {
      this.currentUser = response;
      this.currentProfileEmail = this.currentUser.email;
      this.displayCurrentClient();
    });
  }

  displayCurrentClient() {
    this.add.getCurrentClient(this.currentProfileEmail).subscribe(data => {
      if (data != null) {
        this.display = true;
      }
    });
  }

  oncancel() {
    this.dailogRef.close();
  }

  updateData(message, action) {
    this.newClient.value.clientEmail = this.currentProfileEmail;
    this.add.updateClientData(this.currentProfileEmail, this.newClient.value).subscribe(
      data => {
        // console.log(data);
        // this.client = data;
        // this.display = true;
        this.snackbar.open(message, action, { duration: 1000 });
        this.router.navigateByUrl('user/profileview', {skipLocationChange: true}).then(() => {
          this.router.navigate(['/user/profile']);
        });
      },
      error => (this.errorMsg = 'Server Not Found')
    );
    this.oncancel();
  }

  getClient( cId) {
    this.add.getCurrentClient(cId).subscribe((client) => {
      this.editClient(client);
    });
  }

  editClient(client) {
    this.newClient.patchValue({
      clientEmail: client.clientEmail,
      profileName: client.profileName,
      avatarURL: client.avatarURL,
      orgEmail: client.orgEmail,
      description: client.description,
      organisationName: client.organisationName,
      organisationURL: client.organisationURL,
      aboutOrg: client.aboutOrg
    });
  }

  onURLinserted() {
    this.getImage(this.imgURL).subscribe(data => {
      this.createImageFromBlob(data);
    }, error => {
      this.logger.log('Error occured', error);
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

}
