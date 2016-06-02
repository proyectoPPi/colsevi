package com.colsevi.application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.colsevi.dao.general.model.UnidadPeso;
import com.colsevi.dao.general.model.UnidadPesoExample;
import com.colsevi.dao.producto.model.ClasificarIngrediente;
import com.colsevi.dao.producto.model.ClasificarIngredienteExample;
import com.colsevi.dao.producto.model.DificultadReceta;
import com.colsevi.dao.producto.model.DificultadRecetaExample;
import com.colsevi.dao.producto.model.TipoProducto;
import com.colsevi.dao.producto.model.TipoProductoExample;

public class ProductoManager {

	public static List<ClasificarIngrediente> getClasificar(){
		return ColseviDao.getInstance().getClasificarIngredienteMapper().selectByExample(new ClasificarIngredienteExample());
	}
	
	public static List<UnidadPeso> getTipoPeso(){
		return ColseviDao.getInstance().getUnidadPesoMapper().selectByExample(new UnidadPesoExample());
	}
	
	public static List<DificultadReceta> getDificultad(){
		return ColseviDao.getInstance().getDificultadRecetaMapper().selectByExample(new DificultadRecetaExample());
	}
	
	public static List<ListaGenerica> tipoProducto(){
		List<ListaGenerica> result = new ArrayList<ListaGenerica>();
		ListaGenerica lg = new ListaGenerica();
		
		TipoProductoExample tpExample = new TipoProductoExample();
		tpExample.createCriteria().andPadreIsNotNull();
		List<TipoProducto> listaTipo = ColseviDao.getInstance().getTipoProductoMapper().selectByExample(tpExample);
		
		for(TipoProducto bean: listaTipo){
			lg = new ListaGenerica();
			lg.setNombre(bean.getNombre());
			lg.setId(bean.getId_tipo_producto().toString());
			lg.setSeleccionable(true);
			
			result.add(lg);

			String[] padre = bean.getPadre().split(",");
			List<Integer> listaH = new ArrayList<Integer>();
			for(int i = 0; i<padre.length; i++){
				listaH.add(Integer.parseInt(padre[i]));
			}
			
			tpExample = new TipoProductoExample();
			tpExample.createCriteria().andId_tipo_productoIn(listaH);
			List<TipoProducto> listaHijo = ColseviDao.getInstance().getTipoProductoMapper().selectByExample(tpExample);
			
			if(listaHijo != null && listaHijo.size() < 1){
				lg.setSeleccionable(false);
				continue;
			}
			
			for(TipoProducto bh: listaHijo){
				lg = new ListaGenerica();
				lg.setNombre(bh.getNombre());
				lg.setId(bh.getId_tipo_producto().toString());
				lg.setSeleccionable(false);
				
				result.add(lg);
			}
		}
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public static JSONObject AutocompletarProducto(String producto){
		
		try{
			JSONObject result = new JSONObject();
			Map<String, Object> mapa = new HashMap<String, Object>();
			if(producto != null && !producto.trim().isEmpty() && producto.trim().length() > 0){
				mapa.put("producto", "%" + producto + "%");
				result.put("labels", JsonAutocompletarProd(ColseviDao.getInstance().getProductoMapper().SelectAutocomplete(mapa)));
			}
			
			return result;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static JSONArray JsonAutocompletarProd(List<Map<String, Object>> listaCliente){
		
		JSONArray resultado = new JSONArray();
		JSONObject opciones = new JSONObject();
		
		for(Map<String, Object> map: listaCliente){
			opciones = new JSONObject();
			opciones.put("label", map.get("nombre") + " " + map.get("referencia").toString());
			opciones.put("value", map.get("nombre"));
			opciones.put("nombreC",map.get("referencia"));
			opciones.put("id_producto", map.get("id_producto"));
			opciones.put("descripcion", map.get("descripcion"));
			
			resultado.add(opciones);
		}
		return resultado;
	}

}
