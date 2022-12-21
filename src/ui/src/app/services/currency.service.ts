import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from './../../environments/environment';
import { Injectable } from '@angular/core';

const API_BASE_URL = environment.apiServerUrl; // http://localhost:5000

@Injectable({
  providedIn: 'root',
})
export class CurrencyService {
  constructor(private http: HttpClient) {}

  getCurrencyData(): Observable<any> {
    let url = API_BASE_URL + '/currency/all'; // http://localhost:5000//currency/all
    console.log('Currency url: ', url);
    return this.http.get(url);
  }
}
