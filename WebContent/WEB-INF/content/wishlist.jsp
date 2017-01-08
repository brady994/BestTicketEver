<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">
<div tabindex="-1" id="login-auto-close-alert" class="alert hidden">
		<p id="login-alert-text"></p></div>
	<div id="wishlists">
	<c:choose>
		<c:when test="${empty wishlists}">
		<div class="alert alert-warning">
		<p>We are sorry, you don't have any wishlist, create a new one!</p>
		</div>
		</c:when>
		<c:otherwise>
		<c:forEach items="${wishlists}" var="entry">
			<div class="panel panel-primary panel-table">
				<div class="panel-heading">
					<div class="row">
						<div class="col col-xs-6">
							<h2 class="panel-title">
								<span class="glyphicon glyphicon-star"> </span>
								${entry.value.title}
							</h2>
						</div>
						<div class="col col-xs-6 text-right">
							<a role="button" data-target="${entry.key}" class="delete-wish btn btn-danger"> <i class="fa fa-trash-o"></i></a>
							<button type="button" class="btn btn-info collapsed"
								data-toggle="collapse" data-target="#${entry.key}">
								<i class="hidden-xs glyphicon glyphicon-chevron-down"></i> Show Tickets
							</button>
						</div>
					</div>
				</div>
				<div class="panel-collapse collapse" id="${entry.key}">
					<div class="panel-body">
						<div class="table-container table-responsive">
							<table class="table">
								<tbody>

								</tbody>

							</table>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
		</c:otherwise>
		</c:choose>
	</div>
	<div class="row">
		<div class="col col-sm-4 col-sm-offset-4 col-xs-10 col-xs-offset-1">
			<div  class="panel panel-primary">
				<div class="panel-heading">
					<button class="btn btn-info  btn-block" id="addwishlist">Add
						new Wishlist</button>
				</div>

				<div id="addwishlist-body" class="panel-body hidden">
					<form tabindex="-1" id="addwishlist-form">
						<div class="input-group ">
							<input type="text" class="form-control" id="title" name="title"
								placeholder="Wishlist Title"> <span
								class="input-group-btn">
								<button type="submit" class="btn btn-success">Add</button>
							</span>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
