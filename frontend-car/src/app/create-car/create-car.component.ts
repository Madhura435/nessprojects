import { Component, OnInit } from '@angular/core';
import { CarService } from '../car.service';
import { Car } from "../car"
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms'
import { ActivatedRoute, Router } from '@angular/router'
@Component({
  selector: 'app-create-car',
  templateUrl: './create-car.component.html',
  styleUrls: ['./create-car.component.css']
})
export class CreateCarComponent implements OnInit {
  car: Car = new Car();
  constructor(private carService: CarService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
  }
  createCar() {
    this.carService.createCar(this.car).subscribe(data => {
      console.log(data);
    });

  }


  onSubmit() {
    this.createCar();
    this.router.navigate(['car-type']);
  }

}
