package productos;

import java.util.Date;

class Venta {
    private Date fecha;
    private int codigoProducto;
    private int cantidad;
    private double subtotal;
    private final double IVA = 0.19;
    private double total;

    public Venta(Date fecha, int codigoProducto, int cantidad, double subtotal) {
        this.fecha = fecha;
        this.codigoProducto = codigoProducto;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.total = subtotal * (1 + IVA);
    }

    public double getTotal() {
        return total;
    }

    public double getSubtotal() {
        return subtotal;
    }

    
}
