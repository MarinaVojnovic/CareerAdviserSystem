import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EmploymentScoreFormComponent } from './employment-score-form.component';

describe('EmploymentScoreFormComponent', () => {
  let component: EmploymentScoreFormComponent;
  let fixture: ComponentFixture<EmploymentScoreFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EmploymentScoreFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EmploymentScoreFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
