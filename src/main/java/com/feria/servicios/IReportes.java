/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.feria.servicios;
import com.feria.modelos.*;
/**
 *
 * @author nicor
 */
public interface IReportes {
    String generarReportePorCategoria(GestorFeria gestor, String categoria);

    String generarReportePorCategoriaAlternativo(GestorFeria gestor, String cat);
    
    double calcularVentasTotales(GestorFeria gestor);
    
    void imprimirResumenEjecutivo(GestorFeria gestor);
}
