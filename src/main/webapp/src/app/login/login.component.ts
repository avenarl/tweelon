import { Component } from '@angular/core';
import { UserService } from '../services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  user: any = {};

  constructor(private userService: UserService, private router: Router) {}

  login() {
    this.userService.login(this.user).subscribe(
      (user) => {
        console.log('User logged in successfully!');
        this.router.navigate(['/tweet']);
      },
      (error) => console.log('Error logging in', error)
    );
  }
}
