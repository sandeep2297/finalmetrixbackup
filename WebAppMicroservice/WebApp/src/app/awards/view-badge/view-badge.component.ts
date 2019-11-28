import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Router, ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-view-badge',
  templateUrl: './view-badge.component.html',
  styleUrls: ['./view-badge.component.css']
})
export class ViewBadgeComponent implements OnInit {

  badge: any;
  displayAlignment = false;
  constructor(public dailogRef: MatDialogRef<ViewBadgeComponent>,
              @Inject(MAT_DIALOG_DATA) public badgeData: any, private router: Router,
              private route: ActivatedRoute) {
  }
  panelOpenState = false;
  ngOnInit() {
    this.badge = this.badgeData;
    if (this.badge.alignment.length !== 0) {
     this.displayAlignment = true;
   }
  }
  onClose() {
    this.dailogRef.close();
  }
  editBadge(badgeId) {
    this.router.navigate(['awards/badge/edit', badgeId]);
    this.dailogRef.close();
  }

  // listOfAwardees() {
  //   const id = this.badge.badgeId;
  //   this.onClose();
  //   this.router.navigate([`awards/badge/${id}/details`], {relativeTo: this.route});

  // }
}
