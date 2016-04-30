package com.colsevi.dao.caja.map;

import com.colsevi.dao.caja.model.CierreCaja;
import com.colsevi.dao.caja.model.CierreCajaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CierreCajaMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cierre_caja
     *
     * @mbggenerated Tue Apr 26 19:26:30 COT 2016
     */
    int countByExample(CierreCajaExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cierre_caja
     *
     * @mbggenerated Tue Apr 26 19:26:30 COT 2016
     */
    int deleteByExample(CierreCajaExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cierre_caja
     *
     * @mbggenerated Tue Apr 26 19:26:30 COT 2016
     */
    int deleteByPrimaryKey(Integer id_cierre_caja);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cierre_caja
     *
     * @mbggenerated Tue Apr 26 19:26:30 COT 2016
     */
    int insert(CierreCaja record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cierre_caja
     *
     * @mbggenerated Tue Apr 26 19:26:30 COT 2016
     */
    int insertSelective(CierreCaja record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cierre_caja
     *
     * @mbggenerated Tue Apr 26 19:26:30 COT 2016
     */
    List<CierreCaja> selectByExample(CierreCajaExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cierre_caja
     *
     * @mbggenerated Tue Apr 26 19:26:30 COT 2016
     */
    CierreCaja selectByPrimaryKey(Integer id_cierre_caja);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cierre_caja
     *
     * @mbggenerated Tue Apr 26 19:26:30 COT 2016
     */
    int updateByExampleSelective(@Param("record") CierreCaja record, @Param("example") CierreCajaExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cierre_caja
     *
     * @mbggenerated Tue Apr 26 19:26:30 COT 2016
     */
    int updateByExample(@Param("record") CierreCaja record, @Param("example") CierreCajaExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cierre_caja
     *
     * @mbggenerated Tue Apr 26 19:26:30 COT 2016
     */
    int updateByPrimaryKeySelective(CierreCaja record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cierre_caja
     *
     * @mbggenerated Tue Apr 26 19:26:30 COT 2016
     */
    int updateByPrimaryKey(CierreCaja record);
}