package com.colsevi.dao.proveedor.model;

public class TipoProveedor {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tipo_proveedor.id_tipo_proveedor
     *
     * @mbggenerated Thu Mar 10 16:26:02 COT 2016
     */
    private Integer id_tipo_proveedor;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tipo_proveedor.nombre
     *
     * @mbggenerated Thu Mar 10 16:26:02 COT 2016
     */
    private String nombre;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tipo_proveedor.id_tipo_proveedor
     *
     * @return the value of tipo_proveedor.id_tipo_proveedor
     *
     * @mbggenerated Thu Mar 10 16:26:02 COT 2016
     */
    public Integer getId_tipo_proveedor() {
        return id_tipo_proveedor;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tipo_proveedor.id_tipo_proveedor
     *
     * @param id_tipo_proveedor the value for tipo_proveedor.id_tipo_proveedor
     *
     * @mbggenerated Thu Mar 10 16:26:02 COT 2016
     */
    public void setId_tipo_proveedor(Integer id_tipo_proveedor) {
        this.id_tipo_proveedor = id_tipo_proveedor;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tipo_proveedor.nombre
     *
     * @return the value of tipo_proveedor.nombre
     *
     * @mbggenerated Thu Mar 10 16:26:02 COT 2016
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tipo_proveedor.nombre
     *
     * @param nombre the value for tipo_proveedor.nombre
     *
     * @mbggenerated Thu Mar 10 16:26:02 COT 2016
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}