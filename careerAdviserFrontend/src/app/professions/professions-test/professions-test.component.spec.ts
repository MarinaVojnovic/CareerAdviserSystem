import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfessionsTestComponent } from './professions-test.component';

describe('ProfessionsTestComponent', () => {
  let component: ProfessionsTestComponent;
  let fixture: ComponentFixture<ProfessionsTestComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProfessionsTestComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProfessionsTestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
