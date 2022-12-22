import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { User } from './../../model/user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  user: User = new User();
  isLoggedIn = false;

  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit(): void {
    if (this.authService.getToken()) {
      this.isLoggedIn = true;
    }
  }

  async onSubmit(loginForm: NgForm) {
    const formUser = loginForm.value;

    (await this.authService.login(formUser)).subscribe({
      next: (resData) => {
        this.storeToken(resData);
        this.reloadPage();
      },
      error: (resError) => console.error(resError),
      complete: () => console.info('complete'),
    });

    this.router.navigateByUrl('/profile');
  }

  async storeToken(resData: any) {
    this.authService.saveToken(resData.data);
    this.authService.saveUser(resData.user);
  }

  async reloadPage() {
    window.location.reload();
  }
}
