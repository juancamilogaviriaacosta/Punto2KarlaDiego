package ejemplo;

public class Arco {
	
	private int cola;
	private int cabeza;
	private int costo;

	public Arco(int cola, int cabeza, int costo) {
		this.cola = cola;
		this.cabeza = cabeza;
		this.costo = costo;
	}
	
	//Autogenerado
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
}
