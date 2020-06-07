import { Component, OnInit } from '@angular/core';
import { Profession } from 'src/app/model/profession';
import { Preference } from 'src/app/model/preference';
import { Trait } from 'src/app/model/trait';
import { ProfessionalField } from 'src/app/model/professional-field';
import { FormControl, FormBuilder, Validators, FormGroup } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { ProfessionService } from 'src/app/service/profession.service';
import { NgbModal, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { ProfessionalFieldFormComponent } from '../professional-field-form/professional-field-form.component';
import { AddProfessionActivityComponent } from '../add-profession-activity/add-profession-activity.component';
import { PersonalityTestService } from 'src/app/service/personality-test.service';
import { TraitSelected } from 'src/app/model/trait-selected';
import { HttpEventType, HttpResponse } from '@angular/common/http';
import { PersonalityTraitFormComponent } from 'src/app/personality/personality-trait-form/personality-trait-form.component';

@Component({
  selector: 'app-profession-form',
  templateUrl: './profession-form.component.html',
  styleUrls: ['./profession-form.component.css']
})
export class ProfessionFormComponent implements OnInit {

  public profession : Profession = {
    id : 1,
    name: '',
    activities : new Array<Preference>(),
    traits : new Array<Trait>(),
    description : '',
    isActive : true,
    payment : 0,
    employment: 0,
    image: 'some image'
  }

  traitsList : Array<Trait>;
  traitsSelected : Array<TraitSelected>
  selectedTraits : Array<String> = [];
  field : ProfessionalField;
  nameCtrl: FormControl;
  activitiesCtrl : FormControl;
  traitsCtrl : FormControl;
  fieldCtrl : FormControl;
  descriptionCtrl : FormControl;
  isActiveCtrl : FormControl;
  paymentCtrl : FormControl;
  employmentCtrl : FormControl;
  imageCtrl : FormControl;
  showActivities : Boolean;
  
  public form: FormGroup;

  professionalFields : Array<ProfessionalField>;

  activities : Array<Preference>;

  selectedFiles: FileList;
  currentFileUpload: File;
  progress: { percentage: number } = { percentage: 0 };
  selectedFile = null;
  changeImage = false;


  constructor(public activeModal : NgbActiveModal, private modalService: NgbModal,private fb: FormBuilder, public professionService : ProfessionService) { 
    this.nameCtrl = this.fb.control([this.profession.name, Validators.required])
    this.activitiesCtrl = this.fb.control([this.profession.activities, Validators.required])
    this.traitsCtrl = this.fb.control([this.profession.traits, Validators.required])
    this.fieldCtrl = this.fb.control([this.field, Validators.required])
    this.descriptionCtrl = this.fb.control([this.profession.description, Validators.required])
    this.isActiveCtrl = this.fb.control([this.profession.isActive, Validators.required])
    this.paymentCtrl = this.fb.control([this.profession.payment, Validators.required])
    this.employmentCtrl = this.fb.control([this.profession.employment, Validators.required, Validators.min(0), Validators.max(100)])
    this.imageCtrl = this.fb.control([this.profession.image, Validators.required]),
   

    this.form = this.fb.group({
      name: this.nameCtrl,
      activities: this.activitiesCtrl,
      traits: this.traitsCtrl,
      field: this.fieldCtrl,
      description: this.descriptionCtrl,
      isActive: this.isActiveCtrl,
      payment: this.paymentCtrl,
      employment: this.employmentCtrl,
      image: this.imageCtrl
  
    });
  }

  ngOnInit(): void {
    this.getFields();
    this.getTraits();
  }

  addProfession(){

    console.log('adddingg profession');
  }

  getActivities(){
    this.professionService.getActivitiesByField(this.field).subscribe(
      (response => {
        if (response !== null) {
          this.activities = response;
          console.log('activities size:'+this.activities.length);  
        }
      }),
      (error => {
        alert(error.error.message);
      })
    );
  }

  public getFields(){

    this.professionService.getAllFields().subscribe(
      (response => {
        if (response !== null) {
          this.professionalFields = response;
          console.log('profession fields size:'+this.professionalFields.length);  
        }
      }),
      (error => {
        alert(error.error.message);
      })
    );
  }

  public addField(){
   // let modalRef = this.modalService.open(ProfessionalFieldFormComponent);
    var that = this;
    this.modalService.open(ProfessionalFieldFormComponent).result.then(
      function () {
        that.getFields();
        console.log('AFTER FUNCTION FINISIHED');
      }
    );
  }

  public addTrait(){
    var that = this;
    this.modalService.open(PersonalityTraitFormComponent).result.then(
      function () {
        that.getTraits();
        console.log('AFTER FUNCTION FINISIHED');
      }
    );
  }

  changeProfessionField(event) {
    this.showActivities=true;
    this.getActivities();
  }
  public submit(){
    this.upload();
    for (let st of this.selectedTraits){
      for (let trait of this.traitsList){
        if (trait.target==st){
          this.profession.traits.push(trait);
        }
      }
    }

    alert(this.profession.traits.length);
    console.log(this.profession.traits);
    console.log(this.profession.traits.values);
  
    this.professionService.createProfession(this.profession).subscribe(
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

  addActivity(){
    var that = this;
    console.log('add activity called');
    let modalRef = this.modalService.open(AddProfessionActivityComponent);
    modalRef.componentInstance.activity.field=this.field;
    modalRef.result.then(
      function () {
        that.getActivities();
        console.log('AFTER FUNCTION FINISIHED');
      }
    );
  }

  handleChange($event){
    console.log(event.target);
    console.log('RADIO BUTTON CHANGED');
    
  }

  getTraits(){
    console.log('get traits called');
    this.professionService.getTraits().subscribe(
      (response => {
        if (response !== null) {
          this.traitsList = response;
          console.log('traits size:'+this.traitsList.length);  
          for (let trait of this.traitsList){
            //let traitSelected = new TraitSelected(trait.id, trait.personalityField, "");
            //this.traitsSelected.push(traitSelected);
            this.selectedTraits.push("");
            console.log('selected trait added');
          }
        }
      }),
      (error => {
        alert(error.error.message);
      })
    );
  }

  selectFile(event) {
    this.selectedFiles = event.target.files;
    }

  upload() {
    console.log('UPLOAD CALLED');
    console.log(this.selectedFiles.item(0).name);
    this.profession.image=this.selectedFiles.item(0).name;
    this.progress.percentage = 0;
    this.currentFileUpload = this.selectedFiles.item(0);
    console.log(this.currentFileUpload);
    this.professionService.uploadFile(this.currentFileUpload).subscribe(event => {
    if (event.type === HttpEventType.UploadProgress) {
    this.progress.percentage = Math.round(100 * event.loaded / event.total);
    } else if (event instanceof HttpResponse) {
    alert('File Successfully Uploaded');
    }
    this.selectedFiles = undefined;
    }
    );
    }

  
}



