import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { JwtAuthenticationRequest } from '../model/jwt-authentication-request';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private readonly urlBase = 'http://localhost:8080/auth';
  constructor(private http: HttpClient) { }

  submitTraitQuestion(request : JwtAuthenticationRequest) : Observable<any>{
    return this.http.post<JwtAuthenticationRequest>(`${this.urlBase}`+'/login', request);
  }
}
