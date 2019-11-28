import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient, HttpErrorResponse } from '@angular/common/http';
import { IssuerDetails } from './entity/IssuerDetails';
import { Observable, throwError, BehaviorSubject } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment.prod';

@Injectable({
  providedIn: 'root'
})

export class IssuerDetailsInputService {
  private headers = new HttpHeaders({ 'Content-Type': 'application/json' });
  private messageSource = new BehaviorSubject({});

  constructor(private http: HttpClient) { }

  addIssuerData(issuer: IssuerDetails): Observable<any> {
    return this.http.post(environment.issuerBaseUrl, issuer, { headers: this.headers })
      .pipe(catchError(this.errorHandler));
  }

  errorHandler(error: HttpErrorResponse) {
    return throwError(error);
  }

  getAllIssuers(clientid): Observable<IssuerDetails> {
    return this.http.get<IssuerDetails>(`${environment.issuerBaseUrl}/client/${clientid}`);
  }

  getSelectedIssuer(issuerId): Observable<any> {
    return this.http.get<any>(`${environment.issuerBaseUrl}/${issuerId}`);
  }

  changeMessage(message) {
    this.messageSource.next(message);
  }

  currentMessage(): Observable<any> {
    return this.messageSource.asObservable();
  }
  updateIssuer(issuerProfileId: string, issuer: IssuerDetails): Observable<any> {
    return this.http.put(`${environment.issuerBaseUrl}?issuerid=${issuerProfileId}/update`, issuer)
      .pipe(catchError(this.errorHandler));
  }
}
