package com.colsevi.controllers.producto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.colsevi.application.ColseviDao;
import com.colsevi.application.ColseviDaoTransaccion;
import com.colsevi.application.GeneralManager;
import com.colsevi.application.ProductoManager;
import com.colsevi.controllers.BaseConfigController;
import com.colsevi.dao.producto.model.Plato;
import com.colsevi.dao.producto.model.PlatoExample;
import com.colsevi.dao.producto.model.PlatoXTipoProducto;
import com.colsevi.dao.producto.model.PlatoXTipoProductoExample;

@Controller
@RequestMapping("/Plato")
public class PlatoController extends BaseConfigController{

	private static final long serialVersionUID = 8661656967139033340L;
	private static Logger logger = Logger.getLogger(PlatoController.class);

	@RequestMapping
	public ModelAndView Inicial(HttpServletRequest request, ModelMap model){
		model.addAttribute("listaE", GeneralManager.getEstablecimientos());
		model.addAttribute("listaTP", ProductoManager.tipoProducto());
		return new ModelAndView("producto/Plato","col", getValoresGenericos(request));
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/tabla")
	public void tabla(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		JSONObject opciones = new JSONObject();
		
		try{
			opciones.put("datos", ConstruirJson(ColseviDao.getInstance().getPlatoMapper().selectByExample(new PlatoExample())));
			opciones.put("total", ColseviDao.getInstance().getPlatoMapper().countByExample(new PlatoExample()));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		ResponseJson(request, response, opciones);
	}
	
	@SuppressWarnings("unchecked")
	public JSONArray ConstruirJson(List<Plato> ltsPlato){
		
		JSONArray resultado = new JSONArray();
		JSONObject opciones = new JSONObject(), labels = new JSONObject();
		
		for(Plato bean: ltsPlato){
			try{
				opciones = new JSONObject();
				labels = new JSONObject();
				
				opciones.put("id_plato", bean.getId_plato());
				opciones.put("nombre", bean.getNombre());
				opciones.put("valor", bean.getValor());

				labels.put("value", bean.getId_establecimiento());
				labels.put("label", ColseviDao.getInstance().getEstablecimientoMapper().selectByPrimaryKey(bean.getId_establecimiento()).getNombre());
				opciones.put("id_establecimiento", labels);
				
				opciones.put("detalle", cargarDetalle(bean.getId_plato()));
				
			}catch(Exception e){
				continue;
			}
			
			resultado.add(opciones);
		}
		
		return resultado;
	}
	
	@SuppressWarnings("unchecked")
	public JSONArray cargarDetalle(Integer id_plato){
		JSONArray resultado = new JSONArray();
		JSONObject opciones = new JSONObject();
		
		PlatoXTipoProductoExample PXTPE = new PlatoXTipoProductoExample();
		PXTPE.createCriteria().andId_platoEqualTo(id_plato);
		List<PlatoXTipoProducto> listaDetalle = ColseviDao.getInstance().getPlatoXTipoProductoMapper().selectByExample(PXTPE);
		
		for(PlatoXTipoProducto det: listaDetalle){
			opciones = new JSONObject();
			opciones.put("id", det.getId_tipo_producto());
			opciones.put("cant", det.getCantidad());
			resultado.add(opciones);
		}
		
		return resultado;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/Guardar")
	public void Guardar(HttpServletRequest request, HttpServletResponse response, Plato bean) throws IOException{
		JSONObject resultVista = new JSONObject();
		List<PlatoXTipoProducto> LPTP = new ArrayList<PlatoXTipoProducto>();
		SqlSession sesion = ColseviDaoTransaccion.getInstance();

		try{
			String[] tipoProducto = request.getParameterValues("tipoProducto");
			String[] cantidad = request.getParameterValues("cantidadPlato");
			
			for(int i = 0; i < tipoProducto.length ; i ++){
				PlatoXTipoProducto obj = new PlatoXTipoProducto();
				
				obj.setId_tipo_producto(Integer.parseInt(tipoProducto[i]));
				obj.setCantidad(Integer.parseInt(cantidad[i]));
				LPTP.add(obj);
			}
			
			if(bean != null && bean.getId_plato() != null){
				PlatoXTipoProductoExample ptpe = new PlatoXTipoProductoExample();
				ptpe.createCriteria().andId_platoEqualTo(bean.getId_plato());
				ColseviDaoTransaccion.Eliminar(sesion, "com.colsevi.dao.producto.map.PlatoXTipoProductoMapper.deleteByExample", ptpe);
				
				ColseviDaoTransaccion.Actualizar(sesion, "com.colsevi.dao.producto.map.PlatoMapper.updateByPrimaryKeySelective", bean);
				resultVista.put("correcto", "Plato insertado");
			}else{
				ColseviDaoTransaccion.Insertar(sesion, "com.colsevi.dao.producto.map.PlatoMapper.insertSelective", bean);
				resultVista.put("correcto", "Plato insertado");
			}
			
			for(PlatoXTipoProducto beantp : LPTP){
				beantp.setId_plato(bean.getId_plato());
				ColseviDaoTransaccion.Insertar(sesion, "com.colsevi.dao.producto.map.PlatoXTipoProductoMapper.insertSelective", beantp);
			}
			
			ColseviDaoTransaccion.RealizarCommit(sesion);
		}catch (Exception e) {
			logger.error(e.getMessage());
			resultVista.put("error", "Contactar al administrador");
			ColseviDaoTransaccion.ErrorRollback(sesion);
		}
		
		ColseviDaoTransaccion.CerrarSesion(sesion);
		ResponseJson(request, response, resultVista);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/Eliminar")
	public void Eliminar(HttpServletRequest request, HttpServletResponse response) throws IOException{
		JSONObject result = new JSONObject();
		String id = request.getParameter("id_plato");
		if(id != null){
			try {

				PlatoXTipoProductoExample ptpe = new PlatoXTipoProductoExample();
				ptpe.createCriteria().andId_platoEqualTo(Integer.parseInt(id));
				ColseviDao.getInstance().getPlatoXTipoProductoMapper().deleteByExample(ptpe);
				
				ColseviDao.getInstance().getPlatoMapper().deleteByPrimaryKey(Integer.parseInt(id));
				
				result.put("correcto", "Establecimiento Eliminado");
			}catch(Exception e){
				logger.error(e.getMessage());
				result.put("error", "Contacte al Administrador");
			}
		}
		
		ResponseJson(request, response, result);
	}
}
