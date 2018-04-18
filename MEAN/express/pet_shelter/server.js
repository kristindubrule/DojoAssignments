// Require the Express Module
const express = require('express');
const app = express();

// Require body-parser (to receive post data from clients)
const bodyParser = require('body-parser');
app.use(bodyParser.json());
app.use(express.static( __dirname + '/client/dist' ));

// Require path
const path = require('path');
app.use(express.static(path.join(__dirname, './static')));

const mongoose = require('mongoose');
mongoose.connect('mongodb://localhost/pets');
var validate = require('mongoose-validator')
var extend = require('mongoose-validator').extend
const ObjectId = require('mongodb').ObjectID;

const PetSchema = new mongoose.Schema({
    name: { type: String, required: [ true, 'The pet must have a name'], minlength: [3, 'Pet name must be 3 or more characters' ] },
    type: { type: String, required: [ true, 'The pet must have a type'], minlength: [3, 'Pet type must be 3 or more characters'] },
    description: { type: String, required: [true, 'The pet must have a description'], minlength: [3, 'Pet type must be 3 or more characters'] },
    skills: [ { text: { type: String } } ],
    likes: { type: Number }
}, {timestamps: true});

const Pet = mongoose.model('Pet', PetSchema);

// Use native promises
mongoose.Promise = global.Promise;

app.get('/pets', function(req, res) {
    Pet.find({}).sort([["createdAt",-1]]).exec(function(err,pets) {
        if (err) {
            res.json({message: "Error", error: err });
        } else {
            res.json({message: "Success", pets: pets});
        }
    });

});

app.get('/pets/:id', function(req,res) {
    console.log("ID " + req.params.id);
    Pet.findOne({_id: ObjectId(req.params.id)}, function(err,pet) {
        console.log(pet);
        if (err) {
            res.json({message: "Error", error: err});
        } else {
            res.json({message: "Success", pet: pet});
        }
    })
})

app.post('/pets', function(req,res) {
    console.log('Adding pet');
    console.log(req.body);
    let pet = new Pet(req.body);
    pet.save(function(err) {
        if (err) {
            console.log(pet.errors);
            res.json({message: "Error", errors: pet.errors});
        } else {
            console.log('Saved pet');
            res.json({message: "Success"});
        }
    })
});

app.put('/pets/:id', function(req,res) {
    console.log('Put');
    console.log(req.body);
    Pet.findByIdAndUpdate(req.params.id, req.body, function(err,numaffected) {
        if (err) {
            console.log(err);
            res.json({message: "Error", errors: [err]});
        } else {
            console.log(err + ' ' + numaffected);
            res.json({message: "Success"});
        }
    });
});

app.delete('/pets/:id', function(req,res) {
    Pet.findByIdAndRemove({_id: ObjectId(req.params.id)}, function(err,pet) {
        if (err) {
            res.json({message: "Error", error: err});
        } else {
            res.json({message: "Success"});
        }
    });
})

app.all("*", (req,res,next) => {
    res.sendFile(path.resolve("./client/dist/index.html"))
    });

// Setting our Server to Listen on Port: 8000
app.listen(8000, function() {
    console.log("listening on port 8000");
})