<div class="col-lg-12" id="bread">
    <ul class="breadcrumb">
    	${col.SubMenu}
    </ul>
    <!--breadcrumbs end -->
</div>

<div class="alert alert-block alert-danger fade in" id="error" style="display: none;">
	<button data-dismiss="alert" class="close close-sm" type="button">
		<i class="fa fa-times"></i>
	</button>
	<strong>Error! </strong> ${error}
</div>
<div class="alert alert-success fade in" id="correcto" style="display: none;">
	<button data-dismiss="alert" class="close close-sm" type="button">
		<i class="fa fa-times"></i>
	</button>
	<strong>Correcto! </strong> ${correcto}
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