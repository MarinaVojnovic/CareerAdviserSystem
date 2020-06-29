import { Injectable } from '@angular/core';
import {HttpClient, HttpEvent, HttpRequest} from "@angular/common/http";
import { Observable } from 'rxjs';
import { Profession } from '../model/profession';
import { RecommendedProfessions } from '../model/recommended-professions';
import { Criteriums } from '../model/criteriums';
import { PreferenceQuestionResult } from '../model/preference-question-result';
import { CandidateProfessionsComponent } from '../professions/candidate-professions/candidate-professions.component';
import { ProfessionsSuitabilityList } from '../model/professions-suitability-list';
import { ProfessionalField } from '../model/professional-field';
import { Preference } from '../model/preference';
import { Trait } from '../model/trait';
import { Matching } from '../model/matching';
import { EmploymentScoreTemplate } from '../model/employmentScore.Template';
import { TestsDoneInDayDto } from '../model/testsDoneInDayDto';

@Injectable({
  providedIn: 'root'
})
export class ProfessionService {

  private readonly urlBase = 'http://localhost:8080/professions';
  private readonly urlBase2 = 'http://localhost:8080/preferences';
  private readonly urlBase3 = 'http://localhost:8080/preferencesResult';
  private readonly urlBase4 = "http://localhost:8080/professionalField";
  private readonly urlBase5 = 'http://localhost:8080/personality';
  private readonly urlBase6 = 'http://localhost:8080/report';
  constructor(private http: HttpClient) { }

  allProfessions():Observable<Array<Profession>> {
    console.log('pozvana servisna metoda get professions');
    return this.http.get<Array<Profession>>(`${this.urlBase}`);
  }

  getAllDeleted():Observable<Array<Profession>> {
    console.log('pozvana servisna metoda get professions');
    return this.http.get<Array<Profession>>(`${this.urlBase}/getAllDeleted`);
  }

  showResults(criteriums) : Observable<RecommendedProfessions> {
    console.log('show results from service called');
    return this.http.post<RecommendedProfessions>(`${this.urlBase}/getResults`, criteriums);
  }

  getProfessionById(id: number): Observable<Profession> {
    console.log('GET PROFESSION BY ID CALLED SERVICE');
    return this.http.get<Profession>(`${this.urlBase}` + '/' + id);
  }

  deleteActivity(prefId: number): Observable<any> {
    
    return this.http.delete<any>(`${this.urlBase2}` + '/delete/' + prefId);
  }

  activateProfession(profId: number): Observable<any> {
    
    return this.http.get<any>(`${this.urlBase}` + '/activateProfession/' + profId);
  }

  deleteProfession(profId: number): Observable<any> {
    
    return this.http.delete<any>(`${this.urlBase}` + '/deleteProfession/' + profId);
  }

  activateActivity(prefId: number): Observable<any> {
    
    return this.http.get<any>(`${this.urlBase2}` + '/activate/' + prefId);
  }

  getQuestionsForUser():Observable<Array<PreferenceQuestionResult>> {
   
    return this.http.get<Array<PreferenceQuestionResult>>(`${this.urlBase2}`+'/getAllQuestionsForUser');
  }

  submitQuestions(res : Array<PreferenceQuestionResult>) : Observable<any>{
    return this.http.post<Array<PreferenceQuestionResult>>(`${this.urlBase3}`+'/submitPreferenceQuestions', res);
  }

  submitActivity(activity : Preference) : Observable<any>{
    return this.http.post<Preference>(`${this.urlBase2}`+'/create', activity);
  }

  editProfession(profession : Profession) : Observable<any>{
    return this.http.put<Profession>(`${this.urlBase}`+'/updateProfession', profession);
  }

  getRecommendedProfessionsByTraits() : Observable<ProfessionsSuitabilityList>{
    return this.http.get<ProfessionsSuitabilityList>(`${this.urlBase}`+'/getCandidatesByTraits');
  }

  getRecommendedProfessionsByPreferences() : Observable<ProfessionsSuitabilityList>{
    return this.http.get<ProfessionsSuitabilityList>(`${this.urlBase}`+'/getCandidatesByPreferences');
  }

  getAllFields() : Observable<Array<ProfessionalField>>{
    return this.http.get<Array<ProfessionalField>>(`${this.urlBase4}`);
  }

  addingProfessionalField(field : ProfessionalField) : Observable<any>{
    return this.http.post<ProfessionalField>(`${this.urlBase4}`+'/addField', field);
  }
 
  getActivitiesByField(field : ProfessionalField) : Observable<any>{
    return this.http.post<ProfessionalField>(`${this.urlBase2}`+'/findByField', field);
  }

  getTraits() : Observable<Array<Trait>>{
    return this.http.get<Array<Trait>>(`${this.urlBase5}`+'/getTraits');
  }

  isProfessionTestDone() : Observable<Boolean>{
    return this.http.get<Boolean>(`${this.urlBase}`+'/isTestDone');
  }

  createProfession(profession : Profession) : Observable<any> {
    return this.http.post<Profession>(`${this.urlBase}`+'/createProfession', profession);
  }

  uploadFile(file: File): Observable<HttpEvent<{}>> {
    const data: FormData = new FormData();
    data.append('file', file);
    const newRequest = new HttpRequest('POST', `${this.urlBase}`+'/uploadImage', data, {
    reportProgress: true,
    responseType: 'text'
    });
    return this.http.request(newRequest);
    }

    getMatchingTraitsAndPreferences(profId : number):Observable<Matching> {
   
      return this.http.get<Matching>(`${this.urlBase}`+'/getMatchingTraitsAndPreferences/'+`${profId}`);
    }

    addNewProfessionScores(scores : Array<EmploymentScoreTemplate>):Observable<any>{
      return this.http.post<Array<EmploymentScoreTemplate>>(`${this.urlBase}`+'/newEmploymentScoreTemplates', scores);
    }

    getReports() : Observable<Array<TestsDoneInDayDto>>{
      return this.http.get<Array<TestsDoneInDayDto>>(`${this.urlBase6}`+'/getReport');
    }


}
