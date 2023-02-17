import { Component } from '@angular/core';
import { CurrencyData } from '../model/currency-data';
import { CurrencyService } from './../services/currency.service';

@Component({
  selector: 'app-currency-list',
  templateUrl: './currency-list.component.html',
  styleUrls: ['./currency-list.component.css'],
})
export class CurrencyListComponent {
  infos: CurrencyData[] = [];

  constructor(private currencyService: CurrencyService) {}

  ngOnInit(): void {
    this.currencyService.getCurrencyData().subscribe({
      next: (resData) => {
        this.infos = resData;
        console.log('Response currency data: ', this.infos);
      },
      error: (resError) => console.error(resError),
      complete: () => console.info('complete'),
    });
  }
}
