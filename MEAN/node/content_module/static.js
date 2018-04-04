const fs = require("fs");

module.exports = function (request,response) {
	 if (request.url.endsWith("png")) {
 		fs.readFile("."+request.url, function(errors, contents){
            response.writeHead(200, {'Content-type': 'image/png'});
            response.write(contents);
            response.end();
        });
 	} else {
		let content_type;
		let path = "."+request.url;
	 	if (request.url.endsWith("html") || request.url === '/') {
			content_type = "text/html";
			path = "./views/";
			if (request.url == '/') {
				path += "index.html";
			} else {
				path += request.url;
			}
		} else if (request.url.endsWith("css")) {
			content_type = "text/css";
		}
		fs.readFile(path, 'utf8', function (errors, contents) {
			response.writeHead(200, {'Content-type': content_type});
		   	response.write(contents); 
		   	response.end();
	    });
	} 
	return;
}