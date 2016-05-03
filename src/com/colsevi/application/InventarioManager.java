package com.colsevi.application;

import java.util.Date;

import com.colsevi.controllers.general.UnidadMedidaE;
import com.colsevi.dao.inventario.model.InventarioXMateria;
import com.colsevi.dao.inventario.model.MateriaPrima;
import com.colsevi.dao.inventario.model.MateriaPrimaExample;
import com.colsevi.dao.inventario.model.MovimientoMateria;

public class InventarioManager {

	public static void RegistrarMovimientoMateria(InventarioXMateria invMat, Integer establecimiento, Date Fecha, Integer motivo){
		ColseviDao.getInstance().getInventarioXMateriaMapper().insertSelective(invMat);
		
		MovimientoMateria mm = new MovimientoMateria();
		mm.setLote(invMat.getLote());
		mm.setId_unidad_peso(invMat.getId_unidad_peso());
		mm.setId_establecimiento(establecimiento);
		mm.setCantidad(invMat.getCantidad());
		mm.setFecha_movimiento(Fecha);
		mm.setId_motivo(motivo);
		
		ColseviDao.getInstance().getMovimientoMateriaMapper().insertSelective(mm);
	}
	
	public static void ActualizarMateriaPrima(MateriaPrima beanMP){
		MateriaPrimaExample MPE = new MateriaPrimaExample();
		MPE.createCriteria().andLoteEqualTo(beanMP.getLote());

		ColseviDao.getInstance().getMateriaPrimaMapper().updateByExampleSelective(beanMP, MPE);
	}
	
	public static Object[] ConversionPMayorMenor(Integer tipoP, Integer tipoH, Double cantidad){
		
		Object[] obj = new Object[2];
		
		if(tipoP.equals(UnidadMedidaE.KILO.getUnidadM())){
			if(tipoH.equals(UnidadMedidaE.LIBRA.getUnidadM())){
				obj[0] = cantidad *  2.20462262;
				obj[1] = UnidadMedidaE.LIBRA.getUnidadM();
			}else if(tipoH.equals(UnidadMedidaE.GRAMO.getUnidadM())){
				obj[0] = (double) (cantidad *  1000);
				obj[1] = UnidadMedidaE.GRAMO.getUnidadM();
			}
		}else if(tipoP.equals(UnidadMedidaE.LIBRA.getUnidadM())){
			if(tipoH.equals(UnidadMedidaE.KILO.getUnidadM())){
				obj[0] = cantidad * 0.45359237;
				obj[1] = UnidadMedidaE.KILO.getUnidadM();
			}else if(tipoH.equals(UnidadMedidaE.GRAMO.getUnidadM())){
				obj[0] = cantidad * 453.59237;
				obj[1] = UnidadMedidaE.GRAMO.getUnidadM();
			}
		}else if(tipoP.equals(UnidadMedidaE.GRAMO.getUnidadM())){
			if(tipoH.equals(UnidadMedidaE.LIBRA.getUnidadM())){
				obj[0] = cantidad * 0.00220462262;
				obj[1] = UnidadMedidaE.LIBRA.getUnidadM();
			}else if(tipoH.equals(UnidadMedidaE.KILO.getUnidadM())){
				obj[0] = (double) (cantidad / 1000);
				obj[1] = UnidadMedidaE.KILO.getUnidadM();
			}
		}
		
		return obj;
		
	}
	
	public static Object[] conversionMayor(Integer tipoP, Double cantidad){
		Object[] obj = new Object[2];
		
		if(tipoP.equals(UnidadMedidaE.KILO.getUnidadM())){
			obj[0] = cantidad * 2.20462262;
			obj[1] = UnidadMedidaE.LIBRA.getUnidadM();
			if(cantidad < 1){
				obj[0] = cantidad * 1000;
				obj[1] = UnidadMedidaE.GRAMO.getUnidadM();
			}
		}else if(tipoP.equals(UnidadMedidaE.LIBRA.getUnidadM())){
			obj[0] = cantidad * 453.59237;
			obj[1] = UnidadMedidaE.GRAMO.getUnidadM();
		}
		return obj;
	}
	
	public static Object[] conversionM(Integer tipoP, Double cantidad){
		Object[] obj = new Object[2];
		
		if(tipoP.equals(UnidadMedidaE.LIBRA.getUnidadM()) && (cantidad  * 0.45359237) > 1){
			obj[0] = cantidad * 0.45359237;
			obj[1] = UnidadMedidaE.KILO.getUnidadM();
		}else if(tipoP.equals(UnidadMedidaE.GRAMO.getUnidadM())){
			if((cantidad * 0.00220462262) > 1){
				if((cantidad  / 1000) > 1){
					obj[0] = cantidad / 1000;
					tipoP = UnidadMedidaE.KILO.getUnidadM();
				}else{
					obj[0] = cantidad * 0.00220462262;
					obj[1] = UnidadMedidaE.LIBRA.getUnidadM();
				}
			}
		}
		return obj;
	}
}
