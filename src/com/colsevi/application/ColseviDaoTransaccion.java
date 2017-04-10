package com.colsevi.application;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class ColseviDaoTransaccion {

	public static SqlSession getInstance() {
		return ColseviDaoTransaccion("/spring-mybatis-configTransaccion.xml");
	}

	public static SqlSession getInstance(String transaccion) {
		return ColseviDaoTransaccion(transaccion);
	}

	private static SqlSession ColseviDaoTransaccion(String transaccion) {
		try{
			Reader reader = Resources.getResourceAsReader(transaccion);
			SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			return sqlMapper.openSession();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static Integer Insertar(SqlSession session, String url, Object bean){
		return session.insert(url, bean);
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