package ar.edu.uns.cs.ed.tdas.tdadiccionario;
import ar.edu.uns.cs.ed.tdas.*;
public class Entrada<K,V> implements Entry<K,V>{
	protected K key;
	protected V valor;
	
	public Entrada(K llave,V val) {
		key=llave;
		valor=val;
	}
	
	public void setKey(K llave) {
		key=llave;
	}
	
	public void setValor(V val) {
		valor=val;
	}
	
	public K getKey() {
		return key;
	}
	
	public V getValue() {
		return valor;
	}
}
