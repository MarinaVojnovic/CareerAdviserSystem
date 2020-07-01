import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { PersonalityService } from 'src/app/service/personality.service';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Trait } from 'src/app/model/trait';

@Component({
  selector: 'app-personality-trait-form',
  templateUrl: './personality-trait-form.component.html',
  styleUrls: ['./personality-trait-form.component.css']
})
export class PersonalityTraitFormComponent implements OnInit {

  public trait1 : Trait = {
    id : -1,
    personalityField: '',
    target: ''
  }

  public trait2 : Trait = {
    id : -1,
    personalityField: '',
    target: ''
  }

  personalityFieldCtrl: FormControl;
  targetCtrl1: FormControl;
  targetCtrl2: FormControl;
  
  public form: FormGroup;

  constructor(private fb: FormBuilder, private personalityService : PersonalityService, public activeModal : NgbActiveModal) {
    this.personalityFieldCtrl = this.fb.control([this.trait1.personalityField, Validators.required]);
    this.targetCtrl1= this.fb.control([this.trait1.target, Validators.required]);
    this.targetCtrl2= this.fb.control([this.trait1.target, Validators.required]);

    this.form = this.fb.group({
      personalityField: this.personalityFieldCtrl,
      target1: this.targetCtrl1,
      target2: this.targetCtrl2
  
    });
   }

  ngOnInit(): void {
  }

  submit(){
    if (this.trait1.personalityField=='' || this.trait1.target=='' || this.trait2.target=='' || this.trait2.personalityField==''){
      alert('All fields must be filled!');
    }else {

    
    this.trait2.personalityField=this.trait1.personalityField;
    this.personalityService.createTrait(this.trait1).subscribe(
      (response => {
        if (response !== null) {
         
         this.personalityService.createTrait(this.trait2).subscribe(
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
      }),
      (error => {
 
        alert(error.error.message);
      })
    );
  }
}

}
