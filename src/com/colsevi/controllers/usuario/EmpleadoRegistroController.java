package com.colsevi.controllers.usuario;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
public class EmpleadoRegistroController extends BaseConfigController{

	private static final long serialVersionUID = -8946886707945498254L;
	
	@RequestMapping("/Usuario/EmpleadoRegistro")
	public ModelAndView Registro(HttpServletRequest request, ModelMap model){
		model.addAttribute("tipoDoc", GeneralManager.listaTipoDocumento());
		String persona = request.getParameter("Persona");
		if(persona != null){
			model.addAttribute("bean", getBeanCliente(Integer.parseInt(persona)));
		}
		return new ModelAndView("usuario/RegistroEmpleado","col", getValoresGenericos(request));
	}
	
	@RequestMapping("/Usuario/ClienteEmpleado/Grabar")
	public ModelAndView Guardar(HttpServletRequest request, ModelMap model, ClienteBean bean){
		
		SqlSession sesion = null;
		String error = "";
		Persona persona = new Persona();
		Usuario usuario = new Usuario();
		Direccion direccion = new Direccion();
		Telefono telFijo = new Telefono();
		Telefono telCel = new Telefono();
		Telefono telCon = new Telefono();
		
		try{
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
				usuario.setUsuario(bean.getUsuario());
				
				UsuarioExample UE = new UsuarioExample();
				UE.createCriteria().andUsuarioEqualTo(bean.getUsuario());
				if(ColseviDao.getInstance().getUsuarioMapper().selectByExample(UE).size() > 0){
					model.addAttribute("error", "Ya existe un usuario registrado en el sistema");
					return Registro(request, model);
				}
			}
			
			PersonaExample PE = new PersonaExample();
			PE.createCriteria().andDocumentoEqualTo(bean.getDocumento());
			if(ColseviDao.getInstance().getPersonaMapper().selectByExample(PE).size() > 0){
				model.addAttribute("error", "Ya existe el documento " + bean.getDocumento() + " registrado en el sistema");
				return Registro(request, model);
			}
			
			if(null != bean.getTipo_doc() && !bean.getTipo_doc().trim().isEmpty())
				persona.setTipo_doc(Integer.parseInt(bean.getTipo_doc()));
			else
				error += "Seleccione un tipo de documento";
			String sha = null;
			if(bean.getClave() != null && !bean.getClave().trim().isEmpty()){
				sha = GeneralManager.byteToHex(bean.getClave());
				if(sha == null){
					model.addAttribute("error", "Contactar al administrador");
					return Registro(request, model);
				}
			}
			
			if(sha == null && persona != null){
				model.addAttribute("error","Ingresar la clave");
				return Registro(request, model);
			}
			
			usuario.setClave(sha);
			usuario.setId_rol(RolE.EMPLEADO.getRolE());
			
			direccion.setBarrio(bean.getBarrio());
			direccion.setDescripcion(bean.getDescripcion());
			direccion.setDireccion(bean.getDireccion());
			
			telFijo.setTelefono(bean.getTelFijo());
			telFijo.setId_tipo_telefono(TipoTelefonoE.FIJO.getTipoTelefonoE());
			telCel.setTelefono(bean.getTelCel());
			telCel.setId_tipo_telefono(TipoTelefonoE.CELULAR.getTipoTelefonoE());
			telCon.setTelefono(bean.getTelCon());
			telCon.setId_tipo_telefono(TipoTelefonoE.CONTACTO.getTipoTelefonoE());
			
			if(!error.isEmpty()){
				model.addAttribute("error", error);
				return Registro(request, model);
			}
		}catch(Exception e){
			model.addAttribute("error", "Ocurrió un error, contactar al administrador");
			return Registro(request, model);
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
			model.addAttribute("correcto", "Cliente guardado Correctamente");
		}catch(Exception e){
			model.addAttribute("error", "Ocurrió un error, contactar al administrador");
			ColseviDaoTransaccion.ErrorRollback(sesion);
		}
		ColseviDaoTransaccion.CerrarSesion(sesion);
		model.addAttribute("bean", bean);
		
		return Registro(request, model);
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
		dir = ColseviDao.getInstance().getDireccionMapper().selectByExample(DE).get(0);
		
		if(dir != null && dir.getId_direccion() != null){
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
