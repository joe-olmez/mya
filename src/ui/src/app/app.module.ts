import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BoardAdminComponent } from './board-admin/board-admin.component';
import { BoardUserComponent } from './board-user/board-user.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { NavComponent } from './nav/nav.component';
import { ProfileComponent } from './profile/profile.component';
import { UpdatePasswordComponent } from './profile/update-password/update-password.component';
import { UpdateProfileComponent } from './profile/update-profile/update-profile.component';
import { CreateRateComponent } from './rates/create-rate/create-rate.component';
import { RateDetailsComponent } from './rates/rate-details/rate-details.component';
import { RateListComponent } from './rates/rate-list/rate-list.component';
import { UpdateRateComponent } from './rates/update-rate/update-rate.component';
import { RegisterComponent } from './register/register.component';
import { authInterceptorProviders } from './services/auth.interceptor';
import { UpdateUserComponent } from './board-admin/update-user/update-user.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    ProfileComponent,
    BoardAdminComponent,
    BoardUserComponent,
    NavComponent,
    UpdateProfileComponent,
    UpdatePasswordComponent,
    RateListComponent,
    UpdateRateComponent,
    CreateRateComponent,
    RateDetailsComponent,
    UpdateUserComponent,
  ],
  imports: [BrowserModule, AppRoutingModule, FormsModule, HttpClientModule],
  providers: [authInterceptorProviders],
  bootstrap: [AppComponent],
})
export class AppModule {}
