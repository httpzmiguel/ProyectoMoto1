package gestion;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GestorParqueaderoTest {

    @Test
    public void testInicializacionGestor() {
        GestorParqueadero gestor = new GestorParqueadero();
        assertNotNull(gestor);
    }

    @Test
    public void testRegistrarIngresoExitoso() {
        GestorParqueadero gestor = new GestorParqueadero();
        String respuesta = gestor.registrarIngreso("AAA-123", "Yamaha", "111");
        assertTrue(respuesta.contains("Éxito"), "El ingreso debe ser exitoso en un parqueadero vacío");
    }

    @Test
    public void testRegistrarIngresoParqueaderoLleno() {
        GestorParqueadero gestor = new GestorParqueadero();
        // Llenar los 23 espacios
        for (int i = 0; i < 23; i++) {
            gestor.registrarIngreso("M-" + i, "Marca", "ID-" + i);
        }
        // Intentar ingresar la moto 24
        String respuesta = gestor.registrarIngreso("FUERA", "Honda", "999");
        assertTrue(respuesta.contains("lleno"), "Debe indicar que el parqueadero está lleno");
    }

    @Test
    public void testRegistrarSalidaExitosa() {
        GestorParqueadero gestor = new GestorParqueadero();
        gestor.registrarIngreso("SALIDA-1", "BMW", "000");
        String respuesta = gestor.registrarSalida("SALIDA-1", MedioPago.EFECTIVO);
        assertTrue(respuesta.contains("Salida exitosa"), "Debe permitir la salida de una moto existente");
    }

    @Test
    public void testRegistrarSalidaMotoNoEncontrada() {
        GestorParqueadero gestor = new GestorParqueadero();
        String respuesta = gestor.registrarSalida("FANTASMA", MedioPago.NEQUI);
        assertTrue(respuesta.contains("Error: No se encontró"), "Debe fallar al buscar una placa inexistente");
    }

    @Test
    public void testCobroMinimoUnMinuto() {
        GestorParqueadero gestor = new GestorParqueadero();
        gestor.registrarIngreso("RAPIDA", "TVS", "123");
        // Salida inmediata, debe cobrar 1 minuto ($40.0)
        String respuesta = gestor.registrarSalida("RAPIDA", MedioPago.NEQUI);
        assertTrue(respuesta.contains("$40,0") || respuesta.contains("$40.0"),
                "El cobro mínimo debe ser de 1 minuto ($40)");
    }
}
