import { Component, OnInit } from '@angular/core';
import { HttpService } from './http.service';
import * as moment from 'moment';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'app';
  authors = [];
  editAuthorId: string;
  num: number;
  randNum: number;
  str: string;
  first_name: string;
  snacks: string[];
  loggedIn: boolean;
  book = {};
  searchId = '';
  author = { first_name: '', last_name: '', birth_date: '', country: ''};
  newAuthor = { first_name: '', last_name: '', birth_date: '', country: ''};
  editAuthor = { first_name: '', last_name: '', birth_date: '', country: ''};

  constructor (private _httpService: HttpService) {}
  ngOnInit() {
    this.getAuthorsFromService();
  }
  getAuthorsFromService() {
    const observable = this._httpService.getAuthors();
    observable.subscribe(data => {
      this.authors = data['authors'];
      for (let tempauthor of this.authors) {
        this.formatDate(tempauthor);
      }
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

  formatDate(tempauthor) {
    tempauthor.birth_date = moment(new Date(tempauthor.birth_date)).format('YYYY-MM-DD').toString();
  }

  createAuthor() {
    const observable = this._httpService.createAuthor(this.newAuthor);
    observable.subscribe(data => {
      console.log(data);
      if (data['errors']) {
        console.log(data['errors']);
      } else {
        this.newAuthor = { first_name: '', last_name: '', birth_date: '', country: ''};
        this.getAuthorsFromService();
      }
    });
  }

  editAuthorForm(event) {
    this.editAuthorId = event.target.name;
    const observable = this._httpService.getAuthor(this.editAuthorId);
    observable.subscribe(data => {
      this.editAuthor = data['author'];
      this.formatDate(this.editAuthor);
    });
  }

  updateAuthor() {
    console.log('update');
    const observable = this._httpService.updateAuthor(this.editAuthorId, this.editAuthor);
    observable.subscribe(data => {
      console.log(data);
      if (data['errors']) {
        console.log(data['errors']);
      } else {
        this.editAuthor = { first_name: '', last_name: '', birth_date: '', country: ''};
        this.editAuthorId = undefined;
        this.getAuthorsFromService();
      }
    });
  }

  deleteAuthor(event) {
    const deleteAuthorId = event.target.name;
    const observable = this._httpService.deleteAuthor(deleteAuthorId);
    observable.subscribe(data => {
      if (data['errors']) {
        console.log(data['errors']);
      } else {
        this.getAuthorsFromService();
      }
    });
  }

  do(event) {
    console.log(event);
    this.getBookFromService('5ace6ab2cfb19fab9c1e70fb');
  }
}
