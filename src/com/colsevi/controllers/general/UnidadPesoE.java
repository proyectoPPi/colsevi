package com.colsevi.controllers.general;

public enum UnidadPesoE {
	KILO(1),
	LIBRA(2),
	GRAMO(3),
	LITRO(4),
	MILILITRO(5);
	
 private final int unidadM;

    private UnidadPesoE(int unidadM) {
        this.unidadM = unidadM;
    }
    
    public int getUnidadM() {
        return this.unidadM;
    }
    
}
