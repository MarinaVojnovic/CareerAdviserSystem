import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {MatGridListModule} from '@angular/material/grid-list';
import { ProfessionsListComponent } from './professions-list/professions-list.component';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { RouterModule } from '@angular/router';
import { MatIconModule } from "@angular/material/icon";
import { ProfessionDetailsComponent } from './profession-details/profession-details.component';
import { ProfessionResultsComponent } from './profession-results/profession-results.component'; 
import {MatCheckboxModule} from '@angular/material/checkbox';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ProfessionsTestComponent } from './professions-test/professions-test.component';
import { CandidateProfessionsComponent } from './candidate-professions/candidate-professions.component';
import { ProfessionFormComponent } from './profession-form/profession-form.component';
import { ProfessionalFieldFormComponent } from './professional-field-form/professional-field-form.component';
import { AddProfessionActivityComponent } from './add-profession-activity/add-profession-activity.component';
import {MatRadioModule} from '@angular/material/radio';
import { EmploymentScoreFormComponent } from './employment-score-form/employment-score-form.component';

@NgModule({
  declarations: [ ProfessionsListComponent, ProfessionDetailsComponent, ProfessionResultsComponent, ProfessionsTestComponent, CandidateProfessionsComponent, ProfessionFormComponent, ProfessionalFieldFormComponent, AddProfessionActivityComponent, EmploymentScoreFormComponent],
  imports: [
    CommonModule,
    HttpClientModule,
    MatToolbarModule,
    MatButtonModule,
    RouterModule,
    MatGridListModule,
    MatIconModule,
    MatCheckboxModule,
    FormsModule,
    ReactiveFormsModule,
    MatRadioModule
    
  
   
  ],
  exports: [ProfessionsListComponent, ProfessionResultsComponent, ProfessionsTestComponent],
})
export class ProfessionsModule { }
