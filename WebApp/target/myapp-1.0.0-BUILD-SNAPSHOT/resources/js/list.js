var nov = nov || {};

nov.showDeleteModal = function() {	
	$('.more').click(function() {
		var id = $(this).next('.item-id').val();		
					var formEl = $('#delete-item-form');
					formEl.find('[name=id]').val(id);
					formEl.submit();
					//$(this).dialog("close");									
		});
};

$(document).ready(nov.showDeleteModal);


