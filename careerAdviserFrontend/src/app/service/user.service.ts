import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { JwtAuthenticationRequest } from '../model/jwt-authentication-request';
import { Observable } from 'rxjs';
import { UserDto } from '../model/userDto';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private readonly urlBase = 'http://localhost:8080/auth';
  constructor(private http: HttpClient) { }

  submitTraitQuestion(request : JwtAuthenticationRequest) : Observable<any>{
    return this.http.post<JwtAuthenticationRequest>(`${this.urlBase}`+'/login', request);
  }

  registerAdmin(userDto : UserDto) : Observable<any>{
    return this.http.post<UserDto>(`${this.urlBase}`+'/registerAdmin', userDto);
  }

  registerUser(userDto : UserDto) : Observable<any>{
    return this.http.post<UserDto>(`${this.urlBase}`+'/registerUser', userDto);
  }
}
