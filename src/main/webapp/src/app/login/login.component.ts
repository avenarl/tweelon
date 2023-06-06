import { Component } from '@angular/core';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  user: any = {};

  constructor(private userService: UserService) {}

  login() {
    this.userService.login(this.user).subscribe(
      (user) => console.log('User logged in successfully!'),
      (error) => console.log('Error logging in', error)
    );
  }
}
