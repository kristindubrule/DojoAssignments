import { Component, OnInit } from '@angular/core';
import { HttpService } from './http.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'app';
  games = [];
  gameid: string;
  name: string;
  score: number;
  messages: [{ text: string , class: string}];
  gold_values = {
    'farm': [5, 10],
    'cave': [5, 10],
    'house': [2, 5],
    'casino': [-50, 50]
  };

  constructor(private _httpService: HttpService) {
  }

  ngOnInit() {
    this.score = 0;
    this.retrieveGames();
  }

  setName (event) {
    this.name = event.target.value;
  }

  loadGame(event) {
    console.log(event);
  }
  
  saveName() {
    const observable = this._httpService.createGame(this.name);
    observable.subscribe(data => {
      console.log(data);
      this.gameid = data['game']['_id'];
      console.log(this.gameid);
    });
  }

  retrieveGames() {
    const observable = this._httpService.getGamesFromService();
    observable.subscribe(data => {
      this.games = data['games'];
    });
  }

  earnGold(building: string) {
    const gold = Math.floor(Math.random() *
                    (this.gold_values[building][1] - this.gold_values[building][0])
                    + this.gold_values[building][0]);
    console.log(gold);
    console.log(building);
    const observable = this._httpService.updateScore(this.gameid, gold, this.message(building, gold));
    observable.subscribe(data => {
      console.log(data);
      this.updateDisplay();
    });

  }

  updateDisplay() {
    const observable = this._httpService.getGame(this.gameid);
    observable.subscribe(data => {
      console.log(data);
      this.score = data['game']['score'];
      this.messages = data['game']['messages'];
    });
  }

  message(building: string, gold: number) {
    // tslint:disable-next-line:prefer-const
    let message = { 'text': '', 'class': ''};
    if (gold < 0) {
      message['text'] = 'Entered a casino and lost ' + gold + ' golds... Ouch.';
      message['class'] = 'lose';
    } else if (gold > 0) {
      message['text'] = 'Earned ' + gold + ' golds from the ' + building + '!';
      message['class'] = 'earn';
    } else {
      message['text'] = 'Entered a casino and didn\'t win or lose. Could be worse.';
      message['class'] = 'none';
    }
    return message;
  }
}
