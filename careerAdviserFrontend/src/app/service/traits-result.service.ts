import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TraitsResult } from '../model/traits-result';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TraitsResultService {
 
  private readonly urlBase = 'http://localhost:8080/traitsResult';
  constructor(private http: HttpClient) { }

  getTraitsResult(): Observable<TraitsResult> {
    console.log('get traits result service called');
    return this.http.get<TraitsResult>(this.urlBase);
  }

  isPersonalityTestDone() : Observable<any>{
    return this.http.get<any>(`${this.urlBase}`+'/isTestDone');
  }
}
