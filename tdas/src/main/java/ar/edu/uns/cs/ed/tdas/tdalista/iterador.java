package ar.edu.uns.cs.ed.tdas.tdalista;
import java.util.Iterator;
import java.util.NoSuchElementException;

import ar.edu.uns.cs.ed.tdas.*;
import ar.edu.uns.cs.ed.tdas.excepciones.*;

public class iterador<E> implements Iterator<E>{
	//Atributod e isntancia
	private Position<E> puntero;
	private PositionList<E> list;
	
	public iterador(PositionList<E> l) {
		list = l;
		if (!l.isEmpty()) {
			try {
				puntero = l.first();
			} catch (EmptyListException e) {e.printStackTrace();}
		} else {
			puntero = null;
		}
	}
	
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return puntero!=null;
	}

	@Override
	public E next() throws NoSuchElementException { 	
		if (puntero == null) {
			throw new NoSuchElementException("Error: No hay siguiente");
		}
		E toReturn = puntero.element();  
		try {
			puntero= (puntero == list.last()) ? null : list.next(puntero);
		} catch (EmptyListException | InvalidPositionException | BoundaryViolationException e) {e.printStackTrace();} 
		return toReturn;
	}
}
