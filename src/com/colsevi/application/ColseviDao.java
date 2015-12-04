package com.colsevi.application;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.colsevi.dao.usuario.map.EstablecimientoMapper;
import com.colsevi.dao.usuario.map.PaginaMapper;
import com.colsevi.dao.usuario.map.PersonaMapper;
import com.colsevi.dao.usuario.map.RolMapper;
import com.colsevi.dao.usuario.map.TipoDocumentoMapper;
import com.colsevi.dao.usuario.map.UsuarioMapper;
import com.colsevi.dao.usuario.map.UsuarioXRolMapper;

public class ColseviDao {
	
	private TipoDocumentoMapper tipoDocumentoMapper;
	private PersonaMapper personaMapper;
	private PaginaMapper paginaMapper;
	private RolMapper rolMapper;
	private UsuarioMapper usuarioMapper; 
	private UsuarioXRolMapper usuarioXRolMapper; 
	private EstablecimientoMapper establecimientoMapper;
	
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
		tipoDocumentoMapper = (TipoDocumentoMapper) beanFactoryMyBatis.getBean("tipoDocumentoMapper");
		personaMapper = (PersonaMapper) beanFactoryMyBatis.getBean("personaMapper");
		paginaMapper = (PaginaMapper) beanFactoryMyBatis.getBean("paginaMapper");
		rolMapper = (RolMapper) beanFactoryMyBatis.getBean("rolMapper");
		usuarioMapper = (UsuarioMapper) beanFactoryMyBatis.getBean("usuarioMapper");
		usuarioXRolMapper = (UsuarioXRolMapper) beanFactoryMyBatis.getBean("usuarioXRolMapper");
		establecimientoMapper = (EstablecimientoMapper) beanFactoryMyBatis.getBean("establecimientoMapper");
	}

	public TipoDocumentoMapper getTipoDocumentoMapper() {
		return tipoDocumentoMapper;
	}

	public void setTipoDocumentoMapper(TipoDocumentoMapper tipoDocumentoMapper) {
		this.tipoDocumentoMapper = tipoDocumentoMapper;
	}

	public PersonaMapper getPersonaMapper() {
		return personaMapper;
	}

	public void setPersonaMapper(PersonaMapper personaMapper) {
		this.personaMapper = personaMapper;
	}

	public PaginaMapper getPaginaMapper() {
		return paginaMapper;
	}

	public void setPaginaMapper(PaginaMapper paginaMapper) {
		this.paginaMapper = paginaMapper;
	}

	public RolMapper getRolMapper() {
		return rolMapper;
	}

	public void setRolMapper(RolMapper rolMapper) {
		this.rolMapper = rolMapper;
	}

	public UsuarioMapper getUsuarioMapper() {
		return usuarioMapper;
	}

	public void setUsuarioMapper(UsuarioMapper usuarioMapper) {
		this.usuarioMapper = usuarioMapper;
	}

	public UsuarioXRolMapper getUsuarioXRolMapper() {
		return usuarioXRolMapper;
	}

	public void setUsuarioXRolMapper(UsuarioXRolMapper usuarioXRolMapper) {
		this.usuarioXRolMapper = usuarioXRolMapper;
	}

	public EstablecimientoMapper getEstablecimientoMapper() {
		return establecimientoMapper;
	}

	public void setEstablecimientoMapper(EstablecimientoMapper establecimientoMapper) {
		this.establecimientoMapper = establecimientoMapper;
	}
}