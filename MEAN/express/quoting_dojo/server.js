// Require the Express Module
const express = require('express');
// Create an Express App
const app = express();

const mongoose = require('mongoose');
mongoose.connect('mongodb://localhost/quotes');

const QuoteSchema = new mongoose.Schema({
 name: {type: String, required: true},
 text: {type: String, required: true},
}, {timestamps: true});
mongoose.model('Quote', QuoteSchema); // We are setting this Schema in our Models as 'User'
const Quote = mongoose.model('Quote') // We are retrieving this Schema from our Models, named 'User'

// Use native promises
mongoose.Promise = global.Promise;
var validate = require('mongoose-validator')

// Require body-parser (to receive post data from clients)
const bodyParser = require('body-parser');
// Integrate body-parser with our App
app.use(bodyParser.urlencoded({ extended: true }));
// Require path
const path = require('path');
// Setting our Static Folder Directory
app.use(express.static(path.join(__dirname, './static')));
// Setting our Views Folder Directory
app.set('views', path.join(__dirname, './views'));
// Setting our View Engine set to EJS
app.set('view engine', 'ejs');

var moment = require('moment');

// Routes
// Root Request
app.get('/', function(req, res) {
    res.render('index');
})
// Add User Request 
app.post('/quotes', function(req, res) {
    console.log("POST DATA", req.body);

    var quote = new Quote({name: req.body.name, text: req.body.quote});
    quote.save(function(err) {
    	if (err) {
    		console.log("Something went wrong");
    		res.render('index',{errors: quote.errors});
    	} else {
    		console.log("Successfully added a quote");
		    res.redirect('/');
    	}
    })
})
// Add User Request 
app.get('/quotes', function(req, res) {
    Quote.find({}).sort({'createdAt':'desc'}).exec(function(err, quotes) {
        if (err) {
            console.log("something went wrong");
        } else {
            res.render('quotes', {quotes: quotes, moment: moment});
        }
    });
})
// Setting our Server to Listen on Port: 8000
app.listen(8000, function() {
    console.log("listening on port 8000");
})