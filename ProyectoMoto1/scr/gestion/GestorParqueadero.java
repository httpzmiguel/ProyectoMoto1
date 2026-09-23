package gestion;

import datos.EspacioParqueadero;
import datos.Moto;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class GestorParqueadero {
    private final int CAPACIDAD_TOTAL = 23;
    private final double TARIFA_MINUTO = 40.0;

    private EspacioParqueadero[] espacios;
    private ReporteDiario reporteDia;

    public GestorParqueadero() {
        this.reporteDia = new ReporteDiario();
        this.espacios = new EspacioParqueadero[CAPACIDAD_TOTAL];

        for (int i = 0; i < CAPACIDAD_TOTAL; i++) {
            espacios[i] = new EspacioParqueadero(i + 1);
        }
    }

    public String registrarIngreso(String placa, String marca, String idDuenio) {
        EspacioParqueadero espacioLibre = buscarEspacioLibre();

        if (espacioLibre != null) {
            Moto nuevaMoto = new Moto(placa, marca, idDuenio);
            espacioLibre.ocupar(nuevaMoto);
            return "Éxito: Moto registrada en el espacio #" + espacioLibre.getNumero();
        } else {
            return "Error: El parqueadero está lleno.";
        }
    }

    public String registrarSalida(String placa, MedioPago medioPago) {
        for (EspacioParqueadero espacio : espacios) {
            if (espacio.estaOcupado() && espacio.getMotoAsignada().getPlaca().equalsIgnoreCase(placa)) {

                Moto moto = espacio.getMotoAsignada();
                LocalDateTime horaSalida = LocalDateTime.now();

                long minutosOcupados = ChronoUnit.MINUTES.between(moto.getFechaIngreso(), horaSalida);
                if(minutosOcupados == 0) minutosOcupados = 1;

                double totalAPagar = minutosOcupados * TARIFA_MINUTO;

                reporteDia.agregarPago(medioPago, totalAPagar);
                espacio.liberar();

                return String.format("Salida exitosa. Tiempo: %d min. Total a pagar: $%.2f (%s)",
                        minutosOcupados, totalAPagar, medioPago.name());
            }
        }
        return "Error: No se encontró ninguna moto con la placa " + placa;
    }

    public String generarReporteDiario() {
        return reporteDia.obtenerReporte();
    }

    private EspacioParqueadero buscarEspacioLibre() {
        for (EspacioParqueadero espacio : espacios) {
            if (!espacio.estaOcupado()) {
                return espacio;
            }
        }
        return null;
    }
}