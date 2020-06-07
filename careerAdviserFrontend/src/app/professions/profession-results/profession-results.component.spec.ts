import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfessionResultsComponent } from './profession-results.component';

describe('ProfessionResultsComponent', () => {
  let component: ProfessionResultsComponent;
  let fixture: ComponentFixture<ProfessionResultsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProfessionResultsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProfessionResultsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
