import { Component, OnInit } from '@angular/core';
import { AwardsService } from '../awards.service';
import { LoggerService } from 'src/app/logger.service';

@Component({
  selector: 'app-public-badge',
  templateUrl: './public-badge.component.html',
  styleUrls: ['./public-badge.component.css']
})
export class PublicBadgeComponent implements OnInit {

  badgeId: any;
  badge: any;

  constructor(
    private awardsservice: AwardsService,
    private logger: LoggerService) { }

  ngOnInit() {
  }

  verify() {
    this.badge = this.awardsservice.getBadgeById(this.badgeId);
    // this.logger.log(this.badge);
  }

}
