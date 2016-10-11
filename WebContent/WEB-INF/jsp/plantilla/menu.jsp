
<!-- 		<div id="sidebar" class="nav-collapse "> -->
<!-- 			<ul class="sidebar-menu" id="nav-accordion"> -->
<%-- 				${col.menu} --%>
<!-- 				<li><a -->
<%-- 					href="${pageContext.request.contextPath}/login/Cerrar.html"> <i --%>
<!-- 						class="fa fa-remove"></i> <span>Cerrar Sesión</span> -->
<!-- 				</a></li> -->
<!-- 			</ul> -->
<!-- 		</div> -->
<div class="header-container">
	<div class="header-top dark" id="menuHeader">
		<div class="container">
			<div class="row">
				<div class="col-xs-3 col-sm-6 col-md-9">
					<div class="header-top-first clearfix">
						<ul class="social-links circle small clearfix hidden-xs">
							<li class="twitter"><a target="_blank" href="http://www.twitter.com"><i class="fa fa-twitter"></i></a></li>
							<li class="skype"><a target="_blank" href="http://www.skype.com"><i class="fa fa-skype"></i></a></li>
							<li class="linkedin"><a target="_blank" href="http://www.linkedin.com"><i class="fa fa-linkedin"></i></a></li>
							<li class="googleplus"><a target="_blank" href="http://plus.google.com"><i class="fa fa-google-plus"></i></a></li>
							<li class="youtube"><a target="_blank" href="http://www.youtube.com"><i class="fa fa-youtube-play"></i></a></li>
							<li class="flickr"><a target="_blank" href="http://www.flickr.com"><i class="fa fa-flickr"></i></a></li>
							<li class="facebook"><a target="_blank" href="http://www.facebook.com"><i class="fa fa-facebook"></i></a></li>
							<li class="pinterest"><a target="_blank" href="http://www.pinterest.com"><i class="fa fa-pinterest"></i></a></li>
						</ul>
						<div class="social-links hidden-lg hidden-md hidden-sm circle small">
							<div class="btn-group dropdown">
								<button type="button" class="btn dropdown-toggle" data-toggle="dropdown"><i class="fa fa-share-alt"></i></button>
								<ul class="dropdown-menu dropdown-animation">
									<li class="twitter"><a target="_blank" href="http://www.twitter.com"><i class="fa fa-twitter"></i></a></li>
									<li class="skype"><a target="_blank" href="http://www.skype.com"><i class="fa fa-skype"></i></a></li>
									<li class="linkedin"><a target="_blank" href="http://www.linkedin.com"><i class="fa fa-linkedin"></i></a></li>
									<li class="googleplus"><a target="_blank" href="http://plus.google.com"><i class="fa fa-google-plus"></i></a></li>
									<li class="youtube"><a target="_blank" href="http://www.youtube.com"><i class="fa fa-youtube-play"></i></a></li>
									<li class="flickr"><a target="_blank" href="http://www.flickr.com"><i class="fa fa-flickr"></i></a></li>
									<li class="facebook"><a target="_blank" href="http://www.facebook.com"><i class="fa fa-facebook"></i></a></li>
									<li class="pinterest"><a target="_blank" href="http://www.pinterest.com"><i class="fa fa-pinterest"></i></a></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
				<div class="col-xs-9 col-sm-6 col-md-3">
					<div id="header-top-second" class="clearfix">
						<div class="header-top-dropdown text-right">
							<div class="btn-group">
								<a href="${pageContext.request.contextPath}/Usuario/ClienteRegistro.html" class="btn btn-default btn-sm"><i class="fa fa-user pr-10"></i> Registro</a>
								<a href="${pageContext.request.contextPath}/login.html" class="btn btn-default btn-sm"><i class="fa fa-lock pr-10"></i> Login</a>
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
						
						<div class="header-dropdown-buttons visible-xs">
							<div class="btn-group dropdown">
								<button type="button" class="btn dropdown-toggle" data-toggle="dropdown"><i class="icon-search">Registro</i></button>
								<ul class="dropdown-menu dropdown-menu-right dropdown-animation">
									<li>
										<form role="search" class="search-box margin-clear">
											<div class="form-group has-feedback">
												<input type="text" class="form-control" placeholder="Search">
												<i class="icon-search form-control-feedback"></i>
											</div>
										</form>
									</li>
								</ul>
							</div>
						</div>
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