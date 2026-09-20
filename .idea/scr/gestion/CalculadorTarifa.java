package gestion;

import java.time.Duration;
import java.time.LocalDateTime;

public class CalculadorTarifa {
    private static final double TARIFA_BASE_MINUTO = 40.0; // RQ03: Tarifa base $40/minuto

    public double calcularTotal(LocalDateTime horaIngreso, LocalDateTime horaSalida) {
        long minutos = Duration.between(horaIngreso, horaSalida).toMinutes();

        // Regla de negocio: Si lleva menos de 1 minuto, se cobra mínimo 1 minuto.
        if (minutos == 0) {
            minutos = 1;
        }
        return minutos * TARIFA_BASE_MINUTO;
    }
}