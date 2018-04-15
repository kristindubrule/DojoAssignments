import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service';


@Component({
  selector: 'app-buy',
  templateUrl: './buy.component.html',
  styleUrls: ['./buy.component.css']
})
export class BuyComponent implements OnInit {
  shintoCoins: number;
  shintoValue: number;
  buyNum: number;
  message: string;

  constructor(
    private _httpService: HttpService
  ) { }

  ngOnInit() {
    this.shintoCoins = this._httpService.shintoCoin;
    this.shintoValue = this._httpService.shintoValue;
  }

  buyCoins() {
    if (this.shintoCoins > 0) {
      this._httpService.buyCoins(this.buyNum);
      this.buyNum = 0;
      this.updateCoins();
      this.message = '';
    } else {
      this.message = 'Not enough coins to purchase more';
    }
  }

  updateCoins() {
    this.shintoCoins = this._httpService.shintoCoin;
    this.shintoValue = this._httpService.shintoValue;
  }
}
