package ejemplo;

public class Arco {
	
	private int cola;
	private int cabeza;
	private int costo;
	private int tiempo;
	private double distancia;
	
	public Arco(int cola, int cabeza, int costo, int tiempo, double distancia) {
		super();
		this.cola = cola;
		this.cabeza = cabeza;
		this.costo = costo;
		this.tiempo = tiempo;
		this.distancia = distancia;
	}
	
	public int getCola() {
		return cola;
	}
	public void setCola(int cola) {
		this.cola = cola;
	}
	public int getCabeza() {
		return cabeza;
	}
	public void setCabeza(int cabeza) {
		this.cabeza = cabeza;
	}
	public int getCosto() {
		return costo;
	}
	public void setCosto(int costo) {
		this.costo = costo;
	}
	public int getTiempo() {
		return tiempo;
	}
	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}
	public double getDistancia() {
		return distancia;
	}
	public void setDistancia(double distancia) {
		this.distancia = distancia;
	}
}