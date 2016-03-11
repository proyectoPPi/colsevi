package com.colsevi.dao.producto.map;

import com.colsevi.dao.producto.model.Receta;
import com.colsevi.dao.producto.model.RecetaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RecetaMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table receta
	 * @mbggenerated  Thu Mar 10 16:30:53 COT 2016
	 */
	int countByExample(RecetaExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table receta
	 * @mbggenerated  Thu Mar 10 16:30:53 COT 2016
	 */
	int deleteByExample(RecetaExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table receta
	 * @mbggenerated  Thu Mar 10 16:30:53 COT 2016
	 */
	int deleteByPrimaryKey(Integer id_receta);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table receta
	 * @mbggenerated  Thu Mar 10 16:30:53 COT 2016
	 */
	int insert(Receta record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table receta
	 * @mbggenerated  Thu Mar 10 16:30:53 COT 2016
	 */
	int insertSelective(Receta record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table receta
	 * @mbggenerated  Thu Mar 10 16:30:53 COT 2016
	 */
	List<Receta> selectByExample(RecetaExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table receta
	 * @mbggenerated  Thu Mar 10 16:30:53 COT 2016
	 */
	Receta selectByPrimaryKey(Integer id_receta);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table receta
	 * @mbggenerated  Thu Mar 10 16:30:53 COT 2016
	 */
	int updateByExampleSelective(@Param("record") Receta record, @Param("example") RecetaExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table receta
	 * @mbggenerated  Thu Mar 10 16:30:53 COT 2016
	 */
	int updateByExample(@Param("record") Receta record, @Param("example") RecetaExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table receta
	 * @mbggenerated  Thu Mar 10 16:30:53 COT 2016
	 */
	int updateByPrimaryKeySelective(Receta record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table receta
	 * @mbggenerated  Thu Mar 10 16:30:53 COT 2016
	 */
	int updateByPrimaryKey(Receta record);
}