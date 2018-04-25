// Require the Express Module
const express = require('express');
const app = express();

const mongoose = require('mongoose');
mongoose.connect('mongodb://localhost/authors');
var validate = require('mongoose-validator')
var extend = require('mongoose-validator').extend
const ObjectId = require('mongodb').ObjectID;

const AuthorSchema = new mongoose.Schema({
    name: { type: String, required:true, minlength:3 },
    quotes: [ { text: {type: String, minlength: 3 }, votes: {type: Number, min: 1} } ]
}, {timestamps: true});

const Author = mongoose.model('Author', AuthorSchema);

// Use native promises
mongoose.Promise = global.Promise;

// Require body-parser (to receive post data from clients)
const bodyParser = require('body-parser');
app.use(bodyParser.json());
app.use(express.static( __dirname + '/authors-app/dist' ));
    
// Require path
const path = require('path');
app.use(express.static(path.join(__dirname, './static')));

app.get(['/authors', '/authors/sort/:sort'], function(req, res) {
    let sortVar;
    console.log('Authors!');
    if (req.params.sort) {
        sortVar = req.params.sort;
    } else {
        sortVar = '_id';
    }
    Author.find({}).sort([[sortVar, 1]]).exec(function(err, authors) {
        if (err) {
            res.json({message: "Error", error: err});
        } else {
            res.json({message: "Success", authors: authors});
        }
    });
});

app.post('/authors', function(req,res) {
    let author = new Author(req.body);
    author.save(function (err) {
        if (err) {
            res.json({message: "Error", error: author.errors});
        } else {
            res.json({message: "Success"});
        }
    });
});

app.get('/authors/:id', function(req,res) {
    console.log("ID " + req.params.id);
    Author.findOne({_id: ObjectId(req.params.id)}, function(err,author) {
        console.log(author);
        if (err) {
            res.json({message: "Error", error: err});
        } else {
            res.json({message: "Success", author: author});
        }
    })
})

app.put('/authors/:id', function(req,res) {
    Author.findOne({_id: ObjectId(req.params.id)}, function(err,author) {
        if (err) {
            res.json({message: "Error", error: err});
        } else {
            author.name = req.body.name;
            console.log(author);
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

app.post('/authors/:id/quote', function(req,res) {
    Author.findOne({_id: req.params.id}, function(err, author) {
        if (err) {
            res.json({message: "Error", error: err});
        } else {
            author.quotes.push({ text: req.body.text, votes: 1 });
            author.save(function(err) {
                if (err) {
                    res.json({message: "Error", error: err});
                } else {
                    res.json({message: "Success"});
                }
            });
        }
    });
});

app.delete('/authors/:id/quote/:qid', function(req,res) {
    Author.findOneAndUpdate(
        { "_id": req.params.id },
        { "$pull": { "quotes": { "_id": req.params.qid } } },
        function(err, numAffected) {
            if (err) {
                res.json({message: "Error", error: err});
            } else {
                res.json({message: "Success"});
            }
        }
    )
});

app.delete('/authors/:id', function(req,res) {
    Author.findByIdAndRemove({_id: ObjectId(req.params.id)}, function(err,author) {
        if (err) {
            res.json({message: "Error", error: err});
        } else {
            res.json({message: "Success"});
        }
    });
})

app.post('/authors/:id/vote/:qid', function(req,res) {
    Author.findOneAndUpdate(
        {_id: req.params.id, 'quotes._id': req.params.qid},
        { $inc: { 'quotes.$.votes' : req.body.change } },
        function(err, numAffected) {
            if (err) {
                res.json({message: "Error", error: err});
            } else {
                res.json({message: "Success"});
            }
        }
    )
});

app.all("*", (req,res,next) => {
    res.sendFile(path.resolve("./authors-app/dist/index.html"))
  });

// Setting our Server to Listen on Port: 8000
app.listen(8000, function() {
    console.log("listening on port 8000");
})
