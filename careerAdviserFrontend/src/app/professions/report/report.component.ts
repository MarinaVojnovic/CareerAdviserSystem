import { Component, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { TestsDoneInDayDto } from 'src/app/model/testsDoneInDayDto';
import { ProfessionService } from 'src/app/service/profession.service';

@Component({
  selector: 'app-report',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.css']
})
export class ReportComponent implements OnInit {

  reports : TestsDoneInDayDto[];
  constructor(private professionService : ProfessionService, public activeModal : NgbActiveModal) { }

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
        alert(error.error.message);
      })
    );
  }

  submit(){

  }

}
