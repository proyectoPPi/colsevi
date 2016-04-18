package com.colsevi.dao.deuda.map;

import com.colsevi.dao.deuda.model.DeudaProveedor;
import com.colsevi.dao.deuda.model.DeudaProveedorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeudaProveedorMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table deuda_proveedor
     *
     * @mbggenerated Tue Apr 12 16:30:54 COT 2016
     */
    int countByExample(DeudaProveedorExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table deuda_proveedor
     *
     * @mbggenerated Tue Apr 12 16:30:54 COT 2016
     */
    int deleteByExample(DeudaProveedorExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table deuda_proveedor
     *
     * @mbggenerated Tue Apr 12 16:30:54 COT 2016
     */
    int deleteByPrimaryKey(Integer id_deuda_proveedor);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table deuda_proveedor
     *
     * @mbggenerated Tue Apr 12 16:30:54 COT 2016
     */
    int insert(DeudaProveedor record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table deuda_proveedor
     *
     * @mbggenerated Tue Apr 12 16:30:54 COT 2016
     */
    int insertSelective(DeudaProveedor record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table deuda_proveedor
     *
     * @mbggenerated Tue Apr 12 16:30:54 COT 2016
     */
    List<DeudaProveedor> selectByExample(DeudaProveedorExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table deuda_proveedor
     *
     * @mbggenerated Tue Apr 12 16:30:54 COT 2016
     */
    DeudaProveedor selectByPrimaryKey(Integer id_deuda_proveedor);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table deuda_proveedor
     *
     * @mbggenerated Tue Apr 12 16:30:54 COT 2016
     */
    int updateByExampleSelective(@Param("record") DeudaProveedor record, @Param("example") DeudaProveedorExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table deuda_proveedor
     *
     * @mbggenerated Tue Apr 12 16:30:54 COT 2016
     */
    int updateByExample(@Param("record") DeudaProveedor record, @Param("example") DeudaProveedorExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table deuda_proveedor
     *
     * @mbggenerated Tue Apr 12 16:30:54 COT 2016
     */
    int updateByPrimaryKeySelective(DeudaProveedor record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table deuda_proveedor
     *
     * @mbggenerated Tue Apr 12 16:30:54 COT 2016
     */
    int updateByPrimaryKey(DeudaProveedor record);
}