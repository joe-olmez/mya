import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { CurrencyRate } from './currency.rate';

@Injectable({
  providedIn: 'root',
})
export class RateService {
  private ratesURL = environment.ratesUrl;

  constructor(private http: HttpClient) {}

  getRateList(): Observable<CurrencyRate[]> {
    let url = this.ratesURL;
    return this.http.get<CurrencyRate[]>(url);
  }

  createRate(rate: CurrencyRate): Observable<Object> {
    let url = this.ratesURL;
    return this.http.post(url, rate);
  }

  getRateById(id: number): Observable<CurrencyRate> {
    let url = this.ratesURL + `/${id}`;
    return this.http.get<CurrencyRate>(url);
  }

  updateRate(id: number, rate: CurrencyRate): Observable<Object> {
    let url = this.ratesURL + `/${id}`;
    console.log('-----------------------------------url:', url);
    return this.http.put(url, rate);
  }

  deleteRate(id: number): Observable<Object> {
    let url = this.ratesURL + `/${id}`;
    return this.http.delete(url);
  }
}
