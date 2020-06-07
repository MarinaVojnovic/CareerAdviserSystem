import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PersonalityTraitFormComponent } from './personality-trait-form.component';

describe('PersonalityTraitFormComponent', () => {
  let component: PersonalityTraitFormComponent;
  let fixture: ComponentFixture<PersonalityTraitFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PersonalityTraitFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PersonalityTraitFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
