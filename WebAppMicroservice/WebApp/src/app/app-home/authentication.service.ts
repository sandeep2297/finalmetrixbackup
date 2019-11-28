import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { LoggerService } from '../logger.service';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  private static readonly LOGOUT_URL = 'user/api/v1/userlogout';
  public currentUser;
  private messageSource = new BehaviorSubject({});
  private redirectUrl = '/';

  constructor(private http: HttpClient, private logger: LoggerService) {
    this.getUserProfile().subscribe(data => {
      this.currentUser = data;
      this.changeMessage(this.currentUser.userName);
    });
  }

  getUserProfile() {
    return this.http.get('/user/userprofile');
  }

  getRedirectUrl(): string {
    return this.redirectUrl;
  }

  setRedirectUrl(url: string): void {
    this.redirectUrl = url;
  }

  logout() {
    return this.http.get(AuthenticationService.LOGOUT_URL);
  }
  changeMessage(message) {
    this.messageSource.next(message);
  }
  currentMessage(): Observable<any> {
    return this.messageSource.asObservable();
  }

}

