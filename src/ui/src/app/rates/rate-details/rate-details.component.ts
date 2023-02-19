import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CurrencyRate } from '../currency.rate';
import { RateService } from '../rate.service';

@Component({
  selector: 'app-rate-details',
  templateUrl: './rate-details.component.html',
  styleUrls: ['./rate-details.component.css'],
})
export class RateDetailsComponent implements OnInit {
  id!: number;
  rate!: CurrencyRate;

  constructor(
    private route: ActivatedRoute,
    private rateService: RateService
  ) {}

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];

    this.rate = new CurrencyRate();
    this.rateService.getRateById(this.id).subscribe((data) => {
      this.rate = data;
    });
  }
}
