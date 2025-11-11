package ar.edu.uns.cs.ed.tdas.tdadiccionario;
import java.util.Iterator;

import ar.edu.uns.cs.ed.tdas.tdalista.*;
import ar.edu.uns.cs.ed.tdas.Entry;
import ar.edu.uns.cs.ed.tdas.tdamapeo.*;
import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.excepciones.*;

public class DiccionarioHashAbierto<K,V> implements Dictionary<K,V>{
	
	protected static int BUCKETS;
	protected PositionList<Entry<K,V>>[] array;
	protected int cantElems;
	
	public DiccionarioHashAbierto() {
		BUCKETS = 11;
		array=new lista[BUCKETS];
		for(int i=0;i<array.length;i++) {
			array[i]=new lista<>();
		}
		cantElems=0;
	}
	
	
	@Override
	public int size() {
		return cantElems;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return cantElems==0;
	}

	protected int hash(K key) {
		return key.hashCode()% BUCKETS;
	}	
	
	@Override
	public Entry<K,V> find(K key) throws InvalidKeyException{
		if(key==null)throw new InvalidKeyException("key no valida");
		
		Entry<K,V> toRet=null;
		int clave= hash(key);
		boolean esta=false;
		Iterator<Entry<K,V>> it = array[clave].iterator();
		
		while(it.hasNext() && !esta) { 
			Entry<K,V> entrada=it.next();
			if(entrada.getKey().equals(key)) {
				esta=true;
				toRet=entrada;
			}
		}
		return toRet;	
	}
	

	@Override
	public Iterable<Entry<K,V>> findAll(K key)throws InvalidKeyException {
		if(key==null)throw new InvalidKeyException("key no valida");
		
		PositionList<Entry<K,V>> toRet= new lista<>();
		int clave =hash(key);
		
		for(Entry<K,V> entrada: array[clave]) {
			if(entrada.getKey().equals(key)) {
				toRet.addLast(entrada);
			}
		}
		return toRet;
	}

	@Override
	public Entry<K,V> insert(K key, V value)throws InvalidKeyException {
		if (key == null) {
			throw new InvalidKeyException("Clave nula!");
		}
		Entry<K,V> toRet=new Entrada<>(key,value);
		int clave = hash(key);
		array[clave].addLast(toRet);
		cantElems++;
		return toRet;
	}

	@Override
	public Entry<K,V> remove(Entry<K, V> e)throws InvalidEntryException {
		if (e == null || e.getKey() == null) {
			throw new InvalidEntryException("Entrada invalida!");
		}
		int clave = hash(e.getKey());
		boolean esta=false;
		Iterator<Position<Entry<K,V>>> it = array[clave].positions().iterator();
		while (it.hasNext() && !esta) {
			Position<Entry<K,V>> pos = it.next();
			if (pos.element().equals(e)) {
				esta = true;
				try {
					array[clave].remove(pos);
				} catch (InvalidPositionException e1) {e1.printStackTrace();}
				cantElems--;
			}
		}
		if (!esta) {
			throw new InvalidEntryException("Entrada no se encuentra en el diccionario!");
		}
		return e;
	}

	
	
	@Override
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
