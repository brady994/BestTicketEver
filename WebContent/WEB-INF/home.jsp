<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Tickets Best</title>
<!--  BOOSTRAP CSS -->
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<!--  DATEPICKER CSS -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.1/css/bootstrap-datepicker.min.css"
	rel="stylesheet">

<!-- Custom CSS -->
<link rel="stylesheet" href="css/lavish-bootstrap.css">
<link href="css/star-rating.css" media="all" rel="stylesheet"type="text/css" />
<link rel="stylesheet" href="css/style.css">


</head>

<body>

	<div class="container-fluid">
		<!-- Include header -->
		<%@ include file="content/header.html"%>
		<!-- Include Content -->

		<jsp:include page="${page}" />
	
	</div>
	

	
	<!--  JQUERY -->
	<script src="https://code.jquery.com/jquery-2.2.4.min.js"
		type="text/javascript"></script>
	<!-- BOOTSTRAP -->
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
		type="text/javascript"></script>
	<!-- DATEPICKER -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.1/js/bootstrap-datepicker.min.js"
		type="text/javascript"></script>
	<script src="js/account.js" type="text/javascript"></script>
	<script src="js/user.js" type="text/javascript"></script>
	<script src="js/cart.js" type="text/javascript"></script>
	<script src="js/index.js" type="text/javascript"></script>
	<script src="js/search.js" type="text/javascript"></script>
	<script src="js/organizer.js" type="text/javascript"></script>
	<script src="js/star-rating.js" type="text/javascript"></script>
<script src="js/map.js" type="text/javascript"></script>
	<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyApJSgC5q_fg6F_dsZwJDBUBLPHu01T7C4&callback=initMap"> </script>


	<script>
		$('input[type="date"]').datepicker({
			format: 'yyyy-mm-dd',
			maxViewMode : 2,
			daysOfWeekDisabled : "0",
			daysOfWeekHighlighted : "0",
			orientation : "bottom auto",
			autoclose : true
		});
		$( 'input[type="date"]' ).datepicker( "option", "dateFormat", "yy-mm-dd" );
		
	</script>
</body>
</html>