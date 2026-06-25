package com.feria.servicios;

import com.feria.modelos.*;
import java.util.ArrayList;
import java.util.List;

public class GestorFeria {

    public List<Emprendedor> emprendedores;
    public List<Producto> productos;
    public List<Venta> ventas;
    private final IReportes servicioReportes;

    public GestorFeria(IReportes servicioReportes) {
        emprendedores = new ArrayList<>();
        productos = new ArrayList<>();
        ventas = new ArrayList<>();
        this.servicioReportes = servicioReportes;
    }
    
    public Emprendedor buscarEmprendedorPorId(String idEmprendedor) {
        for (Emprendedor e : this.emprendedores) {  
            if (e.idEmprendedor.equals(idEmprendedor)) {
                return e; 
            }
        }
        throw new IllegalArgumentException("Error: No existe un emprendedor registrado con el ID '" + idEmprendedor + "'.");
    }

        public Producto buscarProductoporNombre(String nombreProducto) {
        for (Producto p : this.productos) {  
            if (p.nombre.equals(nombreProducto)) {
                return p; 
            }
        }
        throw new IllegalArgumentException("Error: No existe un producto registrado con el nombre '" + nombreProducto + "'.");
    }   

    public void registrarEmprendedor (String nombre, String id, String telefono,
                                                    String email, String categoria){
        
        if (nombre == null || nombre.length() < 2) {
            throw new IllegalArgumentException("El nombre del emprendedor no puede estar vacío o ser invalido.");
        }
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("El ID del emprendedor es obligatorio.");
        }    
        
        Emprendedor e = new Emprendedor(nombre, id, telefono, email, categoria);
        emprendedores.add(e);
    }
        
    public void registrarProductosparaEmprendedor(String idEmprendedor, String nombre, double precio, int stock, Categoria categoria){
        
        Emprendedor e = buscarEmprendedorPorId(idEmprendedor);
        Producto p = new Producto(nombre, precio, stock, categoria, e.idEmprendedor);
        e.agregarProducto(p);
        this.productos.add(p);
     
    }

    public void registrarVenta(String idVenta, String empId, String prodNombre, int cantidad, double precio, String fecha ) {

        Producto productoEncontrado = buscarProductoporNombre(prodNombre);
        productoEncontrado.vender(cantidad);
        
        Emprendedor emprendedorEncontrado = buscarEmprendedorPorId(empId);
        
        Venta venta = new Venta(idVenta, emprendedorEncontrado, productoEncontrado, cantidad, precio, fecha);
        ventas.add(venta);
    }

    public List<Emprendedor> getEmprendedoresConStockBajo() {
        List<Emprendedor> resultado = new ArrayList<>();
        for (Emprendedor e : emprendedores) {
            for (Producto p : e.productosEmprendedor) {
                if (p.hayStockBajo()) {
                    resultado.add(e);
                    break;
                }
            }
        }
        return resultado;
    }

    public List<Venta> obtenerVentasPendientes() {
        List<Venta> pendientes = new ArrayList<>();
            for (Venta v : ventas) {
                if (!v.isPagoRealizado()) { 
                pendientes.add(v);
            }
        }
        return pendientes;
    }
    
    public double cobrarVentas(List<Venta> ventasACobrar) {
        double totalRecaudadoEnLote = 0;
    
        for (Venta v : ventasACobrar) {
            // Al usar 'v.calcularTotalConDescuento()' delegamos la matemática a Venta (Tell, Don't Ask)
            totalRecaudadoEnLote += v.calcularTotal();
        
            // Delegamos el cambio de estado mediante un método explícito de la Venta
            v.marcarComoPagada(); 
        }
        // Dentro de cobrarVentas en GestorFeria.java:
        this.servicioReportes.imprimirResumenEjecutivo(this);
        return totalRecaudadoEnLote;
    }
}
