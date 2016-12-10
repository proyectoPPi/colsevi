jQuery(document).ready(function(){
	CompraXEstablecimientoXDia();
});

function CompraXEstablecimientoXDia(){
	HGrafico({
		url: contexto + "/Dashboard/Admin/CompraXEstablecimientoXDia.html?",
		id: 'CompraXEstablecimientoXDia',
		tipo: 'bar',
		opt: {
            scales: {
                yAxes: [{
                    ticks: {
                        min: 0,
                        beginAtZero: true
                    }
                }]
            }
		}
	});
}


//var ctx = document.getElementById("CompraXEstablecimientoXDia");
//var randomScalingFactor = function(){ return Math.round(Math.random()*500)};
//var barChartData = {
//		labels : ["January","February","March","April","May","June","July"],
//		datasets : [
//			{
//				backgroundColor: 'rgba(255, 99, 132, 0.2)',
//				data : [randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor()]
//			},
//			{
//				backgroundColor: 'rgba(215, 40, 40, 0.2)',
//				data : [randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor()]
//			}
//		]
//	}
//
//
//var scatterChart = new Chart(ctx, {
//    type: 'bar',
//    data: barChartData
//});
