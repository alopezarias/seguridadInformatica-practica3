package sinRuido;

import java.util.ArrayList;
import java.util.Scanner;

import sinRuido.fuente.Fuente;
import sinRuido.lineal.Lineal;
import vista.Main;

public class Decodificacion {

	private Fuente fuente;
	private Lineal lineal;
	private ArrayList<Short> list = new ArrayList<Short>();
	private ArrayList<Short> decodLineal = new ArrayList<Short>();
	private String resultado;
	
	public Decodificacion(Fuente f, Lineal l) {
		this.fuente = f;
		this.lineal = l;
	}
	
	public void decode() {
		
		Scanner sc = Main.in;
		System.out.println("Introduce la cadena a decodificar: ");
		String lista = sc.nextLine();
		
		separarLista(lista);
		decodLineal = lineal.decode(this.list);
		resultado = fuente.decode(this.decodLineal);
		
		System.out.println("RESULTADO --> " + resultado);
		Main.opciones();
		
	}
	
	private void separarLista( String lista) {
		
		String listaSinCorchetes = lista.substring(1, lista.length()-1);
		String[] res = listaSinCorchetes.split(",");
		
		for(String s : res) {
			//System.out.println(s);
			this.list.add(Short.valueOf(s));
		}
	}
}
