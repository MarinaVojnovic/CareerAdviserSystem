import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { TraitQuestionResult } from '../model/trait-question-result';
import { Observable } from 'rxjs';
import { Trait } from '../model/trait';

@Injectable({
  providedIn: 'root'
})
export class PersonalityTestService {

  private readonly urlBase = 'http://localhost:8080/personality';
  private readonly urlBase2 = 'http://localhost:8080/traitsResult';
  

  constructor(private http: HttpClient) { }

  getQuestionsForUser():Observable<Array<TraitQuestionResult>> {
    console.log('pozvana servisna metoda dobavi pitanja personality za usera');
    return this.http.get<Array<TraitQuestionResult>>(`${this.urlBase}`+'/getAllQuestionsForUser');
  }

  submitTraitQuestion(res : Array<TraitQuestionResult>) : Observable<any>{
    return this.http.post<Array<TraitQuestionResult>>(`${this.urlBase2}`+'/submitTraitQuestions', res);
  }

  
  
}
