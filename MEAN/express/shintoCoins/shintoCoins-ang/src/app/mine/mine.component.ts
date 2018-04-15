import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service';

@Component({
  selector: 'app-mine',
  templateUrl: './mine.component.html',
  styleUrls: ['./mine.component.css']
})
export class MineComponent implements OnInit {
  mineCount: number;
  answer = '34';
  userAnswer: string;
  message: string;
  shintoCoins: number;


  constructor(
    private _httpService: HttpService
  ) { }

  ngOnInit() {
    this.shintoCoins = this._httpService.shintoCoin;
  }

  updateCoins() {
    this.shintoCoins = this._httpService.shintoCoin;
  }

  mineCoins() {
    if (this.userAnswer !== this.answer) {
      this.message = 'Incorrect answer; try again';
    } else {
      this._httpService.mineCoins();
      this.userAnswer = '';
      this.message = 'Correct. You have earned 1 ShintoCoin';
      this.updateCoins();
      this.reset();
    }
  }

  reset() {
    this.userAnswer = '';
  }
}
