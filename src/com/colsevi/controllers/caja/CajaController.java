package com.colsevi.controllers.caja;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.colsevi.application.ColseviDao;
import com.colsevi.application.GeneralManager;
import com.colsevi.application.UtilidadManager;
import com.colsevi.controllers.BaseConfigController;
import com.colsevi.dao.caja.model.Caja;
import com.colsevi.dao.caja.model.CajaExample;
import com.colsevi.dao.usuario.model.Persona;

@Controller
@RequestMapping("/Caja")
public class CajaController extends BaseConfigController{

	private static final long serialVersionUID = 2407344590292431117L;
	private static Logger logger = Logger.getLogger(CajaController.class);

	@RequestMapping
	public String caja(HttpServletRequest request, ModelMap model){
		model.addAttribute("listaEstablecimiento", GeneralManager.getEstablecimientos());
		return "caja/Caja";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/tabla")
	public void tabla(HttpServletRequest request, HttpServletResponse response) throws IOException{
		JSONObject result = new JSONObject();
		CajaExample CE = new CajaExample();
		
		try{
			String Inicio = request.getParameter("Inicio");
			String Final = request.getParameter("Final");
			
			CE.setLimit(Inicio + "," + Final);
			result.put("datos", ConstruirJson(ColseviDao.getInstance().getCajaMapper().selectByExample(CE)));
			result.put("total", ColseviDao.getInstance().getCajaMapper().countByExample(CE));
		
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		
		ResponseJson(request, response, result);
	}
	
	@SuppressWarnings("unchecked")
	public JSONArray ConstruirJson(List<Caja> listaCaja){
		JSONArray result = new JSONArray();
		JSONObject options = new JSONObject(), labels = new JSONObject();
		
		for(Caja bean: listaCaja){
			try{
				options = new JSONObject();
				
				options.put("id_caja", bean.getId_caja());
				
				labels = new JSONObject();
				labels.put("label", ColseviDao.getInstance().getEstablecimientoMapper().selectByPrimaryKey(bean.getId_establecimiento()).getNombre());
				labels.put("value", bean.getId_establecimiento());
				options.put("establecimiento", labels);
				
				Persona personaBean = ColseviDao.getInstance().getPersonaMapper().selectByPrimaryKey(bean.getId_persona());
				options.put("persona", personaBean.getNombre() + " " + personaBean.getApellido());
				
				options.put("fecha_ejecucion", UtilidadManager.FechaDateConHora_Vista(bean.getFecha_ejecucion()));
				options.put("valor_inicial", UtilidadManager.MonedaVista(bean.getValor_inicial()));
				options.put("estado", bean.getEstado());
				
				result.add(options);
			}catch(Exception e){
				logger.error(e.getMessage());
				continue;
			}
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/Guardar")
	public void Guardar(HttpServletRequest request, HttpServletResponse response) throws IOException{
		JSONObject resultVista = new JSONObject();
		Caja cajaBean = new Caja();

		try{
			cajaBean.setFecha_ejecucion(new Date());
			cajaBean.setId_persona(getUsuario(request).getPersona());
			cajaBean.setEstado("1");
			
			if(request.getParameter("establecimiento").trim().isEmpty())
				resultVista.put("error", "Seleccionar el establecimiento<br/>");
			else
				cajaBean.setId_establecimiento(Integer.parseInt(request.getParameter("establecimiento")));

			if(!request.getParameter("valor_inicial").trim().isEmpty())
				cajaBean.setValor_inicial(new BigDecimal(request.getParameter("valor_inicial")));
			else
				cajaBean.setValor_inicial(new BigDecimal(0));
						
			ColseviDao.getInstance().getCajaMapper().insertSelective(cajaBean);
			resultVista.put("correcto", "Caja creada");
		}catch(Exception e){
			logger.error(e.getMessage());
			resultVista.put("error", "Contactar al administrador");
		}
		
		ResponseJson(request, response, resultVista);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("/Ejecutar")
	public void Ejecutar(HttpServletRequest request, HttpServletResponse response) throws IOException{
		JSONObject resultVista = new JSONObject();
		Caja cajaBean = new Caja();
		Map<String, Object> mapa = new HashMap<String, Object>();
		
		try{
			Integer caja = Integer.parseInt(request.getParameter("caja"));
			mapa.put("cajaFiltro", caja);
			
			ColseviDao.getInstance().getCajaMapper().CONSOLIDAR_PAGOS_PROVEEDOR(mapa);
			ColseviDao.getInstance().getCajaMapper().CONSOLIDAR_COMPRAS_PROVEEDOR(mapa);
			
			cajaBean.setId_caja(caja);
			cajaBean.setEstado("3");
			ColseviDao.getInstance().getCajaMapper().updateByPrimaryKeySelective(cajaBean);
			resultVista.put("correcto", "Caja Ejecutada");
		}catch(Exception e){
			logger.error(e.getMessage());
			resultVista.put("error", "Contactar al administrador");
		}
		
		ResponseJson(request, response, resultVista);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/DetalleEjecucion")
	public void Detalle(HttpServletRequest request, HttpServletResponse response) throws IOException{
		JSONObject resultVista = new JSONObject();
		Map<String, Object> mapa = new HashMap<String, Object>();
		List<Map<String, Object>> detalleCaja = null;
		
		try{
			Integer caja = Integer.parseInt(request.getParameter("caja"));
			mapa.put("cajaFiltro", caja);

			detalleCaja = ColseviDao.getInstance().getCajaMapper().LISTA_COMPRAS_CAJA(mapa);
			resultVista.put("Compras", detalleCaja);
			
			detalleCaja = ColseviDao.getInstance().getCajaMapper().MATERIA_PRIMA_POR_VENCER_CAJA(mapa);
			if(detalleCaja.get(0) != null){
				resultVista.put("materiaPrima", detalleCaja);
			}
		}catch(Exception e){
			logger.error(e.getMessage());
			resultVista.put("error", "Contactar al administrador");
		}
		
		ResponseJson(request, response, resultVista);
	}
}