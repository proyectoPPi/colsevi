package com.colsevi.dao.caja.map;

import com.colsevi.dao.caja.model.Caja;
import com.colsevi.dao.caja.model.CajaExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface CajaMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table caja
	 * @mbggenerated  Tue Apr 11 22:38:27 COT 2017
	 */
	int countByExample(CajaExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table caja
	 * @mbggenerated  Tue Apr 11 22:38:27 COT 2017
	 */
	int deleteByExample(CajaExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table caja
	 * @mbggenerated  Tue Apr 11 22:38:27 COT 2017
	 */
	int deleteByPrimaryKey(Integer id_caja);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table caja
	 * @mbggenerated  Tue Apr 11 22:38:27 COT 2017
	 */
	int insert(Caja record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table caja
	 * @mbggenerated  Tue Apr 11 22:38:27 COT 2017
	 */
	int insertSelective(Caja record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table caja
	 * @mbggenerated  Tue Apr 11 22:38:27 COT 2017
	 */
	List<Caja> selectByExample(CajaExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table caja
	 * @mbggenerated  Tue Apr 11 22:38:27 COT 2017
	 */
	Caja selectByPrimaryKey(Integer id_caja);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table caja
	 * @mbggenerated  Tue Apr 11 22:38:27 COT 2017
	 */
	int updateByExampleSelective(@Param("record") Caja record, @Param("example") CajaExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table caja
	 * @mbggenerated  Tue Apr 11 22:38:27 COT 2017
	 */
	int updateByExample(@Param("record") Caja record, @Param("example") CajaExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table caja
	 * @mbggenerated  Tue Apr 11 22:38:27 COT 2017
	 */
	int updateByPrimaryKeySelective(Caja record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table caja
	 * @mbggenerated  Tue Apr 11 22:38:27 COT 2017
	 */
	int updateByPrimaryKey(Caja record);
	int CONSOLIDAR_PAGOS_PROVEEDOR(Map<String, Object> map);
	int CONSOLIDAR_COMPRAS_PROVEEDOR(Map<String, Object> map);
	List<Map<String, Object>> LISTA_COMPRAS_CAJA(Map<String, Object> map);
	List<Map<String, Object>> LISTA_COMPRAS_ANTIGUAS_PAGOS_CAJA(Map<String, Object> map);
	List<Map<String, Object>> MATERIA_PRIMA_POR_VENCER_CAJA(Map<String, Object> map);
}