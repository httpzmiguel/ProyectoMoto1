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
        this.fechaIngreso = LocalDateTime.now();
    }

    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getIdDuenio() { return idDuenio; }
    public void setIdDuenio(String idDuenio) { this.idDuenio = idDuenio; }

    public LocalDateTime getFechaIngreso() { return fechaIngreso; }

    public void setFechaIngresoPrueba(LocalDateTime fecha) { this.fechaIngreso = fecha; }
}