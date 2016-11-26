package ejemplo;

import java.util.ArrayList;
import java.util.List;

public class Grafo {
	
	private List<Integer> nodos;
	private List<Arco> arcos;
	
	public Grafo() {
		this.nodos = new ArrayList<>();
		this.arcos = new ArrayList<>();
	}
	
	public Grafo(List<Integer> nodos, List<Arco> arcos) {
		this.nodos = nodos;
		this.arcos = arcos;
	}
	
	public void agregarNodo(Integer nodo) {
		nodos.add(nodo);
	}
	
	public void agregarArco(Arco arco) {
		arcos.add(arco);
	}
	
	//Autogenerado
	public List<Integer> getNodos() {
		return nodos;
	}
	public void setNodos(List<Integer> nodos) {
		this.nodos = nodos;
	}
	public List<Arco> getArcos() {
		return arcos;
	}
	public void setArcos(List<Arco> arcos) {
		this.arcos = arcos;
	}
}
