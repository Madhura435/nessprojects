import { Component, OnInit, Inject } from '@angular/core';
import { Car } from "../car"
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms'
import { CarService } from '../car.service';
import { ActivatedRoute, Router } from '@angular/router'
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { cardata, CarListComponent } from '../car-list/car-list.component';
@Component({
  selector: 'app-get-car',
  templateUrl: './get-car.component.html',
  styleUrls: ['./get-car.component.css']
})
export class GetCarComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) public data: cardata, private carService: CarService, private router: Router, private route: ActivatedRoute) { }

  carId: String;
  colour: String;
  car: Car;

  ngOnInit(): void {
    // this.carId = this.route.snapshot.params['id'];
    // this.colour = this.route.snapshot.params['colour'];
    console.log(this.data);
    this.carId = this.data.carId;
    this.colour = this.data.carColour;
    this.getCar(this.carId, this.colour);
  }


  getCar(carid: String, colour: String) {
    this.carService.getCar(carid, colour).subscribe(data => {
      this.car = data;
      console.log(data);
    });

  }


}
