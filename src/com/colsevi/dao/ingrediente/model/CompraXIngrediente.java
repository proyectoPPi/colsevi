package com.colsevi.dao.ingrediente.model;

import java.util.Date;

public class CompraXIngrediente extends CompraXIngredienteKey {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column compra_x_ingrediente.id_tipo_peso
	 * @mbggenerated  Sat Mar 26 15:38:43 COT 2016
	 */
	private Integer id_tipo_peso;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column compra_x_ingrediente.fecha_vencimiento
	 * @mbggenerated  Sat Mar 26 15:38:43 COT 2016
	 */
	private Date fecha_vencimiento;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column compra_x_ingrediente.cantidad
	 * @mbggenerated  Sat Mar 26 15:38:43 COT 2016
	 */
	private Integer cantidad;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column compra_x_ingrediente.id_tipo_peso
	 * @return  the value of compra_x_ingrediente.id_tipo_peso
	 * @mbggenerated  Sat Mar 26 15:38:43 COT 2016
	 */
	public Integer getId_tipo_peso() {
		return id_tipo_peso;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column compra_x_ingrediente.id_tipo_peso
	 * @param id_tipo_peso  the value for compra_x_ingrediente.id_tipo_peso
	 * @mbggenerated  Sat Mar 26 15:38:43 COT 2016
	 */
	public void setId_tipo_peso(Integer id_tipo_peso) {
		this.id_tipo_peso = id_tipo_peso;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column compra_x_ingrediente.fecha_vencimiento
	 * @return  the value of compra_x_ingrediente.fecha_vencimiento
	 * @mbggenerated  Sat Mar 26 15:38:43 COT 2016
	 */
	public Date getFecha_vencimiento() {
		return fecha_vencimiento;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column compra_x_ingrediente.fecha_vencimiento
	 * @param fecha_vencimiento  the value for compra_x_ingrediente.fecha_vencimiento
	 * @mbggenerated  Sat Mar 26 15:38:43 COT 2016
	 */
	public void setFecha_vencimiento(Date fecha_vencimiento) {
		this.fecha_vencimiento = fecha_vencimiento;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column compra_x_ingrediente.cantidad
	 * @return  the value of compra_x_ingrediente.cantidad
	 * @mbggenerated  Sat Mar 26 15:38:43 COT 2016
	 */
	public Integer getCantidad() {
		return cantidad;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column compra_x_ingrediente.cantidad
	 * @param cantidad  the value for compra_x_ingrediente.cantidad
	 * @mbggenerated  Sat Mar 26 15:38:43 COT 2016
	 */
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
}