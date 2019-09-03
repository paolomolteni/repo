import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FarmacolistComponent } from './farmacolist/farmacolist.component';


const routes: Routes = [
  { path: 'medicine-list/:personId', component: FarmacolistComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
