import { Component, OnInit } from '@angular/core';
import { UserDto } from 'src/app/model/userDto';
import { FormControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Router } from '@angular/router';
import { UserService } from 'src/app/service/user.service';
import { MessageBoxComponent } from '../message-box/message-box.component';

@Component({
  selector: 'app-register-user',
  templateUrl: './register-user.component.html',
  styleUrls: ['./register-user.component.css']
})
export class RegisterUserComponent implements OnInit {

  public userDto : UserDto={
    name : "",
    surname : "",
    username: "",
    email : "",
    password : ""
  }

  nameCtrl: FormControl;
  surnameCtrl: FormControl;
  emailCtrl: FormControl;
  public form: FormGroup;
  
  constructor(private modalService:NgbModal, private router: Router, private fb: FormBuilder, public activeModal : NgbActiveModal, public userService : UserService) { 
    this.nameCtrl = this.fb.control([this.userDto.name, Validators.required]);
    this.surnameCtrl = this.fb.control([this.userDto.surname, Validators.required]);
    this.emailCtrl = this.fb.control([this.userDto.email, Validators.required]);

    this.form = this.fb.group({
      name: this.nameCtrl,
      surname: this.surnameCtrl,
      email: this.emailCtrl
  
    });

  }

  ngOnInit(): void {
  }

  register(){
    if (this.userDto.name==''||this.userDto.surname==''||this.userDto.email==''){
    //  alert('All fields must be filled!');
      const modalRef = this.modalService.open(MessageBoxComponent);
      modalRef.componentInstance.success= false;
      modalRef.componentInstance.message='All fields must be filled!';
    }else {

    
    this.userDto.username=this.userDto.email;
    this.userService.registerUser(this.userDto).subscribe(
      (response => {
        if (response !== null) {
          if (response==true){
            //alert("Successfully registered user.");
            //this.activeModal.close();
            const modalRef = this.modalService.open(MessageBoxComponent);
            modalRef.componentInstance.success= true;
            modalRef.componentInstance.message="Successfully registered user.";
            this.activeModal.close();
          }else {
            //alert("User with given email address already exists.");
            const modalRef = this.modalService.open(MessageBoxComponent);
            modalRef.componentInstance.success= false;
            modalRef.componentInstance.message="User with given email address already exists.";
          }
         
        }
      }),
      (error => {
        console.log('some error happend :)');
        //alert(error.error.message);
        const modalRef = this.modalService.open(MessageBoxComponent);
        modalRef.componentInstance.success= false;
        modalRef.componentInstance.message=""+error.error.message;
      })
    );
  }

}

}
