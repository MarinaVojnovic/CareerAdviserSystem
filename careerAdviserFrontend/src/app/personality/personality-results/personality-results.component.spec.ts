import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PersonalityResultsComponent } from './personality-results.component';

describe('PersonalityResultsComponent', () => {
  let component: PersonalityResultsComponent;
  let fixture: ComponentFixture<PersonalityResultsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PersonalityResultsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PersonalityResultsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
