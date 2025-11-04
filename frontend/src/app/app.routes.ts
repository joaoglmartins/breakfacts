import { Routes } from '@angular/router';
import { Home } from './pages/home/home';
import { AiFacts } from './components/ai-facts/ai-facts';
import { Login } from './pages/login/login';
import { Signup } from './pages/signup/signup';

export const routes: Routes = [
    { path: '', component: Home },
    { path: 'login', component: Login },
    { path: 'signup', component: Signup },
];
