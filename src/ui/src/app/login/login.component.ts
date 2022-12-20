import { AppComponent } from './../app.component';
import { Router, NavigationStart } from '@angular/router';
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
  browserRefresh = false;

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

    (await this.authService.login(formUser)).subscribe({
      next: (resData) => {
        console.log('Response data:', resData);
        this.tokenStorage.saveToken(resData.data);
        this.tokenStorage.saveUser(resData.user);
      },
      error: (resError) => console.error(resError),
      complete: () => console.info('complete'),
    });

    //await this.reloadPage();
    //TODO go to currency list page
    this.router.navigateByUrl('/profile');
  }

  async reloadPage() {
    window.location.reload();
  }

  refresh() {
    this.router.events.subscribe((event) => {
      if (event instanceof NavigationStart) {
        this.browserRefresh = !this.router.navigated;
        console.log('----inside');
      }
      console.log('----out');
    });
  }
}
