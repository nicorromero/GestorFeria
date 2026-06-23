/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.feria.modelos;

import com.feria.modelos.descuentos.Descuento;
import java.util.List;

/**
 *
 * @author nicor
 */
public class VentaBuilder {
    private String idVenta;
    private Emprendedor emprendedor;
    private Producto producto;
    private int cantidad;
    private double precioUnitario;
    private String fecha;
    private boolean pagoRealizado;
    private List<Descuento> estrategiasDescuento;
    
    public VentaBuilder conIdVenta (String id){
        this.idVenta = id;
        return this;
    }
    public VentaBuilder conProducto(Producto producto) {
        this.producto = producto;
        return this; 
    }
    public VentaBuilder conEmprendedor(Emprendedor emprendedor){
        this.emprendedor = emprendedor;
        return this;
    }  
    public VentaBuilder conCantidad (int cantidad){
        this.cantidad = cantidad;
        return this;
    }   
    public VentaBuilder conPrecioUnitario(double precio){
        this.precioUnitario = precio;
        return this;
    }
    public VentaBuilder conFecha (String fecha){
        this.fecha = fecha;
        return this;
    }
    public VentaBuilder conPagoRealizado(Boolean pago){
         this.pagoRealizado = pago;
         return this;
    }
    public VentaBuilder conEstrategiaDescuento (Descuento descuento){
         this.estrategiasDescuento.add(descuento);
        return this;
    }
}
