import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard/dashboard.component';
import { ProfessionsListComponent } from './professions/professions-list/professions-list.component';
import { PersonalityResultsComponent } from './personality/personality-results/personality-results.component';



const routes: Routes = [
  {
    path: 'dashboard',
    component: DashboardComponent,
    children: [
      {
        path: 'professionsList',
        component: ProfessionsListComponent,
      },
      {
        path: 'personalityResults',
        component: PersonalityResultsComponent,
      }
    ]
   
  },
 
  {path: '**', redirectTo: 'dashboard'}
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
