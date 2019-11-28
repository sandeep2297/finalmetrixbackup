import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { error } from 'util';
import { catchError } from 'rxjs/operators';
import { configURLItems } from '../pipeline-filter/config';

@Injectable({
  providedIn: 'root'
})

export class PipelineFilterService {
  /*This URL for get issuerDetails */
  private static readonly GET_ISSUER_URL = configURLItems.GET_ISSUER_URL;
  /*This URL for get pipelineDetails based on unique issuer */
  private static readonly GET_PIPELINE_URL = configURLItems.GET_PIPELINE_URL;
  /*This URL for get the Ruleset result data for user activity stream */
  private static readonly GET_RULESET_RESULT_URL = configURLItems.GET_RULESET_RESULT_URL;

  constructor(private http: HttpClient) { }
  /**************************************************************************
   Method : errorHandler()
   Arguments : {err}
   Description: This function is used to handle the REST API error in common method.
  ***************************************************************************/
  errorHandler(err: HttpErrorResponse) {
    return throwError(error || 'Server status = ' + err.status);
  }

  /**************************************************************************
   Method : getAllIssuerListDetails()
   Arguments : {Null}
   Description: This method used for get all the issuer details with help of REST API.
  ***************************************************************************/
  getAllIssuerListDetails(clientId): Observable<any> {
    const issuerid = `pipeline/api/v1/issuer/client/${clientId}`;
    return this.http.get<any>(issuerid)
      .pipe(
        catchError((err: HttpErrorResponse) => {
          return this.errorHandler(err);
        })
      );
  }
  /**************************************************************************
  Method : getAllPipelineListDetails()
  Arguments : {issuerID}
  Description: This method used for get all pipeline details with corresponding issuer.
 ***************************************************************************/
  getAllPipelineListDetails(issuerid: any): Observable<any> {
    const pipelineurl = `pipeline/api/v1/issuer/${issuerid}/pipeline`;
    return this.http.get<any>(pipelineurl)
      .pipe(
        catchError((err: HttpErrorResponse) => {
          return this.errorHandler(err);
        })
      );
  }
   /**************************************************************************
   Method : getAllRulesetResultListDetails()
   Arguments : {issuerID,pipelineID}
   Description: This method used for get all pipeline details based on issuer and
   pipeline with help of REST API.
  ***************************************************************************/
  getAllRulesetResultListDetails(issuerID: any, pipelineID: any): Observable<any> {
    const resultseturl = `stream/api/v1/resultset/issuer/${issuerID}/pipeline/${pipelineID}`;
    return this.http.get<any>(resultseturl)
      .pipe(
        catchError((err: HttpErrorResponse) => {
          return this.errorHandler(err);
        })
      );
  }
}
