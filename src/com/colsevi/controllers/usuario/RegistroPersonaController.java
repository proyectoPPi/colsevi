package com.colsevi.controllers.usuario;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.colsevi.application.ColseviDao;
import com.colsevi.application.ColseviDaoTransaccion;
import com.colsevi.application.GeneralManager;
import com.colsevi.controllers.BaseConfigController;
import com.colsevi.controllers.general.TipoTelefonoE;
import com.colsevi.dao.general.model.Direccion;
import com.colsevi.dao.general.model.DireccionExample;
import com.colsevi.dao.general.model.Telefono;
import com.colsevi.dao.general.model.TelefonoExample;
import com.colsevi.dao.usuario.model.ClienteBean;
import com.colsevi.dao.usuario.model.Persona;
import com.colsevi.dao.usuario.model.PersonaExample;
import com.colsevi.dao.usuario.model.Usuario;
import com.colsevi.dao.usuario.model.UsuarioExample;

@Controller
@RequestMapping("/Usuario/RegistroPersona")
public class RegistroPersonaController extends BaseConfigController{

	private static final long serialVersionUID = 5049120499336624560L;

	@RequestMapping
	public String Registro(HttpServletRequest request, ModelMap model){
		try{
			model.addAttribute("LRol", GeneralManager.listaRolPorSesion(getUsuario(request).getRol()));
			model.addAttribute("tipoDoc", GeneralManager.listaTipoDocumento());
			String persona = request.getParameter("editar");
			if(persona != null){
				model.addAttribute("bean", getBeanCliente(Integer.parseInt(persona)));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "usuario/RegistroPersona";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/Grabar")
	public void Guardar(HttpServletRequest request, HttpServletResponse response, ClienteBean bean) throws IOException{
		
		JSONObject resultVista = new JSONObject();
		SqlSession sesion = null;
		String error = "";
		Persona persona = new Persona();
		Usuario usuario = new Usuario();
		Direccion direccion = new Direccion();
		Telefono telFijo = new Telefono();
		Telefono telCel = new Telefono();
		Telefono telCon = new Telefono();
		
		try{
			String editar = request.getParameter("editar");
			persona.setNombre(bean.getNombre());
			persona.setApellido(bean.getApellido());
			persona.setDocumento(bean.getDocumento());
			persona.setGenero(bean.getGenero());
			if(null != bean.getId_persona() && !bean.getId_persona().trim().isEmpty()){
				persona.setId_persona(Integer.parseInt(bean.getId_persona()));
				usuario.setId_usuario(Integer.parseInt(bean.getId_usuario()));
				if(!bean.getId_direccion().trim().isEmpty())
					direccion.setId_direccion(Integer.parseInt(bean.getId_direccion()));
				
				if(!bean.getId_telFijo().trim().isEmpty())
					telFijo.setId_telefono(Integer.parseInt(bean.getId_telFijo()));
				if(!bean.getId_telCel().trim().isEmpty())
					telCel.setId_tipo_telefono(Integer.parseInt(bean.getId_telCel()));
				if(!bean.getId_telCon().trim().isEmpty())
					telCon.setId_telefono(Integer.parseInt(bean.getId_telCon()));
			}else{
				
				if(!editar.equals("T")){
					usuario.setUsuario(bean.getUsuario());
					UsuarioExample UE = new UsuarioExample();
					UE.createCriteria().andUsuarioEqualTo(bean.getUsuario());
					if(ColseviDao.getInstance().getUsuarioMapper().selectByExample(UE).size() > 0){
						resultVista.put("error", "Ya existe un usuario registrado en el sistema");
						ResponseJson(request, response, resultVista);
						return;
					}
				}
			}
			
			if(!editar.equals("T")){
				PersonaExample PE = new PersonaExample();
				PE.createCriteria().andDocumentoEqualTo(bean.getDocumento());
				if(ColseviDao.getInstance().getPersonaMapper().selectByExample(PE).size() > 0){
					resultVista.put("error", "Ya existe el documento " + bean.getDocumento() + " registrado en el sistema");
					ResponseJson(request, response, resultVista);
					return;
				}
			}
			
			if(null != bean.getTipo_doc() && !bean.getTipo_doc().trim().isEmpty())
				persona.setTipo_doc(Integer.parseInt(bean.getTipo_doc()));
			else
				error += "Seleccione un tipo de documento";
			String sha = null;
			if(bean.getClave() != null && !bean.getClave().trim().isEmpty()){
				sha = GeneralManager.byteToHex(bean.getClave());
				if(sha == null){
					resultVista.put("error", "Contactar al administrador");
					ResponseJson(request, response, resultVista);
					return;
				}
			}
			
			if(sha == null && persona != null){
				resultVista.put("error","Ingresar la clave");
				ResponseJson(request, response, resultVista);
				return;
			}
			
			usuario.setClave(sha);
				if(!request.getParameter("rolPersona").trim().isEmpty())
					usuario.setId_rol(Integer.parseInt(request.getParameter("rolPersona")));
				else{
					if(!editar.equals("T")){
						resultVista.put("error","Seleccionar el rol");
						ResponseJson(request, response, resultVista);
						return;
					}
				}
			if(!bean.getBarrio().trim().isEmpty())
				direccion.setBarrio(bean.getBarrio());
			
			if(!bean.getDescripcion().trim().isEmpty())
				direccion.setDescripcion(bean.getDescripcion());
			
			if(!bean.getDireccion().trim().isEmpty())
				direccion.setDireccion(bean.getDireccion());
			
			if(!bean.getTelFijo().trim().isEmpty())
				telFijo.setTelefono(bean.getTelFijo());
			telFijo.setId_tipo_telefono(TipoTelefonoE.FIJO.getTipoTelefonoE());
			
			if(!bean.getTelCel().trim().isEmpty())
				telCel.setTelefono(bean.getTelCel());
			telCel.setId_tipo_telefono(TipoTelefonoE.CELULAR.getTipoTelefonoE());
			
			if(!bean.getTelCon().trim().isEmpty())
				telCon.setTelefono(bean.getTelCon());
			telCon.setId_tipo_telefono(TipoTelefonoE.CONTACTO.getTipoTelefonoE());
			
			if(!error.isEmpty()){
				resultVista.put("error", error);
				ResponseJson(request, response, resultVista);
				return;
			}
		}catch(Exception e){
			resultVista.put("error", "Ocurrió un error, contactar al administrador");
			ResponseJson(request, response, resultVista);
			return;
		}
		
		try{
			
			sesion = ColseviDaoTransaccion.getInstance();
			
			if(persona.getId_persona() != null){
				ColseviDaoTransaccion.Actualizar(sesion, "com.colsevi.dao.usuario.map.PersonaMapper.updateByPrimaryKeySelective", persona);
			}else{
				ColseviDaoTransaccion.Insertar(sesion, "com.colsevi.dao.usuario.map.PersonaMapper.insertSelective", persona);
			}
			
			direccion.setId_persona(persona.getId_persona());
			usuario.setId_persona(persona.getId_persona());
			telFijo.setId_persona(persona.getId_persona());
			telCel.setId_persona(persona.getId_persona());
			telCon.setId_persona(persona.getId_persona());
			
			if(usuario.getId_usuario() != null){
				ColseviDaoTransaccion.Actualizar(sesion, "com.colsevi.dao.usuario.map.UsuarioMapper.updateByPrimaryKeySelective", usuario);
			}else{
				usuario.setEstado("F");
				usuario.setPrimer_login("F");
				ColseviDaoTransaccion.Insertar(sesion, "com.colsevi.dao.usuario.map.UsuarioMapper.insertSelective", usuario);
			}
			
			if(direccion.getId_direccion() != null){
				ColseviDaoTransaccion.Actualizar(sesion,"com.colsevi.dao.general.map.DireccionMapper.updateByPrimaryKeySelective", direccion);
			}else{
				if(direccion.getDescripcion() != null && !direccion.getDescripcion().trim().isEmpty()){
					ColseviDaoTransaccion.Insertar(sesion, "com.colsevi.dao.general.map.DireccionMapper.insertSelective", direccion);
				}
			}
			
			if(telFijo.getId_telefono() != null){
				ColseviDaoTransaccion.Actualizar(sesion,"com.colsevi.dao.general.map.TelefonoMapper.updateByPrimaryKeySelective", telFijo);
			}else{
				if(telFijo.getTelefono() != null && !telFijo.getTelefono().trim().isEmpty()){
					ColseviDaoTransaccion.Insertar(sesion, "com.colsevi.dao.general.map.TelefonoMapper.insertSelective", telFijo);
				}
			}
			
			if(telCel.getId_telefono() != null){
				ColseviDaoTransaccion.Actualizar(sesion,"com.colsevi.dao.general.map.TelefonoMapper.updateByPrimaryKeySelective", telCel);
			}else{
				if(telCel.getTelefono() != null && !telCel.getTelefono().trim().isEmpty()){
					ColseviDaoTransaccion.Insertar(sesion, "com.colsevi.dao.general.map.TelefonoMapper.insertSelective", telCel);
				}
			}
			
			if(telCon.getId_telefono() != null){
				ColseviDaoTransaccion.Actualizar(sesion,"com.colsevi.dao.general.map.TelefonoMapper.updateByPrimaryKeySelective", telCon);
			}else{
				if(telCon.getTelefono() != null && !telCon.getTelefono().trim().isEmpty()){
					ColseviDaoTransaccion.Insertar(sesion, "com.colsevi.dao.general.map.TelefonoMapper.insertSelective", telCon);
				}
			}
			
			ColseviDaoTransaccion.RealizarCommit(sesion);
			resultVista.put("correcto", "Cliente guardado Correctamente");
		}catch(Exception e){
			resultVista.put("error", "Ocurrió un error, contactar al administrador");
			ColseviDaoTransaccion.ErrorRollback(sesion);
		}
		ColseviDaoTransaccion.CerrarSesion(sesion);
//		resultVista.put("bean", bean);
		
		ResponseJson(request, response, resultVista);
	}
	
	public ClienteBean getBeanCliente(Integer persona){
		ClienteBean bean = new ClienteBean();
		Persona per = null;
		Usuario user = null;
		Direccion dir = null;
		
		per = ColseviDao.getInstance().getPersonaMapper().selectByPrimaryKey(persona);
		
		if(per != null && per.getId_persona() != null){
			bean.setId_persona(per.getId_persona().toString());
			bean.setTipo_doc(per.getTipo_doc().toString());
			bean.setDocumento(per.getDocumento());
			bean.setGenero(per.getGenero());
			bean.setNombre(per.getNombre());
			bean.setApellido(per.getApellido());
		}
		
		UsuarioExample UE = new UsuarioExample();
		UE.createCriteria().andId_personaEqualTo(persona);
		user = ColseviDao.getInstance().getUsuarioMapper().selectByExample(UE).get(0);
		
		if(user != null && user.getId_usuario() != null){
			bean.setId_usuario(user.getId_usuario().toString());
			bean.setUsuario(user.getUsuario());
		}
		
		DireccionExample DE = new DireccionExample();
		DE.createCriteria().andId_personaEqualTo(persona);
		List<Direccion> LD = ColseviDao.getInstance().getDireccionMapper().selectByExample(DE);
		
		if(LD.size() > 0){
			dir = LD.get(0);
			bean.setId_direccion(dir.getId_direccion().toString());
			bean.setDireccion(dir.getDireccion());
			bean.setBarrio(dir.getBarrio());
			bean.setDescripcion(dir.getDescripcion());
		}
		
		TelefonoExample TE = new TelefonoExample();
		TE.createCriteria().andId_personaEqualTo(persona);
		List<Telefono> ListaTel = ColseviDao.getInstance().getTelefonoMapper().selectByExample(TE);
		for(Telefono tel: ListaTel){
			if(tel.getId_tipo_telefono().equals(TipoTelefonoE.FIJO.getTipoTelefonoE())){
				bean.setTelFijo(tel.getTelefono());
				bean.setId_telFijo(tel.getId_telefono().toString());
				continue;
			}else if(tel.getId_tipo_telefono().equals(TipoTelefonoE.CONTACTO.getTipoTelefonoE())){
				bean.setTelCon(tel.getTelefono());
				bean.setId_telCon(tel.getId_telefono().toString());
				continue;
			}else if(tel.getId_tipo_telefono().equals(TipoTelefonoE.CELULAR.getTipoTelefonoE())){
				bean.setTelCel(tel.getTelefono());
				bean.setId_telCel(tel.getId_telefono().toString());
				continue;
			}
		}
		
		return bean;
	}
}
