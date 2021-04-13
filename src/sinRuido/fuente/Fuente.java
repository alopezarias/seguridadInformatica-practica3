package sinRuido.fuente;

import java.util.ArrayList;
import java.util.Collections;

import vista.Main;

public class Fuente {

	private String texto;
	private ArrayList<String> alf = new ArrayList<String>();
	private int q;
	private int bloque;

	public Fuente(String texto) {
		this.texto = texto;
		this.q = Integer.valueOf(Main.seleccionarDelArchivo("q"));
	}

	/**
	 * Metodo que incia el calculo de parametros de la fuente
	 */
	public void run(int simTam) {
		this.simbolSplit(this.texto, simTam);
		this.bloque = calcularBloqueMinimo(alf.size(), this.q);
	}

	private int calcularBloqueMinimo(int n, int q) {
		double result = Math.log(n) / Math.log(q);
		return (int) Math.ceil(result);
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

		//StringBuilder car = new StringBuilder("");
		ArrayList<Short> num = new ArrayList<Short>();
		StringBuilder resultado = new StringBuilder("");
		int posicion;
		// System.out.println(lista.toString());

		for (int i = 0; i < lista.size(); i++) {

			if ((i + 1) % this.bloque == 0 && i != 0) {
				num.add(lista.get(i));
				posicion = qToInt(this.q, num);
				resultado.append(this.alf.get(posicion));
				num = new ArrayList<Short>();
			} else {
				num.add(lista.get(i));
			}

		}

		return resultado.toString();
	}

	private int qToInt(int q, ArrayList<Short> array) {

		int resultado = 0;
		// System.out.println(array.toString());
		Collections.reverse(array);
		for (int i = array.size() - 1; i >= 0; i--) {
			resultado += Math.pow(q, i) * Integer.valueOf(array.get(i));
		}
		// System.out.println(resultado);
		return resultado;

	}
}
