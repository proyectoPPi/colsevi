package com.colsevi.application;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.colsevi.dao.general.map.GeneralLocalMapper;

public class ColseviDao {
	
	private GeneralLocalMapper GeneralLocalMapper;
	
	private static ColseviDao current = null;

	private synchronized static void createInstance() {
		if (current == null) {
			current = new ColseviDao();
		}
	}

	public static ColseviDao getInstance() {
		if (current == null)
			createInstance();
		return current;
	}

	protected ColseviDao() {
		try{
			ApplicationContext beanFactoryMyBatis = new ClassPathXmlApplicationContext("/spring-mybatis-config.xml");
			inicializarMappers(beanFactoryMyBatis);
		}catch(Exception e){
			e.printStackTrace();
			e.getMessage();
		}
	}
	// inicializar Mappers MyBatis
	public void inicializarMappers(BeanFactory beanFactoryMyBatis) {
		GeneralLocalMapper = (GeneralLocalMapper) beanFactoryMyBatis.getBean("GeneralLocalMapper");
	}

	public GeneralLocalMapper getGeneralLocalMapper() {
		return GeneralLocalMapper;
	}

	public void setGeneralLocalMapper(GeneralLocalMapper generalLocalMapper) {
		GeneralLocalMapper = generalLocalMapper;
	}
}
