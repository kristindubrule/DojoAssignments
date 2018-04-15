import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service';

@Component({
  selector: 'app-sell',
  templateUrl: './sell.component.html',
  styleUrls: ['./sell.component.css']
})
export class SellComponent implements OnInit {
  shintoCoins: number;
  shintoValue: number;
  sellNum: number;
  message: string;

  constructor(
    private _httpService: HttpService
  ) { }

  ngOnInit() {
    this.shintoCoins = this._httpService.shintoCoin;
    this.shintoValue = this._httpService.shintoValue;
  }

  sellCoins() {
    if (this.shintoCoins > 0) {
      this._httpService.sellCoins(this.sellNum);
      this.sellNum = 0;
      this.updateCoins();
      this.message = '';
    } else {
      this.message = 'Not enough coins to sell';
    }
  }

  updateCoins() {
    this.shintoCoins = this._httpService.shintoCoin;
    this.shintoValue = this._httpService.shintoValue;
  }
}
