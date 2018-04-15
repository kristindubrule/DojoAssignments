import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class HttpService {
  shintoCoin: number;
  shintoValue: number;
  transactions: any;

  constructor(private _http: HttpClient) {
    this.shintoCoin = 0;
    this.shintoValue = 1;
    this.transactions = [];
  }

  mineCoins() {
    this.shintoCoin += 1;
    this.log('Mine', 1, this.shintoValue);
  }

  buyCoins(buyCount: number) {
    this.log('Buy', buyCount, this.shintoValue);
    this.shintoCoin += (buyCount * 1);
    this.shintoValue += 1;
  }

  sellCoins(sellCount: number) {
    this.log('Sell', sellCount, this.shintoValue);
    this.shintoCoin -= (sellCount * 1);
    this.shintoValue -= 1;
  }

  log(type: string, amount: number, value: number) {
    this.transactions.push({id: this.transactions.length, type: type, amount: amount, value: value});
    console.log(this.transactions);
  }

}
