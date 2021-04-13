package sinRuido.lineal;

import java.util.ArrayList;

import vista.Main;

public class Lineal {

	private ArrayList<Short> lista = new ArrayList<Short>();
	private ArrayList<Short> listaDecodificada = new ArrayList<Short>();
	private static int[][] matriz;
	private static String matriz_str;
	
	public Lineal() {
		matriz_str = Main.seleccionarDelArchivo("matriz");
		matriz = stringAMatriz(matriz_str);
	}
	
	public ArrayList<Short> decode(ArrayList<Short> lista){
		this.lista = lista;
		short pos = 0;
		
		for(int i=0; i<lista.size(); i++) {
			
			if(pos < matriz.length) {
				this.listaDecodificada.add(this.lista.get(i));
			}else {
				if (pos == (matriz.length + matriz[0].length -1)) {
					pos = -1;
				}
			}
			
			pos++;
		}
		
		//System.out.println(lista.size()%9);
		
		return this.listaDecodificada;
	}
	
	/**
	 * Pasar un string a una matriz
	 * @param matriz
	 * @return
	 */
	private static int[][] stringAMatriz(String matriz) {
		
		int[][] resultado = null;
		String fila;
		String[] columnas;
		String mat = matriz;
		String[] filas = mat.split(";");
		for(int i=0; i<filas.length; i++) {
			fila = filas[i].substring(filas[i].indexOf("[")+1, filas[i].lastIndexOf("]"));
			columnas = fila.split(",");
			
			if(i == 0) {
				resultado = new int[filas.length][columnas.length];
			}
			
			for(int j=0; j<columnas.length; j++) {
				resultado[i][j] = Integer.valueOf(columnas[j]);
			}
		}
		return resultado;
	}
	
}
