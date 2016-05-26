package com.colsevi.controllers.pedido;

public enum PedidoE {
	CANCELADO(1),
	BORRADOR(2),
	NUEVO(3),
	PREPARACION(4),
	ENTREGADO(5);
	
 private final int pedidoE;

    private PedidoE(int pedidoE) {
        this.pedidoE = pedidoE;
    }
    
    public int getPedidoE() {
        return this.pedidoE;
    }
}