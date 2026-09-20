package gestion;

import datos.EspacioParqueadero;
import datos.Moto;
import interaccion.MedioPago;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GestorParqueadero {
    private static final int CAPACIDAD_MAXIMA = 23; // RQ02
    private List<EspacioParqueadero> espacios;
    private ReporteDiario reporteDiario;
    private CalculadorTarifa calculador;

    public GestorParqueadero() {
        this.espacios = new ArrayList<>();
        this.reporteDiario = new ReporteDiario();
        this.calculador = new CalculadorTarifa();

        // Inicializar los 23 espacios disponibles
        for (int i = 1; i <= CAPACIDAD_MAXIMA; i++) {
            espacios.add(new EspacioParqueadero(i));
        }
    }

    public void registrarIngresoMoto(String placa, String marca, String idDuenio) {
        EspacioParqueadero espacioLibre = obtenerEspacioLibre();
        if (espacioLibre != null) {
            Moto nuevaMoto = new Moto(placa, marca, idDuenio);
            espacioLibre.asignarMoto(nuevaMoto);
            System.out.println("Ingreso exitoso: Moto " + placa + " asignada al espacio Nro: " + espacioLibre.getIdEspacio());
        } else {
            System.out.println("Error: El parqueadero está lleno. No hay espacios disponibles.");
        }
    }

    public void registrarSalidaMoto(String placa, MedioPago medioPago) {
        for (EspacioParqueadero espacio : espacios) {
            if (espacio.estaOcupado() && espacio.getMotoAsignada().getPlaca().equalsIgnoreCase(placa)) {

                Moto moto = espacio.getMotoAsignada();
                LocalDateTime horaSalida = LocalDateTime.now().plusMinutes(45); // Simulamos 45 mins de estadía para la prueba

                // RQ03: Calcular monto a pagar
                double valorPagar = calculador.calcularTotal(moto.getFechaIngreso(), horaSalida);

                // RQ04: Registrar tipo de pago en el reporte
                reporteDiario.agregarPago(medioPago, valorPagar);

                // Liberar el espacio
                espacio.liberar();

                System.out.println("Salida exitosa: Moto " + placa + ". Valor pagado: $" + valorPagar + " por " + medioPago);
                return;
            }
        }
        System.out.println("Error: No se encontró ninguna moto con la placa " + placa + " en el parqueadero.");
    }

    public void generarReporteDia() {
        this.reporteDiario.imprimir();
    }

    private EspacioParqueadero obtenerEspacioLibre() {
        for (EspacioParqueadero espacio : espacios) {
            if (!espacio.estaOcupado()) {
                return espacio;
            }
        }
        return null;
    }
}
