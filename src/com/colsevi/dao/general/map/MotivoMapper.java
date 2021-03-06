package com.colsevi.dao.general.map;

import com.colsevi.dao.general.model.Motivo;
import com.colsevi.dao.general.model.MotivoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MotivoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table motivo
     *
     * @mbggenerated Tue Apr 12 16:26:48 COT 2016
     */
    int countByExample(MotivoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table motivo
     *
     * @mbggenerated Tue Apr 12 16:26:48 COT 2016
     */
    int deleteByExample(MotivoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table motivo
     *
     * @mbggenerated Tue Apr 12 16:26:48 COT 2016
     */
    int deleteByPrimaryKey(Integer id_motivo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table motivo
     *
     * @mbggenerated Tue Apr 12 16:26:48 COT 2016
     */
    int insert(Motivo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table motivo
     *
     * @mbggenerated Tue Apr 12 16:26:48 COT 2016
     */
    int insertSelective(Motivo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table motivo
     *
     * @mbggenerated Tue Apr 12 16:26:48 COT 2016
     */
    List<Motivo> selectByExample(MotivoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table motivo
     *
     * @mbggenerated Tue Apr 12 16:26:48 COT 2016
     */
    Motivo selectByPrimaryKey(Integer id_motivo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table motivo
     *
     * @mbggenerated Tue Apr 12 16:26:48 COT 2016
     */
    int updateByExampleSelective(@Param("record") Motivo record, @Param("example") MotivoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table motivo
     *
     * @mbggenerated Tue Apr 12 16:26:48 COT 2016
     */
    int updateByExample(@Param("record") Motivo record, @Param("example") MotivoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table motivo
     *
     * @mbggenerated Tue Apr 12 16:26:48 COT 2016
     */
    int updateByPrimaryKeySelective(Motivo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table motivo
     *
     * @mbggenerated Tue Apr 12 16:26:48 COT 2016
     */
    int updateByPrimaryKey(Motivo record);
}