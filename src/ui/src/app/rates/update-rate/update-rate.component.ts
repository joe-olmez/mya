import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CurrencyRate } from '../currency.rate';
import { RateService } from '../rate.service';

@Component({
  selector: 'app-update-rate',
  templateUrl: './update-rate.component.html',
  styleUrls: ['./update-rate.component.css'],
})
export class UpdateRateComponent implements OnInit {
  id!: number;
  rate: CurrencyRate = new CurrencyRate();

  constructor(
    private rateService: RateService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  async ngOnInit(): Promise<void> {
    this.id = this.route.snapshot.params['id'];

    (await this.rateService.getRateById(this.id)).subscribe({
      next: (data) => {
        this.rate = data;
      },
      error: (resError) => console.error(resError),
      complete: () => console.info('complete'),
    });
  }

  async onSubmit() {
    (await this.rateService.updateRate(this.id, this.rate)).subscribe({
      next: (data) => {
        console.log('Received data', data);
        this.goToRateList();
      },
      error: (resError) => console.error(resError),
      complete: () => console.info('complete'),
    });
  }

  goToRateList() {
    this.router.navigate(['/rates']);
  }
}
