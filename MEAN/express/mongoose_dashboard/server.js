// Require the Express Module
const express = require('express');
// Create an Express App
const app = express();

const mongoose = require('mongoose');
mongoose.connect('mongodb://localhost/quotes');

const Schema = mongoose.Schema;

const OtterSchema = new mongoose.Schema({
    name: {type: String, required: true},
    age: {type: Number, required: true},
    color: {type: String},
    foods: [{type: String}]
}, {timestamps: true});
const Otter = mongoose.model('otters', OtterSchema);

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

// Routes
// Root Request
app.get('/', function(req, res) {
    Otter.find({}, function(err, otters) {
        if (err) {
            res.render('index', {errors: err});
        } else {
            res.render('index', {otters: otters});
        }
    });
});

app.get('/mongooses/new', function(req,res) {
    res.render("new_otter");
});

app.post('/mongooses', function(req,res) {
    let otter = new Otter(req.body);
    otter.save(function (err) {
        if (err) {
            console.log("something went wrong " + otter.errors);
            res.render("new_otter", {errors: otter.errors});
        } else {
            res.redirect('/');
        }
    });
})

app.get('/mongooses/:id', function(req,res) {
    Otter.findOne({_id:req.params.id}, function(err,otter) {
        if (err) {
            res.render('details', {errors: [err]});
        } else {
            res.render('details', {otter: otter});
        }
    });
});

app.get('/mongooses/edit/:id', function(req,res) {
    Otter.findOne({_id:req.params.id}, function(err,otter) {
        if (err) {
            res.render('index', {errors: [err]});
        } else {
            res.render('edit', {otter: otter});
        }
    });
});

app.post('/mongooses/:id', function(req,res) {
    Otter.findOne({_id:req.params.id}, function(err,otter) {
        if (err) {
            res.render('index', {errors: otter.errors});
        } else {
            otter.name = req.body.name;
            otter.age = req.body.age;
            otter.color = req.body.color;
            otter.foods = req.body.foods.split(",");
            otter.save(function(err_update,otter_updated) {
                if (err_update) {
                    console.log("something went wrong");
                    res.render("edit", {otter: otter, errors: [err_update]});
                } else {
                    res.render("details", {otter: otter});
                }

            });
        }
    });
});

app.get('/mongooses/destroy/:id', function(req,res) {
    Otter.findByIdAndRemove(req.params.id, function(err, otter) {
        if (err) {
            console.log("something went wrong");
            res.render("details", { errors: [err], otter: [otter]} );
        } else {
            res.redirect("/");
        }
    });
});

// Setting our Server to Listen on Port: 8000
app.listen(8000, function() {
    console.log("listening on port 8000");
})
