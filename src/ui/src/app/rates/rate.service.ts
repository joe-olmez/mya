import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { AuthService } from '../services/auth.service';
import { CurrencyRate } from './currency.rate';

@Injectable({
  providedIn: 'root',
})
export class RateService {
  private ratesURL = environment.ratesUrl;
  private headerObj;

  constructor(private http: HttpClient, private authService: AuthService) {
    this.headerObj = this.authService.getHeaderObject();
  }

  getRateList(): Observable<CurrencyRate[]> {
    let url = this.ratesURL;
    return this.http.get<CurrencyRate[]>(url, this.headerObj);
  }

  createRate(rate: CurrencyRate): Observable<Object> {
    let url = this.ratesURL;
    return this.http.post(url, rate);
  }

  getRateById(id: number): Observable<CurrencyRate> {
    let url = this.ratesURL + `/${id}`;
    console.log('Response update url: ', url);
    return this.http.get<CurrencyRate>(url, this.headerObj);
  }

  updateRate(id: number, rate: CurrencyRate): Observable<Object> {
    let url = this.ratesURL + `/${id}`;
    return this.http.put(url, rate);
  }

  deleteRate(id: number): Observable<Object> {
    let url = this.ratesURL + `/${id}`;
    return this.http.delete(url);
  }
}
