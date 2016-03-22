package com.colsevi.application;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.colsevi.dao.catalogo.map.CatalogoMapper;
import com.colsevi.dao.catalogo.map.CatalogoXProductoMapper;
import com.colsevi.dao.general.map.CorreoMapper;
import com.colsevi.dao.general.map.DireccionMapper;
import com.colsevi.dao.general.map.TelefonoMapper;
import com.colsevi.dao.general.map.TipoTelefonoMapper;
import com.colsevi.dao.ingrediente.map.ClasificarIngredienteMapper;
import com.colsevi.dao.ingrediente.map.CompraXIngredienteMapper;
import com.colsevi.dao.ingrediente.map.IngredienteMapper;
import com.colsevi.dao.ingrediente.map.TipoPesoMapper;
import com.colsevi.dao.inventario.map.CategoriaModuloMapper;
import com.colsevi.dao.inventario.map.InventarioMapper;
import com.colsevi.dao.inventario.map.MotivoMapper;
import com.colsevi.dao.inventario.map.MovimientoInventarioMapper;
import com.colsevi.dao.pago.map.CobroCategoriaMapper;
import com.colsevi.dao.pago.map.CobroMapper;
import com.colsevi.dao.pago.map.DetallePagoMapper;
import com.colsevi.dao.pago.map.DeudaMapper;
import com.colsevi.dao.pago.map.PagoMapper;
import com.colsevi.dao.pedido.map.DetallePedidoMapper;
import com.colsevi.dao.pedido.map.EstadoPedidoMapper;
import com.colsevi.dao.pedido.map.PedidoMapper;
import com.colsevi.dao.producto.map.IngredienteXProductoMapper;
import com.colsevi.dao.producto.map.ProductoMapper;
import com.colsevi.dao.producto.map.RecetaMapper;
import com.colsevi.dao.producto.map.TipoProductoMapper;
import com.colsevi.dao.proveedor.map.CompraMapper;
import com.colsevi.dao.proveedor.map.ProveedorMapper;
import com.colsevi.dao.proveedor.map.TipoProveedorMapper;
import com.colsevi.dao.usuario.map.EstablecimientoMapper;
import com.colsevi.dao.usuario.map.PaginaMapper;
import com.colsevi.dao.usuario.map.PaginaXRolMapper;
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
	private PaginaXRolMapper paginaXRolMapper; 
	private TelefonoMapper telefonoMapper;
	private DireccionMapper direccionMapper;
	private CorreoMapper correoMapper;
	private TipoTelefonoMapper tipoTelefonoMapper;
	private TipoProveedorMapper tipoProveedorMapper;
	private ProveedorMapper proveedorMapper;
	private CompraMapper compraMapper;
	private TipoPesoMapper tipoPesoMapper;
	private IngredienteMapper ingredienteMapper;
	private CompraXIngredienteMapper compraXIngredienteMapper;
	private TipoProductoMapper tipoProductoMapper;
	private ProductoMapper productoMapper;
	private IngredienteXProductoMapper ingredienteXProductoMapper;
	private RecetaMapper recetaMapper;
	private CatalogoMapper catalogoMapper;
	private CatalogoXProductoMapper catalogoXProductoMapper;
	private EstadoPedidoMapper estadoPedidoMapper;
	private PedidoMapper pedidoMapper;
	private DetallePedidoMapper detallePedidoMapper;
	private InventarioMapper inventarioMapper;
	private CategoriaModuloMapper categoriaModuloMapper;
	private PagoMapper pagoMapper;
	private DetallePagoMapper detallePagoMapper;
	private DeudaMapper deudaMapper;
	private CobroCategoriaMapper cobroCategoriaMapper;
	private CobroMapper cobroMapper;
	private MotivoMapper motivoMapper;
	private MovimientoInventarioMapper movimientoInventarioMapper;
	private ClasificarIngredienteMapper clasificarIngredienteMapper;
	
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
		paginaXRolMapper = (PaginaXRolMapper) beanFactoryMyBatis.getBean("paginaXRolMapper");
		telefonoMapper = (TelefonoMapper) beanFactoryMyBatis.getBean("telefonoMapper");
		direccionMapper = (DireccionMapper) beanFactoryMyBatis.getBean("direccionMapper");
		correoMapper = (CorreoMapper) beanFactoryMyBatis.getBean("correoMapper");
		tipoTelefonoMapper = (TipoTelefonoMapper) beanFactoryMyBatis.getBean("tipoTelefonoMapper");
		tipoProveedorMapper = (TipoProveedorMapper) beanFactoryMyBatis.getBean("tipoProveedorMapper");
		proveedorMapper = (ProveedorMapper) beanFactoryMyBatis.getBean("proveedorMapper");
		compraMapper = (CompraMapper) beanFactoryMyBatis.getBean("compraMapper");
		tipoPesoMapper = (TipoPesoMapper) beanFactoryMyBatis.getBean("tipoPesoMapper");
		ingredienteMapper = (IngredienteMapper) beanFactoryMyBatis.getBean("ingredienteMapper");
		compraXIngredienteMapper = (CompraXIngredienteMapper) beanFactoryMyBatis.getBean("compraXIngredienteMapper");
		tipoProductoMapper = (TipoProductoMapper) beanFactoryMyBatis.getBean("tipoProductoMapper");
		productoMapper = (ProductoMapper) beanFactoryMyBatis.getBean("productoMapper");
		ingredienteXProductoMapper = (IngredienteXProductoMapper) beanFactoryMyBatis.getBean("ingredienteXProductoMapper");
		recetaMapper = (RecetaMapper) beanFactoryMyBatis.getBean("recetaMapper");
		catalogoMapper = (CatalogoMapper) beanFactoryMyBatis.getBean("catalogoMapper");
		catalogoXProductoMapper = (CatalogoXProductoMapper) beanFactoryMyBatis.getBean("catalogoXProductoMapper");
		estadoPedidoMapper = (EstadoPedidoMapper) beanFactoryMyBatis.getBean("estadoPedidoMapper");
		pedidoMapper = (PedidoMapper) beanFactoryMyBatis.getBean("pedidoMapper");
		detallePedidoMapper = (DetallePedidoMapper) beanFactoryMyBatis.getBean("detallePedidoMapper");
		inventarioMapper = (InventarioMapper) beanFactoryMyBatis.getBean("inventarioMapper");
		categoriaModuloMapper = (CategoriaModuloMapper) beanFactoryMyBatis.getBean("categoriaModuloMapper");
		pagoMapper = (PagoMapper) beanFactoryMyBatis.getBean("pagoMapper");
		detallePagoMapper = (DetallePagoMapper) beanFactoryMyBatis.getBean("detallePagoMapper");
		deudaMapper = (DeudaMapper) beanFactoryMyBatis.getBean("deudaMapper");
		cobroCategoriaMapper = (CobroCategoriaMapper) beanFactoryMyBatis.getBean("cobroCategoriaMapper");
		cobroMapper = (CobroMapper) beanFactoryMyBatis.getBean("cobroMapper");
		motivoMapper = (MotivoMapper) beanFactoryMyBatis.getBean("motivoMapper");
		movimientoInventarioMapper = (MovimientoInventarioMapper) beanFactoryMyBatis.getBean("movimientoInventarioMapper");
		clasificarIngredienteMapper = (ClasificarIngredienteMapper) beanFactoryMyBatis.getBean("clasificarIngredienteMapper");
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

	public PaginaXRolMapper getPaginaXRolMapper() {
		return paginaXRolMapper;
	}

	public void setPaginaXRolMapper(PaginaXRolMapper paginaXRolMapper) {
		this.paginaXRolMapper = paginaXRolMapper;
	}

	public TelefonoMapper getTelefonoMapper() {
		return telefonoMapper;
	}

	public void setTelefonoMapper(TelefonoMapper telefonoMapper) {
		this.telefonoMapper = telefonoMapper;
	}

	public DireccionMapper getDireccionMapper() {
		return direccionMapper;
	}

	public void setDireccionMapper(DireccionMapper direccionMapper) {
		this.direccionMapper = direccionMapper;
	}

	public CorreoMapper getCorreoMapper() {
		return correoMapper;
	}

	public void setCorreoMapper(CorreoMapper correoMapper) {
		this.correoMapper = correoMapper;
	}

	public TipoTelefonoMapper getTipoTelefonoMapper() {
		return tipoTelefonoMapper;
	}

	public void setTipoTelefonoMapper(TipoTelefonoMapper tipoTelefonoMapper) {
		this.tipoTelefonoMapper = tipoTelefonoMapper;
	}

	public TipoProveedorMapper getTipoProveedorMapper() {
		return tipoProveedorMapper;
	}

	public void setTipoProveedorMapper(TipoProveedorMapper tipoProveedorMapper) {
		this.tipoProveedorMapper = tipoProveedorMapper;
	}

	public ProveedorMapper getProveedorMapper() {
		return proveedorMapper;
	}

	public void setProveedorMapper(ProveedorMapper proveedorMapper) {
		this.proveedorMapper = proveedorMapper;
	}

	public CompraMapper getCompraMapper() {
		return compraMapper;
	}

	public void setCompraMapper(CompraMapper compraMapper) {
		this.compraMapper = compraMapper;
	}

	public TipoPesoMapper getTipoPesoMapper() {
		return tipoPesoMapper;
	}

	public void setTipoPesoMapper(TipoPesoMapper tipoPesoMapper) {
		this.tipoPesoMapper = tipoPesoMapper;
	}

	public IngredienteMapper getIngredienteMapper() {
		return ingredienteMapper;
	}

	public void setIngredienteMapper(IngredienteMapper ingredienteMapper) {
		this.ingredienteMapper = ingredienteMapper;
	}

	public CompraXIngredienteMapper getCompraXIngredienteMapper() {
		return compraXIngredienteMapper;
	}

	public void setCompraXIngredienteMapper(CompraXIngredienteMapper compraXIngredienteMapper) {
		this.compraXIngredienteMapper = compraXIngredienteMapper;
	}

	public TipoProductoMapper getTipoProductoMapper() {
		return tipoProductoMapper;
	}

	public void setTipoProductoMapper(TipoProductoMapper tipoProductoMapper) {
		this.tipoProductoMapper = tipoProductoMapper;
	}

	public ProductoMapper getProductoMapper() {
		return productoMapper;
	}

	public void setProductoMapper(ProductoMapper productoMapper) {
		this.productoMapper = productoMapper;
	}

	public IngredienteXProductoMapper getIngredienteXProductoMapper() {
		return ingredienteXProductoMapper;
	}

	public void setIngredienteXProductoMapper(IngredienteXProductoMapper ingredienteXProductoMapper) {
		this.ingredienteXProductoMapper = ingredienteXProductoMapper;
	}

	public RecetaMapper getRecetaMapper() {
		return recetaMapper;
	}

	public void setRecetaMapper(RecetaMapper recetaMapper) {
		this.recetaMapper = recetaMapper;
	}

	public CatalogoMapper getCatalogoMapper() {
		return catalogoMapper;
	}

	public void setCatalogoMapper(CatalogoMapper catalogoMapper) {
		this.catalogoMapper = catalogoMapper;
	}

	public CatalogoXProductoMapper getCatalogoXProductoMapper() {
		return catalogoXProductoMapper;
	}

	public void setCatalogoXProductoMapper(CatalogoXProductoMapper catalogoXProductoMapper) {
		this.catalogoXProductoMapper = catalogoXProductoMapper;
	}

	public EstadoPedidoMapper getEstadoPedidoMapper() {
		return estadoPedidoMapper;
	}

	public void setEstadoPedidoMapper(EstadoPedidoMapper estadoPedidoMapper) {
		this.estadoPedidoMapper = estadoPedidoMapper;
	}

	public PedidoMapper getPedidoMapper() {
		return pedidoMapper;
	}

	public void setPedidoMapper(PedidoMapper pedidoMapper) {
		this.pedidoMapper = pedidoMapper;
	}

	public DetallePedidoMapper getDetallePedidoMapper() {
		return detallePedidoMapper;
	}

	public void setDetallePedidoMapper(DetallePedidoMapper detallePedidoMapper) {
		this.detallePedidoMapper = detallePedidoMapper;
	}

	public InventarioMapper getInventarioMapper() {
		return inventarioMapper;
	}

	public void setInventarioMapper(InventarioMapper inventarioMapper) {
		this.inventarioMapper = inventarioMapper;
	}

	public CategoriaModuloMapper getCategoriaModuloMapper() {
		return categoriaModuloMapper;
	}

	public void setCategoriaModuloMapper(CategoriaModuloMapper categoriaModuloMapper) {
		this.categoriaModuloMapper = categoriaModuloMapper;
	}

	public PagoMapper getPagoMapper() {
		return pagoMapper;
	}

	public void setPagoMapper(PagoMapper pagoMapper) {
		this.pagoMapper = pagoMapper;
	}

	public DetallePagoMapper getDetallePagoMapper() {
		return detallePagoMapper;
	}

	public void setDetallePagoMapper(DetallePagoMapper detallePagoMapper) {
		this.detallePagoMapper = detallePagoMapper;
	}

	public DeudaMapper getDeudaMapper() {
		return deudaMapper;
	}

	public void setDeudaMapper(DeudaMapper deudaMapper) {
		this.deudaMapper = deudaMapper;
	}

	public CobroCategoriaMapper getCobroCategoriaMapper() {
		return cobroCategoriaMapper;
	}

	public void setCobroCategoriaMapper(CobroCategoriaMapper cobroCategoriaMapper) {
		this.cobroCategoriaMapper = cobroCategoriaMapper;
	}

	public CobroMapper getCobroMapper() {
		return cobroMapper;
	}

	public void setCobroMapper(CobroMapper cobroMapper) {
		this.cobroMapper = cobroMapper;
	}

	public MotivoMapper getMotivoMapper() {
		return motivoMapper;
	}

	public void setMotivoMapper(MotivoMapper motivoMapper) {
		this.motivoMapper = motivoMapper;
	}

	public MovimientoInventarioMapper getMovimientoInventarioMapper() {
		return movimientoInventarioMapper;
	}

	public void setMovimientoInventarioMapper(MovimientoInventarioMapper movimientoInventarioMapper) {
		this.movimientoInventarioMapper = movimientoInventarioMapper;
	}

	public ClasificarIngredienteMapper getClasificarIngredienteMapper() {
		return clasificarIngredienteMapper;
	}

	public void setClasificarIngredienteMapper(ClasificarIngredienteMapper clasificarIngredienteMapper) {
		this.clasificarIngredienteMapper = clasificarIngredienteMapper;
	}
}