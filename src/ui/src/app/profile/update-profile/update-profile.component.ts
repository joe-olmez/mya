import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../../model/user';
import { AuthService } from './../../services/auth.service';
import { UserService } from './../../services/user.service';

@Component({
  selector: 'app-update-profile',
  templateUrl: './update-profile.component.html',
  styleUrls: ['./update-profile.component.css'],
})
export class UpdateProfileComponent implements OnInit {
  user = new User();

  constructor(
    private authService: AuthService,
    private userService: UserService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.getUser();
  }

  async getUser() {
    (await this.authService.getCurrentUser()).subscribe({
      next: (resData) => {
        this.user = resData;
      },
      error: (resError) => console.error(resError),
      complete: () => console.info('complete'),
    });
  }

  async onSubmit() {
    (await this.userService.updateUser(this.user)).subscribe({
      next: (resData) => {
        console.log('Response data:', resData);
        this.goToProfile();
      },
      error: (resError) => console.error(resError),
      complete: () => console.info('complete'),
    });
  }

  goToProfile() {
    this.router.navigateByUrl('/profile');
  }

  onBack() {
    this.goToProfile();
  }

  onUpdatePassword() {
    this.router.navigateByUrl('/update-password');
  }
}
