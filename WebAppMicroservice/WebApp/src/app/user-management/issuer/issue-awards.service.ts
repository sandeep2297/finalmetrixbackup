import { Injectable } from '@angular/core';
import { catchError } from 'rxjs/operators';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { AuthenticationService } from 'src/app/app-home/authentication.service';
import { Observable, throwError, BehaviorSubject } from 'rxjs';
import { AwardManuallyComponent } from './award-manually/award-manually.component';
import { ViewAndVerifyComponent } from './view-and-verify/view-and-verify.component';

@Injectable({
  providedIn: 'root'
})
export class IssueAwardsService {


  private static readonly BADGE_URL = 'awards/api/v1/badge';
  private static readonly BadgeClassUrl = 'awards/api/v1/badgeclass';
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
  verifyAssertionURL: any;
  assertionObject: any;
  issuerURL: any;
  updatedAssertionArray = new BehaviorSubject({});


  constructor(private http: HttpClient, private auth: AuthenticationService) { }

  getAllBadges(clientId: any): Observable<any> {
    return this.http.get<any>(`${IssueAwardsService.BADGE_URL}/clientid/${clientId}`);
  }

  getAllBadgeClass(issuerId: any): Observable<any> {
    return this.http.get<any>(`${IssueAwardsService.BADGE_URL}/issuer/${issuerId}`);
  }

  postBadgeClass(badge: any): Observable<any> {
    return this.http.post(`${IssueAwardsService.BadgeClassUrl}`, badge, this.options)
    .pipe(catchError(this.errorHandler));
  }

  awardBadgeToRecipient(recipient: AwardManuallyComponent): Observable<any> {
    // console.log('award badge to recipient', recipient);
    return this.http.post(IssueAwardsService.ASSERTION_URL, recipient, { headers: this.headers })
      .pipe(catchError(this.errorHandler));
  }

  getBadgeById(badgeId: any): Observable<any> {
    this.badgeURL = `awards/api/v1/badge/${badgeId}`;
    return this.http.get<any>(this.badgeURL);
  }
  getBadgeClassById(badgeClassId: any): Observable<any> {
    // console.log('in servicec badge class  Id', badgeClassId);
    return this.http.get<any>(`${IssueAwardsService.BadgeClassUrl}/${badgeClassId}`);
  }

  updateIssuerId(issuerId: string) {
    this.issuerId = issuerId;
  }
  getSelectedIssuerId() {
    return this.issuerId;
  }
  getAssertionByIssuerId(issuerId: string) {
    this.listOfAssertionURL = `awards/api/v1/assertion/issuer/${issuerId}`;
    // console.log('Inside service: ', issuerId);
    return this.http.get<any>(this.listOfAssertionURL);
  }

  errorHandler(error: HttpErrorResponse) {
    return throwError(error);
  }

  UpdateAssertionAfterRevocation(assertionId: string, revocationReason: string) {
    this.updateAssertionURL = `awards/api/v1/assertion/${assertionId}`;
    return this.http.patch<any>(this.updateAssertionURL, revocationReason, this.options);
  }

  saveIssuerInAwards(clientid: any): Observable<any> {
    this.issuerURL = `awards/api/v1/issuer/${clientid}`;
    return this.http.get<any>(this.issuerURL);
  }

  UpdateAssertionArrayAfterRevocation(assertion) {
    this.updatedAssertionArray.next(assertion.value);
  }

  verifyAssertion(assertion: ViewAndVerifyComponent) {
    // console.log('Inside verify assertion service call with assertion', assertion);
    this.assertionObject = assertion;
    // console.log('assertionObject in service', this.assertionObject);
    // console.log('assertionObject.assertionId in service', this.assertionObject.assertionId);
    const assertionId = this.assertionObject.assertionId;
    // console.log('Inside verify assertion service call with assertion', assertionId);
    // console.log('assertion object', assertion);
    this.verifyAssertionURL = `awards/api/v1/assertion/${assertionId}/verify`;
    return this.http.post<any>(this.verifyAssertionURL, assertion, this.options).pipe(catchError(this.errorHandler));
  }
}
