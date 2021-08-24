import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common"
import { RouterModule, Routes } from "@angular/router";
import { CarListComponent } from './car-list/car-list.component'
import { CreateCarComponent } from './create-car/create-car.component'
import { CarTypeComponent } from './car-type/car-type.component'
import { GetCarComponent } from './get-car/get-car.component'
import { UpdateCarComponent } from './update-car/update-car.component'
import { PageNotFoundComponent } from './page-not-found/page-not-found.component'

const routes: Routes =
    [
        { path: 'carlist/:type', component: CarListComponent },
        { path: 'car-type', component: CarTypeComponent },
        { path: 'create-car', component: CreateCarComponent },
        { path: 'getCar/:id/:colour', component: GetCarComponent },
        { path: 'updateCar/:id/:colour', component: UpdateCarComponent },
        { path: '', redirectTo: 'car-type', pathMatch: 'full' },
        { path: '**', component: PageNotFoundComponent }
    ]

@NgModule(
    {
        declarations: [],
        imports: [
            CommonModule,
            RouterModule.forRoot(routes),
        ],
        exports: [RouterModule]

    }
)
export class CarRoutingModule {

}

