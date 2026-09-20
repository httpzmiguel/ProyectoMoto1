package gestion;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ReporteDiarioTest {

    @Test
    public void testReporteIniciaEnCero() {
        ReporteDiario reporte = new ReporteDiario();
        // Para validar internamente, el reporte imprime en consola, pero
        // probamos que no arroje excepciones al iniciar y operar
        assertNotNull(reporte);
    }

    @Test
    public void testAgregarPagoEfectivo() {
        ReporteDiario reporte = new ReporteDiario();
        assertDoesNotThrow(() -> reporte.agregarPago(MedioPago.EFECTIVO, 4000.0));
    }

    @Test
    public void testAgregarPagoNequi() {
        ReporteDiario reporte = new ReporteDiario();
        assertDoesNotThrow(() -> reporte.agregarPago(MedioPago.NEQUI, 2500.0));
    }

    @Test
    public void testImprimirReporteNoFalla() {
        ReporteDiario reporte = new ReporteDiario();
        reporte.agregarPago(MedioPago.EFECTIVO, 1000);
        assertDoesNotThrow(() -> reporte.imprimirReporte());
    }
}
