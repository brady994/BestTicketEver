$(document).ready(function(e){
    $('.search-panel .dropdown-menu').find('a').click(function(e) {
		e.preventDefault();
		var concept = $(this).text();
		$('.search-panel span#search_concept').text(concept);
		// $('.input-group #search_param').val(param);
	});
<<<<<<< HEAD



=======
>>>>>>> branch 'master' of https://github.com/brady994/TicketsBest.git
});
