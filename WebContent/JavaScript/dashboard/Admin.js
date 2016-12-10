jQuery(document).ready(function(){
	CompraXEstablecimientoXDia();
});

function CompraXEstablecimientoXDia(){
	HGrafico({
		url: contexto + "/Dashboard/Admin/CompraXEstablecimientoXDia.html?",
		id: 'CompraXEstablecimientoXDia',
		tipo: 'bar',
		opt: {
			legend: {
				display: true,
				labels: {
					fontColor: 'rgb(255, 99, 132)',
					backgroundColor: 'rgba(75, 192, 192, 0.4)'
				}
			}
		}
	});
}
//
//{
//	"labels" : ["Enero","Febrero","Marzo","Abril"],
//	"datasets" : [
//	              {
//	            	  "data" : [1,2,3,4]
//	              }
//	             ]
//	
//}
//
//var ctx = document.getElementById("myChart");
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
//    type: 'line',
//    data: barChartData
//});
