package ar.edu.uns.cs.ed.tdas.tdaarbol;
import ar.edu.uns.cs.ed.tdas.*;
import ar.edu.uns.cs.ed.tdas.tdalista.*;

public class Tnodo<E> implements Position<E>{
	
	//atr inst
	protected E elem;
	protected PositionList<Tnodo<E>>  listahijos;
	protected Tnodo<E> padre;
	
	public Tnodo(Tnodo<E> pad,E ele) {
		padre=pad;
		elem=ele;
		listahijos=new lista();
	}
	
	public void setElem(E ele) {
		elem=ele;
	}
	
	public void setPadre(Tnodo<E> pa) {
		padre=pa;
	}
	
	public void setHijo(Tnodo<E> hijo) {
		listahijos.addLast(hijo);
	}
	
	public Tnodo<E> getPadre() {
		return padre;
	}
	
	public PositionList<Tnodo<E>> getListahijos(){
		return listahijos;
	}
	
	public E element() {
		// TODO Auto-generated method stub
		return elem;
	}

}
