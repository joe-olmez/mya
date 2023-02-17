import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SigninRequest } from '../model/signin.request';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  request = new SigninRequest();
  isLoggedIn = false;

  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit(): void {
    if (this.authService.getToken()) {
      this.isLoggedIn = true;
    }
  }

  async signinUser() {
    (await this.authService.login(this.request)).subscribe({
      next: (resData) => {
        console.log('*AUTH RESPONSE TOKEN: ', resData);
        this.authService.setToken(resData);
        this.goToHome();
        //this.reloadPage();
      },
      error: (resError) => console.error(resError),
      complete: () => console.info('complete'),
    });

    this.router.navigateByUrl('/home');
  }

  onSubmit() {
    console.log(this.request);
    this.signinUser();
  }

  async reloadPage() {
    window.location.reload();
  }

  goToHome() {
    this.router.navigateByUrl('/home');
  }
}
