import { Component, OnInit } from '@angular/core';
import { PreferenceQuestionResult } from 'src/app/model/preference-question-result';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ProfessionService } from 'src/app/service/profession.service';
import { MessageBoxComponent } from 'src/app/user/message-box/message-box.component';

@Component({
  selector: 'app-professions-test',
  templateUrl: './professions-test.component.html',
  styleUrls: ['./professions-test.component.css']
})
export class ProfessionsTestComponent implements OnInit {

  preferenceQuestionResults : PreferenceQuestionResult[]=[];

  isChecked : boolean;

  constructor(private modalService : NgbModal, public activeModal: NgbActiveModal, private professionServices : ProfessionService) { }

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
        //alert(error.error.message);
        const modalRef = this.modalService.open(MessageBoxComponent);
        modalRef.componentInstance.success= false;
        modalRef.componentInstance.message=''+error.error.message;
      })
    );
  }

  submitQuestions(){
    console.log('submit questions called');
    
    this.professionServices.submitQuestions(this.preferenceQuestionResults).subscribe(
      (response => {
        if (response !== null) {
         //alert("Test successfully submited!");
         //this.activeModal.close();
         //location.reload();
         const modalRef = this.modalService.open(MessageBoxComponent);
         modalRef.componentInstance.success= true;
         modalRef.componentInstance.message='Test successfully submited.';
         this.activeModal.close();
         location.reload();
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

}
