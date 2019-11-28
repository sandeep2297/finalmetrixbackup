/**********************************************************************************
Enable WebSoket: You need to install the below packages in your node module
-----------------------------------------------------------------------------------
1)npm install stompjs;
2)npm install sockjs-client
3)npm i net -S
-----------------------------------------------------------------------
And you have to this below lines in your polyfills.js file at the last.
-----------------------------------------------------------------------
if (typeof (window as any).global === 'undefined') {
    (window as any).global = window;
}
***********************************************************************************/
import { Injectable } from '@angular/core';
import Stomp from 'stompjs';
import SockJS from 'sockjs-client';
import { configURLItems } from '../pipeline-filter/config';
import { WebsocketDataResponseService } from './websocket-data-response.service';
import { LoggerService } from 'src/app/logger.service';
@Injectable({
  providedIn: 'root'
})

export class WebsocketService {
  constructor(private webSocketResponse: WebsocketDataResponseService, private logger: LoggerService) { }
  private serverUrl = configURLItems.ENABLE_WS_CONNECTION_URL;
  private stompClient;
  private subscription;

  /**************************************************************************
    Method : initializeWebSocketConnection()
    Arguments : {issuerID , pipelineID}
    Description: This function is used to Enable the websocket connection using stomp client & SockJS.
   ***************************************************************************/
  initializeWebSocketConnection(issuerID: any, pipelineID: any) {
    this.logger.log('WEB-SOCKET-CONNECT-ENABLE-ISSUER-ID[' + issuerID + ']PIPELINE-ID[' + pipelineID + ']');
    if (this.stompClient !== undefined) {
      this.disconnectWebsocket();
    }
    this.connectWebSocket(issuerID, pipelineID);
  }
  /**************************************************************************
    Method : connectWebSocket()
    Arguments : {issuerID , pipelineID}
    Description: This function is used to websocket connection with StompClient
  ***************************************************************************/
  connectWebSocket(issuerID, pipelineID) {
    const ws = new SockJS(this.serverUrl);
    this.stompClient = Stomp.over(ws);
    const chatUrl = '/chat/' + issuerID + '/' + pipelineID;
    this.stompClient.connect({}, (frame) => {
      if (this.subscription !== undefined) {
        this.subscription.unsubscribe();
      }
      this.subscription = this.stompClient.subscribe(chatUrl, (resultSetArrayList: any) => {
        if (resultSetArrayList.body) {
          this.logger.log('WEB-SOCKET-RESPONSE =>' + resultSetArrayList.body);
          this.webSocketResponse.sendWebSocketResponse(resultSetArrayList.body);
        }
      });
      // tslint:disable-next-line:no-unused-expression
    }), (error: any) => {
      if (error) {
        this.logger.log('WEB-SOCKETWEB-SOCKET-ERROR Occured while Connecting =>' + error);
        return;
      }
    };
  }
  /**************************************************************************
    Method : disconnectWebsocket()
    Arguments : {No argument}
    Description: This function is used disconnect the websocket before every connection.
   ***************************************************************************/
  disconnectWebsocket() {
    this.stompClient.disconnect(() => {
      this.stompClient.ws.onclose = null;
      this.stompClient = undefined;
      this.logger.log('WEB-SOCKET-DISCONNECTED');
    });
  }
}
