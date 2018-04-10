// Require the Express Module
const express = require('express');
// Create an Express App
const app = express();

const mongoose = require('mongoose');
mongoose.connect('mongodb://localhost/message_board');

const Schema = mongoose.Schema;

const PostSchema = new mongoose.Schema({
    text: { type: String, required: true},
    comments: [ {type: Schema.Types.ObjectId, ref: 'Comment'}]
}, {timestamps: true});

const CommentSchema = new mongoose.Schema({ 
    _post: { type: Schema.Types.ObjectId, ref: 'Post'},
    text: { type: String, required: true }
}, {timestamps: true});

const Post = mongoose.model('Post', PostSchema);
const Comment = mongoose.model('Comment', CommentSchema);

// Use native promises
mongoose.Promise = global.Promise;
var validate = require('mongoose-validator')

// Require body-parser (to receive post data from clients)
const bodyParser = require('body-parser');
// Integrate body-parser with our App
app.use(bodyParser.urlencoded({ extended: true }));

var moment = require('moment');

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
    Post.find({})
        .populate({path: 'comments', options: { sort: { 'createdAt': -1 } } })
        .sort({'createdAt': -1})
        .exec(function(err, posts) {
        if (err) {
            res.render('index', {errors: [err]});
        } else {
            console.log(posts[0].comments);
            res.render('index', {posts: posts, moment: moment});
        }
    });
});

app.get('/posts/new', function(req,res) {
    res.render("new_post");
})

app.get('/posts/:id', function(req,res) {
    Post.findOne({_id: req.params.id})
        .populate({path: 'comments', options: { sort: { 'created_at': -1 } } })
        .exec(function(err, post) {
            if (err) {
                console.log("something went wrong " + err);
                res.redirect('/');
            } else {
                console.log("Id " + post._id + " Comments " + post.comments);
                res.render('post', { post : post, moment: moment });;
            }
        });
})

app.post('/posts', function(req,res) {
    let post = new Post(req.body);
    post.save(function(err) {
        if (err) {
            res.render("new_post", { errors: post.errors });
        } else {
            res.redirect('/');
        }
    })
})

app.post('/posts/:id/comment', function(req,res) {
    Post.findOne({_id: req.params.id}, function(err, post) {
        var comment = new Comment(req.body);
        comment._post = post._id;
        comment.save(function (err) {
            if (err) {
                console.log('error ' + err);
            } else {
                post.comments.push(comment);
                post.save(function(err) {
                    if (err) {
                        console.log('error ' + err);
                    } else {
                        res.redirect('/');
                    }
                })
            }
        })
    })
});

// Setting our Server to Listen on Port: 8000
app.listen(8000, function() {
    console.log("listening on port 8000");
})
