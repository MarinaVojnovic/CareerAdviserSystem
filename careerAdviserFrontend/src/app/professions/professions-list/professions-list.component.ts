import { Component, OnInit } from '@angular/core';
import { Profession } from 'src/app/model/profession';
import { ProfessionService } from 'src/app/service/profession.service';
import { NgbModule, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ProfessionDetailsComponent } from '../profession-details/profession-details.component';
import { MessageBoxComponent } from 'src/app/user/message-box/message-box.component';


@Component({
  selector: 'app-professions-list',
  templateUrl: './professions-list.component.html',
  styleUrls: ['./professions-list.component.css']
})
export class ProfessionsListComponent implements OnInit {

  public professions: Profession[] =[];
  constructor(private professionService: ProfessionService, private modalService: NgbModal) { }

  ngOnInit(): void {
    this.getProfessions();
  }

  showPicture(image : string) : string{
   
    return "http://localhost:8080/" + image;
  }

  showDetails(prof){
    console.log('profession id: '+prof.id);
    console.log(prof.description);
    const modalRef = this.modalService.open(ProfessionDetailsComponent);
    modalRef.componentInstance.profession = prof;
  }


  getProfessions() {
   
    console.log('uslo u get professions');
    this.professionService.allProfessions().subscribe(
      (response => {
        if (response !== null) {
          this.professions = response;
          console.log('professions size:'+this.professions.length);
          /*
          this.professions.push(this.professions[0]);
          this.professions.push(this.professions[0]);
          this.professions.push(this.professions[0]);
          this.professions.push(this.professions[0]);
          console.log(this.professions[0].image);
          */
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

}
