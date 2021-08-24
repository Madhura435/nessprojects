import { Component, OnInit } from '@angular/core';
import { Car } from "../car"
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms'
import { CarService } from '../car.service';
import { ActivatedRoute, Router } from '@angular/router'
@Component({
  selector: 'app-update-car',
  templateUrl: './update-car.component.html',
  styleUrls: ['./update-car.component.css']
})
export class UpdateCarComponent implements OnInit {

  constructor(private carService: CarService, private router: Router, private route: ActivatedRoute) { }

  carId: String;
  colour: String;
  car: Car;

  ngOnInit(): void {
    this.carId = this.route.snapshot.params['id'];
    this.colour = this.route.snapshot.params['colour'];
    this.carService.getCar(this.carId, this.colour).subscribe(data => {
      this.car = data;
      console.log(data)
    },
      error => console.log(error)
    );
  }

  updateCar() {
    this.carService.updateCar(this.carId, this.colour, this.car).subscribe(data => {
      console.log(this.car);
    }

    )
  }

  onSubmit() {
    console.log(this.car);
    this.updateCar();
    this.router.navigate(['carlist/', this.car.type]);
  }

}
