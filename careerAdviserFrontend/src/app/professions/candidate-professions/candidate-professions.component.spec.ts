import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CandidateProfessionsComponent } from './candidate-professions.component';

describe('CandidateProfessionsComponent', () => {
  let component: CandidateProfessionsComponent;
  let fixture: ComponentFixture<CandidateProfessionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CandidateProfessionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CandidateProfessionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
