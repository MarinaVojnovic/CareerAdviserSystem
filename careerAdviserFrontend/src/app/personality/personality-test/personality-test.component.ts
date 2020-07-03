import { Component, OnInit } from '@angular/core';
import { TraitsResult } from 'src/app/model/traits-result';
import { TraitsResultService } from 'src/app/service/traits-result.service';
import { TraitQuestionResult } from 'src/app/model/trait-question-result';
import { PersonalityTestService } from 'src/app/service/personality-test.service';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { MessageBoxComponent } from 'src/app/user/message-box/message-box.component';
import { tick } from '@angular/core/testing';

@Component({
  selector: 'app-personality-test',
  templateUrl: './personality-test.component.html',
  styleUrls: ['./personality-test.component.css']
})
export class PersonalityTestComponent implements OnInit {


  traitQuestionResults : TraitQuestionResult[];

  isChecked : boolean;

  constructor(private modalService : NgbModal, public activeModal: NgbActiveModal, private personalityTestService : PersonalityTestService) { }

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
        //alert(error.error.message);
        const modalRef = this.modalService.open(MessageBoxComponent);
        modalRef.componentInstance.success= false;
        modalRef.componentInstance.message=''+error.error.message;
      })
    );
  }
 
  submitQuestions(){
    console.log('submit questions called');
    if (this.traitQuestionResults.length==0){

      const modalRef = this.modalService.open(MessageBoxComponent);
      modalRef.componentInstance.success= false;
      modalRef.componentInstance.message="At least one statement has to be checked!";
    }else {

    
    this.personalityTestService.submitTraitQuestion(this.traitQuestionResults).subscribe(
      (response => {
        if (response !== null) {
        // alert("Test successfully submited!");
         const modalRef = this.modalService.open(MessageBoxComponent);
         modalRef.componentInstance.success= true;
         modalRef.componentInstance.message="Test successfully submited!";
         this.activeModal.close();
         location.reload();
        }
      }),
      (error => {
        console.log('some error happend :)');
       // alert(error.error.message);
       const modalRef = this.modalService.open(MessageBoxComponent);
       modalRef.componentInstance.success= false;
       modalRef.componentInstance.message=''+error.error.message;
      })
    );
    }
  }

}
