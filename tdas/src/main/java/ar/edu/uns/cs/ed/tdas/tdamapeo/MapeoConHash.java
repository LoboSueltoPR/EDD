package ar.edu.uns.cs.ed.tdas.tdamapeo;
import ar.edu.uns.cs.ed.tdas.excepciones.*;

import java.util.Iterator;
import ar.edu.uns.cs.ed.tdas.*;
import ar.edu.uns.cs.ed.tdas.Entry;
import ar.edu.uns.cs.ed.tdas.tdalista.*;


public class MapeoConHash<K,V> implements Map<K,V>{

	protected static int BUCKETS;
	protected PositionList<Entry<K,V>>[] array;
	protected int cantElems;
	
	
	@SuppressWarnings("unchecked")
	public MapeoConHash() {
		BUCKETS = 11;
		array=new lista[BUCKETS];
		for (int i=0;i<BUCKETS;i++) {
			array[i]=new lista<>();
		}
		cantElems=0;
	}
	public int size() {
		// TODO Auto-generated method stub
		return cantElems;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return cantElems==0;
	}

	protected int hash(K key) {
		return key.hashCode() % BUCKETS;
	}
	
	@Override
	public V get(K key) throws InvalidKeyException{
		// TODO Auto-generated method stub
		if(key==null)throw new InvalidKeyException("Clave nula!");
		
		V toRet =null;
		int clave =hash(key);
		boolean esta=false;
		Iterator<Entry<K,V>> it = array[clave].iterator();
		while(it.hasNext()&&!esta) {
			Entry<K,V> entrada = it.next();
			if(entrada.getKey().equals(key)) {
				esta=true;
				toRet=entrada.getValue();
			}
		}
		
		return toRet;
	}

	
	
	@Override
	public V put(K key, V value) throws InvalidKeyException{
		// TODO Auto-generated method stub
		if(key==null)throw new InvalidKeyException("Clave nula!");
		V toRet=null;
		int clave =hash(key);
		boolean esta=false;
		Iterator<Entry<K,V>> it=array[clave].iterator();
		while(it.hasNext()&&!esta) {
			Entrada<K,V> entrada=(Entrada<K,V> )it.next();
			if(entrada.getKey().equals(key)) {
				esta=true;
				toRet = entrada.getValue();
				entrada.setValor(value);
			}
		}
		if(!esta) {
			array[clave].addLast(new Entrada<>(key,value));
			cantElems++;
		}
		return toRet;
	}

	@Override
	public V remove(K key) throws InvalidKeyException {
		if (key == null) {
			throw new InvalidKeyException("Clave nula!");
		}
		V toRet = null;
		int clave = hash(key);
		boolean esta = false;
		PositionList<Entry<K,V>> lista = array[clave];
		Iterator<Position<Entry<K,V>>> it = lista.positions().iterator();
		while (it.hasNext() && !esta) {
			Position<Entry<K,V>> aux = it.next();
			if (aux.element().getKey().equals(key)) {
				esta = true; 
				toRet = aux.element().getValue();
				try {
					lista.remove(aux);
				} catch (InvalidPositionException e) {e.printStackTrace();}
				cantElems--;
			}
		}
		return toRet;
	} 
	
	
	@Override
	public Iterable<K> keys() {
		PositionList<K> toRet = new lista<>();
		for (int i = 0; i < BUCKETS; i++) {
			PositionList<Entry<K,V>> lista = array[i];
			for (Entry<K,V> entrada : lista) {
				toRet.addLast(entrada.getKey());
			}
		}
		return toRet;
	}

	public Iterable<V> values() {
		PositionList<V> toRet = new lista<>();
		for (int i = 0; i < BUCKETS; i++) {
			PositionList<Entry<K,V>> lista = array[i];
			for (Entry<K,V> entrada : lista) {
				toRet.addLast(entrada.getValue());
			}
		}
		return toRet;
	}

	public Iterable<Entry<K,V>> entries() {
		PositionList<Entry<K,V>> toRet = new lista<>();
		for (int i = 0; i < BUCKETS; i++) {
			PositionList<Entry<K,V>> lista = array[i];
			for (Entry<K,V> entrada : lista) {
				toRet.addLast(entrada);
			}
		}
		return toRet;
	}
	
}

