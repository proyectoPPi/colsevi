package com.colsevi.dao.pedido.map;

import com.colsevi.dao.pedido.model.DetallePedido;
import com.colsevi.dao.pedido.model.DetallePedidoExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface DetallePedidoMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table detalle_pedido
	 * @mbggenerated  Tue Apr 12 16:28:01 COT 2016
	 */
	int countByExample(DetallePedidoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table detalle_pedido
	 * @mbggenerated  Tue Apr 12 16:28:01 COT 2016
	 */
	int deleteByExample(DetallePedidoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table detalle_pedido
	 * @mbggenerated  Tue Apr 12 16:28:01 COT 2016
	 */
	int deleteByPrimaryKey(Integer id_detalle_pedido);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table detalle_pedido
	 * @mbggenerated  Tue Apr 12 16:28:01 COT 2016
	 */
	int insert(DetallePedido record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table detalle_pedido
	 * @mbggenerated  Tue Apr 12 16:28:01 COT 2016
	 */
	int insertSelective(DetallePedido record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table detalle_pedido
	 * @mbggenerated  Tue Apr 12 16:28:01 COT 2016
	 */
	List<DetallePedido> selectByExample(DetallePedidoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table detalle_pedido
	 * @mbggenerated  Tue Apr 12 16:28:01 COT 2016
	 */
	DetallePedido selectByPrimaryKey(Integer id_detalle_pedido);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table detalle_pedido
	 * @mbggenerated  Tue Apr 12 16:28:01 COT 2016
	 */
	int updateByExampleSelective(@Param("record") DetallePedido record,
			@Param("example") DetallePedidoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table detalle_pedido
	 * @mbggenerated  Tue Apr 12 16:28:01 COT 2016
	 */
	int updateByExample(@Param("record") DetallePedido record,
			@Param("example") DetallePedidoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table detalle_pedido
	 * @mbggenerated  Tue Apr 12 16:28:01 COT 2016
	 */
	int updateByPrimaryKeySelective(DetallePedido record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table detalle_pedido
	 * @mbggenerated  Tue Apr 12 16:28:01 COT 2016
	 */
	int updateByPrimaryKey(DetallePedido record);
	
	List<Map<String, Object>> SelectDataView(Map<String, Object> map);
	List<Map<String, Object>> obtenerDetalle(Map<String, Object> map);
}