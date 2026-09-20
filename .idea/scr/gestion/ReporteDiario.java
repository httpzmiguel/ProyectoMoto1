package gestion;

import interaccion.MedioPago;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ReporteDiario {
    private LocalDate fecha;
    private double totalRecaudado;
    private Map<MedioPago, Double> resumenPagos;

    public ReporteDiario() {
        this.fecha = LocalDate.now();
        this.totalRecaudado = 0.0;
        this.resumenPagos = new HashMap<>();

        // Inicializamos los acumuladores en 0 para cada medio de pago
        for (MedioPago medio : MedioPago.values()) {
            resumenPagos.put(medio, 0.0);
        }
    }

    public void agregarPago(MedioPago medio, double monto) {
        this.totalRecaudado += monto;
        double subtotalAnterior = this.resumenPagos.get(medio);
        this.resumenPagos.put(medio, subtotalAnterior + monto);
    }

    public void imprimir() {
        System.out.println("\n=== REPORTE DIARIO (" + this.fecha + ") ===");
        System.out.println("Total Recaudado: $" + this.totalRecaudado);
        System.out.println("- Pagos en Efectivo: $" + this.resumenPagos.get(MedioPago.EFECTIVO));
        System.out.println("- Pagos en Nequi: $" + this.resumenPagos.get(MedioPago.NEQUI));
        System.out.println("=========================================\n");
    }
}
