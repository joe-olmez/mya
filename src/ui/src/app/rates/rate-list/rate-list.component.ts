import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CurrencyRate } from '../currency.rate';
import { RateService } from '../rate.service';

@Component({
  selector: 'app-rate-list',
  templateUrl: './rate-list.component.html',
  styleUrls: ['./rate-list.component.css'],
})
export class RateListComponent implements OnInit {
  rates: CurrencyRate[] = [];

  constructor(private rateService: RateService, private router: Router) {}

  ngOnInit(): void {
    this.getRates();
  }

  private getRates() {
    this.rateService.getRateList().subscribe((data) => {
      this.rates = data;
    });
  }

  rateDetails(id: number) {
    this.router.navigate(['rate-details', id]);
  }

  updateRate(id: number) {
    this.router.navigate(['update-rate', id]);
  }

  deleteRate(id: number) {
    this.rateService.deleteRate(id).subscribe((data) => {
      console.log(data);
      this.getRates();
    });
  }
}
