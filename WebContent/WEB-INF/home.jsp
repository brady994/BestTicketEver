<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>One More Ticket</title>
<!--  BOOSTRAP CSS -->
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet">
	<!--  DATEPICKER CSS -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.1/css/bootstrap-datepicker.min.css"
	rel="stylesheet">
	
<!-- Custom CSS -->
<link rel="stylesheet" href="css/lavish-bootstrap.css">
<link rel="stylesheet" href="css/style.css" >

</head>

<body>
	
	<div class="container-fluid">
		<!-- Include header -->
		<%@ include file="content/header.html"%>
		<!-- Include Content -->
		
		<jsp:include page="${page}"/>
		
		<!-- Include footer -->
	</div>
	<!--  JQUERY -->
	<script src="https://code.jquery.com/jquery-2.2.4.min.js" type="text/javascript"></script>
	<!-- BOOTSTRAP -->
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" type="text/javascript"></script>
			<!-- DATEPICKER -->
		<script
		src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.1/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
	<script src="js/account.js" type="text/javascript"></script>
	<script src="js/user.js" type="text/javascript"></script>
	<script src="js/index.js" type="text/javascript"></script>
	
	<script>
	// move this code somewherelse
	$('.input-group.date').datepicker({
	    maxViewMode: 2,
	    daysOfWeekDisabled: "0",
	    daysOfWeekHighlighted: "0",
	    orientation: "bottom auto",
	    autoclose: true
	});
	</script>
</body>
</html>