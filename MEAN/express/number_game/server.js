// require expresscopy
const express = require("express");
// path module -- try to figure out where and why we use this
const path = require("path");
// create the express app
const app = express();
const bodyParser = require('body-parser');

const session = require('express-session');
app.use(session({secret: 'codingdojorocks'}));

// use it!
app.use(bodyParser.urlencoded({ extended: true }));
// static content
app.use(express.static(path.join(__dirname, "./static")));

// setting up ejs and our views folder
app.set('views', path.join(__dirname, './views'));
app.set('view engine', 'ejs');

// root route to render the index.ejs view
app.get('/', function(req, res) {
	if (!req.session.number) {
		req.session.number = Math.floor(Math.random()*99+1);
	}
	console.log(req.session.number);
	res.render("index");
})

app.post('/guess', function(req, res) {
	let result = "error";
	if (req.body.guess > req.session.number) {
		result = "high";
		message = "Too High!";
	} else if (req.body.guess < req.session.number) {
		result = "low";
		message = "Too Low!";
	} else {
		result = "correct";
		message = req.body.guess + " was the number!";
	}
	res.render("index", {guess: req.body.guess, result: result, message: message});
})

app.get('/reset', function(req,res) {
	req.session.number = undefined;
	res.redirect('/');
});

// tell the express app to listen on port 8000
app.listen(8000, function() {
 console.log("listening on port 8000");
});
