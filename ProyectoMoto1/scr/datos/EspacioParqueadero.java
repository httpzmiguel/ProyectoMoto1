package datos;

public class EspacioParqueadero {
    private int numero;
    private boolean ocupado;
    private Moto motoAsignada;

    public EspacioParqueadero(int numero) {
        this.numero = numero;
        this.ocupado = false;
        this.motoAsignada = null;
    }

    public void ocupar(Moto moto) {
        this.motoAsignada = moto;
        this.ocupado = true;
    }

    public void liberar() {
        this.motoAsignada = null;
        this.ocupado = false;
    }

    public int getNumero() { return numero; }
    public boolean estaOcupado() { return ocupado; }
    public Moto getMotoAsignada() { return motoAsignada; }
}
