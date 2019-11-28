import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { catchError } from 'rxjs/operators';
import { ActivityPipeline } from './entity/ActivityPipeline';
@Injectable({
  providedIn: 'root'
})
export class PipelineService {
  options = {
    headers: new HttpHeaders({
      'content-type': 'application/json'
    })
  };
  constructor(private http: HttpClient, private service: PipelineService) { }
  createPipeline(pipelineData: ActivityPipeline, issuerId): Observable<any> {
    return this.http.post<any>(`${environment.pipelineBaseUrl}/${issuerId}/pipeline`, pipelineData, this.options)
      .pipe(catchError(this.errorHandler));
  }
  errorHandler(error: HttpErrorResponse) {
    return throwError(error);
  }
  getPipelineDetails(issuerProfileId): Observable<any> {
    return this.http.get<any>(`${environment.pipelineBaseUrl}/${issuerProfileId}/pipeline`);
  }
  getSelectedPipeline(issuerId, activityPipelineId): Observable<any> {
    return this.http.get<any>(`${environment.pipelineBaseUrl}/${issuerId}/pipeline/${activityPipelineId}`);
  }
  updateSelectedPipeline(issuerId, activityPipelineId, pipelineStatus): Observable<any> {
    return this.http.patch<any>(`${environment.pipelineBaseUrl}/${issuerId}/pipeline/${activityPipelineId}`, pipelineStatus, this.options);
  }

}

