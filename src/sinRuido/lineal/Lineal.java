package sinRuido.lineal;

import java.util.ArrayList;

public class Lineal {

	private ArrayList<Short> lista = new ArrayList<Short>();
	private ArrayList<Short> listaDecodificada = new ArrayList<Short>();
	
	public Lineal() {
		
	}
	
	public ArrayList<Short> decode(ArrayList<Short> lista){
		this.lista = lista;
		short pos = 0;
		
		for(int i=0; i<lista.size(); i++) {
			
			if(pos < 3) {
				this.listaDecodificada.add(this.lista.get(i));
			}else {
				if (pos == 6) {
					pos = -1;
				}
			}
			
			pos++;
		}
		
		//System.out.println(lista.size()%9);
		
		return this.listaDecodificada;
	}
	
}
