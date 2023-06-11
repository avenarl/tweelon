import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { TweetComponent } from './tweet/tweet.component';

const routes: Routes = [
  { path: '', component: RegisterComponent },
  { path: 'login', component: LoginComponent },
  { path: 'tweet', component: TweetComponent },
  { path: '**', redirectTo: '' },
];

@NgModule({
  declarations: [],
  imports: [CommonModule, RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
