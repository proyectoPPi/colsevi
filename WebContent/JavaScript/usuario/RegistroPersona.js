jQuery(document).ready(function () {
jQuery('.f1 fieldset:first').fadeIn('slow');
    
    jQuery('.f1 input[type="text"], .f1 input[type="password"], .f1 textarea').on('focus', function() {
    	jQuery(this).removeClass('input-error');
    });
    
    jQuery('.f1 .btn-next').on('click', function() {
    	var parent_fieldset = jQuery(this).parents('fieldset');
    	var next_step = true;
    	var current_active_step = jQuery(this).parents('.f1').find('.f1-step.active');
    	var progress_line = jQuery(this).parents('.f1').find('.f1-progress-line');
    	 if(parent_fieldset.data('step') === 1){
    		 next_step = step1();
    	 }
    	 if(parent_fieldset.data('step') === 4){
    		 next_step = step4();
    	 }
    	if( next_step ) {
    		parent_fieldset.fadeOut(400, function() {
    			current_active_step.removeClass('active').addClass('activated').next().addClass('active');
    			bar_progress(progress_line, 'right');
	    		jQuery(this).next().fadeIn();
    			scroll_to_class( jQuery('.f1'), 20 );
	    	});
    	}
    });
    
    jQuery('.f1 .btn-previous').on('click', function() {
    	var current_active_step = jQuery(this).parents('.f1').find('.f1-step.active');
    	var progress_line = jQuery(this).parents('.f1').find('.f1-progress-line');
    	
    	jQuery(this).parents('fieldset').fadeOut(400, function() {
    		current_active_step.removeClass('active').prev().removeClass('activated').addClass('active');
    		bar_progress(progress_line, 'left');
    		jQuery(this).prev().fadeIn();
			scroll_to_class( jQuery('.f1'), 20 );
    	});
    });
    
    jQuery('#formulario').on('submit', function(e) {
    	jQuery('#clave, #repetir').removeClass('input-error');
    	if(jQuery('#clave').val() !== jQuery('#repetir').val()){
    		jQuery('#clave, #repetir').addClass('input-error');
    		e.preventDefault();
    	}
    	
    	
    	var pswd = jQuery('#clave').val();
        //validate the length
        if ( pswd.length < 8 ) {
       	 jQuery('#clave').addClass('input-error');
    		e.preventDefault();
        } 

        //validate letter
        if ( pswd.match(/[A-z]/) ) {
            
        } else {
       	 jQuery('#clave').addClass('input-error');
    		e.preventDefault();
        }

        //validate capital letter
        if ( pswd.match(/[A-Z]/) ) {
            
        } else {
       	 jQuery('#clave').addClass('input-error');
    		e.preventDefault();
        }

        //validate number
        if ( pswd.match(/\d/) ) {
          
        } else {
       	 jQuery('#clave').addClass('input-error');
    		e.preventDefault();
        }
    	
    });
});

function scroll_to_class(element_class, removed_height) {
	var scroll_to = jQuery(element_class).offset().top - removed_height;
	if(jQuery(window).scrollTop() != scroll_to) {
		jQuery('html, body').stop().animate({scrollTop: scroll_to}, 0);
	}
}

function bar_progress(progress_line_object, direction) {
	var number_of_steps = progress_line_object.data('number-of-steps');
	var now_value = progress_line_object.data('now-value');
	var new_value = 0;
	if(direction == 'right') {
		new_value = now_value + ( 100 / number_of_steps );
	}
	else if(direction == 'left') {
		new_value = now_value - ( 100 / number_of_steps );
	}
	progress_line_object.attr('style', 'width: ' + new_value + '%;').data('now-value', new_value);
}

function step1(){
	var result = true;
	jQuery('#nombre, #apellido, #genero, #tipo_doc, #documento').removeClass('input-error');
	var campo = jQuery('#nombre');
	if(campo.val().trim() === ''){
		campo.addClass('input-error');
		result = false;
	}
	campo = jQuery('#apellido');
	if(campo.val().trim() === ''){
		campo.addClass('input-error');
		result = false;
	}
	campo = jQuery('#genero');
	if(campo.val() === '0'){
		campo.addClass('input-error');
		result = false;
	}
	campo = jQuery('#tipo_doc');
	if(campo.val() === '0'){
		campo.addClass('input-error');
		result = false;
	}
	campo = jQuery('#documento');
	if(campo.val().trim() === ''){
		campo.addClass('input-error');
		result = false;
	}
	return result;
}
