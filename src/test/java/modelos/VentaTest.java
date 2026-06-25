package modelos;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import com.feria.modelos.Categoria;
import com.feria.modelos.Emprendedor;
import com.feria.modelos.Producto;
import com.feria.modelos.Venta;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 *
 * @author nicor
 */
public class VentaTest {
 
    @Test
    void deberiaCalcularTotalNormalCuandoNoHayDescuentos() {
    // Valores que no superan ni la cantidad (>10) ni el monto (>5000)
        Emprendedor empDummy = new Emprendedor("Ana", "E001", "123", "a@a.com", "comida");
        Producto prodDummy = new Producto("Empanada", 100.0, 50, Categoria.COMIDA, "E001");       
        Venta ventaNormal = new Venta("V001", empDummy, prodDummy, 5, 100.0, "2026-06-23");
    // Subtotal esperado: 5 * 100 = 500. Total esperado: 500.
        double totalObtenido = ventaNormal.calcularTotal();

    // 3. ASSERT (Verificar)
        assertEquals(500.0, totalObtenido, "El total debería ser 500.0 sin descuentos aplicados");
    }
    
    @Test
    void deberiaAplicarDescuentoPorCantidadCuandoLlevaMasDe10Unidades() {
    // Llevamos 11 unidades a $100 cada una (supera el límite de cantidad)
        Emprendedor empDummy = new Emprendedor("Ana", "E001", "123", "a@a.com", "comida");
        Producto prodDummy = new Producto("Alfajor", 100.0, 50, Categoria.COMIDA, "E001");
        Venta ventaPorCantidad = new Venta("V002", empDummy, prodDummy, 11, 100.0, "2026-06-23");
        
    // Subtotal: 1100. Descuento: 10% de 1100 = 110. Total esperado: 990.
        double totalObtenido = ventaPorCantidad.calcularTotal();
        assertEquals(990.0, totalObtenido, "Debería descontar el 10% por llevar más de 10 unidades");
    }

    @Test
    void deberiaAplicarDescuentoPorMontoCuandoElSubtotalSupera5000() {
    // Llevamos 2 unidades a $3000 cada una (supera el límite de monto, pero no el de cantidad)
        Emprendedor empDummy = new Emprendedor("Ana", "E001", "123", "a@a.com", "comida");
        Producto prodDummy = new Producto("Torta", 3000.0, 5, Categoria.COMIDA, "E001");
        Venta ventaPorMonto = new Venta("V003", empDummy, prodDummy, 2, 3000.0, "2026-06-23");
    // Subtotal: 6000. Descuento: 5% de 6000 = 300. Total esperado: 5700.
        double totalObtenido = ventaPorMonto.calcularTotal();
        assertEquals(5700.0, totalObtenido, "Debería descontar el 5% por superar los $5000 de compra");
    }

    @Test
    void deberiaLanzarExcepcionCuandoSeVendeCantidadNegativa() {
        Emprendedor empDummy = new Emprendedor("Ana", "E001", "123", "a@a.com", "comida");
        Producto prodDummy = new Producto("Torta", 3000.0, 5, Categoria.COMIDA, "E001");
        
    // 2 & 3. ACT & ASSERT juntos probando un caso inválido con assertThrows
        assertThrows(IllegalArgumentException.class, () -> {
            new Venta("V004", empDummy, prodDummy, -5, 3000.0, "2026-06-23");
        }, "Debería lanzar error al intentar crear una venta con cantidad negativa");
    }
    
    //podria crear test mas al limite con exactamente 10 productos y exactamente 5000 de monto (deberian NO aplicar descuento
}
