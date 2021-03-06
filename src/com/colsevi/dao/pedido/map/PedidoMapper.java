package com.colsevi.dao.pedido.map;

import com.colsevi.dao.pedido.model.Pedido;
import com.colsevi.dao.pedido.model.PedidoExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface PedidoMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table pedido
	 * @mbggenerated  Tue May 24 12:18:48 COT 2016
	 */
	int countByExample(PedidoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table pedido
	 * @mbggenerated  Tue May 24 12:18:48 COT 2016
	 */
	int deleteByExample(PedidoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table pedido
	 * @mbggenerated  Tue May 24 12:18:48 COT 2016
	 */
	int deleteByPrimaryKey(Integer id_pedido);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table pedido
	 * @mbggenerated  Tue May 24 12:18:48 COT 2016
	 */
	int insert(Pedido record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table pedido
	 * @mbggenerated  Tue May 24 12:18:48 COT 2016
	 */
	int insertSelective(Pedido record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table pedido
	 * @mbggenerated  Tue May 24 12:18:48 COT 2016
	 */
	List<Pedido> selectByExample(PedidoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table pedido
	 * @mbggenerated  Tue May 24 12:18:48 COT 2016
	 */
	Pedido selectByPrimaryKey(Integer id_pedido);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table pedido
	 * @mbggenerated  Tue May 24 12:18:48 COT 2016
	 */
	int updateByExampleSelective(@Param("record") Pedido record, @Param("example") PedidoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table pedido
	 * @mbggenerated  Tue May 24 12:18:48 COT 2016
	 */
	int updateByExample(@Param("record") Pedido record, @Param("example") PedidoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table pedido
	 * @mbggenerated  Tue May 24 12:18:48 COT 2016
	 */
	int updateByPrimaryKeySelective(Pedido record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table pedido
	 * @mbggenerated  Tue May 24 12:18:48 COT 2016
	 */
	int updateByPrimaryKey(Pedido record);

	List<Map<String, Object>> SelectDataView(Map<String, Object> map);
}