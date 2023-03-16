import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UpdateProfileComponent } from './profile/update-profile/update-profile.component';

import { BoardAdminComponent } from './board-admin/board-admin.component';
import { UpdateUserComponent } from './board-admin/update-user/update-user.component';
import { BoardUserComponent } from './board-user/board-user.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { ProfileComponent } from './profile/profile.component';
import { UpdatePasswordComponent } from './profile/update-password/update-password.component';
import { CreateRateComponent } from './rates/create-rate/create-rate.component';
import { RateDetailsComponent } from './rates/rate-details/rate-details.component';
import { RateListComponent } from './rates/rate-list/rate-list.component';
import { UpdateRateComponent } from './rates/update-rate/update-rate.component';
import { RegisterComponent } from './register/register.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'update-profile', component: UpdateProfileComponent },
  { path: 'update-password', component: UpdatePasswordComponent },
  { path: 'user-board', component: BoardUserComponent },
  { path: 'admin-board', component: BoardAdminComponent },
  //
  { path: 'rates', component: RateListComponent },
  { path: 'create-rate', component: CreateRateComponent },
  { path: 'update-rate/:id', component: UpdateRateComponent },
  { path: 'rate-details/:id', component: RateDetailsComponent },
  //
  { path: 'update-user/:id', component: UpdateUserComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
