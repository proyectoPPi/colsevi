<style>
	.is-countdown {
		border: 1px solid #ccc !important;
		background-color: #e6e7e8 !important;
		height:45px !important;
	}
	.countdown-timer{
		font-size:200% !important;
		background-color: #ffb347;
		border-color: #f3f3f3;
	}
	
	.countdown-label{
		text-align: justify;
	    text-justify: inter-word;
		vertical-align: text-bottom;
		margin-top:10px;
	}
	
	.countdown-expiration{
		text-align: justify;
	    text-justify: inter-word;
		vertical-align: text-bottom;
	}

</style>

<script type="text/javascript">
	var date=new Date(new Date().getTime()+50000);
	$('#selector').countdown(date, function(event) {
	   $(this).html(event.strftime('<div class="col-xs-9 col-sm-9 col-md-9 countdown-label"><b>123</b></div><div class="col-xs-3 col-sm-3 col-md-3 text-right countdown-timer"> %M:%S</div>'));
	 }).on('finish.countdown', function(event) {
		if (typeof endTimer == 'function') { 
			$(this).parent().addClass('disabled');
			endTimer();		
		}else{
			console.log('You must implement the method endTImer in order to do something when timer has been ended')
		}
	 });
	
	 function stopTimer(){
		$('#selector').countdown('stop');
	 }
	 
	 function endTimer (){
		 alert(12);
	 }

</script>

<div id="selector"></div>