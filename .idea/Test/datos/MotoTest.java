package datos;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class MotoTest {

    @Test
    public void testCreacionMoto() {
        Moto moto = new Moto("ABC-12D", "Yamaha", "100100100");
        assertNotNull(moto, "La moto no debería ser nula al instanciarse");
    }

    @Test
    public void testGettersMoto() {
        Moto moto = new Moto("XYZ-999", "Honda", "12345678");
        assertEquals("XYZ-999", moto.obtenerPlaca());
        assertEquals("Honda", moto.obtenerMarca());
        assertEquals("12345678", moto.obtenerIdDuenio());
    }

    @Test
    public void testSetPlaca() {
        Moto moto = new Moto("AAA-111", "Suzuki", "111");
        moto.modificarPlaca("BBB-222");
        assertEquals("BBB-222", moto.obtenerPlaca());
    }

    @Test
    public void testFechaIngresoSeAsignaAutomaticamente() {
        Moto moto = new Moto("QWE-456", "KTM", "999");
        assertNotNull(moto.obtenerFechaIngreso(), "La fecha de ingreso debe generarse automáticamente");
    }

    @Test
    public void testModificarFechaIngresoPrueba() {
        Moto moto = new Moto("ASD-123", "Pulsar", "444");
        LocalDateTime fechaAntigua = LocalDateTime.now().minusHours(2);
        moto.modificarFechaIngresoPrueba(fechaAntigua);
        assertEquals(fechaAntigua, moto.obtenerFechaIngreso());
    }
}
