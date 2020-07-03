import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ProfessionService } from 'src/app/service/profession.service';
import { Criteriums } from 'src/app/model/criteriums';
import { RecommendedProfessions } from 'src/app/model/recommended-professions';
import { ProfessionDetailsComponent } from '../profession-details/profession-details.component';
import { Profession } from 'src/app/model/profession';
import { MessageBoxComponent } from 'src/app/user/message-box/message-box.component';

@Component({
  selector: 'app-profession-results',
  templateUrl: './profession-results.component.html',
  styleUrls: ['./profession-results.component.css']
})
export class ProfessionResultsComponent implements OnInit {

  personality : Boolean = false;
  preferences : Boolean = false;
  employment : Boolean = false;
  payment : Boolean = false;

  recommendedProfessions : RecommendedProfessions = new RecommendedProfessions();
 
 
  constructor(private modalService: NgbModal, private professionService : ProfessionService) { }

  ngOnInit(): void {
   
  }

  close(){
    this.modalService.dismissAll();
  }

  showResults(){
    console.log('SHOW RESULTS FROM COMPONENT CALLED');
   
    let criteriums : Criteriums = new Criteriums(this.personality, this.preferences, this.employment, this.payment);
    /*
    criteriums.personality=this.personality;
    criteriums.preferences=this.preferences;
    criteriums.employment=this.employment;
    criteriums.payment=this.payment;
    */
    //= new Criteriums(this.personality, this.preferences, this.employment, this.payment);
    this.professionService.showResults(criteriums).subscribe(
      (response => {
        if (response !== null) {
          this.recommendedProfessions=response;
         
        
        }
      }),
      (error => {
        console.log('ERROR HAPPENED');
       // alert(error.error.message);
        const modalRef = this.modalService.open(MessageBoxComponent);
        modalRef.componentInstance.success= false;
        modalRef.componentInstance.message=''+error.error.message;
      })

    );

  }

  showDetails(prof){
    console.log('SHOW DETAILS CALLED');
    console.log('profession id: '+prof.profession.id);
    console.log(prof.profession.description);
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

  showPicture(image : string) : string{
    console.log("show picture called: " + image);
    return "http://localhost:8080/" + image;
  }

}
