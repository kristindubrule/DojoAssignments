// get the http module:
var http = require('http');
// fs module allows us to read and write content for responses!!
var fs = require('fs');
// creating a server using http module:
var server = http.createServer(function (request, response){
    // see what URL the clients are requesting:
    console.log('client request URL: ', request.url);
    // this is how we do routing:
    if(request.url === '/cars') {
        fs.readFile('views/cars.html', 'utf8', function (errors, contents){
            response.writeHead(200, {'Content-Type': 'text/html'});  // send data about response
            response.write(contents);  //  send response body
            response.end(); // finished!
        });
    } else if (request.url === '/cats') {
        fs.readFile('views/cats.html', 'utf8', function (errors, contents){
            response.writeHead(200, {'Content-Type': 'text/html'});  // send data about response
            response.write(contents);  //  send response body
            response.end(); // finished!
        });        
    } else if (request.url === '/cars/new') {
        fs.readFile('views/new_car.html', 'utf8', function (errors, contents){
            response.writeHead(200, {'Content-Type': 'text/html'});  // send data about response
            response.write(contents);  //  send response body
            response.end(); // finished!
        });        
    } else if (request.url === '/stylesheets/style.css') {
        fs.readFile('stylesheets/style.css', 'utf8', function (errors, contents){
            response.writeHead(200, {'Content-Type': 'text/css'});  // send data about response
            response.write(contents);  //  send response body
            response.end(); // finished!
        });        
    } else if(request.url === '/images/blkbish.png'){
        // notice we won't include the utf8 encoding
        fs.readFile('./images/blkbish.png', function(errors, contents){
            response.writeHead(200, {'Content-type': 'image/png'});
            response.write(contents);
            response.end();
        })
    } else if(request.url === '/images/whtknight.png'){
        // notice we won't include the utf8 encoding
        fs.readFile('./images/whtknight.png', function(errors, contents){
            response.writeHead(200, {'Content-type': 'image/png'});
            response.write(contents);
            response.end();
        })
    } else if(request.url === '/images/BuickerB.png'){
        // notice we won't include the utf8 encoding
        fs.readFile('./images/BuickerB.png', function(errors, contents){
            response.writeHead(200, {'Content-type': 'image/png'});
            response.write(contents);
            response.end();
        })
    } else if(request.url === '/images/JeepB.png'){
        // notice we won't include the utf8 encoding
        fs.readFile('./images/JeepB.png', function(errors, contents){
            response.writeHead(200, {'Content-type': 'image/png'});
            response.write(contents);
            response.end();
        })
    }
    // request didn't match anything:
    else {
        response.writeHead(404);
        response.end('The URL requested is not available');
    }
});
// tell your server which port to run on
server.listen(6789);
// print to terminal window
console.log("Running in localhost at port 6789");