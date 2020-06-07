import { Component, OnInit } from '@angular/core';
import { TraitQuestion } from 'src/app/model/trait-question';
import { PersonalityService } from 'src/app/service/personality.service';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { PersonalityQuestionFormComponent } from '../personality-question-form/personality-question-form.component';
import { PersonalityQuestionEditComponent } from '../personality-question-edit/personality-question-edit.component';
import { Trait } from 'src/app/model/trait';

@Component({
  selector: 'app-personality-questions',
  templateUrl: './personality-questions.component.html',
  styleUrls: ['./personality-questions.component.css']
})
export class PersonalityQuestionsComponent implements OnInit {

  traitQuestions : Array<TraitQuestion>;

  

  constructor(private personalityService : PersonalityService, public activeModal : NgbActiveModal, public modalService : NgbModal) { }

  ngOnInit(): void {
    this.getTraitQuestions();
  }

  getTraitQuestions(){
    this.personalityService.getTraitQuestions().subscribe(
      (response => {
        if (response !== null) {
          this.traitQuestions = response;
          alert(this.traitQuestions.length);
        }
      }),
      (error => {
        alert(error.error.message);
      })
    );

  }

  newQuestion(){
    const modalRef = this.modalService.open(PersonalityQuestionFormComponent);
    var that = this;
    modalRef.result.then(
      function () {
        that.getTraitQuestions();
        
      }
    );
  }

  editQuestion(question){
  
    let that = this;
    const modalRef = this.modalService.open(PersonalityQuestionFormComponent);
    modalRef.componentInstance.text = question.text;
    modalRef.componentInstance.personalityField=question.trait.personalityField;

    let target1;
    let target2;
    let traitTargets : Trait[];
    this.personalityService.getByPersonalityField(question.trait.personalityField).subscribe(
      (response => {
        if (response !== null) {
          traitTargets=response;
          target1 = traitTargets[0].target;
          target2=traitTargets[1].target;
          modalRef.componentInstance.targetOne=target1;
          modalRef.componentInstance.targetTwo=target2;
          modalRef.componentInstance.chooseTarget=true;
          modalRef.componentInstance.target=question.trait.target;
          modalRef.componentInstance.id=question.id;

          modalRef.result.then(
            function () {
              that.getTraitQuestions();
              
            }
          );
        }
      }),
      (error => {
        alert(error.error.message);
      })
    );
  }

  deleteQuestion(quest){
    this.personalityService.deleteTraitQuestion(quest.id).subscribe(
      (response => {
        if (response !== null) {
          alert('question successfully deleted');
          this.getTraitQuestions();
        }
      }),
      (error => {
        alert(error.error.message);
      })
    );
  }

  activateQuestion(quest){
    this.personalityService.activateTraitQuestion(quest.id).subscribe(
      (response => {
        if (response !== null) {
          alert('question successfully activated');
          this.getTraitQuestions();
        }
      }),
      (error => {
        alert(error.error.message);
      })
    );
  }

}
