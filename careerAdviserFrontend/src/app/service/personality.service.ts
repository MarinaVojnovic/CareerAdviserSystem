import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Trait } from '../model/trait';
import { Observable } from 'rxjs';
import { TraitQuestion } from '../model/trait-question';
import { TraitQuestionDto } from '../model/trait-question-dto';

@Injectable({
  providedIn: 'root'
})
export class PersonalityService {

  private readonly urlBase = 'http://localhost:8080/personality';

  constructor(private http: HttpClient) { }

  createTrait(trait : Trait) : Observable<any>{
    return this.http.post<Trait>(`${this.urlBase}`+'/create', trait);
  }

  
  getTraitQuestions() : Observable<Array<TraitQuestion>>{
    return this.http.get<Array<TraitQuestion>>(`${this.urlBase}`+'/getTraitQuestions');
  }

  newPersonalityTest() : Observable<any>{
    return this.http.get<any>(`${this.urlBase}`+'/newTest');
  }

  createTraitQuestion(traitQuest : TraitQuestion) : Observable<any>{
    return this.http.post<TraitQuestion>(`${this.urlBase}`+'/createQuestion', traitQuest);
  }

  getByPersonalityField(persField : string) : Observable<Array<Trait>>{
    return this.http.get<Array<Trait>>(`${this.urlBase}`+'/getByPersonalityField/'+persField);
  }

  deleteTraitQuestion(traitQuestId : String) : Observable<any>{
    return this.http.delete<String>(`${this.urlBase}`+'/deleteQuestion/'+traitQuestId);
  }

  activateTraitQuestion(traitQuestId : String) : Observable<any>{
    return this.http.delete<String>(`${this.urlBase}`+'/activateQuestion/'+traitQuestId);
  }


}
