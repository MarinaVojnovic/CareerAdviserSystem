import { Component, OnInit } from '@angular/core';
import { RecommendedProfessions } from 'src/app/model/recommended-professions';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ProfessionService } from 'src/app/service/profession.service';
import { ProfessionsSuitabilityList } from 'src/app/model/professions-suitability-list';
import { ProfessionDetailsComponent } from '../profession-details/profession-details.component';
import { Profession } from 'src/app/model/profession';
import { MessageBoxComponent } from 'src/app/user/message-box/message-box.component';

@Component({
  selector: 'app-candidate-professions',
  templateUrl: './candidate-professions.component.html',
  styleUrls: ['./candidate-professions.component.css']
})
export class CandidateProfessionsComponent implements OnInit {

  professionSuitabilityList : ProfessionsSuitabilityList;

  constructor(public activeModal: NgbActiveModal, public professionService : ProfessionService, public modalService : NgbModal) { }

  ngOnInit(): void {
    this.getRecommendedProfessionsByTraits();
    
  }

  getRecommendedProfessionsByTraits(){
    this.professionService.getRecommendedProfessionsByTraits().subscribe(
      (response => {
        if (response !== null) {
          this.professionSuitabilityList=response;
          console.log('number of questions: '+this.professionSuitabilityList.suitableProfessions.length);
        }
      }),
      (error => {
        console.log('some error happend in getting recommended professions by trait:)');
        //alert(error.error.message);
        const modalRef = this.modalService.open(MessageBoxComponent);
        modalRef.componentInstance.success= false;
        modalRef.componentInstance.message=''+error.error.message;
      })
    );

  }

  getRecommendedProfessionsByPreferences(){
    this.professionService.getRecommendedProfessionsByPreferences().subscribe(
      (response => {
        if (response !== null) {
          this.professionSuitabilityList=response;
          console.log(this.professionSuitabilityList.suitableProfessions.length);
        }
      }),
      (error => {
        console.log('some error happend in getting recommended professions by trait:)');
        //alert(error.error.message);
        const modalRef = this.modalService.open(MessageBoxComponent);
        modalRef.componentInstance.success= false;
        modalRef.componentInstance.message=''+error.error.message;
      })
    );
  }

  showPicture(image : string) : string{
   
    return "http://localhost:8080/" + image;
  }

  showDetails(prof){
    console.log('profession id: '+prof.id);
    console.log(prof.description);
   


    let profession : Profession;
    this.professionService.getProfessionById(prof.profession.id).subscribe(
      (response => {
        if (response !== null) {
          profession=response;
          const modalRef = this.modalService.open(ProfessionDetailsComponent);
          modalRef.componentInstance.profession = profession;
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

  close(){
    this.activeModal.close();
  }


}
