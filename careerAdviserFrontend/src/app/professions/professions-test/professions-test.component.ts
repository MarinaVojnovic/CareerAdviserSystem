import { Component, OnInit } from '@angular/core';
import { PreferenceQuestionResult } from 'src/app/model/preference-question-result';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { ProfessionService } from 'src/app/service/profession.service';

@Component({
  selector: 'app-professions-test',
  templateUrl: './professions-test.component.html',
  styleUrls: ['./professions-test.component.css']
})
export class ProfessionsTestComponent implements OnInit {

  preferenceQuestionResults : PreferenceQuestionResult[];

  isChecked : boolean;

  constructor(public activeModal: NgbActiveModal, private professionServices : ProfessionService) { }

  ngOnInit(): void {
    this.getQuestionsForUser();
  }

  getQuestionsForUser(){
    this.professionServices.getQuestionsForUser().subscribe(
      (response => {
        if (response !== null) {
          this.preferenceQuestionResults=response;
          console.log('number of questions preferences: '+this.preferenceQuestionResults.length);
        }
      }),
      (error => {
        console.log('some error happend :)');
        alert(error.error.message);
      })
    );
  }

  submitQuestions(){
    console.log('submit questions called');
    this.professionServices.submitQuestions(this.preferenceQuestionResults).subscribe(
      (response => {
        if (response !== null) {
         alert("Test successfully submited!");
         this.activeModal.close();
         location.reload();
        }
      }),
      (error => {
        console.log('some error happend :)');
        alert(error.error.message);
      })
    );
  }

}
