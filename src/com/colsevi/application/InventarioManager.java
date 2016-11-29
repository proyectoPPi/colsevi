package com.colsevi.application;

import java.util.Date;

import com.colsevi.controllers.general.UnidadPesoE;
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
		}else if(tipoP.equals(UnidadPesoE.KILO.getUnidadM())){
			if(tipoH.equals(UnidadPesoE.LIBRA.getUnidadM())){
				obj[0] = cantidad *  2;
				obj[1] = UnidadPesoE.LIBRA.getUnidadM();
			}else if(tipoH.equals(UnidadPesoE.GRAMO.getUnidadM())){
				obj[0] = (double) (cantidad *  1000);
				obj[1] = UnidadPesoE.GRAMO.getUnidadM();
			}
		}else if(tipoP.equals(UnidadPesoE.LIBRA.getUnidadM())){
			if(tipoH.equals(UnidadPesoE.KILO.getUnidadM())){
				obj[0] = cantidad * 0.5;
				obj[1] = UnidadPesoE.KILO.getUnidadM();
			}else if(tipoH.equals(UnidadPesoE.GRAMO.getUnidadM())){
				obj[0] = cantidad * 500;
				obj[1] = UnidadPesoE.GRAMO.getUnidadM();
			}
		}else if(tipoP.equals(UnidadPesoE.GRAMO.getUnidadM())){
			if(tipoH.equals(UnidadPesoE.LIBRA.getUnidadM())){
				obj[0] = cantidad * 0.002;
				obj[1] = UnidadPesoE.LIBRA.getUnidadM();
			}else if(tipoH.equals(UnidadPesoE.KILO.getUnidadM())){
				obj[0] = (double) (cantidad / 1000);
				obj[1] = UnidadPesoE.KILO.getUnidadM();
			}
		}else if(tipoP.equals(UnidadPesoE.LITRO.getUnidadM())){
			obj[0] = cantidad * 1000;
			obj[1] = UnidadPesoE.MILILITRO.getUnidadM();
		}else if(tipoP.equals(UnidadPesoE.MILILITRO.getUnidadM())){
			obj[0] = (double) (cantidad / 1000);
			obj[1] = UnidadPesoE.LITRO.getUnidadM();
		}
		return obj;
	}
	
	public static Object[] ConversionPMenorMayor(Integer tipoP, Integer tipoH, Double cantidad){
		
		Object[] obj = new Object[2];
		if(tipoP.equals(tipoH)){
			obj[0] = cantidad;
			obj[1] = tipoP;
		}else if(tipoP.equals(UnidadPesoE.KILO.getUnidadM())){
			if(tipoH.equals(UnidadPesoE.LIBRA.getUnidadM())){
				obj[0] = cantidad * 0.5;
			}else if(tipoH.equals(UnidadPesoE.GRAMO.getUnidadM())){
				obj[0] = cantidad / 100;
			}
		}else if(tipoP.equals(UnidadPesoE.LIBRA.getUnidadM())){
			if(tipoH.equals(UnidadPesoE.KILO.getUnidadM())){
				obj[0] = cantidad *  2;
			}else if(tipoH.equals(UnidadPesoE.GRAMO.getUnidadM())){
				obj[0] = cantidad * 0.002;
			}
		}else if(tipoP.equals(UnidadPesoE.GRAMO.getUnidadM())){
			if(tipoH.equals(UnidadPesoE.LIBRA.getUnidadM())){
				obj[0] = cantidad * 500;
			}else if(tipoH.equals(UnidadPesoE.KILO.getUnidadM())){
				obj[0] = cantidad * 1000;
			}
		}else if(tipoP.equals(UnidadPesoE.LITRO.getUnidadM())){
			obj[0] = cantidad * 1000;
			obj[1] = UnidadPesoE.MILILITRO.getUnidadM();
		}else if(tipoP.equals(UnidadPesoE.MILILITRO.getUnidadM())){
			obj[0] = (double) (cantidad / 1000);
			obj[1] = UnidadPesoE.LITRO.getUnidadM();
		}
		return obj;
	}
	
	public static Object[] conversionEncontrarMayorUnidad(Integer tipoP, Double cantidad){
		Object[] obj = new Object[2];
		
		if(tipoP.equals(UnidadPesoE.KILO.getUnidadM())){
			obj[0] = cantidad * 2;
			obj[1] = UnidadPesoE.LIBRA.getUnidadM();
			if(cantidad < 1){
				obj[0] = cantidad * 1000;
				obj[1] = UnidadPesoE.GRAMO.getUnidadM();
			}
		}else if(tipoP.equals(UnidadPesoE.LIBRA.getUnidadM())){
			obj[0] = cantidad * 500;
			obj[1] = UnidadPesoE.GRAMO.getUnidadM();
		}else if(tipoP.equals(UnidadPesoE.LITRO.getUnidadM())){
			obj[0] = cantidad * 1000;
			obj[1] = UnidadPesoE.MILILITRO.getUnidadM();
		}else if(tipoP.equals(UnidadPesoE.MILILITRO.getUnidadM())){
			obj[0] = (double) (cantidad / 1000);
			obj[1] = UnidadPesoE.LITRO.getUnidadM();
		}

		return obj;
	}
	
	public static Object[] conversionMOptima(Integer tipoP, Double cantidad){
		Object[] obj = new Object[2];
		
		if(tipoP.equals(UnidadPesoE.KILO.getUnidadM())){
			obj[0] = cantidad;
			obj[1] = UnidadPesoE.KILO.getUnidadM();
		}else if(tipoP.equals(UnidadPesoE.LIBRA.getUnidadM()) && (cantidad  * 0.45359237) > 1){
			obj[0] = cantidad * 0.5;
			obj[1] = UnidadPesoE.KILO.getUnidadM();
		}else if(tipoP.equals(UnidadPesoE.GRAMO.getUnidadM())){
			if((cantidad * 0.002) >= 1){
				if((cantidad  / 1000) > 1){
					obj[0] = cantidad / 1000;
					obj[1] = UnidadPesoE.KILO.getUnidadM();
				}else{
					obj[0] = cantidad * 0.002;
					obj[1] = UnidadPesoE.LIBRA.getUnidadM();
				}
			}else{
				obj[0] = cantidad;
				obj[1] = UnidadPesoE.GRAMO.getUnidadM();	
			}
		}else if(tipoP.equals(UnidadPesoE.MILILITRO.getUnidadM())){
			obj[0] = (double) (cantidad / 1000);
			obj[1] = UnidadPesoE.LITRO.getUnidadM();
		}

		return obj;
	}
}