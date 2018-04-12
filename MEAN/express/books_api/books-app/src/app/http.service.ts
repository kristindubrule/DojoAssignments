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

  createAuthor(author) {
    return this._http.post('/authors/', author);
  }

  updateAuthor(id: string, author) {
    return this._http.put('/authors/' + id, author);
  }

  deleteAuthor(id: string) {
    return this._http.delete('/authors/' + id);
  }
}
