import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class HttpService {

  constructor(private _http: HttpClient) { }

  addAuthor(author : any) {
    return this._http.post('/authors', author);
  }

  getAuthors() {
    return this._http.get('/authors/sort/name');
  }

  getAuthor(authorId : string) {
    return this._http.get('/authors/' + authorId);
  }

  updateAuthor(author : any) {
    return this._http.put('/authors/' + author._id, author);
  }

  deleteAuthor(authorId: string) {
    return this._http.delete('/authors/' + authorId);
  }

  addQuote(authorId: string, quote: any) {
    return this._http.post('/authors/' + authorId + '/quote', quote);
  }

  deleteQuote(authorId: string, quoteId: any) {
    return this._http.delete('/authors/' + authorId + '/quote/' + quoteId);
  }

  vote(authorId: string, quoteId: string, change: number) {
    return this._http.post('/authors/' + authorId + '/vote/' + quoteId, { change: change });
  }
}
