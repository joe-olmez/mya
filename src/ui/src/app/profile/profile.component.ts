import { Component, OnInit } from '@angular/core';
import { User } from '../model/user';
import { AuthService } from './../services/auth.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css'],
})
export class ProfileComponent implements OnInit {
  curUser: User;

  constructor(private authService: AuthService) {}

  async ngOnInit() {
    this.getUser();
  }

  async getUser() {
    (await this.authService.getCurrentUser()).subscribe({
      next: (resData) => {
        this.curUser = resData;
      },
      error: (resError) => console.error(resError),
      complete: () => console.info('complete'),
    });
  }
}
