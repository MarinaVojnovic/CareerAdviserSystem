import { Component, OnInit } from '@angular/core';
import { TraitsResult } from 'src/app/model/traits-result';
import { TraitsResultService } from 'src/app/service/traits-result.service';
import { TraitQuestionResult } from 'src/app/model/trait-question-result';
import { PersonalityTestService } from 'src/app/service/personality-test.service';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-personality-test',
  templateUrl: './personality-test.component.html',
  styleUrls: ['./personality-test.component.css']
})
export class PersonalityTestComponent implements OnInit {


  traitQuestionResults : TraitQuestionResult[];

  isChecked : boolean;

  constructor(public activeModal: NgbActiveModal, private personalityTestService : PersonalityTestService) { }

  ngOnInit(): void {
    this.getQuestionsForUser();
  }

  getQuestionsForUser(){
    this.personalityTestService.getQuestionsForUser().subscribe(
      (response => {
        if (response !== null) {
          this.traitQuestionResults=response;
          console.log('number of questions: '+this.traitQuestionResults.length);
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
    this.personalityTestService.submitTraitQuestion(this.traitQuestionResults).subscribe(
      (response => {
        if (response !== null) {
         alert("successfully submited");
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
