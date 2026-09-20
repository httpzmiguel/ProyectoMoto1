package datos;

public class EspacioParqueadero {
    private int idEspacio;
    private boolean ocupado;
    private Moto motoAsignada;

    public EspacioParqueadero(int idEspacio) {
        this.idEspacio = idEspacio;
        this.ocupado = false;
        this.motoAsignada = null;
    }

    public boolean estaOcupado() {
        return ocupado;
    }

    public void asignarMoto(Moto moto) {
        this.motoAsignada = moto;
        this.ocupado = true;
    }

    public void liberar() {
        this.motoAsignada = null;
        this.ocupado = false;
    }

    public Moto getMotoAsignada() {
        return motoAsignada;
    }

    public int getIdEspacio() {
        return idEspacio;
    }
}