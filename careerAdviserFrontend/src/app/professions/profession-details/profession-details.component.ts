import { Component, OnInit } from '@angular/core';
import { Profession } from 'src/app/model/profession';
import { NgbModal, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { CurrentUser } from 'src/app/model/current-user';
import { applySourceSpanToExpressionIfNeeded } from '@angular/compiler/src/output/output_ast';
import { ProfessionService } from 'src/app/service/profession.service';
import { TraitsResultService } from 'src/app/service/traits-result.service';
import { Matching } from 'src/app/model/matching';
import { Trait } from 'src/app/model/trait';
import { Preference } from 'src/app/model/preference';
import { CheckedPreference } from 'src/app/model/checkedPreference';
import { OwnedTraits } from 'src/app/model/ownedTraits';
import { ProfessionFormComponent } from '../profession-form/profession-form.component';
import { MessageBoxComponent } from 'src/app/user/message-box/message-box.component';

@Component({
  selector: 'app-profession-details',
  templateUrl: './profession-details.component.html',
  styleUrls: ['./profession-details.component.css']
})
export class ProfessionDetailsComponent implements OnInit {

  profession : Profession;
  loggedUser : CurrentUser;

  editAllowed : boolean;

  showMatching : Boolean = false;
  professionTestDone : Boolean;
  personalityTestDone : Boolean;

  matching : Matching;

  matchingTraits : Array<Trait>;
  matchingPreferences : Array<Preference>;
  check : Boolean = false;
  checkedPreferences : CheckedPreference[]=[];
  ownedTraits : OwnedTraits[]=[];
  constructor( private modalService: NgbModal, public activeModal: NgbActiveModal, private professionService : ProfessionService, private traitsResultService : TraitsResultService) { }

  ngOnInit(): void {
    this.loggedUser = JSON.parse(
      localStorage.getItem('currentUser'));
      if (this.loggedUser!=null){
        if (this.loggedUser.userRoleName=='ROLE_USER'){
          this.checkTestDone();
          this.editAllowed=false;
        }else{
          this.editAllowed=true;
        }
      }
  }

  activateProfession(){

    this.professionService.activateProfession(this.profession.id).subscribe(
      (response => {
        if (response !== null) {
       // alert("Successfully activated profession.");
        const modalRef = this.modalService.open(MessageBoxComponent);
        modalRef.componentInstance.success= true;
        modalRef.componentInstance.message='Profession successfully activated.';
        this.activeModal.close();
        location.reload();
        }
      }),
      (error => {
   
       // alert(error.error.message);
        const modalRef = this.modalService.open(MessageBoxComponent);
        modalRef.componentInstance.success= false;
        modalRef.componentInstance.message=''+error.error.message;
      })
    );
  }

  editProfession(){
   

    let selectedTraits : Array<String> = [];
    for (let trait of this.profession.traits){
      selectedTraits.push(trait.target);
    }

    const modalRef = this.modalService.open(ProfessionFormComponent);
    modalRef.componentInstance.profession = this.profession;
    modalRef.componentInstance.oldImage = this.profession.image;
    modalRef.componentInstance.existingTraits=selectedTraits;
    modalRef.componentInstance.activities=this.profession.activities;
    modalRef.componentInstance.field=this.profession.activities[0].field;
    modalRef.componentInstance.showActivities=false;
    modalRef.componentInstance.edit=true;
  }

  deleteProfession(){
    this.professionService.deleteProfession(this.profession.id).subscribe(
      (response => {
        if (response !== null) {
        //alert("Successfully deleted profession.");
        //this.activeModal.close();
        //location.reload();
        const modalRef = this.modalService.open(MessageBoxComponent);
        modalRef.componentInstance.success= true;
        modalRef.componentInstance.message='Profession successfully deleted.';
        this.activeModal.close();
        location.reload();
        }
      }),
      (error => {
   
        //alert(error.error.message);
        const modalRef = this.modalService.open(MessageBoxComponent);
        modalRef.componentInstance.success= false;
        modalRef.componentInstance.message=''+error.error.message;
      })
    );
  }


  getMatching (){
    this.professionService.getMatchingTraitsAndPreferences(this.profession.id).subscribe(
      (response => {
        if (response !== null) {
          this.matching = response;
          this.matchingTraits=response.traits;
          this.matchingPreferences=response.preferences;
          this.getCheckedPreferences();
          this.getOwnedTraits();
         
         
        }
      }),
      (error => {

        //alert('error');
        const modalRef = this.modalService.open(MessageBoxComponent);
        modalRef.componentInstance.success= false;
        modalRef.componentInstance.message='Error happend.';
       
      })
    );
   
  }

  getCheckedPreferences(){
    for (let preference of this.profession.activities){
     
      if (preference.isActive==true){
        let found = false;
        for (let pref of this.matchingPreferences){
          if (preference.id==pref.id){
            found = true;
            break;
          }
          
        }
        let checkedPref = new CheckedPreference(preference.description, found);
        this.checkedPreferences.push(checkedPref);
      }
   
      
  

    }

 
  }

  getOwnedTraits(){
    for (let trait of this.profession.traits){
      let found = false;
      for (let t of this.matchingTraits){
        if (trait.target==t.target){
         found = true;
         break;
        }
       
      }
      let ownedTrait = new OwnedTraits(trait.personalityField+"-"+trait.target, found);
      this.ownedTraits.push(ownedTrait);

    }


  }

  checkTestDone() {
    this.professionService.isProfessionTestDone().subscribe(
      (response => {
        if (response !== null) {
          this.professionTestDone = response;
          this.traitsResultService.isPersonalityTestDone().subscribe(
            (response => {
              if (response !== null) {
                this.personalityTestDone = response;
                this.getMatching();
              }
            }),
            (error => {
      
              //alert('error');
              const modalRef = this.modalService.open(MessageBoxComponent);
              modalRef.componentInstance.success= false;
              modalRef.componentInstance.message='Error happened.';
             
            })
          );
         
        }
      }),
      (error => {

        //alert('error');
        const modalRef = this.modalService.open(MessageBoxComponent);
        modalRef.componentInstance.success= false;
        modalRef.componentInstance.message=''+error.error.message;
      
      })
    );
  
  }

  /*
  checkPersonalityTestDone(){

    this.traitsResultService.isPersonalityTestDone().subscribe(
      (response => {
        if (response !== null) {
          this.personalityTestDone = response;
         
        }
      }),
      (error => {

        alert('error');
      })
    );
  }
  */

 

}
