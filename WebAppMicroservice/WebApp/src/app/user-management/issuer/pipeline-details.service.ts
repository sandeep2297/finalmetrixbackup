import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PipelineDetailsService {
  private messageSource = new BehaviorSubject({});
  constructor() { }
  changeMessage(message) {
    this.messageSource.next(message);
  }

  currentMessage(): Observable<any> {
    return this.messageSource.asObservable();
  }
}
