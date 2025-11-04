import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  imports: [FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.scss',
})
export class Login {
  username = '';
  password = '';

  constructor(private router: Router) {}

  onLogin() {
    // TODO: replace with API call to backend later
    console.log('Logging in with', this.username, this.password);

    // For now, fake login:
    if (this.username && this.password) {
      alert(`Welcome, ${this.username}!`);
      this.router.navigate(['/']);
    }
  }
}
