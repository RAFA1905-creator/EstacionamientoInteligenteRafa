package modelo;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Estacionamiento {

    private final int capacidadMaxima = 5;
    private final Semaphore semaforo = new Semaphore(capacidadMaxima);
    private final ArrayList<Coche> cochesAparcados = new ArrayList<>();

    private void desalojarCocheNormal(Coche cocheVip) {
        for (Coche coche : cochesAparcados) {
            if (!coche.esVip()) {
                cochesAparcados.remove(coche);
                semaforo.release();
                System.out.println(coche + " fue desalojado por " + cocheVip);
                break;
            }
        }
    }
}
