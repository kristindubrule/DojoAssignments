	// Require the Express Module
	const express = require('express');
	const app = express();

	// Require body-parser (to receive post data from clients)
	const bodyParser = require('body-parser');
	app.use(bodyParser.json());
	app.use(express.static( __dirname + '/notes-ang/dist' ));

	// Require path
	const path = require('path');
	app.use(express.static(path.join(__dirname, './static')));

    const mongoose = require('mongoose');
	mongoose.connect('mongodb://localhost/notes');
	var validate = require('mongoose-validator')
	var extend = require('mongoose-validator').extend
	const ObjectId = require('mongodb').ObjectID;

	const NoteSchema = new mongoose.Schema({
	    text: { type: String, required:true, minlength:3 }
	}, {timestamps: true});

	const Note = mongoose.model('Note', NoteSchema);

	// Use native promises
	mongoose.Promise = global.Promise;

    app.get('/notes', function(req, res) {
        Note.find({}).sort([["createdAt",-1]]).exec(function(err,notes) {
            if (err) {
                res.json({message: "Error", error: err });
            } else {
                res.json({message: "Success", notes: notes});
            }
        });

    });

    app.post('/notes', function(req,res) {
        console.log('Adding note');
        let note = new Note(req.body);
        note.save(function(err) {
            if (err) {
                console.log(note.errors);
                res.json({message: "Error", errors: note.errors});
            } else {
                console.log('Saved note');
                res.json({message: "Success"});
            }
        })
    });

	app.all("*", (req,res,next) => {
	    res.sendFile(path.resolve("./notes-ang/dist/index.html"))
	  });

	// Setting our Server to Listen on Port: 8000
	app.listen(8000, function() {
	    console.log("listening on port 8000");
	})