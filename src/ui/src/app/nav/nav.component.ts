import { Component } from '@angular/core';
import { AuthService } from './../services/auth.service';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css'],
})
export class NavComponent {
  showAdmin = false;
  isLoggedIn = false;
  userLabel!: string | null;
  token: string;

  constructor(private authService: AuthService) {}

  ngOnInit() {
    this.token = this.authService.getToken();
    console.log('Token: ', this.token);

    // this.showAdmin = this.authService.isAdmin();
    this.isLoggedIn = this.authService.isLogged();
    // this.userLabel = this.authService.getUsername();
  }

  public onLogout() {
    this.authService.logout();
    window.location.reload();
  }
}
