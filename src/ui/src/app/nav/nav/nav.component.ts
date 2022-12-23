import { AuthService } from './../../services/auth.service';
import { Component } from '@angular/core';

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
    this.isLoggedIn = this.authService.isLoggedIn();
    this.userLabel = this.authService.getUsername();
  }

  public onLogout() {
    this.authService.logout();
    window.location.reload();
  }
}
