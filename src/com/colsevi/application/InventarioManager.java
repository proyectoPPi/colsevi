package com.colsevi.application;

import java.util.Date;

import com.colsevi.controllers.general.UnidadMedidaE;
import com.colsevi.dao.inventario.model.MateriaPrima;
import com.colsevi.dao.inventario.model.MateriaPrimaExample;
import com.colsevi.dao.inventario.model.MovimientoMateria;

public class InventarioManager {

	public static void RegistrarMovimientoMateria(Integer lote, Integer unidadPeso, Double cantidad, Integer establecimiento, Date Fecha, Integer motivo){
		
		MovimientoMateria mm = new MovimientoMateria();
		mm.setLote(lote);
		mm.setId_unidad_peso(unidadPeso);
		mm.setId_establecimiento(establecimiento);
		mm.setCantidad(cantidad);
		mm.setFecha_movimiento(Fecha);
		mm.setId_motivo(motivo);
		
		ColseviDao.getInstance().getMovimientoMateriaMapper().insertSelective(mm);
	}
	
	public static void ActualizarMateriaPrima(MateriaPrima beanMP){
		MateriaPrimaExample MPE = new MateriaPrimaExample();
		MPE.createCriteria().andLoteEqualTo(beanMP.getLote());

		ColseviDao.getInstance().getMateriaPrimaMapper().updateByExample(beanMP, MPE);
	}
	
	public static Object[] ConversionPMayorMenor(Integer tipoP, Integer tipoH, Double cantidad){
		
		Object[] obj = new Object[2];
		
		if(tipoP.equals(tipoH)){
			obj[0] = cantidad;
			obj[1] = tipoP;
		}else if(tipoP.equals(UnidadMedidaE.KILO.getUnidadM())){
			if(tipoH.equals(UnidadMedidaE.LIBRA.getUnidadM())){
				obj[0] = cantidad *  2;
				obj[1] = UnidadMedidaE.LIBRA.getUnidadM();
			}else if(tipoH.equals(UnidadMedidaE.GRAMO.getUnidadM())){
				obj[0] = (double) (cantidad *  1000);
				obj[1] = UnidadMedidaE.GRAMO.getUnidadM();
			}
		}else if(tipoP.equals(UnidadMedidaE.LIBRA.getUnidadM())){
			if(tipoH.equals(UnidadMedidaE.KILO.getUnidadM())){
				obj[0] = cantidad * 0.5;
				obj[1] = UnidadMedidaE.KILO.getUnidadM();
			}else if(tipoH.equals(UnidadMedidaE.GRAMO.getUnidadM())){
				obj[0] = cantidad * 500;
				obj[1] = UnidadMedidaE.GRAMO.getUnidadM();
			}
		}else if(tipoP.equals(UnidadMedidaE.GRAMO.getUnidadM())){
			if(tipoH.equals(UnidadMedidaE.LIBRA.getUnidadM())){
				obj[0] = cantidad * 0.002;
				obj[1] = UnidadMedidaE.LIBRA.getUnidadM();
			}else if(tipoH.equals(UnidadMedidaE.KILO.getUnidadM())){
				obj[0] = (double) (cantidad / 1000);
				obj[1] = UnidadMedidaE.KILO.getUnidadM();
			}
		}else if(tipoP.equals(UnidadMedidaE.LITRO.getUnidadM())){
			obj[0] = cantidad * 1000;
			obj[1] = UnidadMedidaE.MILILITRO.getUnidadM();
		}else if(tipoP.equals(UnidadMedidaE.MILILITRO.getUnidadM())){
			obj[0] = (double) (cantidad / 1000);
			obj[1] = UnidadMedidaE.LITRO.getUnidadM();
		}
		return obj;
	}
	
	public static Object[] ConversionPMenorMayor(Integer tipoP, Integer tipoH, Double cantidad){
		
		Object[] obj = new Object[2];
		if(tipoP.equals(tipoH)){
			obj[0] = cantidad;
			obj[1] = tipoP;
		}else if(tipoP.equals(UnidadMedidaE.KILO.getUnidadM())){
			if(tipoH.equals(UnidadMedidaE.LIBRA.getUnidadM())){
				obj[0] = cantidad * 0.5;
			}else if(tipoH.equals(UnidadMedidaE.GRAMO.getUnidadM())){
				obj[0] = cantidad / 100;
			}
		}else if(tipoP.equals(UnidadMedidaE.LIBRA.getUnidadM())){
			if(tipoH.equals(UnidadMedidaE.KILO.getUnidadM())){
				obj[0] = cantidad *  2;
			}else if(tipoH.equals(UnidadMedidaE.GRAMO.getUnidadM())){
				obj[0] = cantidad * 0.002;
			}
		}else if(tipoP.equals(UnidadMedidaE.GRAMO.getUnidadM())){
			if(tipoH.equals(UnidadMedidaE.LIBRA.getUnidadM())){
				obj[0] = cantidad * 500;
			}else if(tipoH.equals(UnidadMedidaE.KILO.getUnidadM())){
				obj[0] = cantidad * 1000;
			}
		}else if(tipoP.equals(UnidadMedidaE.LITRO.getUnidadM())){
			obj[0] = cantidad * 1000;
			obj[1] = UnidadMedidaE.MILILITRO.getUnidadM();
		}else if(tipoP.equals(UnidadMedidaE.MILILITRO.getUnidadM())){
			obj[0] = (double) (cantidad / 1000);
			obj[1] = UnidadMedidaE.LITRO.getUnidadM();
		}
		return obj;
	}
	
	public static Object[] conversionEncontrarMayorUnidad(Integer tipoP, Double cantidad){
		Object[] obj = new Object[2];
		
		if(tipoP.equals(UnidadMedidaE.KILO.getUnidadM())){
			obj[0] = cantidad * 2;
			obj[1] = UnidadMedidaE.LIBRA.getUnidadM();
			if(cantidad < 1){
				obj[0] = cantidad * 1000;
				obj[1] = UnidadMedidaE.GRAMO.getUnidadM();
			}
		}else if(tipoP.equals(UnidadMedidaE.LIBRA.getUnidadM())){
			obj[0] = cantidad * 500;
			obj[1] = UnidadMedidaE.GRAMO.getUnidadM();
		}else if(tipoP.equals(UnidadMedidaE.LITRO.getUnidadM())){
			obj[0] = cantidad * 1000;
			obj[1] = UnidadMedidaE.MILILITRO.getUnidadM();
		}else if(tipoP.equals(UnidadMedidaE.MILILITRO.getUnidadM())){
			obj[0] = (double) (cantidad / 1000);
			obj[1] = UnidadMedidaE.LITRO.getUnidadM();
		}

		return obj;
	}
	
	public static Object[] conversionMOptima(Integer tipoP, Double cantidad){
		Object[] obj = new Object[2];
		
		if(tipoP.equals(UnidadMedidaE.KILO.getUnidadM())){
			obj[0] = cantidad;
			obj[1] = UnidadMedidaE.KILO.getUnidadM();
		}else if(tipoP.equals(UnidadMedidaE.LIBRA.getUnidadM()) && (cantidad  * 0.45359237) > 1){
			obj[0] = cantidad * 0.5;
			obj[1] = UnidadMedidaE.KILO.getUnidadM();
		}else if(tipoP.equals(UnidadMedidaE.GRAMO.getUnidadM())){
			if((cantidad * 0.002) >= 1){
				if((cantidad  / 1000) > 1){
					obj[0] = cantidad / 1000;
					obj[1] = UnidadMedidaE.KILO.getUnidadM();
				}else{
					obj[0] = cantidad * 0.002;
					obj[1] = UnidadMedidaE.LIBRA.getUnidadM();
				}
			}else{
				obj[0] = cantidad;
				obj[1] = UnidadMedidaE.GRAMO.getUnidadM();	
			}
		}else if(tipoP.equals(UnidadMedidaE.MILILITRO.getUnidadM())){
			obj[0] = (double) (cantidad / 1000);
			obj[1] = UnidadMedidaE.LITRO.getUnidadM();
		}

		return obj;
	}
}