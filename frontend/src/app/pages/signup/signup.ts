import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { Auth } from '../../services/auth';

@Component({
  selector: 'app-signup',
  imports: [FormsModule],
  templateUrl: './signup.html',
  styleUrl: './signup.scss',
})
export class Signup {
  email = '';
  password = '';
  errorMessage = '';

  constructor(private router: Router, private authService : Auth) {}

  onSignup() {
    this.authService.signup(this.email, this.password).subscribe({
      next: (response) => {
        console.log('Signup successful:', response);
        alert('Account created successfully! Please log in.');
        this.router.navigate(['/login']);
      },
      error: (error) => {
        console.error('Signup failed:', error);
        this.errorMessage = 'Failed to create account.';
      }
    });
  }
}
