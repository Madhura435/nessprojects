import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { CreateCarComponent } from './create-car/create-car.component';
import { CarListComponent } from './car-list/car-list.component';
import { HttpClientModule } from "@angular/common/http";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { CarRoutingModule } from './car-routing.module';
import { CarTypeComponent } from './car-type/car-type.component';
import { GetCarComponent } from './get-car/get-car.component';
import { UpdateCarComponent } from './update-car/update-car.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { MatDialogModule } from '@angular/material/dialog';
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { DialogDeleteComponent } from "./dialog-delete/dialog-delete.component"
import { MatCheckboxModule } from '@angular/material/checkbox'

@NgModule({
  declarations: [
    AppComponent,
    CreateCarComponent,
    CarListComponent,
    CarTypeComponent,
    GetCarComponent,
    UpdateCarComponent,
    PageNotFoundComponent,
    DialogDeleteComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    CarRoutingModule,
    MatDialogModule,
    BrowserAnimationsModule,
    MatCheckboxModule
  ],
  providers: [],
  entryComponents: [
    DialogDeleteComponent,GetCarComponent
  ],
  bootstrap: [AppComponent],

})
export class AppModule { }
