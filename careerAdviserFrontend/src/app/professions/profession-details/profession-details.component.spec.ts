import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfessionDetailsComponent } from './profession-details.component';

describe('ProfessionDetailsComponent', () => {
  let component: ProfessionDetailsComponent;
  let fixture: ComponentFixture<ProfessionDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProfessionDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProfessionDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
