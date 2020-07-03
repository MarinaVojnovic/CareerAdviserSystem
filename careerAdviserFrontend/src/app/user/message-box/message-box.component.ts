import { Component, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-message-box',
  templateUrl: './message-box.component.html',
  styleUrls: ['./message-box.component.css']
})
export class MessageBoxComponent implements OnInit {
  success : boolean;
  message : string;

  constructor(public activeModal : NgbActiveModal) { }

  ngOnInit(): void {
  }

}
