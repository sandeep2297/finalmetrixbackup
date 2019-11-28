import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { MaterialModule } from 'src/app/material/material.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LayoutModule } from '@angular/cdk/layout';
import { CommonModule } from '@angular/common';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserManagementModule } from './user-management/user-management.module';
import { AwardsModule } from './awards/awards.module';
import { FooterComponent } from './app-home/footer/footer.component';
import { LandingPageComponent } from './app-home/landing-page/landing-page.component';
import { LoginDialogBoxComponent } from './app-home/login-dialog-box/login-dialog-box.component';
import { NavbarComponent } from './app-home/navbar/navbar.component';
import { AuthInterceptorService } from './app-home/auth-interceptor.service';
import { ActivityPipelineModule } from './activity-pipeline/activity-pipeline.module';
import { FlexLayoutModule } from '@angular/flex-layout';
import { ChartsModule } from 'ng2-charts';


@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    LandingPageComponent,
    LoginDialogBoxComponent,
    NavbarComponent
  ],
  imports: [
    AwardsModule,
    ActivityPipelineModule,
    UserManagementModule,
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    LayoutModule,
    CommonModule,
    FlexLayoutModule,
    ChartsModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS, useClass: AuthInterceptorService, multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
