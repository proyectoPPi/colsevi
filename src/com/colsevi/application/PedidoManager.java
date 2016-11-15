package com.colsevi.application;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.colsevi.controllers.pedido.PedidoE;
import com.colsevi.dao.inventario.model.Inventario;
import com.colsevi.dao.inventario.model.InventarioExample;
import com.colsevi.dao.inventario.model.InventarioXMateria;
import com.colsevi.dao.inventario.model.InventarioXMateriaExample;
import com.colsevi.dao.pedido.model.DetallePedido;
import com.colsevi.dao.pedido.model.DetallePedidoExample;
import com.colsevi.dao.pedido.model.Pedido;
import com.colsevi.dao.pedido.model.PedidoExample;
import com.colsevi.dao.producto.model.IngredienteXProducto;
import com.colsevi.dao.producto.model.IngredienteXProductoExample;

public class PedidoManager {
	
	public static Boolean crearPedido(Integer persona, Date fecha, BigDecimal total, Boolean pagado, Integer estado, Integer establecimiento, Integer motivo){
		
		try{
			Pedido ped = new Pedido();
			
			ped.setFecha_pedido(fecha);
			ped.setId_persona(persona);
			ped.setTotal(total);
			ped.setPagado(pagado);
			ped.setId_estado_pedido(estado);
			ped.setId_establecimiento(establecimiento);
			ped.setMotivo(motivo);
			
			ColseviDao.getInstance().getPedidoMapper().insertSelective(ped);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public static Pedido obtenerPedido(Integer persona, String pedido){
		Pedido ped = new Pedido();
		PedidoExample PedE = new PedidoExample();
		
		PedE.setOrderByClause("id_pedido DESC");
		PedE.setLimit("1");
		PedidoExample.Criteria criteria = (PedidoExample.Criteria) PedE.createCriteria();
		
		try{		
			criteria.andId_personaEqualTo(persona).andId_estado_pedidoEqualTo(PedidoE.BORRADOR.getPedidoE());
			if(pedido != null && !pedido.trim().isEmpty())
				criteria.andId_pedidoEqualTo(Integer.parseInt(pedido));
		
			ped = ColseviDao.getInstance().getPedidoMapper().selectByExample(PedE).get(0);
		}catch(Exception e){
			ped = null;
		}
		return ped;
	}
	
	public static Boolean crearDetalle(Integer id_pedido, Integer id_producto, Integer cantidad){
		Boolean result = false;
		DetallePedido DP = new DetallePedido();
		BigDecimal venta = ColseviDao.getInstance().getProductoMapper().selectByPrimaryKey(id_producto).getVenta();
		
		DetallePedidoExample DPE = new DetallePedidoExample();
		DPE.createCriteria().andId_pedidoEqualTo(id_pedido).andId_productoEqualTo(id_producto);
		List<DetallePedido> listaDet = ColseviDao.getInstance().getDetallePedidoMapper().selectByExample(DPE);
		
		if(listaDet != null && listaDet.size() >0){
			DP = listaDet.get(0);
			DP.setCantidad(cantidad);
			DP.setSub_total(new BigDecimal(cantidad).multiply(venta));
			ColseviDao.getInstance().getDetallePedidoMapper().updateByPrimaryKeySelective(DP);
		}else{
			venta = venta.multiply(new BigDecimal(cantidad));
			DP.setId_pedido(id_pedido);
			DP.setId_producto(id_producto);
			DP.setCantidad(cantidad);
			DP.setSub_total(venta);
		
			ColseviDao.getInstance().getDetallePedidoMapper().insert(DP);
		}
		actualizarPedido(id_pedido, null, null, null, null);
		
		return result;
	}
	
	public static void actualizarPedido(Integer id_pedido, Integer id_persona, Integer id_estado_pedido, Boolean pagado, String comentario){
		Pedido ped = new Pedido();
		DetallePedidoExample DPE = new DetallePedidoExample();
		BigDecimal suma = new BigDecimal(0);

		DPE.createCriteria().andId_pedidoEqualTo(id_pedido);
		List<DetallePedido> detalle = ColseviDao.getInstance().getDetallePedidoMapper().selectByExample(DPE);
		
		for(DetallePedido bean: detalle){
			suma = new BigDecimal(suma.doubleValue()).add(bean.getSub_total());
		}
		
		ped.setId_pedido(id_pedido);
		ped.setTotal(suma);
		if(id_persona != null)
			ped.setId_persona(id_persona);
		if(id_estado_pedido != null)
			ped.setId_estado_pedido(id_estado_pedido);
		if(pagado != null)
			ped.setPagado(pagado);
		if(comentario != null && !comentario.trim().isEmpty())
			ped.setComentario(comentario);
		
		ColseviDao.getInstance().getPedidoMapper().updateByPrimaryKeySelective(ped);
	}
	
	public static void descontarInventario(Integer pedido, Integer producto, Integer cantidad, Integer esta){
		
		InventarioExample invE = new InventarioExample();
		invE.createCriteria().andId_productoEqualTo(producto).andId_establecimientoEqualTo(esta);
		Inventario inv = ColseviDao.getInstance().getInventarioMapper().selectByExample(invE).get(0);
		Double cantConv = 0d;
		
		IngredienteXProductoExample IXPE = new IngredienteXProductoExample();
		IXPE.createCriteria().andId_productoEqualTo(producto);
		List<IngredienteXProducto> listaIng = ColseviDao.getInstance().getIngredienteXProductoMapper().selectByExample(IXPE);
		
		for(IngredienteXProducto bean: listaIng){
			bean.setCantidad(bean.getCantidad() * cantidad);
			
			InventarioXMateriaExample IXME = new InventarioXMateriaExample();
			IXME.createCriteria().andId_ingredienteEqualTo(bean.getId_ingrediente()).andId_inventarioEqualTo(inv.getId_inventario());
			InventarioXMateria invMat = ColseviDao.getInstance().getInventarioXMateriaMapper().selectByExample(IXME).get(0);
			
			Object[] result = InventarioManager.ConversionPMayorMenor(bean.getId_unidad_peso(), invMat.getId_unidad_peso(), Double.valueOf(bean.getCantidad()));
			cantConv = (Double) result[0];
			
			if(cantConv <= invMat.getCantidad()){
				cantConv -= invMat.getCantidad();
				if(cantConv < 1){
					result = InventarioManager.conversionEncontrarMayorUnidad(invMat.getId_unidad_peso(), cantConv);
				}else{
					result = InventarioManager.conversionMOptima(invMat.getId_unidad_peso(), cantConv);
				}
				
				cantConv = (Double) result[0];
				invMat.setId_unidad_peso((Integer) result[1]);
				invMat.setCantidad(cantConv);
				
				ColseviDao.getInstance().getInventarioXMateriaMapper().updateByExampleSelective(invMat, IXME);
			}
		}
		
		inv.setDisponible(inv.getDisponible() - cantidad);
		inv.setCompromiso(inv.getCompromiso() + cantidad);
		ColseviDao.getInstance().getInventarioMapper().updateByPrimaryKeySelective(inv);
	}
}