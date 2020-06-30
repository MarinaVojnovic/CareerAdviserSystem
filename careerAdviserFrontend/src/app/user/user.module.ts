import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login/login.component';
import { MatButtonModule } from '@angular/material/button';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { RegisterAdminComponent } from './register-admin/register-admin.component';
import { RegisterUserComponent } from './register-user/register-user.component';

@NgModule({
  declarations: [LoginComponent, RegisterAdminComponent, RegisterUserComponent],
  imports: [
    CommonModule,
    MatButtonModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule
  ],
  exports: [
    LoginComponent
  ]
})
export class UserModule { }
