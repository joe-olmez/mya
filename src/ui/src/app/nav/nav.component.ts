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

  constructor(private authService: AuthService) {}

  ngOnInit() {
    this.showAdmin = this.authService.isAdmin();
    this.isLoggedIn = this.authService.isLogged();
    this.userLabel = this.authService.getCurrentUserName();
  }

  public onLogout() {
    this.authService.logout();
    window.location.reload();
  }
}
