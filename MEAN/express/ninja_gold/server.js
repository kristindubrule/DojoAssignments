// Require the Express Module
const express = require('express');
// Create an Express App
const app = express();

const mongoose = require('mongoose');
mongoose.connect('mongodb://localhost/books');

var validate = require('mongoose-validator')
var extend = require('mongoose-validator').extend

const ObjectId = require('mongodb').ObjectID;

const NinjaGoldGameSchema = new mongoose.Schema({
    name: { type: String },
    score: { type: Number},
    messages: [{ text: String, class: String}]
}, {timestamps: true});

const NinjaGoldGame = mongoose.model('NinjaGoldGame', NinjaGoldGameSchema);

// Use native promises
mongoose.Promise = global.Promise;

// Require body-parser (to receive post data from clients)
const bodyParser = require('body-parser');

// Integrate body-parser with our App
app.use(bodyParser.json());
app.use(express.static( __dirname + '/ninja-gold-ang/dist' ));

// Require path
const path = require('path');
// Setting our Static Folder Directory
app.use(express.static(path.join(__dirname, './static')));

// Routes
// Root Request
app.get('/games', function(req, res) {
    NinjaGoldGame.find({},function(err, games) {
        if (err) {
            res.json({message: "Error", error: err});
        } else {
            res.json({message: "Success", games: games});
        }
    });
});

app.post('/games', function(req,res) {
    let game = new NinjaGoldGame(req.body);
    console.log(req.body.name);
    game.save(function (err) {
        if (err) {
            res.json({message: "Error", error: game.errors});
        } else {
            res.json({message: "Success", game: game});
        }
    });
});

app.get('/games/:id', function(req,res) {
    NinjaGoldGame.findOne({_id: ObjectId(req.params.id)}, function(err,game) {
        console.log(game);
        if (err) {
            res.json({message: "Error", error: err});
        } else {
            res.json({message: "Success", game: game});
        }
    })
})

app.put('/games/:id', function(req,res) {
    NinjaGoldGame.findOne({_id: ObjectId(req.params.id)}, function(err,game) {
        if (err) {
            res.json({message: "Error", error: err});
        } else {
            game.score += req.body.gold;
            game.messages.push(req.body.message);
            game.save(function(err) {
                if (err) {
                    res.json({message: "Error", error: err});
                } else {
                    console.log("Success");
                    res.json({message: "Success"});
                }
            })
        }
    })
})

app.delete('/games/:id', function(req,res) {
    NinjaGoldGame.findByIdAndRemove({_id: ObjectId(req.params.id)}, function(err,game) {
        if (err) {
            res.json({message: "Error", error: err});
        } else {
            res.json({message: "Success"});
        }
    });
})

// Setting our Server to Listen on Port: 8000
app.listen(8000, function() {
    console.log("listening on port 8000");
})
