import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfessionalFieldFormComponent } from './professional-field-form.component';

describe('ProfessionalFieldFormComponent', () => {
  let component: ProfessionalFieldFormComponent;
  let fixture: ComponentFixture<ProfessionalFieldFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProfessionalFieldFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProfessionalFieldFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
