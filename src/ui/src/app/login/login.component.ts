import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { User } from './../../model/user';
import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { TokenStorageService } from '../services/token-storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  user: User = new User();

  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';

  constructor(
    private authService: AuthService,
    private tokenStorage: TokenStorageService,
    private router: Router
  ) {}

  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
    }
  }

  async onSubmit(loginForm: NgForm) {
    const formUser = loginForm.value;
    console.log('Form value: ', formUser);

    (await this.authService.login(formUser)).subscribe({
      next: (resData) => {
        console.log('Response data:', resData);
        this.tokenStorage.saveToken(resData);
        this.tokenStorage.saveUser(resData);
      },
      error: (resError) => console.error(resError),
      complete: () => console.info('complete'),
    });
    this.reloadPage();
    //TODO go to currency list page
    this.router.navigateByUrl('/home');
  }

  async reloadPage() {
    window.location.reload();
  }
}
