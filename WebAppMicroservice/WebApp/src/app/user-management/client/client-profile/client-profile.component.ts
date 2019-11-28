import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { ClientProfileService } from '../client-profile.service';
import { Router, ActivatedRoute } from '@angular/router';
import * as moment from 'moment';
import { MatSnackBar } from '@angular/material';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { LoggerService } from 'src/app/logger.service';
import { AuthenticationService } from 'src/app/app-home/authentication.service';


export interface ClientProfile {
  profileName: string;
  avatarURL: string;
  clientEmail: string;
  organizationEmail: string;
  description: string;
  organisationName: string;
  organisationURL: string;
  aboutOrg: string;
}

@Component({
  selector: 'app-client-profile',
  templateUrl: './client-profile.component.html',
  styleUrls: ['./client-profile.component.css']
})
export class ClientProfileComponent implements OnInit {

  avatar = 'https://www.osea-asia.com/wp-content/uploads/Dummy-Icon-Female-x-1.jpg';
  records = {};
  editForm = false;
  display = false;
  patchDisplayData = false;
  today: string;
  client: any;
  newClient: FormGroup;
  timestamp: any;
  formData: any;
  profileName: string;
  errorMsg = '';
  confirmationDialogService: any;
  public imagePath;
  imgURL: any;
  clientId;
  imageToShow: any;
  currentUser: any;
  editProfileEmail: string;
  currentProfileEmail: string;
  private httpOptions = {
    headers: new HttpHeaders({
      responseType: 'blob'
    })
  };
  constructor(private auth: AuthenticationService, private iw: FormBuilder, private add: ClientProfileService, private router: Router, private route: ActivatedRoute, private snackbar: MatSnackBar, private http: HttpClient, private logger: LoggerService) {

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
    this.timestamp = moment().format('LLL');
  }

  ngOnInit(): void {
  //  this.route.paramMap.subscribe(params => {
  //    const cId = params.get('id');
  //    this.clientId = cId;
  //    if (cId) {
  //      this.editForm = true;
  //      this.getClient(cId);
  //    }
  //  });
   this.getUserProfile();
   this.route.snapshot.paramMap.get('name');
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

  receiveMessage(displayData) {
    this.display = displayData;
    this.patchDisplayData = !displayData;
    // this.router.navigate(['user/profile']);
    // this.router.navigate(['/user/profile/edit', this.currentProfileEmail]);
  }
  // function for storing client data in the database
  submitEvent(message, action) {
    this.newClient.value.clientEmail = this.currentProfileEmail;
    this.add.addClientData(this.newClient.value).subscribe(
      data => {
        // console.log(data);
        // this.client = data;
        this.display = true;
        this.snackbar.open(message, action, { duration: 1000 });
        this.router.navigate(['/user/profileview', this.newClient.value.profileName]);
      },
      error => (this.errorMsg = 'Server Not Found')
    );
  }

  // submitData(message, action) {
  //   this.newClient.value.clientEmail = this.currentProfileEmail;
  //   this.add.updateClientData(this.currentProfileEmail, this.newClient.value).subscribe(
  //     data => {
  //       // console.log(data);
  //       // this.client = data;
  //       this.display = true;
  //       this.snackbar.open(message, action, { duration: 1000 });
  //       this.router.navigate(['/user/profileview', this.newClient.value.profileName]);
  //     },
  //     error => (this.errorMsg = 'Server Not Found')
  //   );
  // }

  // getClient( cId) {
  //   this.add.getCurrentClient(cId).subscribe((client) => {
  //     this.editClient(client);
  //   });
  // }

  // function for closing modal window and navigating it back to client dashboard
  closeModal() {
    this.router.navigate(['clientdashboard']);
  }

  oncancel() {
    this.router.navigate(['clientdashboard']);
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
