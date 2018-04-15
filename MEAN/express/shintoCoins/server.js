// Require the Express Module
const express = require('express');
const app = express();

// Require body-parser (to receive post data from clients)
const bodyParser = require('body-parser');
app.use(bodyParser.json());
app.use(express.static( __dirname + '/shintoCoins-ang/dist' ));

// Require path
const path = require('path');
app.use(express.static(path.join(__dirname, './static')));

app.all("*", (req,res,next) => {
    res.sendFile(path.resolve("./shintoCoins-ang/dist/index.html"))
  });

// Setting our Server to Listen on Port: 8000
app.listen(8000, function() {
    console.log("listening on port 8000");
})
