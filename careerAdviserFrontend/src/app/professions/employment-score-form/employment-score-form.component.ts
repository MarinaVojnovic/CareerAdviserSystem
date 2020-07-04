import { Component, OnInit } from '@angular/core';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { EmploymentScoreTemplate } from 'src/app/model/employmentScore.Template';
import { FormControl, FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ProfessionService } from 'src/app/service/profession.service';
import { MessageBoxComponent } from 'src/app/user/message-box/message-box.component';

@Component({
  selector: 'app-employment-score-form',
  templateUrl: './employment-score-form.component.html',
  styleUrls: ['./employment-score-form.component.css']
})
export class EmploymentScoreFormComponent implements OnInit {

  scores: EmploymentScoreTemplate[]=[];
  currentScores: EmploymentScoreTemplate[]=[];
  public employmentScoreTemplate : EmploymentScoreTemplate = {
    minPerc: 1,
    maxPerc: 100,
    score: 0
  }

  minPercCtrl: FormControl;
  maxPercCtrl: FormControl;
  scoreCtrl: FormControl;
  public form: FormGroup;
  constructor(private modalService : NgbModal, private professionService : ProfessionService, private fb: FormBuilder, public activeModal : NgbActiveModal) { 
    this.minPercCtrl = this.fb.control([this.employmentScoreTemplate.minPerc, Validators.required, Validators.min(1), Validators.max(100)]);
    this.maxPercCtrl = this.fb.control([this.employmentScoreTemplate.maxPerc, Validators.required, Validators.min(1), Validators.max(100)]);
    this.scoreCtrl=this.fb.control([this.employmentScoreTemplate.score, Validators.required, Validators.min(0), Validators.max(1.5)]);
  
    this.form = this.fb.group({
      minPerc: this.minPercCtrl,
      maxPerc: this.maxPercCtrl,
      score: this.scoreCtrl
  
    });
  
  }

  ngOnInit(): void {
    this.getCurrentScores();
  }

  getCurrentScores(){
    this.professionService.getCurrentScores().subscribe(
      (response => {
        if (response !== null) {
       
        this.currentScores=response;
        }
      }),
      (error => {
        console.log('some error happend :)');
        //alert(error.error.message);
        const modalRef = this.modalService.open(MessageBoxComponent);
        modalRef.componentInstance.success= false;
        modalRef.componentInstance.message=""+error.error.message;
      })
    );
  }
  submit(){

    if (this.scores.length==0){
      const modalRef = this.modalService.open(MessageBoxComponent);
      modalRef.componentInstance.success= false;
      modalRef.componentInstance.message="Scores must be entered!";
    }else {

    

    this.professionService.addNewProfessionScores(this.scores).subscribe(
      (response => {
        if (response !== null) {
         //alert("Successfully added new employment scores.");
         //this.activeModal.close();
         const modalRef = this.modalService.open(MessageBoxComponent);
         modalRef.componentInstance.success= true;
         modalRef.componentInstance.message="Successfully added new employment scores."
         this.activeModal.close();
        }
      }),
      (error => {
        console.log('some error happend :)');
        //alert(error.error.message);
        const modalRef = this.modalService.open(MessageBoxComponent);
        modalRef.componentInstance.success= false;
        modalRef.componentInstance.message=""+error.error.message;
      })
    );
    }
  }

  addScore(){
    let allowed  = true;
    if (this.employmentScoreTemplate.minPerc== undefined || this.employmentScoreTemplate.minPerc <= 0 || this.employmentScoreTemplate.minPerc >= 100){
      //alert('Minimum percentage must be positive number and less then 100.');
      allowed = false;
      const modalRef = this.modalService.open(MessageBoxComponent);
      modalRef.componentInstance.success= false;
      modalRef.componentInstance.message='Minimum percentage must be positive number and less then 100.';
    }
    if (this.employmentScoreTemplate.maxPerc== undefined || this.employmentScoreTemplate.maxPerc <= 0 || this.employmentScoreTemplate.minPerc > 100){
     // alert('Maximum percentage must be positive number and less then 100.');
      allowed = false;
      const modalRef = this.modalService.open(MessageBoxComponent);
      modalRef.componentInstance.success= false;
      modalRef.componentInstance.message='Maximum percentage must be positive number and less then 100.';
    }

    if (this.employmentScoreTemplate.minPerc >= this.employmentScoreTemplate.maxPerc){
      //alert('Minimum percentage must be less than maximum percentage!');
      allowed = false;
      const modalRef = this.modalService.open(MessageBoxComponent);
      modalRef.componentInstance.success= false;
      modalRef.componentInstance.message='Minimum percentage must be less than maximum percentage!';
      
    }

    if (this.employmentScoreTemplate.score<=0){
      //alert('Score must be positive number!');
      allowed = false;
      const modalRef = this.modalService.open(MessageBoxComponent);
      modalRef.componentInstance.success= false;
      modalRef.componentInstance.message='Score must be positive number!';
      
    }

    if (allowed == true){
      let score = new EmploymentScoreTemplate(this.employmentScoreTemplate.minPerc, this.employmentScoreTemplate.maxPerc, this.employmentScoreTemplate.score);
      this.scores.push(score);
      this.employmentScoreTemplate.minPerc=this.employmentScoreTemplate.maxPerc;
      this.employmentScoreTemplate.maxPerc=100;
      this.employmentScoreTemplate.score=0;
    }
    
  }

}
