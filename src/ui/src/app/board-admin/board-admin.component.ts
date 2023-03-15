import { Component, OnInit } from '@angular/core';
import { CurrencyWrapper } from '../model/currency.wrapper';
import { User } from '../model/user';
import { RateService } from '../rates/rate.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-board-admin',
  templateUrl: './board-admin.component.html',
  styleUrls: ['./board-admin.component.css'],
})
export class BoardAdminComponent implements OnInit {
  content = '';
  users: User[] = [];
  user: User;
  showUsersTab = false;
  showConvertorTab = false;
  showCurrencyTab = false;

  curWrapper = new CurrencyWrapper();
  result = '0.0';

  constructor(
    private userService: UserService,
    private rateService: RateService
  ) {}

  ngOnInit(): void {
    this.content = 'Admin Dashboard';
  }

  async getAllUsers() {
    this.showTab(true, false, false);
    (await this.userService.getAllUsers()).subscribe((data) => {
      this.users = data;
    });
  }

  goToConverter() {
    this.showTab(false, true, false);
  }

  async updateRates() {
    this.showTab(false, false, true);
    (await this.rateService.updateRatesByLastMonth()).subscribe(() => {
      console.log('Updated rates!');
      alert('Updated! To see up-to-date rates, click the Currencies tab');
    });
  }

  showTab(showUser: boolean, showConvertor: boolean, showCurrency: boolean) {
    this.showUsersTab = showUser;
    this.showConvertorTab = showConvertor;
    this.showCurrencyTab = showCurrency;
  }

  async convert() {
    if (this.curWrapper.date != null) {
      (await this.rateService.getConvertedAmount(this.curWrapper)).subscribe({
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

  async updateUser(user: User) {
    (await this.userService.updateUser(this.user)).subscribe({
      next: (d) => {
        console.log('Updated');
        this.getAllUsers();
      },
      error: (resError) => console.error(resError),
      complete: () => console.info('complete'),
    });
  }

  deleteUser(user: User) {
    this.userService.deleteUser(user).subscribe((data) => {
      console.log(data);
      this.getAllUsers();
    });
  }
}
