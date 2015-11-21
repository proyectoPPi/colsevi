package com.colsevi.application;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.colsevi.dao.general.map.GeneralLocalMapper;
import com.colsevi.dao.general.map.GeneralPaginaMapper;

public class ColseviDao {
	
	private GeneralLocalMapper generalLocalMapper;
	private GeneralPaginaMapper generalPaginaMapper;
	
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
		generalLocalMapper = (GeneralLocalMapper) beanFactoryMyBatis.getBean("generalLocalMapper");
		generalPaginaMapper = (GeneralPaginaMapper) beanFactoryMyBatis.getBean("generalPaginaMapper");
	}

	public GeneralLocalMapper getGeneralLocalMapper() {
		return generalLocalMapper;
	}

	public void setGeneralLocalMapper(GeneralLocalMapper generalLocalMapper) {
		this.generalLocalMapper = generalLocalMapper;
	}

	public GeneralPaginaMapper getGeneralPaginaMapper() {
		return generalPaginaMapper;
	}

	public void setGeneralPaginaMapper(GeneralPaginaMapper generalPaginaMapper) {
		this.generalPaginaMapper = generalPaginaMapper;
	}

}
