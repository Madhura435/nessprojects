import { Component, OnInit } from '@angular/core';
import { Car } from "../car"
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms'
import { Router } from '@angular/router'

@Component({
  selector: 'app-car-type',
  templateUrl: './car-type.component.html',
  styleUrls: ['./car-type.component.css']
})
export class CarTypeComponent implements OnInit {

  constructor(private router: Router) { }
  type: string;
  ngOnInit(): void {
  }

  onSubmit() {
    this.router.navigate(['carlist/', this.type]);
  }

}
