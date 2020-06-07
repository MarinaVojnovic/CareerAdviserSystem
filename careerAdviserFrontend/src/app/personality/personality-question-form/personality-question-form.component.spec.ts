import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PersonalityQuestionFormComponent } from './personality-question-form.component';

describe('PersonalityQuestionFormComponent', () => {
  let component: PersonalityQuestionFormComponent;
  let fixture: ComponentFixture<PersonalityQuestionFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PersonalityQuestionFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PersonalityQuestionFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
