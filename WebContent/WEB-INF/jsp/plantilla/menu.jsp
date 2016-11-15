
<div class="header-container">
	<div class="header-top dark" id="menuHeader">
		<div class="container">
			<div class="row">
				<div class="col-xs-9 col-sm-6 col-md-3">
					<div id="header-top-second" class="clearfix">
						<div class="header-top-dropdown text-right">
							<div class="btn-group">
								<a href="${pageContext.request.contextPath}/Usuario/ClienteRegistro.html" class="btn btn-default btn-sm"><i class="fa fa-user pr-10"></i> Registro</a>
								<a data-toggle="modal" href="#ModalLogin" class="btn btn-default btn-sm"><i class="fa fa-lock pr-10"></i> Login</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<header class="header fixed clearfix">
		<div class="container">
			<div class="row">
				<div class="col-md-3 ">
					<div class="header-left clearfix">
						<div id="logo" class="logo">
							<a href="index.html"><img id="logo_img" src="images/logo_light_blue.png" alt="The Project"></a>
						</div>
						<div class="site-slogan">
							Colsevi PPI
						</div>
					</div>
				</div>
				<div class="col-md-9">
					<div class="header-right clearfix">
					<div class="main-navigation animated with-dropdown-buttons">
		
						<nav class="navbar navbar-default" role="navigation">
							<div class="container-fluid">
								<div class="navbar-header">
									<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-collapse-1">
										<span class="sr-only">Toggle navigation</span>
										<span class="icon-bar"></span>
										<span class="icon-bar"></span>
										<span class="icon-bar"></span>
									</button>
								</div>
								<div class="collapse navbar-collapse" id="navbar-collapse-1">
									<ul class="nav navbar-nav">
										${col.menu}
									</ul>
								</div>
							</div>
						</nav>
					</div>
					</div>
				</div>
			</div>
		</div>
	</header>
	
	<div id="menuSecundario">
		<div class="breadcrumb-container">
			<div class="container">
				<ol class="breadcrumb">
					${col.SubMenu}
				</ol>
			</div>
		</div>
	</div>
</div>