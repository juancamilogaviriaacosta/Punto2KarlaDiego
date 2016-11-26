package ejemplo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Utilidades {

	public static Grafo cargarGrafo(String rutaArchivo) throws IOException {
		File archivo = new File(rutaArchivo);
		FileReader fr = new FileReader(archivo);
		BufferedReader br = new BufferedReader(fr);
		Grafo grafo = new Grafo();
		String linea;
		int i = 0;
		int tail;
		int head;
		int cost;
		Arco cadaarco;
		while ((linea = br.readLine()) != null) {
			String[] list = linea.split(" ");
			if (i == 0) {
				for (int j = 0; j < list.length; j++) {
					grafo.agregarNodo(Integer.valueOf(list[j]));
				}
				i++;
			} else {
				tail = Integer.valueOf(list[0]);
				head = Integer.valueOf(list[1]);
				cost = Integer.valueOf(list[2]);
				cadaarco = new Arco(tail, head, cost, 0, 0);
				grafo.agregarArco(cadaarco);
			}
		}
		fr.close();
		br.close();
		return grafo;
	}
}