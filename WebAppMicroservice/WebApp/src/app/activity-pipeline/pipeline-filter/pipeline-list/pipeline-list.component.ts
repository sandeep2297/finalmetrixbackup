import { Component, OnInit, ViewChild, Input, AfterViewInit, DoCheck } from '@angular/core';
import { animate, state, style, transition, trigger } from '@angular/animations';
import { PipelineListService } from '../pipeline-list.service';
import { MatPaginator } from '@angular/material/paginator';
import { WebsocketDataResponseService } from '../websocket-data-response.service';
import * as moment from 'moment';
// @ViewChildren(MatPaginator) paginator = new QueryList<MatPaginator>();
@Component({
  selector: 'app-pipeline-list',
  templateUrl: './pipeline-list.component.html',
  styleUrls: ['./pipeline-list.component.css'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({ height: '0px', minHeight: '0', display: 'none' })),
      state('expanded', style({ height: '*' })),
      // transition('expanded <=> collapsed', animate('105ms cubic-bezier(0,0, 0, 1)')),
    ]),
  ]
})

export class PipelineListComponent implements OnInit {
  @Input() dataSource: any;
  @Input() columnsToDisplay: any;
  @Input() expandedElement: any;
  @Input() dataSourceEmpty: boolean;
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;
  behavioursubscription: any;
  constructor(private resultRulesetListItems: PipelineListService, private webSocketResponse: WebsocketDataResponseService) {
  }

  ngOnInit() {
    if (this.behavioursubscription !== undefined) {
      this.behavioursubscription.unsubscribe();
    }
    // console.log('data from parent: ', this.dataSource.length);
    this.behavioursubscription = this.webSocketResponse.recieveWebSocketResponse().subscribe((data) => {
      if (data !== '' && data !== null && data.length !== 0) {
        this.dataSource = (JSON.parse(data)).concat(this.dataSource);
        this.dataSource[0].activitytime = moment(this.dataSource[0].activitytime).format('LLL');
        // console.log(this.dataSource);
        // this.dataSource.paginator = this.paginator;
      }
    });
  }
  headerStringMapping(headerstr) {
    switch (headerstr) {
      case 'activitytime':
        return 'Activity Time';
        break;
      case 'rulename':
        return 'Rule Name';
        break;
      case 'result':
        return 'Result';
        break;
      case 'actor':
        return 'Actor';
        break;
      case 'object':
        return 'Object';
        break;
      case 'duration':
        return 'Duration(ms)';
        break;
    }
  }
}

