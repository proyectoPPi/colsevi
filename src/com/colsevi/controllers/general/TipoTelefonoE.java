package com.colsevi.controllers.general;

public enum TipoTelefonoE {
	CELULAR(1),
	FIJO(2),
	CONTACTO(3);
	
	 private final int tipoTelE;

    private TipoTelefonoE(int tipoTelE) {
        this.tipoTelE = tipoTelE;
    }
    
    public int getTipoTelefonoE() {
        return this.tipoTelE;
    }
	
}
