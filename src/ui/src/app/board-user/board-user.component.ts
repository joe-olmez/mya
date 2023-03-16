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
  result = '0.0';

  constructor(private rateServise: RateService) {}

  ngOnInit(): void {
    // ignorable
  }

  async convert() {
    if (this.curWrapper.date != null) {
      (await this.rateServise.getConvertedAmount(this.curWrapper)).subscribe({
        next: (data) => {
          this.result = data;
          console.log('Response data:', this.result);
        },
        error: (resError) => console.error(resError),
        complete: () => console.info('complete'),
      });
    }
  }

  reset() {
    console.log('Wrapper info:', this.curWrapper);
    this.curWrapper = new CurrencyWrapper();
    this.result = '0.0';
  }
}
