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
    name: { type: String, 
            unique: true,
            required: [ true, 'The pet must have a name'], 
            minlength: [3, 'Pet name must be 3 or more characters' ] },
    type: { type: String, required: [ true, 'The pet must have a type'], minlength: [3, 'Pet type must be 3 or more characters'] },
    description: { type: String, required: [true, 'The pet must have a description'], minlength: [3, 'Pet type must be 3 or more characters'] },
    skills: [ { text: { type: String } } ],
    likes: { type: Number }
}, {timestamps: true});

const Pet = mongoose.model('Pet', PetSchema);

// Use native promises
mongoose.Promise = global.Promise;

app.get(['/pets', '/pets/sort/:sort'], function(req, res) {
    let sortVar;
    if (req.params.sort) {
        sortVar = req.params.sort;
    } else {
        sortVar = 'name';
    }
    Pet.find({}).sort([[sortVar,1]]).exec(function(err,pets) {
        if (err) {
            res.json({message: "Error", error: err });
        } else {
            res.json({message: "Success", pets: pets});
        }
    });

});

app.get('/pets/:id', function(req,res) {
    Pet.findOne({_id: ObjectId(req.params.id)}, function(err,pet) {
        if (err) {
            res.json({message: "Error", error: err});
        } else {
            res.json({message: "Success", pet: pet});
        }
    })
})

function fixType (type) {
    if (type && type.length > 2) {
        return type.charAt(0).toUpperCase() + type.slice(1);
    } else {
        return type;
    }
}

function addExistingError (existing, errors) {
    if (existing) {
        if (errors == undefined) {
            return {existing: {message: 'There is already a pet with this name'} };
        } else {
            errors['existing'] = {};
            errors['existing']['message'] = 'There is already a pet with this name';
            return errors;
        }
    } else {
        return errors;
    }
}

app.post('/pets', function(req,res) {
    let pet = new Pet(req.body);
    let existingPet = false;
    pet.type = fixType(pet.type);
    Pet.findOne({name: pet.name}, function(err,existing) {
        pet.save(function(err) {                
            if (err || existing) {
                if (existing) {
                    addExistingError(pet.errors);
                }
                res.json({message: "Error", errors: addExistingError(existing, pet.errors)});
            } else {
                res.json({message: "Success"});
            }
        });
    });
});

app.put('/pets/:id', function(req,res) {
    Pet.findById(req.params.id, function(err,pet) {
        if (err) {
            res.json({message: "Error", errors: [err]});
        } else {
            Pet.findOne({name: req.body.name, _id: { "$ne": ObjectId(pet._id)}}, function(err,existing) {
                pet.name = req.body.name;
                pet.type = fixType(req.body.type);
                pet.skills = req.body.skills;
                pet.description = req.body.description;
                pet.likes = req.body.likes;
                pet.save(function(errors) {
                    if (errors || existing) {
                        res.json({message: "Error", errors: addExistingError(existing, errors.errors)});
                    } else {
                        res.json({message: "Success"});
                    }
                })
            });
        }
    })    
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