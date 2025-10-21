package modelo;

public class Coche {

    private String nombre;
    private boolean vip;
    private Estacionamiento estacionamiento;

    public boolean esVip() {
        return vip;
    }

    public void run() {
        boolean aparcado = estacionamiento.entrar(this);
        if (aparcado == true) {
            try {
                int tiempo = 2000 + ((int) (Math.random() * 4000));
                Thread.sleep(tiempo);
            } catch (Exception e) {
                Thread.currentThread().interrupt();
            } finally {
                //TODO: SALIR DEL ESTACIONAMIENTO
            }
        }
    }
}
