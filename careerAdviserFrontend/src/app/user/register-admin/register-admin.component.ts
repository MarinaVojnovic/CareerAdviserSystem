import { Component, OnInit } from '@angular/core';
import { UserDto } from 'src/app/model/userDto';
import { FormControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AuthenticationService } from 'src/app/security/authentication-service.service';
import { UserService } from 'src/app/service/user.service';
import { MessageBoxComponent } from '../message-box/message-box.component';

@Component({
  selector: 'app-register-admin',
  templateUrl: './register-admin.component.html',
  styleUrls: ['./register-admin.component.css']
})
export class RegisterAdminComponent implements OnInit {

  public userDto : UserDto={
    name : "",
    surname : "",
    username: "",
    email : "",
    password : ""
  }

  nameCtrl: FormControl;
  surnameCtrl: FormControl;
  usernameCtrl : FormControl;
  emailCtrl: FormControl;
  passwordCtrl: FormControl;

  public form: FormGroup;

  constructor( private modalService : NgbModal, private router: Router, private fb: FormBuilder, public activeModal : NgbActiveModal, public userService : UserService) {

    this.nameCtrl = this.fb.control([this.userDto.name, Validators.required]);
    this.surnameCtrl = this.fb.control([this.userDto.surname, Validators.required]);
    this.usernameCtrl = this.fb.control([this.userDto.username, Validators.required]);
    this.emailCtrl = this.fb.control([this.userDto.email, Validators.required]);
    this.passwordCtrl = this.fb.control([this.userDto.password, Validators.required]);

    this.form = this.fb.group({
      name: this.nameCtrl,
      surname: this.surnameCtrl,
      username: this.usernameCtrl,
      email: this.emailCtrl,
      password: this.passwordCtrl
  
    });
   }

  ngOnInit(): void {
  
  }

  register(){

 

    if (this.userDto.name==''||this.userDto.surname==''||this.userDto.username==''||this.userDto.email==''||this.userDto.password==''){
      //alert('All fields must be filled!');
      const modalRef = this.modalService.open(MessageBoxComponent);
      modalRef.componentInstance.success= false;
      modalRef.componentInstance.message='All fields must be filled!';
    }else{

    
    this.userService.registerAdmin(this.userDto).subscribe(
      (response => {
        if (response !== null) {
          if (response==true){
           // alert("Successfully registered admin.");
            const modalRef = this.modalService.open(MessageBoxComponent);
            modalRef.componentInstance.success= true;
            modalRef.componentInstance.message="Successfully registered admin."
            this.activeModal.close();

          }else {
            //alert("Username taken. Choose another one.");
            const modalRef = this.modalService.open(MessageBoxComponent);
            modalRef.componentInstance.success= false;
            modalRef.componentInstance.message="Username taken. Choose another one."
          }
        
        }
      }),
      (error => {
        console.log('some error happend :)');
        //alert(error.error.message);
        const modalRef = this.modalService.open(MessageBoxComponent);
        modalRef.componentInstance.success= false;
        modalRef.componentInstance.message=""+error.eror.message;
      })
    );
  }

}
}
