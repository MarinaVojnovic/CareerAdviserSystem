import { Component, OnInit } from '@angular/core';
import { ProfessionalField } from 'src/app/model/professional-field';
import { Preference } from 'src/app/model/preference';
import { FormControl, FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ProfessionService } from 'src/app/service/profession.service';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { MessageBoxComponent } from 'src/app/user/message-box/message-box.component';

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


  constructor(private modalService : NgbModal, private fb: FormBuilder, private professionService : ProfessionService, public activeModal : NgbActiveModal) { 
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
        // alert("successfully submited");
        // this.activeModal.close();
        const modalRef = this.modalService.open(MessageBoxComponent);
        modalRef.componentInstance.success= true;
        modalRef.componentInstance.message='Profession activity successfully added.';
        }
      }),
      (error => {
        //console.log('some error happend :)');
        //alert(error.error.message);
        const modalRef = this.modalService.open(MessageBoxComponent);
        modalRef.componentInstance.success= false;
        modalRef.componentInstance.message=''+error.error.message;
      })
    );
  }

}
