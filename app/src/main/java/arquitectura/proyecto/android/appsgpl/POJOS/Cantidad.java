package arquitectura.proyecto.android.appsgpl.POJOS;

/**
 * Created by Jair Barzola on 30-Jun-17.
 */

public class Cantidad {

    private int enEspera;
    private int Ganado;
    private int Inconcluso;
    private int Perdido;
    private int Finalizado;

    public int getEnEspera() {
        return enEspera;
    }

    public void setEnEspera(int enEspera) {
        this.enEspera = enEspera;
    }


    public int getGanado() {
        return Ganado;
    }

    public void setGanado(int ganado) {
        Ganado = ganado;
    }

    public int getInconcluso() {
        return Inconcluso;
    }

    public void setInconcluso(int inconcluso) {
        Inconcluso = inconcluso;
    }

    public int getPerdido() {
        return Perdido;
    }

    public void setPerdido(int perdido) {
        Perdido = perdido;
    }

    public int getFinalizado() {
        return Finalizado;
    }

    public void setFinalizado(int finalizado) {
        Finalizado = finalizado;
    }
}