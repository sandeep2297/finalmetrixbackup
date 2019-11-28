import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError, BehaviorSubject } from 'rxjs';
import { ClientProfile } from './client-profile/client-profile.component';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment.prod';

@Injectable({
  providedIn: 'root'
})

export class ClientProfileService {
  getCurrentClient(currentProfileEmail: string) {
    return this.http.get(`${environment.currentClientUrl}?clientEmail=${currentProfileEmail}`);
  }
  // userSubject = new BehaviorSubject<ClientProfile>(aboutOrg, profileName, avatarURL, clientEmail, description);
  // user: Observable<ClientProfile> = this.userSubject;
  // private headers = new HttpHeaders({'Content-Type': 'application/json' });
  constructor(private http: HttpClient) { }

  addClientData(client: ClientProfile): Observable<any> {
    return this.http.post(`${environment.clientUrl}`, client)
      .pipe(catchError(this.errorHandler));
  }

  updateClientData(clientEmail: string, client: ClientProfile): Observable<any> {
    return this.http.put(`${environment.clientUrl}?clientEmail=${clientEmail}`, client)
      .pipe(catchError(this.errorHandler));
  }

  updateUserData(email: string): Observable<any> {
    return this.http.patch(`${environment.userUrl}?email=${email}`, 'patch')
      .pipe(catchError(this.errorHandler));
  }

  getData(issuerid): Observable<any> {
    return this.http.get<any>(`stream/api/v1/issuer/${issuerid}/resultdata`);
  }

  getDateData(issuerid, startDate, endDate): Observable<any> {
    return this.http.get<any>(`stream/api/v1/issuer/${issuerid}/evalresult?startDate=${startDate}&endDate=${endDate}`);
  }

  errorHandler(error: HttpErrorResponse) {
    return throwError(error);
  }

  getAllClients(): Observable<any> {
    return this.http.get<any>(`${environment.allClientUrl}`);
  }

  getSpecificClient(): Observable<ClientProfile> {
    return this.http.get<ClientProfile>(`${environment.clientUrl}`);
  }
}
