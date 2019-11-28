import { TestBed, inject } from '@angular/core/testing';

import { IssuerDetailsInputService } from './issuer-details-input.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpEvent, HttpEventType } from '@angular/common/http';

describe('IssuerDetailsInputService', () => {
  beforeEach(() => TestBed.configureTestingModule({
    imports: [HttpClientTestingModule],
    providers: [IssuerDetailsInputService]
  }));
//   it('should get the data', inject(
//     [HttpTestingController, IssuerDetailsInputService],
//     (httpMock: HttpTestingController, issuerService: IssuerDetailsInputService) => {
//       const mockUsers = [
//         { issuerName: 'ITC Infotech', issuerOrgUrl: 'www.itcinfotech.com', issuerAvatarURL: 'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQYG1GCmO0o2PwjfayucWy-bapNVsPMI2_0fTWfyLMBm-3tkh30', issuerDescription:'This is issuer description.', profileObjective:'This is issuer profile objective.', issuerEmail:'itc@infotech.com'},
//         { issuerName: 'ITC Info', issuerOrgUrl: 'www.itcinfo.com', issuerAvatarURL: 'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQYG1GCmO0o2PwjfayucWy-bapNVsPMI2_0fTWfyLMBm-3tkh30', issuerDescription:'This is description.', profileObjective:'This is profile objective.', issuerEmail:'itc@info.com'},
//       ];
//       issuerService.getAllIssuers().subscribe((value) => {
//       });

//       const mockReq = httpMock.expectOne(issuerService.ISSUER_URL);
//       expect(mockReq.cancelled).toBeFalsy();
//       expect(mockReq.request.responseType).toEqual('json');
//       mockReq.flush(mockUsers);
//       httpMock.verify();
//     }
//   )
//  );
  });
