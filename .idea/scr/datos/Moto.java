package datos;

import java.time.LocalDateTime;

public class Moto {
    private String placa;
    private String marca;
    private String idDuenio;
    private LocalDateTime fechaIngreso;

    public Moto(String placa, String marca, String idDuenio) {
        this.placa = placa;
        this.marca = marca;
        this.idDuenio = idDuenio;
        this.fechaIngreso = LocalDateTime.now(); // RQ01: Registra la hora exacta de ingreso
    }

    // Getters
    public String getPlaca() { return placa; }
    public String getMarca() { return marca; }
    public String getIdDuenio() { return idDuenio; }
    public LocalDateTime getFechaIngreso() { return fechaIngreso; }
}