import { AuthService } from './auth.service';
import {
  HttpInterceptor,
  HttpHandler,
  HttpRequest,
  HTTP_INTERCEPTORS,
  HttpEvent,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const HEADER_KEY = 'Authorization'; // for Spring Boot back-end (see it AuthRequestFilter)

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private authService: AuthService) {}

  // This adds JWT to each request [(Key, Value) = (Authorization, Bearer eyJhbGciOiJIUzI1NiJ...)]
  intercept(
    request: HttpRequest<any>,
    handler: HttpHandler
  ): Observable<HttpEvent<any>> {
    let authReq = request;
    const token = this.authService.getToken();
    if (token != null) {
      authReq = request.clone({
        headers: request.headers.set(HEADER_KEY, 'Bearer ' + token),
      });
    }
    return handler.handle(authReq);
  }
}

export const authInterceptorProviders = [
  { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
];
