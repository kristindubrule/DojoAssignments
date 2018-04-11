// Require the Express Module
const express = require('express');
// Create an Express App
const app = express();

const mongoose = require('mongoose');
mongoose.connect('mongodb://localhost/books');
var validate = require('mongoose-validator')
var extend = require('mongoose-validator').extend

const ObjectId = require('mongodb').ObjectID;

var birthdateValidator = [
    validate({
      validator: function(val) {
        var now = new Date();
        now.setHours(0,0,0,0);
        return val < now;
    },
    message: 'Birthdate must be in the past'
    })
]

const AuthorSchema = new mongoose.Schema({
    first_name: { type: String, required:true, minlength:2 },
    last_name: { type:String, required:true, minlength:2 },
    birth_date: { type:Date, required:true, validate:birthdateValidator },
    books: [{ title: { type:String, required:true, minlength:2 }, pub_year: {type:Number, required:true} }],
    country: {type:String, required:true}
}, {timestamps: true});

const Author = mongoose.model('Author', AuthorSchema);

// Use native promises
mongoose.Promise = global.Promise;

// Require body-parser (to receive post data from clients)
const bodyParser = require('body-parser');

// Integrate body-parser with our App
app.use(bodyParser.json());
app.use(express.static( __dirname + '/books-app/dist' ));

// Require path
const path = require('path');
// Setting our Static Folder Directory
app.use(express.static(path.join(__dirname, './static')));

// Routes
// Root Request
app.get('/authors', function(req, res) {
    Author.find({},function(err, authors) {
        if (err) {
            res.json({message: "Error", error: err});
        } else {
            res.json({message: "Success", data: authors});
        }
    });
});

app.post('/authors', function(req,res) {
    let author = new Author(req.body);
    author.save(function (err) {
        if (err) {
            res.json({message: "Error", error: author.errors});
        } else {
            res.json({message: "Success", error: []});
        }
    });
});

app.post('/authors/:id/book', function(req,res) {
    Author.findOne({_id: ObjectId(req.params.id)}, function(err,author) {
        if (err) {
            res.json({message: "Error", error:err});
        } else {
            let book = { "title": req.body.title, "pub_year": req.body.pub_year }
            author.books.push(book);
            author.save(function(err) {
                if (err) {
                    res.json({message: "Error", error: err});
                } else {
                    res.json({message: "Success"});
                }
            })
        }
    })
})

app.get('/authors/:id', function(req,res) {
    Author.findOne({_id: ObjectId(req.params.id)}, function(err,author) {
        console.log(author);
        if (err) {
            res.json({message: "Error", error: err});
        } else {
            res.json({message: "Success", data: author});
        }
    })
})

app.put('/authors/:id', function(req,res) {
    Author.findOne({_id: ObjectId(req.params.id)}, function(err,author) {
        if (err) {
            res.json({message: "Error", error: err});
        } else {
            author.first_name = req.body.first_name;
            author.last_name = req.body.last_name;
            author.country = req.body.country;
            author.birth_date = req.body.birth_date;
            author.save(function(err) {
                if (err) {
                    res.json({message: "Error", error: err});
                } else {
                    res.json({message: "Success"});
                }
            })
        }
    })
})

app.delete('/authors/:id', function(req,res) {
    Author.findByIdAndRemove({_id: ObjectId(req.params.id)}, function(err,author) {
        if (err) {
            res.json({message: "Error", error: err});
        } else {
            res.json({message: "Success"});
        }
    });
})

app.delete('/authors/book/:id', function(req,res) {
    Author.update({}, {$pull: {books: {_id: ObjectId(req.params.id)}}}, {multi:true}, function(err) {
        if (err) {
            res.json({message: "Error", error: err});
        } else {
            res.json({message: "Success"});
        }
    });
});

// Setting our Server to Listen on Port: 8000
app.listen(8000, function() {
    console.log("listening on port 8000");
})
