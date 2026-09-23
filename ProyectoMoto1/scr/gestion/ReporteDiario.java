package gestion;

public class ReporteDiario {
    private double totalRecaudado;
    private double totalEfectivo;
    private double totalNequi;
    private int motosAtendidas;

    public ReporteDiario() {
        this.totalRecaudado = 0.0;
        this.totalEfectivo = 0.0;
        this.totalNequi = 0.0;
        this.motosAtendidas = 0;
    }

    public void agregarPago(MedioPago medio, double monto) {
        this.totalRecaudado += monto;
        this.motosAtendidas++;

        if (medio == MedioPago.EFECTIVO) {
            this.totalEfectivo += monto;
        } else if (medio == MedioPago.NEQUI) {
            this.totalNequi += monto;
        }
    }

    public String obtenerReporte() {
        StringBuilder sb = new StringBuilder();
        sb.append("========== REPORTE DIARIO ==========\n");
        sb.append("Motos atendidas: ").append(motosAtendidas).append("\n");
        sb.append("Total en Efectivo: $").append(totalEfectivo).append("\n");
        sb.append("Total en Nequi: $").append(totalNequi).append("\n");
        sb.append("------------------------------------\n");
        sb.append("TOTAL RECAUDADO: $").append(totalRecaudado).append("\n");
        sb.append("====================================\n");
        return sb.toString();
    }
}
