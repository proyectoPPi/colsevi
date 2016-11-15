package com.colsevi.controllers.general;

public enum MotivoE {
	ASIGNACION(1),
	DEVOLUCION(2),
	PRESTAMO(3),
	PAGO(4),
	GASTO(5);
	
 private final int motivoE;

    private MotivoE(int motivoE) {
        this.motivoE = motivoE;
    }
    
    public int getMotivoE() {
        return this.motivoE;
    }
}
