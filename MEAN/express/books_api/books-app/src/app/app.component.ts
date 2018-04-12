import { Component, OnInit } from '@angular/core';
import { HttpService } from './http.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'app';
  authors = [];
  author = {};
  num: number;
  randNum: number;
  str: string;
  first_name: string;
  snacks: string[];
  loggedIn: boolean;
  book = {};
  searchId = '';

  constructor (private _httpService: HttpService) {}
  ngOnInit() {
    this.num = 7;
    this.randNum = Math.floor( (Math.random()  * 2 ) + 1);
    this.str = 'Hello Angular Developer!';
    this.first_name = 'Alpha';
    this.snacks = ['vanilla latte with skim milk', 'brushed suede', 'cookie'];
    this.loggedIn = true;
  }
  getAuthorsFromService() {
    const observable = this._httpService.getAuthors();
    observable.subscribe(data => {
      console.log(data);
      this.authors = data['authors'];
      console.log(this.authors);
    });
  }

  getOneAuthor() {
    this.getAuthorFromService(this.searchId);
  }

  getAuthorFromService(authorId: String) {
    const observable = this._httpService.getAuthor(authorId);
    observable.subscribe(data => {
      console.log(data);
      this.author = data['author'];
      console.log(this.author);
    });
  }

  getBookFromService(authorId: String) {
    const observable = this._httpService.getAuthor(authorId);
    observable.subscribe(data => {
      this.book = data['author'].books[0];
    });
  }

  setAuthorId(event) {
    console.log(event.target.value);
    this.searchId = event.target.value;
  }

  getAuthors() {
    this.getAuthorsFromService();
  }
  do(event) {
    console.log(event);
    this.getBookFromService('5ace6ab2cfb19fab9c1e70fb');
  }
}
