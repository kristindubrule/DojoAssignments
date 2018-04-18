import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class HttpService {

  constructor(private _http: HttpClient) { }

  getPets() {
    return this._http.get('/pets/sort/type');
  }

  getPet(petId: string) {
    return this._http.get('/pets/' + petId);
  }

  deletePet(petId: string) {
    return this._http.delete('/pets/' + petId);
  }

  updatePet(pet) {
    return this._http.put('/pets/' + pet._id, pet);
  }

  addPet(pet) {
    return this._http.post('/pets', pet);
  }

}
