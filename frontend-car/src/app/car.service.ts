import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from "rxjs"
import { Car } from "./car"

@Injectable({
  providedIn: 'root'
})
export class CarService {

  private getListUrl = "http://localhost:8080/api/car/getCars"
  private createUrl = "http://localhost:8080//api/car/saveCar"
  private getCarUrl = "http://localhost:8080//api/car//getCar"
  private updateUrl = "http://localhost:8080/api/car//alterCar"
  private deleteUrl = "http://localhost:8080//api/car//delete"

  constructor(private httpClint: HttpClient) { }

  getCarList(type: String): Observable<Car[]> {
    return this.httpClint.get<Car[]>(`${this.getListUrl}/${type}`);
  }
  createCar(car: Car): Observable<Object> {
    return this.httpClint.post(`${this.createUrl}`, car);
  }
  getCar(carId: String, colour: String): Observable<Car> {
    return this.httpClint.get<Car>(`${this.getCarUrl}/${carId}/colour/${colour}`);
  }

  deleteCar(carId: String, colour: String): Observable<Object> {
    return this.httpClint.delete(`${this.deleteUrl}/${carId}/colour/${colour}`);
  }

  updateCar(carId: String, colour: String, car: Car): Observable<Object> {
    return this.httpClint.put(`${this.updateUrl}/${carId}/colour/${colour}`, car);
  }


}
