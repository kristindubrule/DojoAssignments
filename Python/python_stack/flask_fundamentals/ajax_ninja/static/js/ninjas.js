var colors = { "blue": "leonardo.jpg",
			"purple": "donatello.jpg",
			"orange": "michelangelo.jpg",
			"red": "raphael.jpg",
			"invalid": "notapril.jpg"};

function adjustImages(showcolor) {
	$('img#'+showcolor).show();
	$('#selection').html("You chose "+$('img#'+showcolor).attr("turtle"));
	for (othercolor in colors) {
		if (othercolor != showcolor) {
			$('img#'+othercolor).hide();
		}
	}
}

$(document).ready(function() {
	$('input.color').click(function () {
		var params = { color: $(this).attr('name') }
		// $.getJSON( $SCRIPT_ROOT + '/changecolor', 
		// 			params,
		// 			function (result) {
		// 	adjustImages(result["color"]);
		// });

		$.ajax( { type: "POST",
					url: '/changecolor',
					dataType: "json",
					data: JSON.stringify(params),
					contentType: "application/json; charset=utf-8",
					traditional: true,
					success: function(result) {
						adjustImages(result["color"]);
					}
				});
	});

	$('input[name=enter]').click(function () {
		var validColor = false;
		var params = { color: $('input[name=customcolor]').val() }
		$.ajax( { type: "POST",
					url: $SCRIPT_ROOT + '/changecolor',
					dataType: "json",
					data: JSON.stringify(params),
					contentType: "application/json; charset=utf-8",
					traditional: true,
					success: function(result) {
						for (color in colors) {
							if (color == result["color"]) {
								adjustImages(result["color"]);
								validColor = true;
								break;
							}
						}
						if (!validColor) {
							adjustImages("invalid");
						}	
					}
				});
		// $.getJSON( $SCRIPT_ROOT + '/changecolor', 
		// 			params,
		// 			function (result) {
		// 	for (color in colors) {
		// 		if (color == result["color"]) {
		// 			adjustImages(result["color"]);
		// 			validColor = true;
		// 			break;
		// 		}
		// 	}
		// 	if (!validColor) {
		// 		adjustImages("invalid");
		// 	}		
		// });
		return false;
	});

	$('input[name=reset]').click(function () {
		$('img').show();
		$('#selection').html("All the turtles!");
	})
});