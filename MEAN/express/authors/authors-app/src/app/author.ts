export class Author {
    constructor(
        public _id: string,
        public name: string,
        public  quotes: [ { text: {type: String, minlength: 3 }, votes: {type: Number, min: 1} } ]
    ) {}
}

