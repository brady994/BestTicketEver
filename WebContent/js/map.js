function initMap() {
	var map = new google.maps.Map(document.getElementById('map'), {
		zoom : 8,
		center : {
			lat : 0.00,
			lng : 0.00
		}
	});
	var geocoder = new google.maps.Geocoder();

	$(document).on('click','#seemap', function() {
		geocodeAddress(geocoder, map);
	});
}

function geocodeAddress(geocoder, resultsMap) {
	// var address = document.getElementById('address').value;
	var d = $("#location").text();
	geocoder.geocode({
		'address' : d
	}, function(results, status) {
		if (status === 'OK') {
			resultsMap.setCenter(results[0].geometry.location);
			var marker = new google.maps.Marker({
				map : resultsMap,
				position : results[0].geometry.location
			});
		} else {
			alert('Geocode was not successful for the following reason: '
					+ status);
		}
	});
}

