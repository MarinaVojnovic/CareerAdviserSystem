import { Component, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { JwtAuthenticationRequest } from 'src/app/model/jwt-authentication-request';
import { AuthenticationService } from 'src/app/security/authentication-service.service';
import { Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public authRequest : JwtAuthenticationRequest={
    username: "",
    password: ""
  }

  usernameCtrl: FormControl;
  passwordCtrl: FormControl;
  public form: FormGroup;

  constructor( private router: Router, private fb: FormBuilder, public activeModal : NgbActiveModal, public authenticationService : AuthenticationService) {
    this.usernameCtrl = this.fb.control([this.authRequest.username, Validators.required]);
    this.passwordCtrl = this.fb.control([this.authRequest.password, Validators.required]);

    this.form = this.fb.group({
      username: this.usernameCtrl,
      password: this.passwordCtrl
  
    });
   }

  ngOnInit(): void {
  }

  login(){
    console.log('login called');
    console.log(this.authRequest.username);
    console.log(this.authRequest.password);

   if (this.authRequest.username=='' || this.authRequest.password==''){
    alert('Username and password are not allowed to be null!');
   }else {
    this.authenticationService.login(this.authRequest.username, this.authRequest.password).subscribe(
      (loggedIn) => {
        console.log("logged in");
        location.reload();
        this.router.navigate(['/dashboard']);
      }
    ,
    (err:Error) => {
      var errResponse = err as HttpErrorResponse;
      if (errResponse.status == 404){
        alert('Wrong password or username');
      }else if (errResponse.status==403){
        alert('Access forbidden, try again in 3 minutes');
      }
    });
   }

  
  }
}
