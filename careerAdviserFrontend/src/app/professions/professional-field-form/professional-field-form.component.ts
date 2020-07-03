import { Component, OnInit } from '@angular/core';
import { ProfessionalField } from 'src/app/model/professional-field';
import { FormControl, FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ProfessionService } from 'src/app/service/profession.service';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { MessageBoxComponent } from 'src/app/user/message-box/message-box.component';

@Component({
  selector: 'app-professional-field-form',
  templateUrl: './professional-field-form.component.html',
  styleUrls: ['./professional-field-form.component.css']
})
export class ProfessionalFieldFormComponent implements OnInit {

  public professionalField : ProfessionalField = {
    id : -1,
    name: '',
  }

  nameCtrl: FormControl;
  public form: FormGroup;
  constructor( private modalService :NgbModal, private fb: FormBuilder, private professionService : ProfessionService, public activeModal : NgbActiveModal) { 
    this.nameCtrl = this.fb.control([this.professionalField.name, Validators.required]);

    this.form = this.fb.group({
      name: this.nameCtrl
  
    });
  }

  ngOnInit(): void {
  }

  submit(){

    this.professionService.addingProfessionalField(this.professionalField).subscribe(
      (response => {
        if (response !== null) {
        // alert("successfully submited");
         //this.activeModal.close();
         const modalRef = this.modalService.open(MessageBoxComponent);
         modalRef.componentInstance.success= true;
         modalRef.componentInstance.message='Professional field successfully added.';
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

  

}
