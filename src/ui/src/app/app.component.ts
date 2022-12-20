import { User } from './../model/user';
import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from './services/token-storage.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit {
  isLoggedIn = false;
  showAdminBoard = false;

  username?: string;

  constructor(private tokenStorageService: TokenStorageService) {}

  ngOnInit(): void {
    const jwt = this.tokenStorageService.getToken();
    if (jwt != null) {
      this.isLoggedIn = true;
      const user = this.tokenStorageService.getUser();
      console.log('******user in storage service', user);
      this.username = user.username;
      let role = user.userType;
      this.showAdminBoard = role == 'ADMIN' ? true : false;
    }
  }

  logout(): void {
    this.tokenStorageService.signOut();
    window.location.reload();
  }
}
