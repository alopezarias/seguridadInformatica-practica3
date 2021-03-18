package sinRuido.fuente;

import java.util.ArrayList;

public class Fuente {

	private String texto;
	private ArrayList<String> alf = new ArrayList<String>();
	
	public Fuente (String texto) {
		this.texto = texto;
	}

	/**
	 * Metodo que incia el calculo de parametros de la fuente
	 */
	public void run(int simTam) {
		this.simbolSplit(this.texto, simTam);
	}

	/**
	 * Metodo que separa los simbolos de uno en uno y los almacena en espacios
	 * diferentes para cada simbolo distinto, alamcenando tambien la frecuencia de
	 * los mismos.
	 * 
	 * @param text Texto origen de la fuente
	 */
	private void simbolSplit(String text, int n) {

		StringBuffer finalText = new StringBuffer("");

		for (int i = 1; i <= texto.length(); i++) {

			finalText.append(texto.charAt(i - 1));
			if (i != 0 && i % n == 0 && i != texto.length()) {
				finalText.append("\t");
			}

		}

		this.texto = finalText.toString();
		finalText = new StringBuffer("");

		String[] simbDif = this.texto.split("\t");

		for (String simb : simbDif) {
			this.alf.add(simb);
		}
	}
	
	public String decode(ArrayList<Short> lista) {
		
		StringBuilder car = new StringBuilder("");
		StringBuilder resultado = new StringBuilder("");
		int posicion;
		
		for(int i=0; i<lista.size(); i++) {
			
			if((i+1)%7 == 0 && i!= 0) {
				car.append(lista.get(i).toString());
				posicion = qToInt(2,car.reverse());
				resultado.append(this.alf.get(posicion));
				car = new StringBuilder("");
			}else {
				car.append(lista.get(i).toString());
			}
			
		}
		
		return resultado.toString();
	}
	
	private int qToInt(int q, StringBuilder cad) {
		
		int resultado = 0;
		
		for(int i=0; i<cad.length(); i++) {
			if(cad.charAt(i) == '1') {
				resultado += Math.pow(q, i);
			}
		}
		return resultado;
		
	}
}
