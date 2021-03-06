package com.colsevi.dao.catalogo.model;

public class Catalogo {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column catalogo.id_catalogo
	 * @mbggenerated  Tue Apr 12 16:35:47 COT 2016
	 */
	private Integer id_catalogo;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column catalogo.id_establecimiento
	 * @mbggenerated  Tue Apr 12 16:35:47 COT 2016
	 */
	private Integer id_establecimiento;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column catalogo.nombre
	 * @mbggenerated  Tue Apr 12 16:35:47 COT 2016
	 */
	private String nombre;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column catalogo.descripcion
	 * @mbggenerated  Tue Apr 12 16:35:47 COT 2016
	 */
	private String descripcion;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column catalogo.vigente
	 * @mbggenerated  Tue Apr 12 16:35:47 COT 2016
	 */
	private Boolean vigente;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column catalogo.id_catalogo
	 * @return  the value of catalogo.id_catalogo
	 * @mbggenerated  Tue Apr 12 16:35:47 COT 2016
	 */
	public Integer getId_catalogo() {
		return id_catalogo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column catalogo.id_catalogo
	 * @param id_catalogo  the value for catalogo.id_catalogo
	 * @mbggenerated  Tue Apr 12 16:35:47 COT 2016
	 */
	public void setId_catalogo(Integer id_catalogo) {
		this.id_catalogo = id_catalogo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column catalogo.id_establecimiento
	 * @return  the value of catalogo.id_establecimiento
	 * @mbggenerated  Tue Apr 12 16:35:47 COT 2016
	 */
	public Integer getId_establecimiento() {
		return id_establecimiento;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column catalogo.id_establecimiento
	 * @param id_establecimiento  the value for catalogo.id_establecimiento
	 * @mbggenerated  Tue Apr 12 16:35:47 COT 2016
	 */
	public void setId_establecimiento(Integer id_establecimiento) {
		this.id_establecimiento = id_establecimiento;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column catalogo.nombre
	 * @return  the value of catalogo.nombre
	 * @mbggenerated  Tue Apr 12 16:35:47 COT 2016
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column catalogo.nombre
	 * @param nombre  the value for catalogo.nombre
	 * @mbggenerated  Tue Apr 12 16:35:47 COT 2016
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column catalogo.descripcion
	 * @return  the value of catalogo.descripcion
	 * @mbggenerated  Tue Apr 12 16:35:47 COT 2016
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column catalogo.descripcion
	 * @param descripcion  the value for catalogo.descripcion
	 * @mbggenerated  Tue Apr 12 16:35:47 COT 2016
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column catalogo.vigente
	 * @return  the value of catalogo.vigente
	 * @mbggenerated  Tue Apr 12 16:35:47 COT 2016
	 */
	public Boolean getVigente() {
		return vigente;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column catalogo.vigente
	 * @param vigente  the value for catalogo.vigente
	 * @mbggenerated  Tue Apr 12 16:35:47 COT 2016
	 */
	public void setVigente(Boolean vigente) {
		this.vigente = vigente;
	}
}