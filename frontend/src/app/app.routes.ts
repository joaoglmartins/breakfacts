import { Routes } from '@angular/router';
import { Home } from './pages/home/home';
import { AiFacts } from './components/ai-facts/ai-facts';

export const routes: Routes = [
    { path: '', component: Home },
    { path: 'generate', component: AiFacts },
];
