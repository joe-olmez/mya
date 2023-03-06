import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CurrencyRate } from '../currency.rate';
import { RateService } from '../rate.service';

@Component({
  selector: 'app-create-rate',
  templateUrl: './create-rate.component.html',
  styleUrls: ['./create-rate.component.css'],
})
export class CreateRateComponent implements OnInit {
  rate: CurrencyRate = new CurrencyRate();

  constructor(private rateService: RateService, private router: Router) {}

  ngOnInit(): void {}

  saveRate() {
    this.rateService.createRate(this.rate).subscribe(
      (data) => {
        console.log(data);
        this.goToRateList();
      },
      (error) => console.log(error)
    );
  }

  goToRateList() {
    this.router.navigate(['/rates']);
  }

  onSubmit() {
    console.log(this.rate);
    this.saveRate();
  }
}
