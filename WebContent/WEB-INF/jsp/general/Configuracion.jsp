<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2>Configuración Inicial 
</h2>
<div class="separator-2"></div>

<div class="row">
	<section class="panel">
		<header class="panel-heading tab-bg-dark-navy-blue">
		    <ul class="nav nav-tabs">
		        <li class="active">
		            <a data-toggle="tab" href="#general">General</a>
		        </li>
		        <li class="">
		            <a data-toggle="tab" href="#otros">Producto</a>
		        </li>
		    </ul>
		</header>
        <div class="panel-body">
            <div class="tab-content"> 
            	<div id="general" class="tab-pane active">
            	
            		<section class="panel">
						<div class="panel-body">
							<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
								<label>Código</label>
								<input type="text" class="form-control" id="codigoF" name="filtro"/>
							</div>
					
							<div class="col-xs-12 col-md-12 col-lg-12"><br/>
								<button class="btn btn-button" onclick="Tabla(1);">Filtrar</button>
							</div>	
						</div>	
					</section>
            		
            		<div class=" col-xs-12 col-md-12 col-lg-12">
	      				<div id="tabla"></div>
						<div id="paginacion" class="col-md-12"></div>
					</div>
                </div>
               <div id="otros" class="tab-pane">
               	<div class="col-xs-12">
               		<span><strong>Reconstruir las configuraciones del sistema</strong></span> <br/>
               		<button type="button" class="btn btn-gray-transparent" id="configuracionRegenerar">Construir</button>
               	</div>
               </div>
            </div>
         </div>
	</section>
</div>

<div class="modal fade modal-dialog-center " id="ModalFormulario" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content-wrap">
			<form method="post" action="${pageContext.request.contextPath}/general/Configuracion/Guardar.html?" 
				id="Formulario" data-modal="ModalFormulario" data-tabla="Tabla">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fa fa-times-circle"></i></button>
						<h4 class="modal-title">Configurar...</h4>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label>*C&#243;digo</label>
							<input type="text" readonly class="form-control" id="codigo" name="codigo"/>
						</div>
						<div class="form-group">
							<label>*Valor</label>
							<input type="text" class="form-control" id="valor" name="valor"/>
						</div>
						<div class="form-group">
							<label>Descripci&#243;n</label>
							<input type="text" class="form-control" id="descripcion" name="descripcion"/>
						</div>
					</div>
					<div class="modal-footer">
						<button class="btn btn-warning" type="button" onclick="validarFormulario();">Guardar</button>
						<button data-dismiss="modal" class="btn btn-gray-transparent" type="button" onclick="Limpiar();">Cerrar</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
		
<script type="text/javascript">
	titulos = new Array();
	titulos["id"] = "ID";
	titulos["codigo"] = "Nombre";
	titulos["valor"] = "Valor";
	titulos["descripcion"] = "Descripci&#243;n";
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/JavaScript/general/Configuracion.js"></script>