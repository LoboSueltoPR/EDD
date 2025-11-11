package ar.edu.uns.cs.ed.tdas.tdalista
;
import java.util.Iterator;

import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.excepciones.*;

public class lista<E> implements PositionList<E>{
	//Atributos de instancia
	private int cantElem;
	private Dnodo<E> head,tail;
	
	public lista() {
		cantElem=0;
		head=new Dnodo(null,null,null);
		tail=new Dnodo(null,head,null);
		head.setNext(tail);
	}
	
	
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return cantElem;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return cantElem==0;
	}

	@Override
	public Position<E> first() throws EmptyListException {
		if(cantElem==0) {
			throw new EmptyListException("Lista vacia");
		}
		return head.getNext();
	}

	@Override
	public Position<E> last()throws EmptyListException {
		if(cantElem==0) {
			throw new EmptyListException("Lista vacia");
		}
		return tail.getPrev();
	
	}
	
	private Dnodo<E> CheckPositions(Position<E> p)throws InvalidPositionException{
		if(p==null||cantElem==0)throw new InvalidPositionException("Posicion invalida");
		try {
			Dnodo<E> check =(Dnodo<E>) p; 
			return check;
		}catch (ClassCastException h){
			throw new InvalidPositionException("Posicion invalida");
		}
	}
	
	@Override
	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		Dnodo<E> nodo = CheckPositions(p);
		if (nodo.getNext() == tail) {
			throw new BoundaryViolationException("La ultima posicion no tiene siguiente");
		}
		return nodo.getNext();
	}

	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		Dnodo<E> nodo = CheckPositions(p);
		if (nodo.getPrev() == head) {
			throw new BoundaryViolationException("La primera posicion no tiene anterior");
		}
		return nodo.getPrev();
	}
	
	@Override
	public void addFirst(E element)  {
		// TODO Auto-generated method stub
		Dnodo<E> nodo=new Dnodo(element,head,null);
		nodo.setNext(head.getNext());
		head.getNext().setPrev(nodo);
		head.setNext(nodo);
		cantElem++;
	}

	@Override
	public void addLast(E element) {
		// TODO Auto-generated method stub
		Dnodo<E> nodo=new Dnodo(element,null,tail);
		
		nodo.setPrev(tail.getPrev());
		tail.getPrev().setNext(nodo);
		tail.setPrev(nodo);
		cantElem++;
		
	}

	@Override
	public void addAfter(Position<E> p, E element)throws EmptyListException {
		// TODO Auto-generated method stub
		Dnodo<E> nodoanterior=CheckPositions(p);
		
		if(cantElem==0||nodoanterior==null)throw new EmptyListException ("ListaVacia");
		
		Dnodo<E> nodonuevo=new Dnodo(element,nodoanterior,null);
		
		nodonuevo.setNext(nodoanterior.getNext());
		nodoanterior.getNext().setPrev(nodonuevo);
		nodoanterior.setNext(nodonuevo);
		cantElem++;
		
		
	}

	@Override
	public void addBefore(Position<E> p, E element) {
		// TODO Auto-generated method stub
		Dnodo<E> nododespues=CheckPositions(p);
		
		if(cantElem==0||nododespues==null)throw new EmptyListException ("ListaVacia");
		
		Dnodo<E> nodonuevo=new Dnodo(element,null,nododespues);
		
		
		nodonuevo.setPrev(nododespues.getPrev());
		nododespues.getPrev().setNext(nodonuevo);
		nododespues.setPrev(nodonuevo);
		cantElem++;
		
	}

	@Override
	public E remove(Position<E> p)throws InvalidPositionException {
		Dnodo<E> nodo=CheckPositions(p);
		if(nodo==null||cantElem==0)throw new InvalidPositionException("ds");
		// TODO Auto-generated method stub
		E elem=nodo.element();
		nodo.getNext().setPrev(nodo.getPrev());
		nodo.getPrev().setNext(nodo.getNext());
		nodo.setNext(null);
		nodo.setPrev(null);
		nodo=null;
		cantElem--;
		return elem;
		
	}

	@Override
	public E set(Position<E> p, E element) throws InvalidPositionException{
		Dnodo<E> nodo = CheckPositions(p);
		if(nodo==null||cantElem==0)throw new InvalidPositionException("ds");
		E toRet = nodo.element();
		nodo.setElement(element);
		return toRet;
	
	}
	public PositionList<E> listaelemsrepetidos(PositionList<E> l){
		PositionList<E> toRet=new lista();
			
		Iterator<E> it=l.iterator();
		
		while(it.hasNext()) {
			E aux=it.next();
			toRet.addLast(aux);
			toRet.addLast(aux);
		}
		return toRet;
	}
	
	@Override
	public Iterator<E> iterator() {
		return new iterador<E>(this);
	}

	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> toRet = new lista<>();
		if (!isEmpty()) {
			try {
				Position<E> pos = first();
				while (pos != last()) {
					toRet.addLast(pos);
					pos = next(pos);
				}
				toRet.addLast(pos);
			} catch (EmptyListException | InvalidPositionException | BoundaryViolationException e) {e.printStackTrace();}
		}
		return toRet;
	
	}
	
	
}
