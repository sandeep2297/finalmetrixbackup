import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class WebsocketDataResponseService {
  private SocketDataResultArray = new BehaviorSubject([]);
  constructor() { }

  /*This below function is used to after receving the websocket reponse and that resonse send to this below method and processing*/
  sendWebSocketResponse(webSockResponse) {
    this.SocketDataResultArray.next(webSockResponse);
  }

  /*This below function is used to subscribe to websocket response and send it to UI properly using behavior subject.*/
  recieveWebSocketResponse(): Observable<any> {
    return this.SocketDataResultArray;
  }
}
