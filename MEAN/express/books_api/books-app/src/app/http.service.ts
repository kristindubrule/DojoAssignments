import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class HttpService {

  constructor(private _http: HttpClient) {
  }

  getAuthors() {
    return this._http.get('/authors');
  }

  getAuthor(authorId: String) {
    return this._http.get('/authors/' + authorId);
  }

  getBook(authorId: String) {
    return this._http.get('/authors/' + authorId);
  }
}
