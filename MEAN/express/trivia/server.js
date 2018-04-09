"use strict";

const Game = require("./trivia.js");
const Player = require("./player.js");
let newgame = new Game();
let players = [];

// Import express and path modules.
const express = require( "express");
const path = require( "path");
const cookie = require('cookie');

var user_counter = 0;
// Create the express app.
const app = express();
// Define the static folder.
app.use(express.static(path.join(__dirname, "./static")));
// Setup ejs templating and define the views folder.
app.set('views', path.join(__dirname, './views'));
app.set('view engine', 'ejs');
// Root route to render the index.ejs view.
app.get('/', function(req, res) {
	res.render("index");
})
// Start Node server listening on port 8000.
const server = app.listen(8000, function() {
	console.log("listening on port 8000");
});
var io = require('socket.io').listen(server);

io.sockets.on('connection', function (socket) {
    socket.userid = user_counter;
    socket.emit( 'ask_question', { question : newgame.question.text });
	console.log("Client/socket id is: ", socket.id, " user id is ", socket.userid);

	socket.on( "give_id", function(data) {
	    user_counter++;
	    players.push(new Player(data.name,user_counter));
	    socket.emit('your_id', { id : user_counter });
	});

	// all the server socket code goes in here
	// If you don't know where this code is supposed to go reread the above info 
	socket.on( "try_answer", function (data){
		if (newgame.checkAnswer(data.data.answer)) {
	    	socket.emit( 'answer_correct', {});
	    	newgame = new Game();
	    	io.sockets.emit( 'ask_question', { question : newgame.question.text });
		} else {
			socket.emit( 'answer_incorrect', {});
		}
	})
})
