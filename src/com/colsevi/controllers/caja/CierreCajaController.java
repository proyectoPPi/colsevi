package com.colsevi.controllers.caja;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.colsevi.controllers.BaseConfigController;

@Controller
@RequestMapping("/Caja/CierreCaja")
public class CierreCajaController extends BaseConfigController{

	private static final long serialVersionUID = 2407344590292431117L;
	private static Logger logger = Logger.getLogger(CierreCajaController.class);

	@RequestMapping
	public ModelAndView cierre(HttpServletRequest request, ModelMap model){
		return new ModelAndView("caja/Cierre", "co", getValoresGenericos(request));
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/tabla")
	public void tabla(HttpServletRequest request, HttpServletResponse response) throws IOException{
		JSONObject result = new JSONObject();
		
		try{
			String Inicio = request.getParameter("Inicio");
			String Final = request.getParameter("Final");

//			result.put("datos", ConstruirJson(ColseviDao.getInstance().getCierreCajaMapper().selectByExample(new CierreCajaExample())));
			result.put("total", 0);
		
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		
		response.setContentType("text/html;charset=ISO-8859-1");
		request.setCharacterEncoding("UTF8");
		
		result.writeJSONString(response.getWriter());
	}
	
//	@SuppressWarnings("unchecked")
//	public JSONArray ConstruirJson(List<CierreCaja> listaCierre){
//		JSONArray resultado = new JSONArray();
//		JSONObject opciones = new JSONObject();
//		
//		for(CierreCaja bean: listaCierre){
//			try{
//				opciones = new JSONObject();
//				opciones.put("id_cierre_caja", bean.getId_cierre_caja());
//				opciones.put("id_persona", bean.getId_persona());
//				Persona per = ColseviDao.getInstance().getPersonaMapper().selectByPrimaryKey(bean.getId_persona());
//				opciones.put("nombre", per.getNombre() + " " + per.getApellido());
//				opciones.put("mensaje", bean.getMensaje());
//				opciones.put("fecha_ejecucion", UtilidadManager.FormatDateDB(bean.getFecha_ejecucion()));
//				opciones.put("fecha_cierre", UtilidadManager.FormatDateDB(bean.getFecha_cierre()));
//				
//				resultado.add(opciones);
//			}catch(Exception e){
//				logger.error(e.getMessage());
//				continue;
//			}
//		}
//		
//		return resultado;
//	}
	
//	
//	public void ejecutarCierre(HttpServletRequest request, ModelMap model){
//		
//		PagoProveedor pp = new PagoProveedor();
//		CierreCaja cc = new CierreCaja();
//		List<CompraProveedor> listaC = new ArrayList<CompraProveedor>();
//		List<Pedido> listaP = new ArrayList<Pedido>();
//		CompraProveedorExample CE = new CompraProveedorExample();
//		PedidoExample PE = new PedidoExample();
//		Date inicio = getStartOfDay(new Date(System.currentTimeMillis()));
//		Date fin = getEndOfDay(new Date(System.currentTimeMillis()));
//		
//		
//		CE.createCriteria().andFecha_compraBetween(inicio, fin);
//		listaC = ColseviDao.getInstance().getCompraProveedorMapper().selectByExample(CE);
//		
//		for(CompraProveedor bean: listaC){
//			if(bean.getPagado()){
//
//				ProveedorManager.InsertarPago(bean.getId_compra_proveedor(), new Date(), new  BigDecimal(0), bean.getValor(), "Pago completo al proveedor");
//			
//			}else{
//				dp = new DeudaProveedor();
//				dp.setId_compra(bean.getId_compra_proveedor());
//				dp.setFecha_deuda(new Date());
//				dp.setPendiente(bean.getValor());
//				dp.setObservacion("Deuda de la compra al proveedor");
//				
//				ColseviDao.getInstance().getDeudaProveedorMapper().insertSelective(dp);
//			}
//		}
//		
//		ColseviDao.getInstance().getInventarioXMateriaMapper().deleteByExample(new InventarioXMateriaExample());
//		
//		List<Integer> estados = new ArrayList<Integer>();
//		estados.add(2);
//		PE.createCriteria().andId_estado_pedidoNotIn(estados).andFecha_pedidoBetween(inicio, fin);
//		listaP = ColseviDao.getInstance().getPedidoMapper().selectByExample(PE);
//		
//		for(Pedido ped: listaP){
//			ped.setId_estado_pedido(1);
//			ColseviDao.getInstance().getPedidoMapper().updateByPrimaryKey(ped);
//		}
//		
//		cc.setFecha_cierre(new Date(System.currentTimeMillis()));
//		cc.setFecha_ejecucion(new Date());
//		cc.setId_persona(getUsuario(request).getPersona());
//		cc.setMensaje(request.getParameter("mensaje"));
//		
//		ColseviDao.getInstance().getCierreCajaMapper().insertSelective(cc);
//		
//	}
//	
	 private Date getStartOfDay(Date date) {
		    Calendar calendar = Calendar.getInstance();
		    calendar.setTime(date);
		    int year = calendar.get(Calendar.YEAR);
		    int month = calendar.get(Calendar.MONTH);
		    int day = calendar.get(Calendar.DATE);
		    calendar.set(year, month, day, 0, 0, 0);
		    return calendar.getTime();
		}

		private Date getEndOfDay(Date date) {
		    Calendar calendar = Calendar.getInstance();
		    calendar.setTime(date);
		    int year = calendar.get(Calendar.YEAR);
		    int month = calendar.get(Calendar.MONTH);
		    int day = calendar.get(Calendar.DATE);
		    calendar.set(year, month, day, 23, 59, 59);
		    return calendar.getTime();
		}

}