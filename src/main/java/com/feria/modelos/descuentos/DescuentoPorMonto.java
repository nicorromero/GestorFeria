/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.feria.modelos.descuentos;

/**
 *
 * @author nicor
 */
public class DescuentoPorMonto implements Descuento {
    @Override
    public double calcularMontoDescuento(double subtotal, int cantidad) {
        // Nota: Si querés que se aplique sobre el subtotal puro usás 'subtotal'.
        if (subtotal > 5000) {
            return subtotal * 0.05; 
        }
        return 0;
    }   
}
