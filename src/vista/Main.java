package vista;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import sinRuido.Codificacion;
import sinRuido.Decodificacion;
import sinRuido.fuente.Fuente;
import sinRuido.lineal.Lineal;


public class Main {

	private static Fuente fuente;
	private static Lineal lineal;
	private static Codificacion codificacion;
	private static Decodificacion decodificacion;
	
	/**
	 * Para poder leer elementos por consola
	 */
	public static Scanner in = new Scanner(System.in);

	/**
	 * El método main de todo el programa
	 * 
	 * @param args
	 */
	public static void main(String args[]) {

		System.out.println(inicioPrograma());
		String texto;
		try {
			texto = introducirArchivo();
			fuente = new Fuente(texto);
			lineal = new Lineal();
			int n = escogerSplit();
			fuente.run(n);
			codificacion = new Codificacion(fuente, lineal);
			decodificacion = new Decodificacion(fuente, lineal);
			opciones();
			System.out.println(finPrograma());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Mensaje de inicio del programa
	 * 
	 * @return Texto de bienvenida
	 */
	private static String inicioPrograma() {
		StringBuffer cad = new StringBuffer("");
		cad.append("CODIFICACION BINARIA LINEAL SIN RUIDO");
		cad.append("\n");
		return cad.toString();
	}

	/**
	 * Metodo para introducir la ruta del archivo y capturar el texto del mismo
	 * 
	 * @return texto contenido en el archivo
	 * @throws IOException
	 */
	private static String introducirArchivo() throws IOException {
		System.out.println("\nINTRODUCE LA RUTA ABSOLUTA DEL ARCHIVO CON EL TEXTO:\n");
		String ruta = in.nextLine();
		String linea;
		StringBuffer contenido = new StringBuffer("");
		String texto;

		try {
			FileReader f = new FileReader(ruta, StandardCharsets.UTF_8);
			BufferedReader b = new BufferedReader(f);
			while ((linea = b.readLine()) != null) {

				contenido.append(linea);
				contenido.append("\n");

			}
			b.close();
		} catch (IOException e) {
			throw e;
		}

		texto = contenido.substring(contenido.indexOf("\"") + 1, contenido.lastIndexOf("\""));
		return texto;
	}

	/**
	 * Metodo que nos permite elegir el split que le haremos al texto
	 * 
	 * @return Numero de caracteres que tendrá el simbolo
	 */
	private static int escogerSplit() {
		System.out.println("ESCOGE LA LONGITUD DE LOS SÍMBOLOS: \n");
		String l = in.nextLine();
		boolean b = false;
		while (!b) {
			try {
				Integer.parseInt(l);
				b = true;
			} catch (NumberFormatException excepcion) {
				System.out.println("INTRODUCE UN NUMERO, POR FAVOR: \n");
				l = in.nextLine();
			}
		}

		return Integer.valueOf(l);
	}

	/**
	 * Opciones que se pueden realizar en el programa
	 */
	public static void opciones() {
		// CAMBIAR MENÚ
		imprimirMenu();
		String menu = in.nextLine();
		int opc = Integer.valueOf(menu);

		if (opc != 0) {
			if (opc == 1)
				codificacion.code();
			else if (opc == 2)
				decodificacion.decode();
			else {
				System.out.println(" - OPCIÓN INVÁLIDA!! ");
				opciones();
			}
		} else {
			System.exit(0);
		}
	}

	/**
	 * Metodo para imprimir el menú general de la app de consola
	 */
	private static void imprimirMenu() {
		System.out.println("------------");
		System.out.println("MENU GENERAL");
		System.out.println("------------");
		System.out.println("1) CODIFICAR");
		System.out.println("2) DECODIFICAR");
		System.out.println("0) PARA SALIR");
		System.out.println("-------------");
	}

	/**
	 * Cadena de despedida del programa
	 * 
	 * @return Texto de despedida del programa
	 */
	private static String finPrograma() {
		StringBuffer cad = new StringBuffer("");
		cad.append("\n");
		cad.append("--------------------------\n");
		cad.append("- FIN EJECUCIÓN PROGRAMA -");
		cad.append("\n");
		cad.append("--------------------------\n");
		cad.append("\n");
		return cad.toString();
	}

}

