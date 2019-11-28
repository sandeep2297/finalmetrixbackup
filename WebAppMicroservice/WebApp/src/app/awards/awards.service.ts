import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient, HttpErrorResponse } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { CreateBadgeComponent } from './create-badge/create-badge.component';
import { Observable, throwError } from 'rxjs';
import { AuthenticationService } from '../app-home/authentication.service';
import { AwardBadgeComponent } from './award-badge/award-badge.component';

@Injectable({
  providedIn: 'root'
})

export class AwardsService {


  private static readonly BADGE_URL = 'awards/api/v1/badge';
  private static readonly ASSERTION_URL = 'awards/api/v1/assertion';
  private headers = new HttpHeaders({ 'Content-Type': 'application/json' });
  options = {
    headers: new HttpHeaders({
      'content-type': 'application/json'
    })
  };
  badge: any;
  listOfIssuersURL: any;
  badgeURL: any;
  issuerId: string;
  listOfAssertionURL: any;
  updateAssertionURL: any;
  constructor(private http: HttpClient, private auth: AuthenticationService) { }

  addBadgeData(badge: CreateBadgeComponent): Observable<any> {
    return this.http.post(AwardsService.BADGE_URL, badge, { headers: this.headers })
      .pipe(catchError(this.errorHandler));
  }

  awardBadgeToRecipient(recipient: AwardBadgeComponent): Observable<any> {
    return this.http.post(AwardsService.ASSERTION_URL, recipient, { headers: this.headers })
      .pipe(catchError(this.errorHandler));
  }

  getBadgeById(badgeId: any): Observable<any> {
    this.badgeURL = `awards/api/v1/badge/${badgeId}`;
    return this.http.get<any>(this.badgeURL);
  }
  getAllBadges(clientId: any): Observable<any> {
    return this.http.get<any>(`${AwardsService.BADGE_URL}/clientid/${clientId}`);
  }

  getIssuers(clientId: any): Observable<any> {
    this.listOfIssuersURL = `awards/api/v1/${clientId}/issuers`;
    return this.http.get<any>(this.listOfIssuersURL);
  }
  updateIssuerId(issuerId: string) {
    this.issuerId = issuerId;
  }
  getSelectedIssuerId() {
    return this.issuerId;
  }
  getAssertionByIssuerId(issuerId: string) {
    this.listOfAssertionURL = `awards/api/v1/assertionrecord/issuer/${issuerId}`;
    return this.http.get<any>(this.listOfAssertionURL);
  }
  errorHandler(error: HttpErrorResponse) {
    return throwError(error);
  }

  UpdateAssertionAfterRevocation(assertionId: string, revocationReason: string) {
    this.updateAssertionURL = `awards/api/v1/assertion/${assertionId}`;
    return this.http.patch<any>(this.updateAssertionURL, revocationReason, this.options);
  }

  UpdateBadge(badgeId: string, newBadge: any) {
    return this.http.patch<any>(`${AwardsService.BADGE_URL}/${badgeId}`, newBadge, this.options);
  }
}

