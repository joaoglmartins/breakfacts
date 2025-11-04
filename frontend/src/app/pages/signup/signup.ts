import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-signup',
  imports: [FormsModule],
  templateUrl: './signup.html',
  styleUrl: './signup.scss',
})
export class Signup {
  username = '';
  password = '';

  constructor(private router: Router) {}

  onSignup() {
    // TODO: replace with real API call to backend later
    console.log('Signing up with', this.username, this.password);
    alert(`User ${this.username} created successfully!`);
    this.router.navigate(['/login']);
  }
}
