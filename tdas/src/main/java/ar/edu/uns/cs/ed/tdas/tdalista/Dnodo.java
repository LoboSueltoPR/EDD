package ar.edu.uns.cs.ed.tdas.tdalista;
import ar.edu.uns.cs.ed.tdas.*;

public class Dnodo<E> implements Position<E> {
	//Atributos de instancia
	private Dnodo<E> next,prev;
	private E element;
	
	public Dnodo(E elem,Dnodo<E> pre,Dnodo<E> nex) {
		this.element=elem;
		this.prev=pre;
		this.next=nex;
	}
	

	public void setPrev(Dnodo<E> nodo){
		prev=nodo;
	}

	public void setNext(Dnodo<E> nodo){
		next=nodo;
	}
	
	public Dnodo<E> getPrev(){
		return prev;
	}
	public Dnodo<E> getNext(){
		return next;
	}

	public E element() {
		// TODO Auto-generated method stub
		return element;
	}


	public void setElement(E elem) {
		// TODO Auto-generated method stub
		element=elem;
	}
}
