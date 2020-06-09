import { Component, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { EmploymentScoreTemplate } from 'src/app/model/employmentScore.Template';
import { FormControl, FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ProfessionService } from 'src/app/service/profession.service';

@Component({
  selector: 'app-employment-score-form',
  templateUrl: './employment-score-form.component.html',
  styleUrls: ['./employment-score-form.component.css']
})
export class EmploymentScoreFormComponent implements OnInit {

  scores: EmploymentScoreTemplate[]=[];
  public employmentScoreTemplate : EmploymentScoreTemplate = {
    minPerc: 1,
    maxPerc: 100,
    score: 0
  }

  minPercCtrl: FormControl;
  maxPercCtrl: FormControl;
  scoreCtrl: FormControl;
  public form: FormGroup;
  constructor(private professionService : ProfessionService, private fb: FormBuilder, public activeModal : NgbActiveModal) { 
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
  }

  submit(){

    this.professionService.addNewProfessionScores(this.scores).subscribe(
      (response => {
        if (response !== null) {
         alert("Successfully added new employment scores.");
         this.activeModal.close();
        }
      }),
      (error => {
        console.log('some error happend :)');
        alert(error.error.message);
      })
    );
  }

  addScore(){
    let score = new EmploymentScoreTemplate(this.employmentScoreTemplate.minPerc, this.employmentScoreTemplate.maxPerc, this.employmentScoreTemplate.score);
    this.scores.push(score);
    this.employmentScoreTemplate.minPerc=this.employmentScoreTemplate.maxPerc;
    this.employmentScoreTemplate.maxPerc=100;
    this.employmentScoreTemplate.score=0;
  }

}
