package modelo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Estacionamiento {

    private final int capacidadMaxima = 5;
    private final Semaphore semaforo = new Semaphore(capacidadMaxima);
    private final ArrayList<Coche> cochesAparcados = new ArrayList<>();

    public boolean entrar(Coche coche) {
        boolean aparcado = false;

        if (coche.esVip() == true) {
            try {
                semaforo.acquire();
                synchronized (cochesAparcados) {
                    if (cochesAparcados.size() >= capacidadMaxima) {
                        desalojarCocheNormal(coche);
                        cochesAparcados.add(coche);

                        aparcado = true;
                    }
                }
            } catch(Exception e) {}
        } else if (coche.esVip() == false) {
            try {
                if (semaforo.tryAcquire(5)) {
                    cochesAparcados.add(coche);

                    aparcado = true;
                } else {
                    System.out.println(coche + " ha abandonado la cola del estacionamiento.");
                }
            } catch(Exception e) {}
        }
        return aparcado;
    }
    public void desalojarCocheNormal(Coche cocheVip) {
        synchronized (cochesAparcados) {
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
}
