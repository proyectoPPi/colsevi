package com.colsevi.dao.inventario.model;

public class Inventario {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column inventario.id_inventario
	 * @mbggenerated  Mon Mar 21 22:50:01 COT 2016
	 */
	private Integer id_inventario;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column inventario.id_establecimiento
	 * @mbggenerated  Mon Mar 21 22:50:01 COT 2016
	 */
	private Integer id_establecimiento;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column inventario.id_producto
	 * @mbggenerated  Mon Mar 21 22:50:01 COT 2016
	 */
	private Integer id_producto;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column inventario.disponible
	 * @mbggenerated  Mon Mar 21 22:50:01 COT 2016
	 */
	private Integer disponible;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column inventario.comprometido
	 * @mbggenerated  Mon Mar 21 22:50:01 COT 2016
	 */
	private Integer comprometido;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column inventario.id_inventario
	 * @return  the value of inventario.id_inventario
	 * @mbggenerated  Mon Mar 21 22:50:01 COT 2016
	 */
	public Integer getId_inventario() {
		return id_inventario;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column inventario.id_inventario
	 * @param id_inventario  the value for inventario.id_inventario
	 * @mbggenerated  Mon Mar 21 22:50:01 COT 2016
	 */
	public void setId_inventario(Integer id_inventario) {
		this.id_inventario = id_inventario;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column inventario.id_establecimiento
	 * @return  the value of inventario.id_establecimiento
	 * @mbggenerated  Mon Mar 21 22:50:01 COT 2016
	 */
	public Integer getId_establecimiento() {
		return id_establecimiento;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column inventario.id_establecimiento
	 * @param id_establecimiento  the value for inventario.id_establecimiento
	 * @mbggenerated  Mon Mar 21 22:50:01 COT 2016
	 */
	public void setId_establecimiento(Integer id_establecimiento) {
		this.id_establecimiento = id_establecimiento;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column inventario.id_producto
	 * @return  the value of inventario.id_producto
	 * @mbggenerated  Mon Mar 21 22:50:01 COT 2016
	 */
	public Integer getId_producto() {
		return id_producto;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column inventario.id_producto
	 * @param id_producto  the value for inventario.id_producto
	 * @mbggenerated  Mon Mar 21 22:50:01 COT 2016
	 */
	public void setId_producto(Integer id_producto) {
		this.id_producto = id_producto;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column inventario.disponible
	 * @return  the value of inventario.disponible
	 * @mbggenerated  Mon Mar 21 22:50:01 COT 2016
	 */
	public Integer getDisponible() {
		return disponible;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column inventario.disponible
	 * @param disponible  the value for inventario.disponible
	 * @mbggenerated  Mon Mar 21 22:50:01 COT 2016
	 */
	public void setDisponible(Integer disponible) {
		this.disponible = disponible;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column inventario.comprometido
	 * @return  the value of inventario.comprometido
	 * @mbggenerated  Mon Mar 21 22:50:01 COT 2016
	 */
	public Integer getComprometido() {
		return comprometido;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column inventario.comprometido
	 * @param comprometido  the value for inventario.comprometido
	 * @mbggenerated  Mon Mar 21 22:50:01 COT 2016
	 */
	public void setComprometido(Integer comprometido) {
		this.comprometido = comprometido;
	}
}