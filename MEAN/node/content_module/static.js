const fs = require("fs");

module.exports = function (request,response) {
	if (request.url.endsWith("html")) {
		fs.readFile("./views/"+request.url, 'utf8', function (errors, contents) {
			response.writeHead(200, {'Content-type': 'text/html'});
	      	response.write(contents); 
	      	response.end();
      });
	} else if (request.url.endsWith("css")) {
		console.log(request.url);
		fs.readFile("."+request.url, 'utf8', function (errors, contents) {
			response.writeHead(200, {'Content-Type': 'text/css'});
	      	response.write(contents);
	      	response.end();
    	});
	} else if (request.url.endsWith("css")) {
		console.log(request.url);
		fs.readFile("."+request.url, 'utf8', function (errors, contents) {
			response.writeHead(200, {'Content-Type': 'text/css'});
	      	response.write(contents);
	      	response.end();
    	});
	} else {
		response.write("not recognized");
		response.end();
	}
	return;
}