$(document).ready(function() {
	$('.message').click(function() {
		var message_id = $(this).attr("message_id");
		$('#'+message_id).toggle();
	})

});