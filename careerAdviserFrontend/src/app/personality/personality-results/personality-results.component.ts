import { Component, OnInit } from '@angular/core';
import { TraitsResult } from 'src/app/model/traits-result';
import { TraitsResultService } from 'src/app/service/traits-result.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-personality-results',
  templateUrl: './personality-results.component.html',
  styleUrls: ['./personality-results.component.css']
})
export class PersonalityResultsComponent implements OnInit {

  traitsResult : TraitsResult;
  
  constructor(private traitsResultService : TraitsResultService, private modalService: NgbModal) { }

  ngOnInit(): void {
    this.getTraitsResult();
  }

  getTraitsResult(){
    console.log('get traits result called');
    this.traitsResultService.getTraitsResult().subscribe(
      (response => {
        if (response !== null) {
          this.traitsResult = response;
          this.traitsResultView();
          console.log('RESPONSE IS NOT NULLLLL');
        }
      }),
      (error => {
        alert(error.error.message);
      })
    );
  }

  traitsResultView(){
    if (this.traitsResult.extraverted==0){
      this.traitsResult.extraverted=1;
    }
    if (this.traitsResult.introverted==0){
      this.traitsResult.introverted=1;
    }
    if (this.traitsResult.assertive==0){
      this.traitsResult.assertive=1;
    }
    if (this.traitsResult.turbulent==0){
      this.traitsResult.turbulent=1;
    }
    if (this.traitsResult.thinking==0){
      this.traitsResult.thinking=1;
    }
    if (this.traitsResult.feeling==0){
      this.traitsResult.feeling=1;
    }
    if (this.traitsResult.realist==0){
      this.traitsResult.realist=1;
    }
    if (this.traitsResult.visionary==0){
      this.traitsResult.visionary=1;
    }

    if (this.traitsResult.judging==0){
      this.traitsResult.judging=1;
    }
    if (this.traitsResult.prospecting==0){
      this.traitsResult.prospecting=1;
    }

  }

  
  close(){
    this.modalService.dismissAll();
  }
}


