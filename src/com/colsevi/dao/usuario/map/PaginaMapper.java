package com.colsevi.dao.usuario.map;

import com.colsevi.dao.usuario.model.Pagina;
import com.colsevi.dao.usuario.model.PaginaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PaginaMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table pagina
	 * @mbggenerated  Tue Apr 26 16:40:39 COT 2016
	 */
	int countByExample(PaginaExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table pagina
	 * @mbggenerated  Tue Apr 26 16:40:39 COT 2016
	 */
	int deleteByExample(PaginaExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table pagina
	 * @mbggenerated  Tue Apr 26 16:40:39 COT 2016
	 */
	int deleteByPrimaryKey(Integer id_pagina);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table pagina
	 * @mbggenerated  Tue Apr 26 16:40:39 COT 2016
	 */
	int insert(Pagina record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table pagina
	 * @mbggenerated  Tue Apr 26 16:40:39 COT 2016
	 */
	int insertSelective(Pagina record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table pagina
	 * @mbggenerated  Tue Apr 26 16:40:39 COT 2016
	 */
	List<Pagina> selectByExample(PaginaExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table pagina
	 * @mbggenerated  Tue Apr 26 16:40:39 COT 2016
	 */
	Pagina selectByPrimaryKey(Integer id_pagina);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table pagina
	 * @mbggenerated  Tue Apr 26 16:40:39 COT 2016
	 */
	int updateByExampleSelective(@Param("record") Pagina record, @Param("example") PaginaExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table pagina
	 * @mbggenerated  Tue Apr 26 16:40:39 COT 2016
	 */
	int updateByExample(@Param("record") Pagina record, @Param("example") PaginaExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table pagina
	 * @mbggenerated  Tue Apr 26 16:40:39 COT 2016
	 */
	int updateByPrimaryKeySelective(Pagina record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table pagina
	 * @mbggenerated  Tue Apr 26 16:40:39 COT 2016
	 */
	int updateByPrimaryKey(Pagina record);
}