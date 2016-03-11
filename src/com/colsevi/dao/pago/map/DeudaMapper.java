package com.colsevi.dao.pago.map;

import com.colsevi.dao.pago.model.Deuda;
import com.colsevi.dao.pago.model.DeudaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeudaMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table deuda
     *
     * @mbggenerated Thu Mar 10 16:44:01 COT 2016
     */
    int countByExample(DeudaExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table deuda
     *
     * @mbggenerated Thu Mar 10 16:44:01 COT 2016
     */
    int deleteByExample(DeudaExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table deuda
     *
     * @mbggenerated Thu Mar 10 16:44:01 COT 2016
     */
    int deleteByPrimaryKey(Integer id_deuda);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table deuda
     *
     * @mbggenerated Thu Mar 10 16:44:01 COT 2016
     */
    int insert(Deuda record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table deuda
     *
     * @mbggenerated Thu Mar 10 16:44:01 COT 2016
     */
    int insertSelective(Deuda record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table deuda
     *
     * @mbggenerated Thu Mar 10 16:44:01 COT 2016
     */
    List<Deuda> selectByExample(DeudaExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table deuda
     *
     * @mbggenerated Thu Mar 10 16:44:01 COT 2016
     */
    Deuda selectByPrimaryKey(Integer id_deuda);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table deuda
     *
     * @mbggenerated Thu Mar 10 16:44:01 COT 2016
     */
    int updateByExampleSelective(@Param("record") Deuda record, @Param("example") DeudaExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table deuda
     *
     * @mbggenerated Thu Mar 10 16:44:01 COT 2016
     */
    int updateByExample(@Param("record") Deuda record, @Param("example") DeudaExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table deuda
     *
     * @mbggenerated Thu Mar 10 16:44:01 COT 2016
     */
    int updateByPrimaryKeySelective(Deuda record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table deuda
     *
     * @mbggenerated Thu Mar 10 16:44:01 COT 2016
     */
    int updateByPrimaryKey(Deuda record);
}