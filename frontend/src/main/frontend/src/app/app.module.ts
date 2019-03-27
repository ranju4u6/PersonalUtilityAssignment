import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { APP_BASE_HREF } from '@angular/common';
import { UserComponent } from './user/user.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { UtilityModule } from './utility/utility.module';
import { SettingsComponent } from './settings/settings.component';
import { DataTableModule } from 'angular-6-datatable'

@NgModule({
  declarations: [
    AppComponent,
    UserComponent,
    WelcomeComponent,
    SettingsComponent
  ],
  imports: [
    UtilityModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    RouterModule,
    DataTableModule
  ],
  providers: [{provide:APP_BASE_HREF, useValue:"/PersonalUtility"}, CookieService],
  bootstrap: [AppComponent]
})
export class AppModule { }
