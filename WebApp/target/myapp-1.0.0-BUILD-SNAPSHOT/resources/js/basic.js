$(document).ready(function() {
	$('#newUser').submit(function() {
		$.ajax({
			url : $("#newUser").attr( "action"),
			type : 'POST',
			dataType : 'json',
			data : $('#newUser').serialize(),
			success : function(data) {
				if (data.isValid) {
					$('#hidetext').show();
				} else {
					alert('You have not written anything!');
				}
			}

		});
			return false;
	});
});
