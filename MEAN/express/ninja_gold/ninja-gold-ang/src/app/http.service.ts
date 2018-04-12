import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class HttpService {

  constructor(private _http: HttpClient) { }

  createGame(name: String) {
    return this._http.post('/games', {'name': name, 'score': 0, 'messages': []});
  }

  updateScore(id: string, gold: number, message: {text: string, class: string}) {
    console.log('id ' + id + ' message ' + message);
    return this._http.put('/games/' + id, {'name': name, 'gold': gold, 'message': message});
  }

  getGame(id: string) {
    return this._http.get('/games/' + id);
  }

  getGamesFromService() {
    return this._http.get('/games');
  }
}
