package com.colsevi.dao.caja.model;

import java.util.Date;

public class CierreCaja {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cierre_caja.id_cierre_caja
     *
     * @mbggenerated Tue Apr 26 19:26:30 COT 2016
     */
    private Integer id_cierre_caja;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cierre_caja.id_persona
     *
     * @mbggenerated Tue Apr 26 19:26:30 COT 2016
     */
    private Integer id_persona;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cierre_caja.mensaje
     *
     * @mbggenerated Tue Apr 26 19:26:30 COT 2016
     */
    private String mensaje;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cierre_caja.fecha_ejecucion
     *
     * @mbggenerated Tue Apr 26 19:26:30 COT 2016
     */
    private Date fecha_ejecucion;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cierre_caja.fecha_cierre
     *
     * @mbggenerated Tue Apr 26 19:26:30 COT 2016
     */
    private Date fecha_cierre;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cierre_caja.id_cierre_caja
     *
     * @return the value of cierre_caja.id_cierre_caja
     *
     * @mbggenerated Tue Apr 26 19:26:30 COT 2016
     */
    public Integer getId_cierre_caja() {
        return id_cierre_caja;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cierre_caja.id_cierre_caja
     *
     * @param id_cierre_caja the value for cierre_caja.id_cierre_caja
     *
     * @mbggenerated Tue Apr 26 19:26:30 COT 2016
     */
    public void setId_cierre_caja(Integer id_cierre_caja) {
        this.id_cierre_caja = id_cierre_caja;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cierre_caja.id_persona
     *
     * @return the value of cierre_caja.id_persona
     *
     * @mbggenerated Tue Apr 26 19:26:30 COT 2016
     */
    public Integer getId_persona() {
        return id_persona;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cierre_caja.id_persona
     *
     * @param id_persona the value for cierre_caja.id_persona
     *
     * @mbggenerated Tue Apr 26 19:26:30 COT 2016
     */
    public void setId_persona(Integer id_persona) {
        this.id_persona = id_persona;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cierre_caja.mensaje
     *
     * @return the value of cierre_caja.mensaje
     *
     * @mbggenerated Tue Apr 26 19:26:30 COT 2016
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cierre_caja.mensaje
     *
     * @param mensaje the value for cierre_caja.mensaje
     *
     * @mbggenerated Tue Apr 26 19:26:30 COT 2016
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cierre_caja.fecha_ejecucion
     *
     * @return the value of cierre_caja.fecha_ejecucion
     *
     * @mbggenerated Tue Apr 26 19:26:30 COT 2016
     */
    public Date getFecha_ejecucion() {
        return fecha_ejecucion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cierre_caja.fecha_ejecucion
     *
     * @param fecha_ejecucion the value for cierre_caja.fecha_ejecucion
     *
     * @mbggenerated Tue Apr 26 19:26:30 COT 2016
     */
    public void setFecha_ejecucion(Date fecha_ejecucion) {
        this.fecha_ejecucion = fecha_ejecucion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cierre_caja.fecha_cierre
     *
     * @return the value of cierre_caja.fecha_cierre
     *
     * @mbggenerated Tue Apr 26 19:26:30 COT 2016
     */
    public Date getFecha_cierre() {
        return fecha_cierre;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cierre_caja.fecha_cierre
     *
     * @param fecha_cierre the value for cierre_caja.fecha_cierre
     *
     * @mbggenerated Tue Apr 26 19:26:30 COT 2016
     */
    public void setFecha_cierre(Date fecha_cierre) {
        this.fecha_cierre = fecha_cierre;
    }
}