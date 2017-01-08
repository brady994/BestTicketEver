<div class="container">
	<div tabindex="-1" id="login-auto-close-alert" class="alert hidden">
		<p id="login-alert-text"></p>
	</div>
	<div class="row">
		<div
			class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad">

			<div class="panel panel-info">
				<div class="panel-heading">
					<h3 class="panel-title">Username</h3>
				</div>
				<form id="update-user-form" role="form" method="post">
					<div class="panel-body">
						<div class="row">
							<div class="col-md-1 col-lg-1 " align="center"></div>
							<div class=" col-md-9 col-lg-9 table-responsive">
								<table class="table table-user-information ">
									<tbody>
										<tr>
											<td><label for="username">Username:</label></td>
											<td>${user.username}</td>
										</tr>


										<tr>
											<td><label for="email">Email:</label></td>
											<td>${user.email}</td>
										</tr>

										<tr>
											<td><label for="name">Name:</label></td>
											<td>${user.name}</td>
										</tr>
										<tr>
											<td><label for="surname">Surname:</label></td>
											<td>${user.surname}</td>
										</tr>


									</tbody>
								</table>
							</div>
						</div>
					</div>
					<div class="panel-footer" id="footer-profile">
						<div class="text-right" id="edit-user">
							<a href="" data-original-title="Edit this user" id="edit"
								data-toggle="tooltip" type="button"
								class="btn btn-sm btn-warning text-right"><i
								class="glyphicon glyphicon-edit"></i></a>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
</body>
</html>
