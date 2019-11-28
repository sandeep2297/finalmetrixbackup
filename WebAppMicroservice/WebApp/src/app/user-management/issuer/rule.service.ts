import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError, BehaviorSubject } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { RulesComponent } from './rules/rules.component';
import { environment } from 'src/environments/environment.prod';

@Injectable({
  providedIn: 'root'
})
export class RuleService {

  private static readonly RULE_URL = 'pipeline/api/v1/activityrule';
  private static readonly EXPRESSION_URL = 'pipeline/api/v1/expression';
  private static readonly BADGE_URL = 'awards/api/v1/';
  private headers = new HttpHeaders({ 'Content-Type': 'application/json' });
  private messageSource = new BehaviorSubject({});
  constructor(private http: HttpClient) { }

  addRuleData(issuerId, pipelineId, rule: RulesComponent): Observable<any> {
    return this.http.post(`${environment.issuerBaseUrl}/${issuerId}/pipeline/${pipelineId}/rule`, rule, { headers: this.headers })
    .pipe(catchError(this.errorHandler));
  }
  errorHandler(error: HttpErrorResponse) {
    return throwError(error);
  }
  getBadgeByBadgeId(awardId) {
    return this.http.get<any>(`${environment.badgeUrl}/${awardId}`);
  }
  getAllRules(issuerId, pipelineId): Observable<any> {
    return this.http.get<any>(`${environment.issuerBaseUrl}/${issuerId}/pipeline/${pipelineId}/rule`);
  }
  getRuleById(issuerId, pipelineId, ruleId): Observable<any> {
    return this.http.get<any>(`${environment.issuerBaseUrl}/${issuerId}/pipeline/${pipelineId}/rule/${ruleId}`);
  }
  getExpressionByRuleId(ruleId): Observable<any> {
    return this.http.get<any>(`${environment.expressionUrl}/${ruleId}`);
  }
  archiveRule(issuerId, pipelineId, ruleId, archiveStatus: boolean) {
    return this.http.patch<any>(`${environment.issuerBaseUrl}/${issuerId}/pipeline/${pipelineId}/rule/${ruleId}`, archiveStatus, { headers: this.headers });
  }

  changeMessage(message) {
    this.messageSource.next(message);
  }
  currentMessage(): Observable<any> {
    return this.messageSource.asObservable();
  }
}
