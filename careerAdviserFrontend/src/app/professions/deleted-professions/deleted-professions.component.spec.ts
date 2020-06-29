import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeletedProfessionsComponent } from './deleted-professions.component';

describe('DeletedProfessionsComponent', () => {
  let component: DeletedProfessionsComponent;
  let fixture: ComponentFixture<DeletedProfessionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeletedProfessionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeletedProfessionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
