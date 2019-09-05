import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FarmacolistComponent } from './farmacolist/farmacolist.component';

import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

import { FormsModule } from '@angular/forms';

import { HttpClientModule } from '@angular/common/http';
import { PersonlistComponent } from './personlist/personlist.component';
import { MedicalexaminationlistComponent } from './medicalexaminationlist/medicalexaminationlist.component';

@NgModule({
  declarations: [
    AppComponent,
    FarmacolistComponent,
    PersonlistComponent,
    MedicalexaminationlistComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
