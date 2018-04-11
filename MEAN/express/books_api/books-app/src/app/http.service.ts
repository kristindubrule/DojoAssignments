import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class HttpService {

  constructor(private _http: HttpClient) {
    this.getAuthors();

    this.getAuthor('5ace766f3a2712112225c7d4');
  }

  getAuthors() {
    const tempObservable = this._http.get('/authors');

    tempObservable.subscribe(data => console.log('Got authors!', data));
  }

  getAuthor(authorId: String) {
    const tempObservable = this._http.get('/authors/' + authorId);

    tempObservable.subscribe(data => console.log('Got author!', data));
  }
}
