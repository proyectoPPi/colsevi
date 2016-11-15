package com.colsevi.application;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class ColseviDaoTransaccion {
	
	private static SqlSession current = null;

	public static SqlSession getInstance() {
		return current = ColseviDaoTransaccion();
	}

	private static SqlSession ColseviDaoTransaccion() {
		try{
			Reader reader = Resources.getResourceAsReader("/spring-mybatis-configTransaccion.xml");
			SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			return sqlMapper.openSession();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static void Insertar(SqlSession session, String url, Object bean){
		session.insert(url, bean);
	}
	
	public static void Actualizar(SqlSession session, String url, Object bean){
		session.update(url, bean);
	}
	
	public static void Eliminar(SqlSession session, String url, Object bean){
		session.delete(url, bean);
	}
	public static void RealizarCommit(SqlSession session){
		session.commit();
	}
	
	public static void ErrorRollback(SqlSession session){
		session.rollback();
	}
	
	public static void CerrarSesion(SqlSession session){
		session.close();
	}
}