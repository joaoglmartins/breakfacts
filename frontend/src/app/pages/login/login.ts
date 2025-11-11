import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { Auth } from '../../services/auth';

@Component({
  selector: 'app-login',
  imports: [FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.scss',
})
export class Login {
  email = '';
  password = '';
  errorMessage = '';

  constructor(private router: Router, private authService : Auth) {}

  onLogin() {
    this.authService.login(this.email, this.password).subscribe({
      next: (response) => {
        if (response.token) {
          this.authService.saveToken(response.token);
          alert('Login successful!');
          this.router.navigate(['/']);
        }
      },
      error: (error) => {
        console.error('Login failed:', error);
        this.errorMessage = 'Invalid email or password.';
      }
    });
  }
}
