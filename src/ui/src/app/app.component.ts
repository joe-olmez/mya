import { User } from './../model/user';
import { AuthService } from './services/auth.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit {
  isLoggedIn = false;
  isAdmin = false;
  username?: string;

  constructor(private authService: AuthService) {}

  ngOnInit(): void {
    const jwt = this.authService.getToken();
    if (jwt != null) {
      this.isLoggedIn = true;
      const user: User = this.authService.getCurrentUser();
      console.log('******User in storage service:', user);
      this.username = user.username;
      if (user.role == 'ADMIN') {
        this.isAdmin = true;
      }
    }
  }

  logout(): void {
    this.authService.signOut();
    window.location.reload();
  }
}
