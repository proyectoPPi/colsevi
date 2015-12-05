package com.colsevi.dao.usuario.map;

import com.colsevi.dao.usuario.model.Establecimiento;
import com.colsevi.dao.usuario.model.EstablecimientoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EstablecimientoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table establecimiento
     *
     * @mbggenerated Thu Dec 03 21:56:26 COT 2015
     */
    int countByExample(EstablecimientoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table establecimiento
     *
     * @mbggenerated Thu Dec 03 21:56:26 COT 2015
     */
    int deleteByExample(EstablecimientoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table establecimiento
     *
     * @mbggenerated Thu Dec 03 21:56:26 COT 2015
     */
    int deleteByPrimaryKey(Integer id_establecimiento);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table establecimiento
     *
     * @mbggenerated Thu Dec 03 21:56:26 COT 2015
     */
    int insert(Establecimiento record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table establecimiento
     *
     * @mbggenerated Thu Dec 03 21:56:26 COT 2015
     */
    int insertSelective(Establecimiento record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table establecimiento
     *
     * @mbggenerated Thu Dec 03 21:56:26 COT 2015
     */
    List<Establecimiento> selectByExample(EstablecimientoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table establecimiento
     *
     * @mbggenerated Thu Dec 03 21:56:26 COT 2015
     */
    Establecimiento selectByPrimaryKey(Integer id_establecimiento);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table establecimiento
     *
     * @mbggenerated Thu Dec 03 21:56:26 COT 2015
     */
    int updateByExampleSelective(@Param("record") Establecimiento record, @Param("example") EstablecimientoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table establecimiento
     *
     * @mbggenerated Thu Dec 03 21:56:26 COT 2015
     */
    int updateByExample(@Param("record") Establecimiento record, @Param("example") EstablecimientoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table establecimiento
     *
     * @mbggenerated Thu Dec 03 21:56:26 COT 2015
     */
    int updateByPrimaryKeySelective(Establecimiento record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table establecimiento
     *
     * @mbggenerated Thu Dec 03 21:56:26 COT 2015
     */
    int updateByPrimaryKey(Establecimiento record);
}