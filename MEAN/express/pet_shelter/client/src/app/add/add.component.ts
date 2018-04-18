import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service';
import { ActivatedRoute, Params, Router } from '@angular/router';

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css']
})
export class AddComponent implements OnInit {
  instructions: string;
  message = [];
  pet: any;
  editFlag: boolean;
  buttonText: string;

  constructor(
    private _httpService: HttpService,
    private _route: ActivatedRoute,
    private _router: Router) { }

  ngOnInit() {
    this.editFlag = false;
    this.resetPet();
    this.message = [];

    this._route.params.subscribe((params: Params) => {
      if (params['id']) {
        this.getPet(params['id']);
      } else {
        this.instructions = 'Know of a pet needing a home?';
        this.buttonText = 'Add pet';
      }
    });
  }

  getPet(petId: string): void {
    const obs = this._httpService.getPet(petId);
        obs
          .subscribe(data => {
            if (data['error']) {
              this.message = [data['error']];
            } else {
              this.pet = data['pet'];
              this.editFlag = true;
            }
            if (this.pet == null) {
              this.message = ['No pet found. Would you like to add it?'];
              this.resetPet();
              this.editFlag = false;
            } else {
              this.instructions = 'Edit this pet';
              this.buttonText = 'Edit pet';
            }
          },
          error => {
            this.message = ['There was an error with this request. Would you like to add this pet?'];
          });
  }

  resetPet() {
    this.pet = {name: '', type: '', skills: [], likes: 0};
    for (let i = 0; i < 3; i++) {
      this.pet.skills.push({ text: ''});
    }
  }

  updatePet() {
    let obs;

    if (this.editFlag) {
      obs = this._httpService.updatePet(this.pet);
    } else {
      obs = this._httpService.addPet(this.pet);
    }
    obs.subscribe(data => {
      if (data['errors']) {
        this.message = [];
        // tslint:disable-next-line:forin
        for (let message_text in data['errors']) {
          this.message.push(data['errors'][message_text].message);
        }
      } else {
        let redirectRoute = ((this.editFlag) ? '/details/' + this.pet._id : '/');
        this.resetPet();
        this.message = [];
        this._router.navigate([redirectRoute]);
      }
    });
  }
}
