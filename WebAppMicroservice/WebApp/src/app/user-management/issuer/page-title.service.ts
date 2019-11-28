import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PageTitleService {
  public title: BehaviorSubject<string> = new BehaviorSubject<string>(null);
  constructor() { }
  setTitle(value: string) {
    this.title.next(value);
}
}
