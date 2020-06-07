import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddProfessionActivityComponent } from './add-profession-activity.component';

describe('AddProfessionActivityComponent', () => {
  let component: AddProfessionActivityComponent;
  let fixture: ComponentFixture<AddProfessionActivityComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddProfessionActivityComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddProfessionActivityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
