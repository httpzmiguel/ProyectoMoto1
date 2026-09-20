package datos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EspacioParqueaderoTest {

    @Test
    public void testEspacioIniciaLibre() {
        EspacioParqueadero espacio = new EspacioParqueadero(1);
        assertFalse(espacio.estaOcupado(), "El espacio debe iniciar libre");
        assertNull(espacio.obtenerMotoAsignada(), "No debe haber moto asignada al inicio");
    }

    @Test
    public void testNumeroEspacio() {
        EspacioParqueadero espacio = new EspacioParqueadero(15);
        assertEquals(15, espacio.obtenerNumero());
    }

    @Test
    public void testOcuparEspacio() {
        EspacioParqueadero espacio = new EspacioParqueadero(5);
        Moto moto = new Moto("ZZZ-000", "Bajaj", "123");
        espacio.ocupar(moto);

        assertTrue(espacio.estaOcupado());
        assertEquals(moto, espacio.obtenerMotoAsignada());
    }

    @Test
    public void testLiberarEspacio() {
        EspacioParqueadero espacio = new EspacioParqueadero(2);
        Moto moto = new Moto("YYY-111", "AKT", "321");

        espacio.ocupar(moto);
        espacio.liberar();

        assertFalse(espacio.estaOcupado(), "El espacio debe quedar libre tras liberarlo");
        assertNull(espacio.obtenerMotoAsignada(), "La moto asignada debe ser nula al liberar");
    }

    @Test
    public void testReasignarEspacio() {
        EspacioParqueadero espacio = new EspacioParqueadero(10);
        espacio.ocupar(new Moto("M1", "M", "1"));
        espacio.liberar();

        Moto moto2 = new Moto("M2", "M", "2");
        espacio.ocupar(moto2);

        assertEquals("M2", espacio.obtenerMotoAsignada().obtenerPlaca());
    }
}