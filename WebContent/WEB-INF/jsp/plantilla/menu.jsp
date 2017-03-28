
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
								
								<div class="header-dropdown-buttons hidden-xs ">
									<div class="btn-group dropdown">
										<button type="button" class="btn dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
											<i class="fa fa-shopping-cart"></i><span class="cart-count default-bg">0</span>
										</button>
										<ul class="dropdown-menu dropdown-menu-right dropdown-animation cart">
											<li>
												<table class="table table-hover">
													<thead>
														<tr>
															<th class="quantity">Cantidad</th>
															<th class="product">Producto</th>
															<th class="amount">Total</th>
														</tr>
													</thead>
													<tbody>
<!-- 														<tr> -->
<!-- 															<td class="quantity">2 x</td> -->
<!-- 															<td class="product"><a href="shop-product.html">Android 4.4 Smartphone</a><span class="small">4.7" Dual Core 1GB</span></td> -->
<!-- 															<td class="amount">$199.00</td> -->
<!-- 														</tr> -->
<!-- 														<tr> -->
<!-- 															<td class="quantity">3 x</td> -->
<!-- 															<td class="product"><a href="shop-product.html">Android 4.2 Tablet</a><span class="small">7.3" Quad Core 2GB</span></td> -->
<!-- 															<td class="amount">$299.00</td> -->
<!-- 														</tr> -->
<!-- 														<tr> -->
<!-- 															<td class="quantity">3 x</td> -->
<!-- 															<td class="product"><a href="shop-product.html">Desktop PC</a><span class="small">Quad Core 3.2MHz, 8GB RAM, 1TB Hard Disk</span></td> -->
<!-- 															<td class="amount">$1499.00</td> -->
<!-- 														</tr> -->
														<tr>
															<td class="total-quantity" colspan="2">Total 0 Productos</td>
															<td class="total-amount">$000</td>
														</tr>
													</tbody>
												</table>
												<div class="panel-body text-right">
													<a href="shop-checkout.html" class="btn btn-group btn-gray btn-sm">Detalle</a>
												</div>
											</li>
										</ul>
									</div>
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