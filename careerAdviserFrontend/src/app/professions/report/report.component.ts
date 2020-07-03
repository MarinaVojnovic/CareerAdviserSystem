import { Component, OnInit } from '@angular/core';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { TestsDoneInDayDto } from 'src/app/model/testsDoneInDayDto';
import { ProfessionService } from 'src/app/service/profession.service';
import { MessageBoxComponent } from 'src/app/user/message-box/message-box.component';

@Component({
  selector: 'app-report',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.css']
})
export class ReportComponent implements OnInit {

  reports : TestsDoneInDayDto[];
  constructor(private modalService : NgbModal, private professionService : ProfessionService, public activeModal : NgbActiveModal) { }

  ngOnInit(): void {
    this.getReports();

  }

  getReports(){
    this.professionService.getReports().subscribe(
      (response => {
        if (response !== null) {
          this.reports = response;
         
        }
      }),
      (error => {
        //alert(error.error.message);
        const modalRef = this.modalService.open(MessageBoxComponent);
        modalRef.componentInstance.success= false;
        modalRef.componentInstance.message=''+error.error.message;
      })
    );
  }

  submit(){

  }

}
