<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8" />
	<title>Cliente</title>
	<c:import url="/WEB-INF/jsp/plantilla/estilos_genericos.jsp" />
	<link href="${pageContext.request.contextPath}/resources/css/estilosWizard.css" rel="stylesheet"/>
	 <style>
      html, body {
        height: 100%;
        margin: 0;
        padding: 0;
      }
      #map {
        height: 100%;
      }
.controls {
  margin-top: 10px;
  border: 1px solid transparent;
  border-radius: 2px 0 0 2px;
  box-sizing: border-box;
  -moz-box-sizing: border-box;
  height: 32px;
  outline: none;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.3);
}

#pac-input {
  background-color: #fff;
  font-family: Roboto;
  font-size: 15px;
  font-weight: 300;
  margin-left: 12px;
  padding: 0 11px 0 13px;
  text-overflow: ellipsis;
  width: 300px;
}

#pac-input:focus {
  border-color: #4d90fe;
}

.pac-container {
  font-family: Roboto;
}

#type-selector {
  color: #fff;
  background-color: #4d90fe;
  padding: 5px 11px 0px 11px;
}

#type-selector label {
  font-family: Roboto;
  font-size: 13px;
  font-weight: 300;
}

    </style>
</head>
<body>
	<c:import url="/WEB-INF/jsp/plantilla/encabezado.jsp"></c:import>
	<section id="container" class="" style="background: white;">
		<c:import url="/WEB-INF/jsp/plantilla/menu.jsp"></c:import>
		<section id="main-content">
			<section class="wrapper">
				<div class="row">
				<c:import url="/WEB-INF/jsp/plantilla/alertas.jsp"></c:import>
							<h2 style="text-align: center;"><strong>REGISTRO DE CLIENTES</strong></h2>
					        <div class="wizard">
					            <div class="wizard-inner">
					                <div class="connecting-line"></div>
					                <ul class="nav nav-tabs" role="tablist">
					
					                    <li role="presentation" class="active">
					                        <a href="#step1" data-toggle="tab" aria-controls="step1" role="tab" title="Step 1">
					                            <span class="round-tab">
					                                <i class="fa fa-user"></i>
					                            </span>
					                        </a>
					                    </li>
					
					                    <li role="presentation" class="disabled">
					                        <a href="#step2" data-toggle="tab" aria-controls="step2" role="tab" title="Telefonos">
					                            <span class="round-tab">
					                                <i class="fa fa-phone"></i>
					                            </span>
					                        </a>
					                    </li>
					                    <li role="presentation" class="disabled">
					                        <a href="#step3" data-toggle="tab" aria-controls="step3" role="tab" title="Step 3">
					                            <span class="round-tab">
					                                <i class="fa fa-road"></i>
					                            </span>
					                        </a>
					                    </li>
					
					                    <li role="presentation" class="disabled">
					                        <a href="#complete" data-toggle="tab" aria-controls="complete" role="tab" title="Complete">
					                            <span class="round-tab">
					                                <i class="fa fa-ok"></i>
					                            </span>
					                        </a>
					                    </li>
					                </ul>
					            </div>
					
					            <form role="form">
					                <div class="tab-content">
					                    <div class="tab-pane active" role="tabpanel" id="step1">
					                        <div class="col-md-6">
						                        <label>Nombre</label>
						                        <input type="text" class="form-control" id="nombre" name="nombre"/>
						                    </div>
						                    <div class="col-md-6">
						                        <label>Apellido</label>
						                        <input type="text" class="form-control" id="apellido" name="apellido"/>
						                    </div>
						                    <div class="col-md-6">
						                        <label>Genero</label>
						                        <select class="form-control" id="genero" name="genero">
						                        	<option value="0">Seleccione</option>
						                        	<option value="1">Masculino</option>
						                        	<option value="2">Femenino</option>
						                        </select>
						                    </div>
						                    <div class="col-md-6">
												<label>Tipo Doc</label>
												<select class="form-control" id="tipoDoc" name="tipoDoc">
													<option value="0">Seleccione</option>
													<c:forEach items="${listaTD}" var="doc">
														<option value="${doc.id_tipo_documento}">${doc.nombre}</option>
													</c:forEach>
												</select>
											</div>
						                    <div class="col-md-6">    
						                        <label>Documento</label>
						                        <input type="text" class="form-control" id="documento" name="documento"/>
					                        </div>
					                        <ul class="col-md-12 pull-right">
					                        	<br/>
					                            <li><button type="button" class="btn btn-primary next-step">Siguiente</button></li>
					                        </ul>
					                    </div>
					                    <div class="tab-pane" role="tabpanel" id="step2">
					                         <div class="col-md-6">    
						                        <label>Telefono Fijo</label>
						                        <input type="number" class="form-control" id="telFijo" name="telFijo"/>
					                        </div>
					                        <div class="col-md-6">    
						                        <label>Telefono Celular</label>
						                        <input type="number" class="form-control" id="telCel" name="telCel"/>
					                        </div>
					                        <div class="col-md-6">    
						                        <label>Telefono Contacto</label>
						                        <input type="number" class="form-control" id="telCon" name="telCon"/>
					                        </div>
					                        
					                        <ul class=" list-inline pull-right col-md-12">
					                        	<br/>
					                            <li><button type="button" class="btn btn-default prev-step">Anterior</button></li>
					                            <li><button type="button" class="btn btn-primary next-step">Siguiente</button></li>
					                        </ul>
					                    </div>
					                    <div class="tab-pane" role="tabpanel" id="step3">
						                        <input id="pac-input" class="controls" type="text" placeholder="Enter a location">
											    <div id="type-selector" class="controls">
											      <input type="radio" name="type" id="changetype-all" checked="checked">
											      <label for="changetype-all">All</label>
											
											      <input type="radio" name="type" id="changetype-establishment">
											      <label for="changetype-establishment">Establishments</label>
											
											      <input type="radio" name="type" id="changetype-address">
											      <label for="changetype-address">Addresses</label>
											
											      <input type="radio" name="type" id="changetype-geocode">
											      <label for="changetype-geocode">Geocodes</label>
											    </div>
											    <div id="map"></div>
					                        
					                        <ul class=" list-inline pull-right col-md-12">
					                        	<br/>
					                            <li><button type="button" class="btn btn-default prev-step">Anterior</button></li>
					                            <li><button type="button" class="btn btn-primary btn-info-full next-step">Siguiente</button></li>
					                        </ul>
					                    </div>
					                    <div class="tab-pane" role="tabpanel" id="complete">
					                        <div class="col-md-12">    
						                        <label>Usuario</label>
						                        <input type="text" class="form-control" id="telCon" name="telCon"/>
					                        </div>
					                        <div class="col-md-6">    
						                        <label>Contraseña</label>
						                        <input type="text" class="form-control" id="telCon" name="telCon"/>
					                        </div>
					                        <div class="col-md-6">    
						                        <label>Confirmar contraseña</label>
						                        <input type="text" class="form-control" id="telCon" name="telCon"/>
					                        </div>
					                        <ul class=" list-inline pull-right col-md-12">
					                        	<br/>
					                            <li><button type="button" class="btn btn-default prev-step">Anterior</button></li>
					                            <li><button type="button" class="btn btn-default">Finalizar</button></li>
					                        </ul>
					                    </div>
					                    <div class="clearfix"></div>
					                </div>
					            </form>
					        </div>
					
				</div>
			</section>
		</section>
		<c:import url="/WEB-INF/jsp/plantilla/pie_pagina.jsp"></c:import>
	</section>
	<c:import url="/WEB-INF/jsp/plantilla/javascript_genericos.jsp"></c:import>
	 <script>
function initMap() {
  var map = new google.maps.Map(document.getElementById('map'), {
    center: {lat: -33.8688, lng: 151.2195},
    zoom: 13
  });
  var input = /** @type {!HTMLInputElement} */(
      document.getElementById('pac-input'));

  var types = document.getElementById('type-selector');
  map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);
  map.controls[google.maps.ControlPosition.TOP_LEFT].push(types);

  var autocomplete = new google.maps.places.Autocomplete(input);
  autocomplete.bindTo('bounds', map);

  var infowindow = new google.maps.InfoWindow();
  var marker = new google.maps.Marker({
    map: map,
    anchorPoint: new google.maps.Point(0, -29)
  });

  autocomplete.addListener('place_changed', function() {
    infowindow.close();
    marker.setVisible(false);
    var place = autocomplete.getPlace();
    if (!place.geometry) {
      window.alert("Autocomplete's returned place contains no geometry");
      return;
    }

    // If the place has a geometry, then present it on a map.
    if (place.geometry.viewport) {
      map.fitBounds(place.geometry.viewport);
    } else {
      map.setCenter(place.geometry.location);
      map.setZoom(17);  // Why 17? Because it looks good.
    }
    marker.setIcon(/** @type {google.maps.Icon} */({
      url: place.icon,
      size: new google.maps.Size(71, 71),
      origin: new google.maps.Point(0, 0),
      anchor: new google.maps.Point(17, 34),
      scaledSize: new google.maps.Size(35, 35)
    }));
    marker.setPosition(place.geometry.location);
    marker.setVisible(true);

    var address = '';
    if (place.address_components) {
      address = [
        (place.address_components[0] && place.address_components[0].short_name || ''),
        (place.address_components[1] && place.address_components[1].short_name || ''),
        (place.address_components[2] && place.address_components[2].short_name || '')
      ].join(' ');
    }

    infowindow.setContent('<div><strong>' + place.name + '</strong><br>' + address);
    infowindow.open(map, marker);
  });

  // Sets a listener on a radio button to change the filter type on Places
  // Autocomplete.
  function setupClickListener(id, types) {
    var radioButton = document.getElementById(id);
    radioButton.addEventListener('click', function() {
      autocomplete.setTypes(types);
    });
  }

  setupClickListener('changetype-all', []);
  setupClickListener('changetype-address', ['address']);
  setupClickListener('changetype-establishment', ['establishment']);
  setupClickListener('changetype-geocode', ['geocode']);
}

    </script>
	
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCzoZVPlWJZ35wf7uVuZoHw9IRwFnsu74c&signed_in=true&libraries=places&callback=initMap"
        async defer></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/JavaScript/usuario/ClienteRegistro.js"></script>
</body>
</html>