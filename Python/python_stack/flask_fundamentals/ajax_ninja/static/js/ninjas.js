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
	$('input[type=button]').click(function () {
		var color = $(this).attr("name");
		adjustImages(color);
	});

	$('input[name=submit]').click(function () {
		var validColor = false;
		for (color in colors) {
			if (color == $('input[name=customcolor]')) {
				adjustImages($('input[name=customcolor]'));
				validColor = true;
				break;
			}
		}
		if (!validColor) {
			adjustImages("invalid");
		}
	});

	$('input[name=reset]').click(function () {
		$('img').show();
		$('#selection').html("All the turtles!");
	})
});