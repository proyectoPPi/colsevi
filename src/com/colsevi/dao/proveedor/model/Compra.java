package com.colsevi.dao.proveedor.model;

import java.math.BigDecimal;
import java.util.Date;

public class Compra {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column compra.id_compra
	 * @mbggenerated  Sun Mar 27 11:09:07 COT 2016
	 */
	private Integer id_compra;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column compra.id_proveedor
	 * @mbggenerated  Sun Mar 27 11:09:07 COT 2016
	 */
	private Integer id_proveedor;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column compra.valor
	 * @mbggenerated  Sun Mar 27 11:09:07 COT 2016
	 */
	private BigDecimal valor;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column compra.fecha_compra
	 * @mbggenerated  Sun Mar 27 11:09:07 COT 2016
	 */
	private Date fecha_compra;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column compra.pagado
	 * @mbggenerated  Sun Mar 27 11:09:07 COT 2016
	 */
	private Boolean pagado;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column compra.id_compra
	 * @return  the value of compra.id_compra
	 * @mbggenerated  Sun Mar 27 11:09:07 COT 2016
	 */
	public Integer getId_compra() {
		return id_compra;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column compra.id_compra
	 * @param id_compra  the value for compra.id_compra
	 * @mbggenerated  Sun Mar 27 11:09:07 COT 2016
	 */
	public void setId_compra(Integer id_compra) {
		this.id_compra = id_compra;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column compra.id_proveedor
	 * @return  the value of compra.id_proveedor
	 * @mbggenerated  Sun Mar 27 11:09:07 COT 2016
	 */
	public Integer getId_proveedor() {
		return id_proveedor;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column compra.id_proveedor
	 * @param id_proveedor  the value for compra.id_proveedor
	 * @mbggenerated  Sun Mar 27 11:09:07 COT 2016
	 */
	public void setId_proveedor(Integer id_proveedor) {
		this.id_proveedor = id_proveedor;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column compra.valor
	 * @return  the value of compra.valor
	 * @mbggenerated  Sun Mar 27 11:09:07 COT 2016
	 */
	public BigDecimal getValor() {
		return valor;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column compra.valor
	 * @param valor  the value for compra.valor
	 * @mbggenerated  Sun Mar 27 11:09:07 COT 2016
	 */
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column compra.fecha_compra
	 * @return  the value of compra.fecha_compra
	 * @mbggenerated  Sun Mar 27 11:09:07 COT 2016
	 */
	public Date getFecha_compra() {
		return fecha_compra;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column compra.fecha_compra
	 * @param fecha_compra  the value for compra.fecha_compra
	 * @mbggenerated  Sun Mar 27 11:09:07 COT 2016
	 */
	public void setFecha_compra(Date fecha_compra) {
		this.fecha_compra = fecha_compra;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column compra.pagado
	 * @return  the value of compra.pagado
	 * @mbggenerated  Sun Mar 27 11:09:07 COT 2016
	 */
	public Boolean getPagado() {
		return pagado;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column compra.pagado
	 * @param pagado  the value for compra.pagado
	 * @mbggenerated  Sun Mar 27 11:09:07 COT 2016
	 */
	public void setPagado(Boolean pagado) {
		this.pagado = pagado;
	}
}