import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { HttpService } from '../http.service';

@Component({
  selector: 'app-add-note',
  templateUrl: './add-note.component.html',
  styleUrls: ['./add-note.component.css']
})
export class AddNoteComponent implements OnInit {
  note: { text: string };
  message: any;
  @Output () updateNotes = new EventEmitter();

  constructor(private _httpService: HttpService) { }

  ngOnInit() {
    this.note = { text: '' };
    this.message = [];
  }

  addNote() {
    console.log('Adding note');
    const obs = this._httpService.addNote(this.note);
    obs.subscribe(data => {
      if (data['errors']) {
        this.processErrors(data['errors']);
      } else {
        this.message = [];
        this.updateNotes.emit();
      }
    });
  }

  processErrors(errors) {
    // tslint:disable-next-line:forin tslint:disable-next-line:prefer-const
    for (let message_text in errors) {
      this.message.push(errors[message_text].message);
    }
  }
}
