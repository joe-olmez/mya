import { Component } from '@angular/core';
import { CurrencyWrapper } from '../model/currency.wrapper';
import { RateService } from '../rates/rate.service';

@Component({
  selector: 'app-board-user',
  templateUrl: './board-user.component.html',
  styleUrls: ['./board-user.component.css'],
})
export class BoardUserComponent {
  curWrapper = new CurrencyWrapper();
  result: number = 0.0;

  constructor(private rateServise: RateService) {}

  ngOnInit(): void {}

  async convert() {
    console.log('Wrapper info:', this.curWrapper);
    this.rateServise.getConvertedAmount(this.curWrapper).subscribe({
      next: (data) => {
        this.result = data;
        console.log('Response data:', this.result);
      },
      error: (resError) => console.error(resError),
      complete: () => console.info('complete'),
    });
  }

  clearVal() {
    // TODO
  }
}
