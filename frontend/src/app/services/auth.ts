import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class Auth {

  constructor(private http: HttpClient) {}

  private isBrowser(): boolean {
    return typeof window !== 'undefined' && !!window.localStorage;
  }

  login(email: string, password: string): Observable<any> {
    return this.http.post(`${environment.apiBaseUrl}/auth/login`, { email, password });
  }

  signup(email: string, password: string): Observable<any> {
    return this.http.post(`${environment.apiBaseUrl}/auth/signup`, { email, password });
  }

  saveToken(token: string) {
    localStorage.setItem('token', token);
  }

  getToken(): string | null {
    if (this.isBrowser()) {
      return localStorage.getItem('token');
    }
    return null;
  }

  logout() {
    localStorage.removeItem('token');
  }
}
