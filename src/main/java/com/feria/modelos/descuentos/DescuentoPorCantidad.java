/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.feria.modelos.descuentos;

/**
 *
 * @author nicor
 */
public class DescuentoPorCantidad implements Descuento{
    @Override
    public double calcularMontoDescuento(double subtotal, int cantidad) {
        if (cantidad > 10) {
            return subtotal * 0.10; // Devuelve el 10% del subtotal
        }
        return 0; // Sin descuento
    }
}
