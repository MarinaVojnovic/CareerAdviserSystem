import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PersonalityQuestionEditComponent } from './personality-question-edit.component';

describe('PersonalityQuestionEditComponent', () => {
  let component: PersonalityQuestionEditComponent;
  let fixture: ComponentFixture<PersonalityQuestionEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PersonalityQuestionEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PersonalityQuestionEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
