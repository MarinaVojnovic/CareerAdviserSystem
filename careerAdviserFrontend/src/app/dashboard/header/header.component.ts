import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { PersonalityResultsComponent } from 'src/app/personality/personality-results/personality-results.component';
import { ProfessionResultsComponent } from 'src/app/professions/profession-results/profession-results.component';
import { PersonalityTestComponent } from 'src/app/personality/personality-test/personality-test.component';
import { ProfessionsTestComponent } from 'src/app/professions/professions-test/professions-test.component';
import { CandidateProfessionsComponent } from 'src/app/professions/candidate-professions/candidate-professions.component';
import { ProfessionFormComponent } from 'src/app/professions/profession-form/profession-form.component';
import { PersonalityTraitFormComponent } from 'src/app/personality/personality-trait-form/personality-trait-form.component';
import { PersonalityQuestionsComponent } from 'src/app/personality/personality-questions/personality-questions.component';
import { LoginComponent } from 'src/app/user/login/login.component';
import { CurrentUser } from 'src/app/model/current-user';
import { ProfessionService } from 'src/app/service/profession.service';
import { PersonalityService } from 'src/app/service/personality.service';
import { TraitsResult } from 'src/app/model/traits-result';
import { TraitsResultService } from 'src/app/service/traits-result.service';
import { applySourceSpanToExpressionIfNeeded } from '@angular/compiler/src/output/output_ast';
import { EmploymentScoreFormComponent } from 'src/app/professions/employment-score-form/employment-score-form.component';
import { ReportComponent } from 'src/app/professions/report/report.component';
import { DeletedProfessionsComponent } from 'src/app/professions/deleted-professions/deleted-professions.component';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  loggedUser: CurrentUser;
  @Input() loggedIn: boolean;
  professionTestDone : Boolean = false;
  personalityTestDone : Boolean = false;
  
  constructor(public router: Router, private modalService: NgbModal, private personalityService : PersonalityService, private professionService : ProfessionService, private traitsResultService : TraitsResultService) { }

  ngOnInit(): void {

    this.loggedUser = JSON.parse(
      localStorage.getItem('currentUser'));

      if (this.loggedUser!=null){
         this.checkProfessionTestDone();
        this.checkPersonalityTestDone();
      }
   

  }

  newPersonalityTest(){
    this.personalityService.newPersonalityTest().subscribe(
      (response => {
        if (response !== null) {
         alert('new test is allowed');
        }
      }),
      (error => {

        alert('new test cannot be done');
      })
    );
  }

  checkProfessionTestDone(){
    this.professionService.isProfessionTestDone().subscribe(
      (response => {
        if (response !== null) {
          this.professionTestDone = response;
          //alert(this.personalityTestDone);
        }
      }),
      (error => {

        alert('error');
      })
    );

  }

  checkPersonalityTestDone(){

    this.traitsResultService.isPersonalityTestDone().subscribe(
      (response => {
        if (response !== null) {
          this.personalityTestDone = response;
          //alert(this.personalityTestDone);
        }
      }),
      (error => {

        alert('error');
      })
    );
  }
  logout(){
  
   
    this.loggedIn = false;
    localStorage.removeItem('currentUser');
    this.router.navigate(['/dashboard']);
    location.reload();
    
  }

  login(){
    this.loggedIn=true;
    const modalRef = this.modalService.open(LoginComponent);
  }

  personalityResults(){
    const modalRef = this.modalService.open(PersonalityResultsComponent);
  }

  professionResults(){
    const modalRef = this.modalService.open(ProfessionResultsComponent);
  }

  personalityTest(){
    const modalRef = this.modalService.open(PersonalityTestComponent);
  }

  professionTest(){
    const modalRef = this.modalService.open(ProfessionsTestComponent);
  }

  candidateProfessions(){
    const modalRef = this.modalService.open(CandidateProfessionsComponent);
  }

  addProfession(){
    const modalRef = this.modalService.open(ProfessionFormComponent);
    modalRef.componentInstance.edit=false;
  }

  addPersonalityTrait(){
    const modalRef = this.modalService.open(PersonalityTraitFormComponent);
  }

  personalityQuestions(){
    const modalRef = this.modalService.open(PersonalityQuestionsComponent);
  }

  employmentScoreForm(){
    const modalRef = this.modalService.open(EmploymentScoreFormComponent);
  }

  report(){
    const modalRef = this.modalService.open(ReportComponent);
  }

  deletedProfessions(){
    const modalRef = this.modalService.open(DeletedProfessionsComponent);
  }
}
