// Require the Express Module
const express = require('express');
// Create an Express App
const app = express();

const mongoose = require('mongoose');
mongoose.connect('mongodb://localhost/restful_tasks');

const ObjectId = require('mongodb').ObjectID;

const TaskSchema = new mongoose.Schema({
    title: String,
    description: String,
    completed: Boolean
}, {timestamps: true});

const Task = mongoose.model('Task', TaskSchema);

// Use native promises
mongoose.Promise = global.Promise;

var validate = require('mongoose-validator')

// Require body-parser (to receive post data from clients)
const bodyParser = require('body-parser');
// Integrate body-parser with our App
app.use(bodyParser.json());
app.use(express.static( __dirname + '/helloangular/dist' ));

// Require path
const path = require('path');
// Setting our Static Folder Directory
app.use(express.static(path.join(__dirname, './static')));

// Routes
// Root Request
app.get('/', function(req, res) {
    Task.find({},function(err, tasks) {
        if (err) {
            res.json({message: "Error", error: err});
        } else {
            res.json({message: "Success", data: tasks});
        }
    });
});

app.post('/tasks', function(req,res) {
    let task = new Task(req.body);
    console.log(req.body.title + " " + task);
    task.save(function (err) {
        if (err) {
            res.json({message: "Error", error: task.errors});
        } else {
            res.json({message: "Success", error: []});
        }
    });
});

app.get('/tasks/:id', function(req,res) {
    Task.findOne({_id: ObjectId(req.params.id)}, function(err,task) { 
        if (err) {
            res.json({message: "Error", error: err});
        } else {
            res.json({message: "Success", data: task});
        }
    })
})

app.put('/tasks/:id', function(req,res) {
    Task.findOne({_id: ObjectId(req.params.id)}, function(err,task) {
        if (err) {
            res.json({message: "Error", error: err});
        } else {
            task.title = req.body.title;
            task.description = req.body.description;
            task.completed = req.body.completed;
            task.save(function(err) {
                if (err) {
                    res.json({message: "Error", error: err});
                } else {
                    res.json({message: "Success"});
                }
            })
        }
    })
})

app.delete('/tasks/:id', function(req,res) {
    Task.findByIdAndRemove({_id: ObjectId(req.params.id)}, function(err,task) {
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
