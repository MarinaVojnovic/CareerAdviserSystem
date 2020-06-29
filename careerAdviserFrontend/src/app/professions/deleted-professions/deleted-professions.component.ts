import { Component, OnInit } from '@angular/core';
import { Profession } from 'src/app/model/profession';
import { ProfessionService } from 'src/app/service/profession.service';
import { NgbModal, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { ProfessionDetailsComponent } from '../profession-details/profession-details.component';

@Component({
  selector: 'app-deleted-professions',
  templateUrl: './deleted-professions.component.html',
  styleUrls: ['./deleted-professions.component.css']
})
export class DeletedProfessionsComponent implements OnInit {

  public professions: Profession[] =[];

  constructor(private professionService: ProfessionService, private modalService: NgbModal, private activeModal : NgbActiveModal) { }

  ngOnInit(): void {
    this.getDeletedProfessions();
  }

  getDeletedProfessions(){
   
    this.professionService.getAllDeleted().subscribe(
      (response => {
        if (response !== null) {
          this.professions = response;
          console.log('professions size:'+this.professions.length);
         
        }
      }),
      (error => {
        alert(error.error.message);
      })
    );
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

  close(){
    this.activeModal.close();
  }


}
