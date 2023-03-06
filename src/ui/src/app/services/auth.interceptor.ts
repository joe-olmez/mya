import {
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
  HTTP_INTERCEPTORS,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';

const HEADER_KEY = 'Authorization'; // for Spring Boot back-end (see it AuthRequestFilter)

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private authService: AuthService) {}

  // This adds JWT to each request [(Key, Value) = (Authorization, Bearer eyJhbGciOiJIUzI1NiJ...)]
  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    const token = this.authService.getToken();

    console.log('Token in auth interceptor: ', token);

    if (token != null) {
      return next.handle(req);
    }

    const req1 = req.clone({
      headers: req.headers.set(HEADER_KEY, `Bearer ${token}`),
    });

    console.log('Token in auth interceptor: ', token);

    return next.handle(req1);
  }
}

// Put this into app.module.ts
export const authInterceptorProviders = [
  { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
];
