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

@Component({
  selector: 'app-profession-details',
  templateUrl: './profession-details.component.html',
  styleUrls: ['./profession-details.component.css']
})
export class ProfessionDetailsComponent implements OnInit {

  profession : Profession;
  loggedUser : CurrentUser;

  showMatching : Boolean = false;
  professionTestDone : Boolean;
  personalityTestDone : Boolean;

  matching : Matching;

  matchingTraits : Array<Trait>;
  matchingPreferences : Array<Preference>;
  check : Boolean = false;
  checkedPreferences : CheckedPreference[]=[];
  ownedTraits : OwnedTraits[]=[];
  constructor(public activeModal: NgbActiveModal, private professionService : ProfessionService, private traitsResultService : TraitsResultService) { }

  ngOnInit(): void {
    this.loggedUser = JSON.parse(
      localStorage.getItem('currentUser'));
      if (this.loggedUser.userRoleName=='ROLE_USER'){
        this.checkTestDone();
      }
     
      
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

        alert('error');
       
      })
    );
   
  }

  getCheckedPreferences(){
    for (let preference of this.profession.activities){
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
      
              alert('error');
             
            })
          );
         
        }
      }),
      (error => {

        alert('error');
      
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
