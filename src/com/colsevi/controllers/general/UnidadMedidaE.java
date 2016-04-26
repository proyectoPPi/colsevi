package com.colsevi.controllers.general;

public enum UnidadMedidaE {
	KILO(1),
	LIBRA(2),
	GRAMO(3),
	LITRO(4);
	
 private final int unidadM;

    private UnidadMedidaE(int unidadM) {
        this.unidadM = unidadM;
    }
    
    public int getUnidadM() {
        return this.unidadM;
    }
    
}
