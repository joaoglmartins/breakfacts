import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class AiApiService {

  constructor(private http: HttpClient) {}

  getFact(topicId : string): Observable<any> {
    return this.http.post<any>(`${environment.apiBaseUrl}/ai/generate`, { topicId });
  }
}
