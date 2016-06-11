<div class="col-lg-12" id="bread">
    <ul class="breadcrumb">
    	${col.SubMenu}
    </ul>
    <!--breadcrumbs end -->
</div>

<div class="alert alert-danger fade in" id="error" style="display: none;">
	<button data-dismiss="alert" class="close close-sm" type="button">
		<i class="fa fa-times"></i>
	</button>
	<strong>Error! </strong> <div id="mensajeE">${error}</div>
</div>
<div class="alert alert-success fade in" id="correcto" style="display: none;">
	<button data-dismiss="alert" class="close close-sm" type="button">
		<i class="fa fa-times"></i>
	</button>
	<strong>Correcto! </strong> <div id="mensajeC">${correcto}</div>
</div>
<div class="alert alert-info fade in" id="info" style="display: none;">
	<button data-dismiss="alert" class="close close-sm" type="button">
		<i class="fa fa-times"></i>
	</button>
	<strong>Informacion! </strong> ${info}
</div>
<div class="alert alert-warning fade in" id="peligro" style="display: none;">
	<button data-dismiss="alert" class="close close-sm" type="button">
		<i class="fa fa-times"></i>
	</button>
	<strong>Peligro! </strong> ${peligro}
</div>


<div class="modal fade modal-dialog-center " id="ModalCargando" style="z-index: 100000;"
	tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content-wrap">
			<div class="modal-content modal-lg">
				<div class="modal-body">
					<div class="cssload-thecube">
						<div class="cssload-cube cssload-c1"></div>
						<div class="cssload-cube cssload-c2"></div>
						<div class="cssload-cube cssload-c4"></div>
						<div class="cssload-cube cssload-c3"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>