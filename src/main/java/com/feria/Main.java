package com.feria;

import com.feria.modelos.*;
import com.feria.servicios.*;
import com.feria.utils.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        GestorFeria gestor = new GestorFeria();
        Reportes reportes = new Reportes();
        try {
            gestor.registrarEmprendedorConProductos(
                "Ana", "E001", "3423456789", "ana@gmail.com", "comida",
                Arrays.asList("Empanadas", "Tortas", "Alfajores"),
                Arrays.asList(500.0, 1500.0, 300.0),
                Arrays.asList(50, 10, 100)
            );
            gestor.registrarVenta("V001", e, "Empanadas", 10, 500.0, "2026-05-12");

        }catch(IllegalArgumentException excepcionCapturada) {
            System.out.println("Ocurrió un problema: " + excepcionCapturada.getMessage());
        }
        
        try{
            Emprendedor emp2 = new Emprendedor("Carlos", "E002", "3423987654", "carlos@hotmail.com", "artesania");
            gestor.emprendedores.add(emp2);
            Producto p1 = new Producto("Collar", 2000.0, 5, "artesania", emp2);
            emp2.agregarProducto(p1);
            gestor.productos.add(p1);
            Producto p2 = new Producto("Pulsera", 800.0, 20, "artesania", emp2);
            emp2.agregarProducto(p2);
            gestor.productos.add(p2);
            gestor.registrarVenta("V002", emp2, "Collar", 1, 2000.0, "2026-05-12");
        }catch(IllegalArgumentException excepcionCapturada){
            System.out.println("Ocurrió un problema: " + excepcionCapturada.getMessage());
        }
        
     
        System.out.println(reportes.generarReportePorCategoria(gestor, "comida"));

        gestor.procesarVentasPendientesYCobrar();

        reportes.imprimirResumenEjecutivo(gestor);
        
        //corregir de aca para abajo
         System.out.println("Emprendedor Ana válido? " + Validadores.validarEmprendedorCompleto(gestor.emprendedores.get(0)));
        System.out.println(gestor.emprendedores.get(0).mostrarInfoYValidar());
    }
}