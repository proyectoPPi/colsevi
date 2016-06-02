package com.colsevi.dao.pago.model;

import java.math.BigDecimal;
import java.util.Date;

public class PagoProveedor {

	/**
	 * This field was generated by MyBatis Generator.
	 * This field corresponds to the database column pago_proveedor.id_pago_proveedor
	 *
	 * @mbggenerated Tue Apr 12 16:29:49 COT 2016
	 */
	private Integer id_pago_proveedor;

	/**
	 * This field was generated by MyBatis Generator.
	 * This field corresponds to the database column pago_proveedor.id_compra
	 *
	 * @mbggenerated Tue Apr 12 16:29:49 COT 2016
	 */
	private Integer id_compra;

	/**
	 * This field was generated by MyBatis Generator.
	 * This field corresponds to the database column pago_proveedor.pendiente
	 *
	 * @mbggenerated Tue Apr 12 16:29:49 COT 2016
	 */
	private BigDecimal pendiente;

	/**
	 * This field was generated by MyBatis Generator.
	 * This field corresponds to the database column pago_proveedor.fecha_pago
	 *
	 * @mbggenerated Tue Apr 12 16:29:49 COT 2016
	 */
	private Date fecha_pago;

	/**
	 * This field was generated by MyBatis Generator.
	 * This field corresponds to the database column pago_proveedor.valor_pagado
	 *
	 * @mbggenerated Tue Apr 12 16:29:49 COT 2016
	 */
	private BigDecimal valor_pagado;

	/**
	 * This field was generated by MyBatis Generator.
	 * This field corresponds to the database column pago_proveedor.observacion
	 *
	 * @mbggenerated Tue Apr 12 16:29:49 COT 2016
	 */
	private String observacion;

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column pago_proveedor.id_pago_proveedor
	 *
	 * @return the value of pago_proveedor.id_pago_proveedor
	 *
	 * @mbggenerated Tue Apr 12 16:29:49 COT 2016
	 */
	public Integer getId_pago_proveedor() {
		return id_pago_proveedor;
	}

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column pago_proveedor.id_pago_proveedor
	 *
	 * @param id_pago_proveedor the value for pago_proveedor.id_pago_proveedor
	 *
	 * @mbggenerated Tue Apr 12 16:29:49 COT 2016
	 */
	public void setId_pago_proveedor(Integer id_pago_proveedor) {
		this.id_pago_proveedor = id_pago_proveedor;
	}

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column pago_proveedor.id_compra
	 *
	 * @return the value of pago_proveedor.id_compra
	 *
	 * @mbggenerated Tue Apr 12 16:29:49 COT 2016
	 */
	public Integer getId_compra() {
		return id_compra;
	}

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column pago_proveedor.id_compra
	 *
	 * @param id_compra the value for pago_proveedor.id_compra
	 *
	 * @mbggenerated Tue Apr 12 16:29:49 COT 2016
	 */
	public void setId_compra(Integer id_compra) {
		this.id_compra = id_compra;
	}

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column pago_proveedor.pendiente
	 *
	 * @return the value of pago_proveedor.pendiente
	 *
	 * @mbggenerated Tue Apr 12 16:29:49 COT 2016
	 */
	public BigDecimal getPendiente() {
		return pendiente;
	}

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column pago_proveedor.pendiente
	 *
	 * @param pendiente the value for pago_proveedor.pendiente
	 *
	 * @mbggenerated Tue Apr 12 16:29:49 COT 2016
	 */
	public void setPendiente(BigDecimal pendiente) {
		this.pendiente = pendiente;
	}

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column pago_proveedor.fecha_pago
	 *
	 * @return the value of pago_proveedor.fecha_pago
	 *
	 * @mbggenerated Tue Apr 12 16:29:49 COT 2016
	 */
	public Date getFecha_pago() {
		return fecha_pago;
	}

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column pago_proveedor.fecha_pago
	 *
	 * @param fecha_pago the value for pago_proveedor.fecha_pago
	 *
	 * @mbggenerated Tue Apr 12 16:29:49 COT 2016
	 */
	public void setFecha_pago(Date fecha_pago) {
		this.fecha_pago = fecha_pago;
	}

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column pago_proveedor.valor_pagado
	 *
	 * @return the value of pago_proveedor.valor_pagado
	 *
	 * @mbggenerated Tue Apr 12 16:29:49 COT 2016
	 */
	public BigDecimal getValor_pagado() {
		return valor_pagado;
	}

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column pago_proveedor.valor_pagado
	 *
	 * @param valor_pagado the value for pago_proveedor.valor_pagado
	 *
	 * @mbggenerated Tue Apr 12 16:29:49 COT 2016
	 */
	public void setValor_pagado(BigDecimal valor_pagado) {
		this.valor_pagado = valor_pagado;
	}

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column pago_proveedor.observacion
	 *
	 * @return the value of pago_proveedor.observacion
	 *
	 * @mbggenerated Tue Apr 12 16:29:49 COT 2016
	 */
	public String getObservacion() {
		return observacion;
	}

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column pago_proveedor.observacion
	 *
	 * @param observacion the value for pago_proveedor.observacion
	 *
	 * @mbggenerated Tue Apr 12 16:29:49 COT 2016
	 */
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
}