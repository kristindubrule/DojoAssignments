var answer_strings = {
						"invalid": "Something went wrong :-(",
						"correct": "Correct",
						"too low": "Too Low",
						"too high": "Too High"
};

$(document).ready(function() {
	$('form').submit(function(e) {
		var params = { number: $('input[name=number]').val() }
		$.ajax( { type: "POST",
			url: '/guess',
			dataType: "json",
			data: JSON.stringify(params),
			contentType: "application/json; charset=utf-8",
			traditional: true,
			success: function(result) {
				$('#answer').text(answer_strings[result["result"]])
				$('#answerbox').css("background-color",result["color"]);
				if (result["result"] == "correct") {
					$('#resetbutton').show();
					$('#inputbox').hide();
				} else { // For the case when a wrong answer follows a correct answer
					$('#resetbutton').hide();
				}
				$('#answerbox').show();
			}
		});
		return false;
	});

	$('#guess').keypress(function(e) {
		var key = e.which;
 		if(key == 13) {  // the enter key code
		    $('#guess').click();
    		return false;  
  		}
	});

	// Why does the input box stick around for initial submit?
	$('input[name=number]').keypress(function(e) {
		var key = e.which;
 		if(key == 13) {  // the enter key code
		    $('#guess').click();
    		return false;  
  		}
	});

	$('#resetbutton').click(function() {
		$('#answerbox').hide();
		$('#inputbox').show();
	});
});