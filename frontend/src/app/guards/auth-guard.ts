import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { Auth } from '../services/auth';
import { jwtDecode } from 'jwt-decode';

export const authGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);
  const authService = inject(Auth);
  const token = authService.getToken();

  if (token) {
    try {
      const decoded: any = jwtDecode(token);
      const exp = decoded.exp * 1000;

      if (Date.now() < exp) {
        return true;
      } else {
        console.warn('Token expired');
        authService.logout();
      }
    } catch (err) {
      console.error('Invalid token', err);
      authService.logout();
    }
  }

  router.navigate(['/login']);
  return false;
};
