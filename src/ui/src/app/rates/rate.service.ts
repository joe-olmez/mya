import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { CurrencyWrapper } from '../model/currency.wrapper';
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

  async getRateList(): Promise<Observable<CurrencyRate[]>> {
    let url = this.ratesURL;
    return this.http.get<CurrencyRate[]>(url, this.headerObj);
  }

  async createRate(rate: CurrencyRate): Promise<Observable<Object>> {
    let url = this.ratesURL;
    return this.http.post(url, rate, this.headerObj);
  }

  async getRateById(id: number): Promise<Observable<CurrencyRate>> {
    let url = this.ratesURL + `/${id}`;
    console.log('Response update url: ', url);
    return this.http.get<CurrencyRate>(url, this.headerObj);
  }

  async updateRate(
    id: number,
    rate: CurrencyRate
  ): Promise<Observable<Object>> {
    let url = this.ratesURL + `/${id}`;
    return this.http.put(url, rate, this.headerObj);
  }

  async deleteRate(id: number): Promise<Observable<Object>> {
    let url = this.ratesURL + `/${id}`;
    return this.http.delete(url, this.headerObj);
  }

  async getConvertedAmount(curWrapper: CurrencyWrapper) {
    let url = this.ratesURL + `/convert`;
    console.log('Convert url: ', url);
    return this.http.post<string>(url, curWrapper, this.headerObj);
  }

  async updateRatesByLastMonth() {
    let url = this.ratesURL + `/update/last`;
    return this.http.get(url, this.headerObj);
  }
}
