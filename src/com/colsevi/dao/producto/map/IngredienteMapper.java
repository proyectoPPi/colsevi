package com.colsevi.dao.producto.map;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.colsevi.dao.producto.model.Ingrediente;
import com.colsevi.dao.producto.model.IngredienteExample;

public interface IngredienteMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ingrediente
	 * @mbggenerated  Tue Apr 12 16:33:50 COT 2016
	 */
	int countByExample(IngredienteExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ingrediente
	 * @mbggenerated  Tue Apr 12 16:33:50 COT 2016
	 */
	int deleteByExample(IngredienteExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ingrediente
	 * @mbggenerated  Tue Apr 12 16:33:50 COT 2016
	 */
	int deleteByPrimaryKey(Integer id_ingrediente);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ingrediente
	 * @mbggenerated  Tue Apr 12 16:33:50 COT 2016
	 */
	int insert(Ingrediente record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ingrediente
	 * @mbggenerated  Tue Apr 12 16:33:50 COT 2016
	 */
	int insertSelective(Ingrediente record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ingrediente
	 * @mbggenerated  Tue Apr 12 16:33:50 COT 2016
	 */
	List<Ingrediente> selectByExample(IngredienteExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ingrediente
	 * @mbggenerated  Tue Apr 12 16:33:50 COT 2016
	 */
	Ingrediente selectByPrimaryKey(Integer id_ingrediente);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ingrediente
	 * @mbggenerated  Tue Apr 12 16:33:50 COT 2016
	 */
	int updateByExampleSelective(@Param("record") Ingrediente record,
			@Param("example") IngredienteExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ingrediente
	 * @mbggenerated  Tue Apr 12 16:33:50 COT 2016
	 */
	int updateByExample(@Param("record") Ingrediente record,
			@Param("example") IngredienteExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ingrediente
	 * @mbggenerated  Tue Apr 12 16:33:50 COT 2016
	 */
	int updateByPrimaryKeySelective(Ingrediente record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ingrediente
	 * @mbggenerated  Tue Apr 12 16:33:50 COT 2016
	 */
	int updateByPrimaryKey(Ingrediente record);
	
	List<Map<String, Object>> SelectAutocomplete(Map<String, Object> map);
}