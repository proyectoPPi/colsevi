package com.colsevi.controllers.usuario;

public enum RolE {
	ADMIN(1),
	EMPLEADO(2),
	CLIENTE(3),
	INVITADO(4);
	
 private final int rolE;

    private RolE(int rolE) {
        this.rolE = rolE;
    }
    
    public int getRolE() {
        return this.rolE;
    }
}
