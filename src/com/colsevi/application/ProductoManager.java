package com.colsevi.application;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.colsevi.dao.catalogo.model.Catalogo;
import com.colsevi.dao.catalogo.model.CatalogoExample;
import com.colsevi.dao.general.model.UnidadMedida;
import com.colsevi.dao.general.model.UnidadMedidaExample;
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
	
	public static List<UnidadMedida> getMedida(){
		return ColseviDao.getInstance().getUnidadMedidaMapper().selectByExample(new UnidadMedidaExample());
	}
	
	public static List<DificultadReceta> getDificultad(){
		return ColseviDao.getInstance().getDificultadRecetaMapper().selectByExample(new DificultadRecetaExample());
	}
	
	public static List<TipoProducto> tipoProducto(){
		return ColseviDao.getInstance().getTipoProductoMapper().selectByExample(new TipoProductoExample());
	}
	
	public static List<Catalogo> catalogo(){
		return ColseviDao.getInstance().getCatalogoMapper().selectByExample(new CatalogoExample());
	}
	
	@SuppressWarnings("unchecked")
	public static JSONObject AutocompletarProducto(String producto){
		
		try{
			JSONObject result = new JSONObject();
			Map<String, Object> mapa = new HashMap<String, Object>();
			if(producto != null && !producto.trim().isEmpty() && producto.trim().length() > 0){
				mapa.put("producto", "%" + producto + "%");
			}
			result.put("labels", JsonAutocompletarProd(ColseviDao.getInstance().getProductoMapper().SelectAutocomplete(mapa)));
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
