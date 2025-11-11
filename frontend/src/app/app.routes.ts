import { Routes } from '@angular/router';
import { Home } from './pages/home/home';
import { Login } from './pages/login/login';
import { Signup } from './pages/signup/signup';
import { authGuard } from './guards/auth-guard';

export const routes: Routes = [
    { path: '', component: Home, canActivate: [authGuard] },
    { path: 'login', component: Login },
    { path: 'signup', component: Signup },
];
