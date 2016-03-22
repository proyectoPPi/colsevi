package com.colsevi.dao.inventario.map;

import com.colsevi.dao.inventario.model.Inventario;
import com.colsevi.dao.inventario.model.InventarioExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InventarioMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table inventario
	 * @mbggenerated  Mon Mar 21 22:50:01 COT 2016
	 */
	int countByExample(InventarioExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table inventario
	 * @mbggenerated  Mon Mar 21 22:50:01 COT 2016
	 */
	int deleteByExample(InventarioExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table inventario
	 * @mbggenerated  Mon Mar 21 22:50:01 COT 2016
	 */
	int deleteByPrimaryKey(Integer id_inventario);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table inventario
	 * @mbggenerated  Mon Mar 21 22:50:01 COT 2016
	 */
	int insert(Inventario record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table inventario
	 * @mbggenerated  Mon Mar 21 22:50:01 COT 2016
	 */
	int insertSelective(Inventario record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table inventario
	 * @mbggenerated  Mon Mar 21 22:50:01 COT 2016
	 */
	List<Inventario> selectByExample(InventarioExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table inventario
	 * @mbggenerated  Mon Mar 21 22:50:01 COT 2016
	 */
	Inventario selectByPrimaryKey(Integer id_inventario);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table inventario
	 * @mbggenerated  Mon Mar 21 22:50:01 COT 2016
	 */
	int updateByExampleSelective(@Param("record") Inventario record, @Param("example") InventarioExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table inventario
	 * @mbggenerated  Mon Mar 21 22:50:01 COT 2016
	 */
	int updateByExample(@Param("record") Inventario record, @Param("example") InventarioExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table inventario
	 * @mbggenerated  Mon Mar 21 22:50:01 COT 2016
	 */
	int updateByPrimaryKeySelective(Inventario record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table inventario
	 * @mbggenerated  Mon Mar 21 22:50:01 COT 2016
	 */
	int updateByPrimaryKey(Inventario record);
}