package com.feria.modelos;

import com.feria.modelos.descuentos.Descuento;
import com.feria.modelos.descuentos.DescuentoPorCantidad;
import com.feria.modelos.descuentos.DescuentoPorMonto;
import java.util.ArrayList;
import java.util.List;

public class Venta {

    private String idVenta;
    private Emprendedor emprendedor;
    private Producto producto;
    private int cantidad;
    private double precioUnitario;
    private String fecha;
    private boolean pagoRealizado;
    private List<Descuento> estrategiasDescuento;

    public Venta(String idVenta, Emprendedor emp, Producto prodNombre, int cant, double precioUnit, String fecha) {
        this.idVenta = idVenta;
        this.emprendedor = emp;
        this.producto = prodNombre;
        this.cantidad = cant;
        this.precioUnitario = precioUnit;
        this.fecha = fecha;
        this.pagoRealizado = false; 
        // Cargamos las estrategias que aplican a la feria de forma limpia
        this.estrategiasDescuento = new ArrayList<>();
        this.estrategiasDescuento.add(new DescuentoPorCantidad());
        this.estrategiasDescuento.add(new DescuentoPorMonto());
    }
    
    public double calcularSubtotal() {
        return cantidad * precioUnitario;
    }
    
    public double calcularDescuentoTotal() {
        double subtotal = calcularSubtotal();
        double descuentoAcumulado = 0;

        // Recorremos polimórficamente cada descuento sin importar cuál sea su regla interna
        for (Descuento d : estrategiasDescuento) {
            descuentoAcumulado += d.calcularMontoDescuento(subtotal, cantidad);
        }
        return descuentoAcumulado;
    }

    public double calcularTotal() {
        return calcularSubtotal() - calcularDescuentoTotal();
    }


    public boolean isPagoRealizado() {
        return pagoRealizado;
    }
    
    public void marcarComoPagada() {
        this.pagoRealizado = true; 
    }

    public String generarRecibo() {
        String recibo = "=== RECIBO DE VENTA ===\n";
        recibo += "Venta ID: " + idVenta + "\n";
        recibo += "Fecha: " + fecha + "\n";
        recibo += "Producto: " + producto + "\n";
        recibo += "Cantidad: " + cantidad + "\n";
        recibo += "Precio unitario: $" + precioUnitario + "\n";
        recibo += "Total con descuentos: $" + calcularTotal() + "\n";
        recibo += "Pago: " + (pagoRealizado ? "Realizado" : "Pendiente") + "\n";
        return recibo;
    }
}