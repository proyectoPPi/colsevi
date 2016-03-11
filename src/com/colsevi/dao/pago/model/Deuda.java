package com.colsevi.dao.pago.model;

import java.math.BigDecimal;
import java.util.Date;

public class Deuda {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column deuda.id_deuda
     *
     * @mbggenerated Thu Mar 10 16:44:01 COT 2016
     */
    private Integer id_deuda;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column deuda.id_categoria_modulo
     *
     * @mbggenerated Thu Mar 10 16:44:01 COT 2016
     */
    private Integer id_categoria_modulo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column deuda.documento
     *
     * @mbggenerated Thu Mar 10 16:44:01 COT 2016
     */
    private Integer documento;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column deuda.fecha_registro
     *
     * @mbggenerated Thu Mar 10 16:44:01 COT 2016
     */
    private Date fecha_registro;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column deuda.valor_deuda
     *
     * @mbggenerated Thu Mar 10 16:44:01 COT 2016
     */
    private BigDecimal valor_deuda;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column deuda.observacion
     *
     * @mbggenerated Thu Mar 10 16:44:01 COT 2016
     */
    private String observacion;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column deuda.id_deuda
     *
     * @return the value of deuda.id_deuda
     *
     * @mbggenerated Thu Mar 10 16:44:01 COT 2016
     */
    public Integer getId_deuda() {
        return id_deuda;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column deuda.id_deuda
     *
     * @param id_deuda the value for deuda.id_deuda
     *
     * @mbggenerated Thu Mar 10 16:44:01 COT 2016
     */
    public void setId_deuda(Integer id_deuda) {
        this.id_deuda = id_deuda;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column deuda.id_categoria_modulo
     *
     * @return the value of deuda.id_categoria_modulo
     *
     * @mbggenerated Thu Mar 10 16:44:01 COT 2016
     */
    public Integer getId_categoria_modulo() {
        return id_categoria_modulo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column deuda.id_categoria_modulo
     *
     * @param id_categoria_modulo the value for deuda.id_categoria_modulo
     *
     * @mbggenerated Thu Mar 10 16:44:01 COT 2016
     */
    public void setId_categoria_modulo(Integer id_categoria_modulo) {
        this.id_categoria_modulo = id_categoria_modulo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column deuda.documento
     *
     * @return the value of deuda.documento
     *
     * @mbggenerated Thu Mar 10 16:44:01 COT 2016
     */
    public Integer getDocumento() {
        return documento;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column deuda.documento
     *
     * @param documento the value for deuda.documento
     *
     * @mbggenerated Thu Mar 10 16:44:01 COT 2016
     */
    public void setDocumento(Integer documento) {
        this.documento = documento;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column deuda.fecha_registro
     *
     * @return the value of deuda.fecha_registro
     *
     * @mbggenerated Thu Mar 10 16:44:01 COT 2016
     */
    public Date getFecha_registro() {
        return fecha_registro;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column deuda.fecha_registro
     *
     * @param fecha_registro the value for deuda.fecha_registro
     *
     * @mbggenerated Thu Mar 10 16:44:01 COT 2016
     */
    public void setFecha_registro(Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column deuda.valor_deuda
     *
     * @return the value of deuda.valor_deuda
     *
     * @mbggenerated Thu Mar 10 16:44:01 COT 2016
     */
    public BigDecimal getValor_deuda() {
        return valor_deuda;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column deuda.valor_deuda
     *
     * @param valor_deuda the value for deuda.valor_deuda
     *
     * @mbggenerated Thu Mar 10 16:44:01 COT 2016
     */
    public void setValor_deuda(BigDecimal valor_deuda) {
        this.valor_deuda = valor_deuda;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column deuda.observacion
     *
     * @return the value of deuda.observacion
     *
     * @mbggenerated Thu Mar 10 16:44:01 COT 2016
     */
    public String getObservacion() {
        return observacion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column deuda.observacion
     *
     * @param observacion the value for deuda.observacion
     *
     * @mbggenerated Thu Mar 10 16:44:01 COT 2016
     */
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
}