package com.colsevi.dao.usuario.map;

import com.colsevi.dao.usuario.model.Pagina;
import com.colsevi.dao.usuario.model.PaginaExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface PaginaMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table pagina
	 * @mbg.generated  Fri Apr 28 17:29:08 COT 2017
	 */
	long countByExample(PaginaExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table pagina
	 * @mbg.generated  Fri Apr 28 17:29:08 COT 2017
	 */
	int deleteByExample(PaginaExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table pagina
	 * @mbg.generated  Fri Apr 28 17:29:08 COT 2017
	 */
	int deleteByPrimaryKey(Integer id_pagina);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table pagina
	 * @mbg.generated  Fri Apr 28 17:29:08 COT 2017
	 */
	int insert(Pagina record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table pagina
	 * @mbg.generated  Fri Apr 28 17:29:08 COT 2017
	 */
	int insertSelective(Pagina record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table pagina
	 * @mbg.generated  Fri Apr 28 17:29:08 COT 2017
	 */
	List<Pagina> selectByExample(PaginaExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table pagina
	 * @mbg.generated  Fri Apr 28 17:29:08 COT 2017
	 */
	Pagina selectByPrimaryKey(Integer id_pagina);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table pagina
	 * @mbg.generated  Fri Apr 28 17:29:08 COT 2017
	 */
	int updateByExampleSelective(@Param("record") Pagina record, @Param("example") PaginaExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table pagina
	 * @mbg.generated  Fri Apr 28 17:29:08 COT 2017
	 */
	int updateByExample(@Param("record") Pagina record, @Param("example") PaginaExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table pagina
	 * @mbg.generated  Fri Apr 28 17:29:08 COT 2017
	 */
	int updateByPrimaryKeySelective(Pagina record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table pagina
	 * @mbg.generated  Fri Apr 28 17:29:08 COT 2017
	 */
	int updateByPrimaryKey(Pagina record);
	List<Pagina> ListaMenuPadre(Map<String, Object> map);
	List<Pagina> ListaMenuHijos(Map<String, Object> map);
}