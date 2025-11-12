import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class TopicApiService {
  
  constructor(private http: HttpClient) {}

  getTopics(): Observable<any> {
    return this.http.get(`${environment.apiBaseUrl}/topics`);
  }

}
