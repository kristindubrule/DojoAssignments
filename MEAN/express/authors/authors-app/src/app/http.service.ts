import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class HttpService {

  constructor(private _http: HttpClient) { }

  addAuthor(author) {
    return this._http.post('/authors', author);
  }

  getAuthors() {
    return this._http.get('/authors/sort/name');
  }

  getAuthor(authorId) {
    return this._http.get('/authors/' + authorId);
  }

  updateAuthor(author) {
    return this._http.put('/authors/' + author._id, author);
  }

  deleteAuthor(authorId) {
    return this._http.delete('/authors/' + authorId);
  }

  addQuote(authorId, quote) {
    return this._http.post('/authors/' + authorId + '/quote', quote);
  }

  deleteQuote(authorId, quoteId) {
    return this._http.delete('/authors/' + authorId + '/quote/' + quoteId);
  }

  vote(authorId, quoteId, change) {
    return this._http.post('/authors/' + authorId + '/vote/' + quoteId, { change: change });
  }
}
