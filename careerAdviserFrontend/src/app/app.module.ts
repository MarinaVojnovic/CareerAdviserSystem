import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DashboardModule } from './dashboard/dashboard.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ProfessionsModule } from './professions/professions.module';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { DatePipe } from '@angular/common';
import { CanActivateAuthGuard } from './security/can-activate-auth.guard';
import { JwtUtilsService } from './security/jwt-utils.service';
import { AuthenticationService } from './security/authentication-service.service';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { TokenInterceptorService } from './security/token-interceptor.service';





@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    DashboardModule,
    BrowserAnimationsModule,
    ProfessionsModule,
    NgbModule
  ],

  providers: [
    DatePipe,
    CanActivateAuthGuard,
    JwtUtilsService,
    AuthenticationService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptorService,
      multi: true
    },

    
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
