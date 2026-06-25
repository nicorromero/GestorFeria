/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;
/**
 *
 * @author nicor
 */
import com.feria.modelos.*;
import com.feria.servicios.GestorFeria;
import com.feria.servicios.IReportes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GestorFeriaTest {

    @Mock
    private IReportes reportesMock; // Dependencia falsa simulada

    @InjectMocks
    private GestorFeria gestorFeria; // El servicio real bajo prueba

    @Test
    void deberiaCobrarVentasPendientesEInvocarelReporte() {
        // 1. ARRANGE (Preparar el escenario)
        Emprendedor emp = new Emprendedor("Ana", "E001", "123", "ana@mail.com", "nivel 1");
        Producto prod = new Producto("Empanada", 100.0, 10, Categoria.COMIDA, "E001");
        
        // Creamos una venta que por defecto nace con pagoRealizado = false
        Venta ventaPendiente = new Venta("V001", emp, prod, 5, 100.0, "2026-06-25");
        
        // 🚨 Agregamos la venta a la lista de ventas del gestor
        // (Asegúrate de que tu GestorFeria tenga un getter para su lista de ventas, ej: getVentas())
        gestorFeria.ventas.add(ventaPendiente);

        // 2. ACT (Hacer trabajar los métodos juntos)
        // Paso A: Obtenemos la lista de pendientes del gestor
        List<Venta> pendientes = gestorFeria.obtenerVentasPendientes();
        
        // Paso B: Le pasamos esa lista al método que realiza el cobro y calcula el lote
        double totalRecaudado = gestorFeria.cobrarVentas(pendientes);

        // 3. ASSERT / VERIFY (Verificar resultados)
        // Assert de Estado: El subtotal de 5 unidades * $100 es $500. El total cobrado debe ser 500.
        assertEquals(500.0, totalRecaudado, "El cobro del lote pendiente debería sumar $500.0");
        
        // Assert de Estado: Verificamos que la venta individual cambió internamente a pagada
        assertTrue(ventaPendiente.isPagoRealizado(), "La venta debería estar marcada como pagada");

        // Verify de Comportamiento (Mock): Si tu método cobrarVentas invoca al sistema de reportes
        // al terminar el lote, verificamos que el mock haya sido llamado exactamente 1 vez
        verify(reportesMock, times(1)).imprimirResumenEjecutivo(any());
    }
}
