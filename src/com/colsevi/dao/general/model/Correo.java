package com.colsevi.dao.general.model;

public class Correo {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column correo.id_correo
	 * @mbggenerated  Tue Apr 12 16:26:48 COT 2016
	 */
	private Integer id_correo;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column correo.id_persona
	 * @mbggenerated  Tue Apr 12 16:26:48 COT 2016
	 */
	private Integer id_persona;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column correo.correo
	 * @mbggenerated  Tue Apr 12 16:26:48 COT 2016
	 */
	private String correo;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column correo.id_correo
	 * @return  the value of correo.id_correo
	 * @mbggenerated  Tue Apr 12 16:26:48 COT 2016
	 */
	public Integer getId_correo() {
		return id_correo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column correo.id_correo
	 * @param id_correo  the value for correo.id_correo
	 * @mbggenerated  Tue Apr 12 16:26:48 COT 2016
	 */
	public void setId_correo(Integer id_correo) {
		this.id_correo = id_correo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column correo.id_persona
	 * @return  the value of correo.id_persona
	 * @mbggenerated  Tue Apr 12 16:26:48 COT 2016
	 */
	public Integer getId_persona() {
		return id_persona;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column correo.id_persona
	 * @param id_persona  the value for correo.id_persona
	 * @mbggenerated  Tue Apr 12 16:26:48 COT 2016
	 */
	public void setId_persona(Integer id_persona) {
		this.id_persona = id_persona;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column correo.correo
	 * @return  the value of correo.correo
	 * @mbggenerated  Tue Apr 12 16:26:48 COT 2016
	 */
	public String getCorreo() {
		return correo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column correo.correo
	 * @param correo  the value for correo.correo
	 * @mbggenerated  Tue Apr 12 16:26:48 COT 2016
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}
}