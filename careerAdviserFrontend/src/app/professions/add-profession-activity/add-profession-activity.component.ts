import { Component, OnInit } from '@angular/core';
import { ProfessionalField } from 'src/app/model/professional-field';
import { Preference } from 'src/app/model/preference';
import { FormControl, FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ProfessionService } from 'src/app/service/profession.service';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-add-profession-activity',
  templateUrl: './add-profession-activity.component.html',
  styleUrls: ['./add-profession-activity.component.css']
})
export class AddProfessionActivityComponent implements OnInit {

  public activity : Preference = {
    id : -1,
    description: '',
    field : null,
    isActive : true
  }

  descriptionCtrl: FormControl;
  public form: FormGroup;


  constructor(private fb: FormBuilder, private professionService : ProfessionService, public activeModal : NgbActiveModal) { 
    this.descriptionCtrl = this.fb.control([this.activity.description, Validators.required]);

    this.form = this.fb.group({
      description: this.descriptionCtrl
  
    });
  }

  ngOnInit(): void {
  }

  submit(){
    console.log('PROFESSIONAL FIELD');
    console.log(this.activity.field);
    this.professionService.submitActivity(this.activity).subscribe(
      (response => {
        if (response !== null) {
         alert("successfully submited");
         this.activeModal.close();
        }
      }),
      (error => {
        console.log('some error happend :)');
        alert(error.error.message);
      })
    );
  }

}
