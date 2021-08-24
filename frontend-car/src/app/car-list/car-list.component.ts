import { Component, OnInit, Type } from '@angular/core';
import { CarService } from '../car.service';
import { Car } from "../car"
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms'
import { ActivatedRoute, Router } from '@angular/router'
import { GetCarComponent } from '../get-car/get-car.component';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DialogDeleteComponent } from '../dialog-delete/dialog-delete.component'

export interface cardata {
  carId: String;
  carColour: String;
}

@Component({
  selector: 'app-car-list',
  templateUrl: './car-list.component.html',
  styleUrls: ['./car-list.component.css']
})
export class CarListComponent implements OnInit {

  public cars: Car[];
  type: string;
  toggle: boolean= true;
  deleteresult: String;
  cardata: Array<Car> = [];
  update_delete: String;

  constructor(private carService: CarService, private router: Router, private route: ActivatedRoute,
    public dialog: MatDialog) 
    { 
      
    }

  ngOnInit(): void {
    this.type = this.route.snapshot.params['type'];
    this.getCars();
  }

  private getCars() {

    this.carService.getCarList(this.type).subscribe(data => {
      this.cars = data;
    });

  }


  getCar(id: String, colour: String) {
    this.dialog.open(GetCarComponent, {
      data: {
        carId: id,
        carColour: colour
      },

    });

    //this.router.navigate(['getCar', id, colour]);
  }


  update_DeleteCar(event, carhtml: Car) {

    if (event.target.checked) {
      this.cardata.push(carhtml);
      this.toggle=false;
    }
    else {
      var r = this.cardata.indexOf(carhtml);
      this.cardata.splice(r, 1);
      if(this.cardata.length>0) 
      {
        this.toggle=false;
      }
     else
     {
      this.toggle=true;
     }
    }

  }
  

  updateCar() {
    var l = this.cardata.length;
      if (l > 1) {
        alert("Please select single record multiple updates not possible " + l);
      }
    if (l == 1) {
      var car = this.cardata[0];
      this.router.navigate(['updateCar', car.carId, car.colour]);
    }
  }

  deleteCar() {
    var ld = this.cardata.length;
      let dialogRef = this.dialog.open(DialogDeleteComponent, {
        height: '400px',
        width: '600px',
      });

      dialogRef.afterClosed().subscribe(result => {
        this.deleteresult = `${result}`;
       
      for (let eachcar of this.cardata) {
        console.log(this.deleteresult);
        if (this.deleteresult === "true") {
          this.carService.deleteCar(eachcar.carId, eachcar.colour).subscribe(data => {
          });
        }
      }
      });
  }




}


