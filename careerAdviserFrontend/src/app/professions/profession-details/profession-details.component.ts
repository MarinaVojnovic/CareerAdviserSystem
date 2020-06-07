import { Component, OnInit } from '@angular/core';
import { Profession } from 'src/app/model/profession';
import { NgbModal, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-profession-details',
  templateUrl: './profession-details.component.html',
  styleUrls: ['./profession-details.component.css']
})
export class ProfessionDetailsComponent implements OnInit {

  profession : Profession;


  constructor(public activeModal: NgbActiveModal) { }

  ngOnInit(): void {
  }

 

}
