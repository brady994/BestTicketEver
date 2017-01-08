
<div class="container">
<h2>Create Account</h2>
	<form id="signup-form" class="form-horizontal" method="post" action="#">
		<div id="login-auto-close-alert" class="alert hidden">
		<p id="login-alert-text"></p></div>
		<div class="form-group">
			<label for="name" class="cols-sm-2 control-label">Name</label>
			<div class="cols-sm-10">
				<div class="input-group">
					<span class="input-group-addon"><i class="glyphicon glyphicon-user"
						aria-hidden="true"></i></span> <input type="text" class="form-control"
						name="name" id="name" placeholder="Enter your Name" required="required"/>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="surname" class="cols-sm-2 control-label">Surname</label>
			<div class="cols-sm-10">
				<div class="input-group">
					<span class="input-group-addon"><i class="glyphicon glyphicon-user"
						aria-hidden="true"></i></span> <input type="text" class="form-control"
						name="surname" id="surname" placeholder="Enter your Surname" />
				</div>
			</div>
		</div>

		<div class="form-group">
			<label for="email" class="cols-sm-2 control-label">Email</label>
			<div class="cols-sm-10">
				<div class="input-group">
					<span class="input-group-addon"><i class="glyphicon glyphicon-envelope"
						aria-hidden="true"></i></span> <input type="email" class="form-control"
						name="email" id="email" placeholder="Enter your Email" />
				</div>
			</div>
		</div>

		<div class="form-group">
			<label for="username" class="cols-sm-2 control-label">Username</label>
			<div class="cols-sm-10">
				<div class="input-group">
					<span class="input-group-addon"><i class="glyphicon glyphicon-user"
						aria-hidden="true"></i></span> <input type="text" class="form-control"
						name="username" id="username" placeholder="Enter your Username" />
				</div>
			</div>
		</div>

		<div class="form-group">
			<label for="password" class="cols-sm-2 control-label">Password</label>
			<div class="cols-sm-10">
				<div class="input-group">
					<span class="input-group-addon"><i class="glyphicon glyphicon-lock"
						aria-hidden="true"></i></span> <input type="password"
						class="form-control" name="password" id="password"
						placeholder="Enter your Password" />
				</div>
			</div>
		</div>
		<div class="form-group row">
			<label class="c-input c-radio col-md2 col-xs-2"> Organizer <span
				class="c-indicator"> </span><input
				id="radio1" type="radio" value="Organizer" name="type" /> 
			</label> <label class="c-input c-radio col-md2 col-xs-2"> Customer <span
				class="c-indicator"></span><input
				id="radio2" type="radio" value="Customer" name="type" /> 
			</label>
		</div>

		<div class="form-group ">
			<button type="submit" class="btn btn-primary btn-lg btn-block">Register</button>
		</div>
		<div class="login-register">
			<a class="dropdown-toggle" href="login" role="button" data-toggle="dropdown">Login</a>
		</div>
	</form>
</div>