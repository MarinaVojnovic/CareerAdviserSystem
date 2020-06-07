import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PersonalityTestComponent } from './personality-test/personality-test.component';
import { PersonalityResultsComponent } from './personality-results/personality-results.component';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import { MatButtonModule } from '@angular/material/button';
import {MatCheckboxModule} from '@angular/material/checkbox';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { PersonalityTraitFormComponent } from './personality-trait-form/personality-trait-form.component';
import { PersonalityQuestionsComponent } from './personality-questions/personality-questions.component';
import { PersonalityQuestionFormComponent } from './personality-question-form/personality-question-form.component';
import {MatRadioModule} from '@angular/material/radio';
import { PersonalityQuestionEditComponent } from './personality-question-edit/personality-question-edit.component';

@NgModule({
  declarations: [PersonalityTestComponent, PersonalityResultsComponent, PersonalityTraitFormComponent, PersonalityQuestionsComponent, PersonalityQuestionFormComponent, PersonalityQuestionEditComponent],
  imports: [
    CommonModule,
    MatProgressBarModule,
    MatButtonModule,
    MatCheckboxModule,
    FormsModule,
    ReactiveFormsModule.withConfig({warnOnNgModelWithFormControl: 'never'}),
    MatRadioModule
  ],
  exports:[
    PersonalityResultsComponent, PersonalityTestComponent, PersonalityTraitFormComponent, PersonalityQuestionFormComponent
  ]
})
export class PersonalityModule { }
