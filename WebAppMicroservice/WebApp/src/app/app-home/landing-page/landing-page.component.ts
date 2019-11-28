import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material';
import { LoginDialogBoxComponent } from '../login-dialog-box/login-dialog-box.component';


@Component({
  selector: 'app-landing-page',
  templateUrl: './landing-page.component.html',
  styleUrls: ['./landing-page.component.css']
})
export class LandingPageComponent implements OnInit {

  cookieMessage = 'This website uses cookies to ensure you get the best experience on our website.';
  cookieLinkText = 'Learn More';
  cookieDismiss = 'Got-it!';

  constructor(public dialog: MatDialog) { }

  ngOnInit() {
    const cc = window as any;
    cc.cookieconsent.initialise({
      palette: {
        popup: {
          background: '#000000'
        },
        button: {
          background: '#DCDCDC',
          text: '#000000'
        }
      },
      theme: 'classic',
      content: {
        message: this.cookieMessage,
        dismiss: this.cookieDismiss,
        link: this.cookieLinkText,
        href: 'https://www.online-sciences.com/computer/cookies-uses-features-advantages-and-disadvantages/'
      }
    });

  }

  openDialog() {
    this.dialog.open(LoginDialogBoxComponent, {
      width: '400px', height: '250px',
    });
  }
}

