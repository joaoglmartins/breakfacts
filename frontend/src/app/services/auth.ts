import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class Auth {

  constructor(private http: HttpClient) {}

  login(username: string, password: string): Observable<any> {
    return this.http.post(`${environment.apiBaseUrl}/login`, { username, password });
  }

  signup(username: string, password: string): Observable<any> {
    return this.http.post(`${environment.apiBaseUrl}/signup`, { username, password });
  }
}
