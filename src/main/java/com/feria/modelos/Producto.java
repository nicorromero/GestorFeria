package com.feria.modelos;


public class Producto {


    public String nombre;
    public double precio;
    public int stock;
    public Categoria categoriaProducto;  // duplicado con la categoría del emprendedor
    public String idEmprendedor;

    public Producto(String nombre, double precio, int stock, Categoria categoriaProd, String idEmprendedor) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.categoriaProducto = categoriaProd;
        this.idEmprendedor = idEmprendedor;
    }
    

    public void vender(int cantidad) {
        if (this.stock < cantidad) {
            throw new IllegalStateException("Error: Stock insuficiente. Stock actual: " + this.stock);
        }
    this.stock -= cantidad; 
    }


    public double valorTotal() {
        return precio * stock;
    }


    public String mostrar() {
        return nombre + " - $" + precio + " (stock: " + stock + ")";
    }


    public boolean hayStockBajo() {
        if (stock < 5) {
            return true;
        }
        return false;
    }


    public boolean isStockBajo() {
        return stock < 5;
    }
}