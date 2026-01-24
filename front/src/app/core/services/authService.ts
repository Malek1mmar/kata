import { inject, Injectable, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private readonly http = inject(HttpClient);
  private readonly API_URL = 'http://localhost:8080/api/token';

  public isAuthenticated = signal<boolean>(!!localStorage.getItem('token'));

  login(credentials: { email: string; password: string }): Observable<any> {
    return this.http.post<{token: string}>(this.API_URL, credentials).pipe(
      tap(response => {
        localStorage.setItem('token', response.token);
        this.isAuthenticated.set(true);
      })
    );
  }

  logout() {
    localStorage.removeItem('token');
    this.isAuthenticated.set(false);
    window.location.reload();
  }
}
