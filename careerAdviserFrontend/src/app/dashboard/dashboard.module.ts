import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './header/header.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatMenuModule } from '@angular/material/menu';
import { RouterModule } from '@angular/router';
import { MatGridListModule } from '@angular/material/grid-list';
import { ProfessionsModule } from '../professions/professions.module';
import { PersonalityModule } from '../personality/personality.module';
import { UserModule } from '../user/user.module';

@NgModule({
  declarations: [HeaderComponent, DashboardComponent],
  imports: [
    CommonModule,
    MatToolbarModule,
    MatButtonModule,
    MatMenuModule,
    RouterModule,
    MatGridListModule,
    ProfessionsModule,
    PersonalityModule,
    UserModule
  ]
})
export class DashboardModule { }
