import { Component, OnInit } from '@angular/core';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Trait } from 'src/app/model/trait';
import { ProfessionService } from 'src/app/service/profession.service';
import { TraitQuestion } from 'src/app/model/trait-question';
import { FormControl, FormGroup, FormBuilder, Validators } from '@angular/forms';
import { PersonalityService } from 'src/app/service/personality.service';
import { TraitQuestionDto } from 'src/app/model/trait-question-dto';
import { PersonalityTraitFormComponent } from '../personality-trait-form/personality-trait-form.component';

@Component({
  selector: 'app-personality-question-form',
  templateUrl: './personality-question-form.component.html',
  styleUrls: ['./personality-question-form.component.css']
})
export class PersonalityQuestionFormComponent implements OnInit {

  public traitsList : Array<Trait>;
  public traits : Array<Trait>=[];

  public id : number = -1;
  public target : string = '';
  public chooseTarget : Boolean;
  personalityField : string='';
  public targetOne : String;
  public targetTwo : String;
  public text : string='';
  public personalityFieldCtrl: FormControl;
  public textCtrl : FormControl;

  public form: FormGroup;

  constructor(private personalityService : PersonalityService, private fb: FormBuilder, private professionService : ProfessionService, public activeModal : NgbActiveModal, public modalService : NgbModal) {
    this.personalityFieldCtrl = this.fb.control([this.personalityField, Validators.required]),
    this.textCtrl = this.fb.control([this.text, Validators.required])

    this.form = this.fb.group({
      personalityField: this.personalityFieldCtrl,
      text: this.textCtrl
  
    });
   }

  ngOnInit(): void {
    this.getTraits();
   
  }

  getTraits(){
    console.log('get traits called');
    this.professionService.getTraits().subscribe(
      (response => {
        if (response !== null) {
          this.traitsList = response;

          for (var _i = 0; _i < this.traitsList.length; _i++) {
            var trait = this.traitsList[_i];
            if (_i%2==0){
              this.traits.push(trait);
            }
          }
          console.log('traits size:'+this.traitsList.length);  
        
        }
      }),
      (error => {
        alert(error.error.message);
      })
    );
  }

  addQuestion(){

  }

  changePersonalityField($event){

  }

  addField(){
    var that = this;
    this.modalService.open(PersonalityTraitFormComponent
    ).result.then(
      function () {
        that.getTraits();
        
      }
    );
  }

  addedPersonalityField($event){
    this.target='';
    this.chooseTarget=true;
    for (var _i = 0; _i < this.traitsList.length; _i++) {
      var trait = this.traitsList[_i];
      if (trait.personalityField==this.personalityField){
        this.targetOne=trait.target;
        this.targetTwo=this.traitsList[_i+1].target;
        break;
      }
    }
  }

  submit(){

    if (this.text=='' || this.personalityField==''||this.target==''){
      alert('All fields must be filled!');
    }else {

    
    let traitQuestion : TraitQuestion = new TraitQuestion();
    traitQuestion.text =this.text;
    traitQuestion.isActive=true;
    let trait : Trait = new Trait();
    trait.personalityField=this.personalityField;
    trait.target=this.target;
    traitQuestion.trait=trait;
    traitQuestion.id=this.id;

    this.personalityService.createTraitQuestion(traitQuestion).subscribe(
      (response => {
        if (response !== null) {
          
         this.activeModal.close();
          
        }
      }),
      (error => {
        alert(error.error.message);
      })
    );
  }

  }
}
