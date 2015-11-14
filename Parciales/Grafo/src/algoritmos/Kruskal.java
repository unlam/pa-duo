package algoritmos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;

import model.Arista;
import model.Grafo;
import model.MatrizSimetrica;
import model.Nodo;

/**
 * Conectar todos los nodos del grafo, a traves del camino mas corto.
 * */
public class Kruskal extends Grafo{
	private int[] representantes;
		
	public Kruskal(int cantidadNodos) {
		super(cantidadNodos);
		representantes = new int[cantidadNodos];
	}
	
	public Kruskal(String in){
		super();
		FileReader fr = null;
		BufferedReader br = null;
		
		try {
			fr = new FileReader(in);
			br = new BufferedReader(fr);
			
			String line = br.readLine();
			String[] data = line.split(" ");
			
			nodos = new Nodo[Integer.parseInt(data[0])];
			initNodos();
			aristas = new ArrayList<Arista>(Integer.parseInt(data[1]));
			matrizAdyacencia = new MatrizSimetrica(nodos.length);
			representantes = new int[nodos.length];
			for (int i = 0; i < representantes.length; i++) {
				representantes[i] = i;
			}
			
			while((line = br.readLine()) != null){
				data = line.split(" ");
				int indiceOrigen = Integer.parseInt(data[0]) - 1;
				int indiceDestino = Integer.parseInt(data[1]) - 1;
				int costo = Integer.parseInt(data[2]);
				
				nodos[indiceOrigen].addGrado();
				nodos[indiceDestino].addGrado();
				aristas.add(new Arista(nodos[indiceOrigen], nodos[indiceDestino], costo));
				matrizAdyacencia.setNodo(indiceOrigen, indiceDestino);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fr != null) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	private int find(Nodo nodo){
		return representantes[nodo.getIndice()];
	}
	
	private void replaceAll(int index, int value){
		for (int i = 0; i < representantes.length; i++) {
			if (representantes[i] == index) {
				representantes[i] = value;
			}
		}
	}
	
	private void union(Nodo origen, Nodo destino){
		int representanteOrigen = find(origen);
		int representanteDestino = find(destino);
		
		if (representanteOrigen < representanteDestino) {
			replaceAll(representanteDestino, representanteOrigen);
		}else{
			replaceAll(representanteOrigen, representanteDestino);
		}
	}
	
	public void resolver(){
		Collections.sort(aristas);
		
		for (Arista arista : aristas) {
			if ( find(arista.getOrigen()) != find(arista.getDestino())) {
				union(arista.getOrigen(), arista.getDestino());
			}else{
				matrizAdyacencia.removeNodo(arista.getOrigen().getIndice(), arista.getDestino().getIndice());
			}
		}
	}
	
	public static void main(String[] args) {
		Kruskal grafo = new Kruskal("kruskal.in");
		grafo.resolver();
		System.out.println(grafo.toString());
	}

}
