package com.colsevi.application;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.colsevi.dao.catalogo.map.CatalogoMapper;
import com.colsevi.dao.catalogo.map.CatalogoXProductoMapper;
import com.colsevi.dao.deuda.map.DeudaPedidoMapper;
import com.colsevi.dao.deuda.map.DeudaProveedorMapper;
import com.colsevi.dao.general.map.CorreoMapper;
import com.colsevi.dao.general.map.DireccionMapper;
import com.colsevi.dao.general.map.EstablecimientoMapper;
import com.colsevi.dao.general.map.MotivoMapper;
import com.colsevi.dao.general.map.TelefonoMapper;
import com.colsevi.dao.general.map.TipoTelefonoMapper;
import com.colsevi.dao.general.map.UnidadPesoMapper;
import com.colsevi.dao.producto.map.ClasificarIngredienteMapper;
import com.colsevi.dao.producto.map.DificultadRecetaMapper;
import com.colsevi.dao.inventario.map.InventarioMapper;
import com.colsevi.dao.inventario.map.MovimientoCompraMapper;
import com.colsevi.dao.inventario.map.MovimientoInventarioMapper;
import com.colsevi.dao.inventario.map.InventarioXMateriaMapper;
import com.colsevi.dao.pago.map.PagoPedidoMapper;
import com.colsevi.dao.pago.map.PagoProveedorMapper;
import com.colsevi.dao.pedido.map.CategoriaCobroMapper;
import com.colsevi.dao.pedido.map.CobroMapper;
import com.colsevi.dao.pedido.map.DetallePedidoMapper;
import com.colsevi.dao.pedido.map.EstadoPedidoMapper;
import com.colsevi.dao.pedido.map.PedidoMapper;
import com.colsevi.dao.pedido.model.CategoriaCobro;
import com.colsevi.dao.producto.map.IngredienteMapper;
import com.colsevi.dao.producto.map.IngredienteXProductoMapper;
import com.colsevi.dao.producto.map.PreparacionRecetaMapper;
import com.colsevi.dao.producto.map.ProductoMapper;
import com.colsevi.dao.producto.map.RecetaMapper;
import com.colsevi.dao.producto.map.TipoProductoMapper;
import com.colsevi.dao.proveedor.map.CompraMapper;
import com.colsevi.dao.proveedor.map.CompraXIngredienteMapper;
import com.colsevi.dao.proveedor.map.ProveedorMapper;
import com.colsevi.dao.proveedor.map.TipoProveedorMapper;
import com.colsevi.dao.usuario.map.PaginaMapper;
import com.colsevi.dao.usuario.map.PaginaXRolMapper;
import com.colsevi.dao.usuario.map.PersonaMapper;
import com.colsevi.dao.usuario.map.RolMapper;
import com.colsevi.dao.usuario.map.TipoDocumentoMapper;
import com.colsevi.dao.usuario.map.UsuarioMapper;
import com.colsevi.dao.usuario.map.UsuarioXRolMapper;

public class ColseviDao {
	
	private EstablecimientoMapper establecimientoMapper;
	private TipoTelefonoMapper tipoTelefonoMapper;
	private TelefonoMapper telefonoMapper;
	private DireccionMapper direccionMapper;
	private CorreoMapper correoMapper;
	private UnidadPesoMapper unidadPesoMapper;
	private MotivoMapper motivoMapper;
	private EstadoPedidoMapper estadoPedidoMapper;
	private PedidoMapper pedidoMapper;
	private DetallePedidoMapper detallePedidoMapper;
	private CategoriaCobroMapper categoriaCobroMapper;
	private CobroMapper cobroMapper;
	private TipoProveedorMapper tipoProveedorMapper;
	private ProveedorMapper proveedorMapper;
	private CompraMapper compraMapper;
	private CompraXIngredienteMapper compraXIngredienteMapper;
	private PagoPedidoMapper pagoPedidoMapper;
	private PagoProveedorMapper pagoProveedorMapper;
	private DeudaPedidoMapper deudaPedidoMapper;
	private DeudaProveedorMapper deudaProveedorMapper;
	private InventarioMapper inventarioMapper;
	private MovimientoInventarioMapper movimientoInventarioMapper;
	private MovimientoCompraMapper movimientoCompraMapper;
	private ClasificarIngredienteMapper clasificarIngredienteMapper; 
	private IngredienteMapper ingredienteMapper;
	private TipoProductoMapper tipoProductoMapper;
	private ProductoMapper productoMapper;
	private IngredienteXProductoMapper ingredienteXProductoMapper;
	private RecetaMapper recetaMapper;
	private DificultadRecetaMapper dificultadRecetaMapper;
	private PreparacionRecetaMapper preparacionRecetaMapper;
	private CatalogoMapper catalogoMapper;
	private CatalogoXProductoMapper catalogoXProductoMapper;
	private PersonaMapper personaMapper;
	private UsuarioMapper usuarioMapper;
	private RolMapper rolMapper;
	private UsuarioXRolMapper usuarioXRolMapper;
	private PaginaMapper paginaMapper;
	private PaginaXRolMapper paginaXRolMapper;
	private TipoDocumentoMapper tipoDocumentoMapper;
	private InventarioXMateriaMapper inventarioXMateriaMapper;

	
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
		
		establecimientoMapper = (EstablecimientoMapper) beanFactoryMyBatis.getBean("establecimientoMapper");
		tipoTelefonoMapper = (TipoTelefonoMapper) beanFactoryMyBatis.getBean("tipoTelefonoMapper");
		telefonoMapper = (TelefonoMapper) beanFactoryMyBatis.getBean("telefonoMapper");
		direccionMapper = (DireccionMapper) beanFactoryMyBatis.getBean("direccionMapper");
		correoMapper = (CorreoMapper) beanFactoryMyBatis.getBean("correoMapper");
		unidadPesoMapper = (UnidadPesoMapper) beanFactoryMyBatis.getBean("unidadPesoMapper");
		motivoMapper = (MotivoMapper) beanFactoryMyBatis.getBean("motivoMapper");
		estadoPedidoMapper = (EstadoPedidoMapper) beanFactoryMyBatis.getBean("estadoPedidoMapper");
		pedidoMapper = (PedidoMapper) beanFactoryMyBatis.getBean("pedidoMapper");
		detallePedidoMapper = (DetallePedidoMapper) beanFactoryMyBatis.getBean("detallePedidoMapper");
		categoriaCobroMapper = (CategoriaCobroMapper) beanFactoryMyBatis.getBean("categoriaCobroMapper");
		cobroMapper = (CobroMapper) beanFactoryMyBatis.getBean("cobroMapper");
		tipoProveedorMapper = (TipoProveedorMapper) beanFactoryMyBatis.getBean("tipoProveedorMapper");
		proveedorMapper = (ProveedorMapper) beanFactoryMyBatis.getBean("proveedorMapper");
		compraMapper = (CompraMapper) beanFactoryMyBatis.getBean("compraMapper");
		compraXIngredienteMapper = (CompraXIngredienteMapper) beanFactoryMyBatis.getBean("compraXIngredienteMapper");
		pagoPedidoMapper = (PagoPedidoMapper) beanFactoryMyBatis.getBean("pagoPedidoMapper");
		pagoProveedorMapper = (PagoProveedorMapper) beanFactoryMyBatis.getBean("pagoProveedorMapper");
		deudaPedidoMapper = (DeudaPedidoMapper) beanFactoryMyBatis.getBean("deudaPedidoMapper");
		deudaProveedorMapper = (DeudaProveedorMapper) beanFactoryMyBatis.getBean("deudaProveedorMapper");
		inventarioMapper = (InventarioMapper) beanFactoryMyBatis.getBean("inventarioMapper");
		movimientoInventarioMapper = (MovimientoInventarioMapper) beanFactoryMyBatis.getBean("movimientoInventarioMapper");
		movimientoCompraMapper = (MovimientoCompraMapper) beanFactoryMyBatis.getBean("movimientoCompraMapper");
		clasificarIngredienteMapper = (ClasificarIngredienteMapper) beanFactoryMyBatis.getBean("clasificarIngredienteMapper");
		ingredienteMapper = (IngredienteMapper) beanFactoryMyBatis.getBean("ingredienteMapper");
		tipoProductoMapper = (TipoProductoMapper) beanFactoryMyBatis.getBean("tipoProductoMapper");
		productoMapper = (ProductoMapper) beanFactoryMyBatis.getBean("productoMapper");
		ingredienteXProductoMapper = (IngredienteXProductoMapper) beanFactoryMyBatis.getBean("ingredienteXProductoMapper");
		recetaMapper = (RecetaMapper) beanFactoryMyBatis.getBean("recetaMapper");
		preparacionRecetaMapper = (PreparacionRecetaMapper) beanFactoryMyBatis.getBean("preparacionRecetaMapper");
		dificultadRecetaMapper = (DificultadRecetaMapper) beanFactoryMyBatis.getBean("dificultadRecetaMapper");
		catalogoMapper = (CatalogoMapper) beanFactoryMyBatis.getBean("catalogoMapper");
		catalogoXProductoMapper = (CatalogoXProductoMapper) beanFactoryMyBatis.getBean("catalogoXProductoMapper");
		personaMapper = (PersonaMapper) beanFactoryMyBatis.getBean("personaMapper");
		usuarioMapper = (UsuarioMapper) beanFactoryMyBatis.getBean("usuarioMapper");
		rolMapper = (RolMapper) beanFactoryMyBatis.getBean("rolMapper");
		usuarioXRolMapper = (UsuarioXRolMapper) beanFactoryMyBatis.getBean("usuarioXRolMapper");
		paginaMapper = (PaginaMapper) beanFactoryMyBatis.getBean("paginaMapper");
		paginaXRolMapper = (PaginaXRolMapper) beanFactoryMyBatis.getBean("paginaXRolMapper");
		tipoDocumentoMapper = (TipoDocumentoMapper) beanFactoryMyBatis.getBean("tipoDocumentoMapper");
		inventarioXMateriaMapper = (InventarioXMateriaMapper) beanFactoryMyBatis.getBean("inventarioXMateriaMapper");
		
	}

	public EstablecimientoMapper getEstablecimientoMapper() {
		return establecimientoMapper;
	}

	public void setEstablecimientoMapper(EstablecimientoMapper establecimientoMapper) {
		this.establecimientoMapper = establecimientoMapper;
	}

	public TipoTelefonoMapper getTipoTelefonoMapper() {
		return tipoTelefonoMapper;
	}

	public void setTipoTelefonoMapper(TipoTelefonoMapper tipoTelefonoMapper) {
		this.tipoTelefonoMapper = tipoTelefonoMapper;
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

	public UnidadPesoMapper getUnidadPesoMapper() {
		return unidadPesoMapper;
	}

	public void setUnidadPesoMapper(UnidadPesoMapper unidadPesoMapper) {
		this.unidadPesoMapper = unidadPesoMapper;
	}

	public MotivoMapper getMotivoMapper() {
		return motivoMapper;
	}

	public void setMotivoMapper(MotivoMapper motivoMapper) {
		this.motivoMapper = motivoMapper;
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

	public CategoriaCobroMapper getCategoriaCobroMapper() {
		return categoriaCobroMapper;
	}

	public void setCategoriaCobroMapper(CategoriaCobroMapper categoriaCobroMapper) {
		this.categoriaCobroMapper = categoriaCobroMapper;
	}

	public CobroMapper getCobroMapper() {
		return cobroMapper;
	}

	public void setCobroMapper(CobroMapper cobroMapper) {
		this.cobroMapper = cobroMapper;
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

	public CompraXIngredienteMapper getCompraXIngredienteMapper() {
		return compraXIngredienteMapper;
	}

	public void setCompraXIngredienteMapper(CompraXIngredienteMapper compraXIngredienteMapper) {
		this.compraXIngredienteMapper = compraXIngredienteMapper;
	}

	public PagoPedidoMapper getPagoPedidoMapper() {
		return pagoPedidoMapper;
	}

	public void setPagoPedidoMapper(PagoPedidoMapper pagoPedidoMapper) {
		this.pagoPedidoMapper = pagoPedidoMapper;
	}

	public PagoProveedorMapper getPagoProveedorMapper() {
		return pagoProveedorMapper;
	}

	public void setPagoProveedorMapper(PagoProveedorMapper pagoProveedorMapper) {
		this.pagoProveedorMapper = pagoProveedorMapper;
	}

	public DeudaPedidoMapper getDeudaPedidoMapper() {
		return deudaPedidoMapper;
	}

	public void setDeudaPedidoMapper(DeudaPedidoMapper deudaPedidoMapper) {
		this.deudaPedidoMapper = deudaPedidoMapper;
	}

	public DeudaProveedorMapper getDeudaProveedorMapper() {
		return deudaProveedorMapper;
	}

	public void setDeudaProveedorMapper(DeudaProveedorMapper deudaProveedorMapper) {
		this.deudaProveedorMapper = deudaProveedorMapper;
	}

	public InventarioMapper getInventarioMapper() {
		return inventarioMapper;
	}

	public void setInventarioMapper(InventarioMapper inventarioMapper) {
		this.inventarioMapper = inventarioMapper;
	}

	public MovimientoInventarioMapper getMovimientoInventarioMapper() {
		return movimientoInventarioMapper;
	}

	public void setMovimientoInventarioMapper(MovimientoInventarioMapper movimientoInventarioMapper) {
		this.movimientoInventarioMapper = movimientoInventarioMapper;
	}

	public MovimientoCompraMapper getMovimientoCompraMapper() {
		return movimientoCompraMapper;
	}

	public void setMovimientoCompraMapper(MovimientoCompraMapper movimientoCompraMapper) {
		this.movimientoCompraMapper = movimientoCompraMapper;
	}

	public ClasificarIngredienteMapper getClasificarIngredienteMapper() {
		return clasificarIngredienteMapper;
	}

	public void setClasificarIngredienteMapper(ClasificarIngredienteMapper clasificarIngredienteMapper) {
		this.clasificarIngredienteMapper = clasificarIngredienteMapper;
	}

	public IngredienteMapper getIngredienteMapper() {
		return ingredienteMapper;
	}

	public void setIngredienteMapper(IngredienteMapper ingredienteMapper) {
		this.ingredienteMapper = ingredienteMapper;
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

	public PreparacionRecetaMapper getPreparacionRecetaMapper() {
		return preparacionRecetaMapper;
	}

	public void setPreparacionRecetaMapper(PreparacionRecetaMapper preparacionRecetaMapper) {
		this.preparacionRecetaMapper = preparacionRecetaMapper;
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

	public PersonaMapper getPersonaMapper() {
		return personaMapper;
	}

	public void setPersonaMapper(PersonaMapper personaMapper) {
		this.personaMapper = personaMapper;
	}

	public UsuarioMapper getUsuarioMapper() {
		return usuarioMapper;
	}

	public void setUsuarioMapper(UsuarioMapper usuarioMapper) {
		this.usuarioMapper = usuarioMapper;
	}

	public RolMapper getRolMapper() {
		return rolMapper;
	}

	public void setRolMapper(RolMapper rolMapper) {
		this.rolMapper = rolMapper;
	}

	public UsuarioXRolMapper getUsuarioXRolMapper() {
		return usuarioXRolMapper;
	}

	public void setUsuarioXRolMapper(UsuarioXRolMapper usuarioXRolMapper) {
		this.usuarioXRolMapper = usuarioXRolMapper;
	}

	public PaginaMapper getPaginaMapper() {
		return paginaMapper;
	}

	public void setPaginaMapper(PaginaMapper paginaMapper) {
		this.paginaMapper = paginaMapper;
	}

	public PaginaXRolMapper getPaginaXRolMapper() {
		return paginaXRolMapper;
	}

	public void setPaginaXRolMapper(PaginaXRolMapper paginaXRolMapper) {
		this.paginaXRolMapper = paginaXRolMapper;
	}

	public TipoDocumentoMapper getTipoDocumentoMapper() {
		return tipoDocumentoMapper;
	}

	public void setTipoDocumentoMapper(TipoDocumentoMapper tipoDocumentoMapper) {
		this.tipoDocumentoMapper = tipoDocumentoMapper;
	}

	public DificultadRecetaMapper getDificultadRecetaMapper() {
		return dificultadRecetaMapper;
	}

	public void setDificultadRecetaMapper(DificultadRecetaMapper dificultadRecetaMapper) {
		this.dificultadRecetaMapper = dificultadRecetaMapper;
	}

	public InventarioXMateriaMapper getInventarioXMateriaMapper() {
		return inventarioXMateriaMapper;
	}

	public void setInventarioXMateriaMapper(InventarioXMateriaMapper inventarioXMateriaMapper) {
		this.inventarioXMateriaMapper = inventarioXMateriaMapper;
	}

}