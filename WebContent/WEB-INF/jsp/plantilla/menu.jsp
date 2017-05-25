<div class="header-container">
	<header class="header fixed clearfix">
		<div class="container">
			<div class="row">
				<div class="col-md-2">
					<div class="header-left clearfix">
						<div id="logo" class="logo">
							<a href="${pageContext.request.contextPath}"><img id="logo_img" src="https://raw.githubusercontent.com/proyectoPPi/colsevi/demo1.0/WebContent/resources/menu.PNG" alt="colsevi"></a>
						</div>
					</div>
				</div>
				<div class="col-md-10">
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
								
								<div class="header-dropdown-buttons" id="Login">
									<a data-toggle="modal" href="#ModalLogin" >Login</a>
<%-- 									<a href="${pageContext.request.contextPath}/Usuario/ClienteRegistro.html" class="btn btn-default btn-sm"><i class="fa fa-user pr-10"></i> Registro</a> --%>
								</div>
								
								<div class="header-dropdown-buttons" id="CerrarSesion">
									<a data-toggle="modal" href="${pageContext.request.contextPath}/login/Cerrar.html">Salir</a>
								</div>
								
								<div class="header-dropdown-buttons hidden ">
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
</div>