import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Author } from '../author';

@Component({
  selector: 'app-add-quote',
  templateUrl: './add-quote.component.html',
  styleUrls: ['./add-quote.component.css']
})
export class AddQuoteComponent implements OnInit {
  message: any;
  editFlag = false;
  instructions: string;
  author: any;
  quote: { text: string };
  user = { email: ''};


  constructor(
    private _httpService: HttpService,
    private _route: ActivatedRoute,
    private _router: Router) {
  }

  ngOnInit() {
    this.quote = { text: '' };
    this._route.params.subscribe((params: Params) => {
      if (params['id']) {
        this.getAuthor(params['id']);
      } else {
        this._router.navigate(['/']);
      }
    });
  }

  getAuthor(authorId: string) {
    const obs = this._httpService.getAuthor(authorId);
    obs.subscribe(data => {
      if (data['error']) {
        this.message = [data['error']];
      } else {
        this.author = data['author'];
        this.instructions = 'Provide a quote by ' + this.author.name;
      }
    });
  }

  addQuote() {
    console.log('Add quote ' + this.quote.text);
    const obs = this._httpService.addQuote(this.author._id, this.quote);
    obs.subscribe(data => {
      if (data['errors']) {
        // tslint:disable-next-line:forin
        for (let message_text in data['errors']) {
          this.message.push(data['errors'][message_text].message);
        }
      } else {
        this._router.navigate(['/view/' + this.author._id]);
      }
    });
  }
}
