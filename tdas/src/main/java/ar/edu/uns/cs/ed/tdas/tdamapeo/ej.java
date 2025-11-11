package ar.edu.uns.cs.ed.tdas.tdamapeo;
import ar.edu.uns.cs.ed.tdas.*;
import ar.edu.uns.cs.ed.tdas.tdalista.*;
import java.util.Map;
import ar.edu.uns.cs.ed.*;
public class ej {
	


		public Map<Character,Integer> mapeocaracteres(PositionList<Character> l){
			Map<Character,Integer> toRet= new MapeoConHash<>();
			for(Character a:l) {
				Integer aux=toRet.get(a);
				if(aux==null) {
					toRet.put(a, 1);
				}else {
					toRet.put(a, aux+1);
				}
				
			}
			return toRet;
		}
	
		public static void main (String[]args){
		        // Crear la lista
		        PositionList<Character> lista = new lista<>();

		        // Cargar datos: <a, b, a, c, d, b>
		        lista.addLast('a');
		        lista.addLast('b');
		        lista.addLast('a');
		        lista.addLast('c');
		        lista.addLast('d');
		        lista.addLast('b');

		        // Instanciar la clase que contiene el método
		        ej t = new ej();

		        // Ejecutar el método
		        Map<Character, Integer> res = t.mapeocaracteres(lista);

		        // Mostrar resultados (ITERANDO CON entrySet())
		        System.out.println("Frecuencias:");
		        for (Character k : res.keySet()) {           // ¡llamá keys() con paréntesis!
		            System.out.println(k + " -> " + res.get(k));
		        }
		    }
		
		
		
	
}
