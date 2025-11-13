package ar.edu.uns.cs.ed.tdas.tdaarbolbinario;
import ar.edu.uns.cs.ed.tdas.*;

import java.util.Iterator;

import ar.edu.uns.cs.ed.tdas.excepciones.*;
import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.tdalista.*;


public class LinkedBinaryTree<E> implements BinaryTree<E> {
	protected BTNode<E> root;
	protected int cantElems;
	
	public LinkedBinaryTree() {
		root=null;
		cantElems=0;
	}
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return cantElems;
	}
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return cantElems==0;
	}
	@Override
	public Iterator<E> iterator() {
		PositionList<E> it=new lista<>();
		//caso base
		if(!isEmpty()) {
			preOrden(root,it);
		}
		// TODO Auto-generated method stub
		return it.iterator();
	}
	
	private void preOrden(BTNode<E> root,PositionList<E> lista) {
			lista.addLast(root.element());
			if(root.getLeft()!=null){
			preOrden(root.getLeft(),lista);
			}
			if(root.getRight()!=null){
			preOrden(root.getRight(),lista);
			}
	}
	@Override
	public Iterable<Position<E>> positions() {
		// TODO Auto-generated method stub
		PositionList<Position<E>> it=new lista<>();
		
		if(!isEmpty()) {
			posOrden(root,it);
		}
		return it;
	}
	
	

	private void posOrden(BTNode<E> root,PositionList<Position<E>> list) {
		if(root.getLeft()!=null){
			posOrden(root.getLeft(),list);
			}
			if(root.getRight()!=null){
			posOrden(root.getRight(),list);
			}
			list.addLast(root);
	}
	private BTNode<E> checkpositions(Position<E> p)throws InvalidPositionException{
		// TODO Auto-generated method stub
		if(p==null)throw new InvalidPositionException("posicion nula");
		BTNode<E> toRet=(BTNode<E>) p;
		return toRet;
	}
	@Override
	public E replace(Position<E> v, E e) throws InvalidPositionException{
		// TODO Auto-generated method stub
		
		if(v==null)throw new InvalidPositionException("posicion nula");
		BTNode<E> nodo=checkpositions(v);
		E toRet=nodo.element();
		nodo.setElement(e);
		return toRet;
	}
	@Override
	public Position<E> root()throws EmptyTreeException  {
		if(cantElems==0)throw new EmptyTreeException("arbol vacio");
		// TODO Auto-generated method stub
		return root;
	}
	@Override
	public Position<E> parent(Position<E> v) throws InvalidPositionException,BoundaryViolationException {
		// TODO Auto-generated method stub
		if(v==null)throw new InvalidPositionException("posicion nula");
		BTNode<E> nodo=checkpositions(v);
		if(nodo==root)throw new BoundaryViolationException("pos igual a raiz");
		
		return nodo.getFather();
	}
	@Override
	public Iterable<Position<E>> children(Position<E> v) {
		// TODO Auto-generated method stub
		PositionList<Position<E>> it=new lista();
		BTNode<E> nodo=checkpositions(v);
		
		
		if(nodo.getLeft()!=null){
			it.addLast(nodo);
		}
		if(nodo.getRight()!=null){
			it.addLast(nodo);
		}
		return it;
	}
	@Override
	public boolean isInternal(Position<E> v)throws InvalidPositionException{
		// TODO Auto-generated method stub
		if(v==null)throw new InvalidPositionException("posicion nula");
		BTNode<E>nodo=checkpositions(v);
		return (nodo.getLeft()!=null)||(nodo.getRight()!=null);
		}
	@Override
	public boolean isExternal(Position<E> v) throws InvalidPositionException{
		// TODO Auto-generated method stub
		if(v==null)throw new InvalidPositionException("posicion nula");
		BTNode<E>nodo=checkpositions(v);
		return (nodo.getLeft()==null)&&(nodo.getRight()==null);
		}
		
	@Override
	public boolean isRoot(Position<E> v) throws InvalidPositionException{
		// TODO Auto-generated method stub
		if(v==null)throw new InvalidPositionException("posicion nula");
		BTNode<E>nodo=checkpositions(v);
		
		return nodo==root;
	}
	public void createRoot(E e) throws InvalidOperationException {
		if(root!=null)throw new InvalidOperationException ("ya hay raiz");
		// TODO Auto-generated method stub
		root=new BTNode<>(e);
		
	}
	public E remove(Position<E> v)throws InvalidOperationException, InvalidPositionException{
		BTNode<E> node = checkpositions(v);
		if (node.getLeft() != null && node.getRight() != null) {
			throw new InvalidOperationException("El nodo a eliminar tiene dos hijos");
		}
		E toRet = node.element();
			
			if(node!=root) {
				BTNode<E> padre = node.getFather();
				if (isExternal(node)) {
					if (padre.getLeft() == node) {
						padre.setLeft(null);
					} else {
						padre.setRight(null);
					}
				}
			 
			if(node.getLeft()!=null) {
				if(padre.getLeft()==node) {
					padre.setLeft(node.getLeft());
				}else {
					padre.setRight(node.getLeft());
				}
			}
			
			if(node.getRight()!=null) {
				if(padre.getLeft()==node) {
					padre.setLeft(node.getRight());
				}else {
					padre.setRight(node.getRight());
				}
				node.getRight().setFather(padre);
				node.setRight(null);
			}
			
			
		}else {
			if(isExternal(node)) {
				root=null;
			}
			if (node.getLeft() != null) {
				root = node.getLeft();
				node.setLeft(null);
			}
			if (node.getRight() != null) {
				root = node.getRight();
				node.setRight(null);
			}
			
		}

			node.setElement(null);
			cantElems--;
			return toRet;
	}
	public void removeNode(Position<E> p) {
		// TODO Auto-generated method stub
		
	}
	public Position<E> left(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		BTNode<E> node = checkpositions(v);
		if (node.getLeft() == null) {
			throw new BoundaryViolationException("EL nodo no tiene hijo izquierdo");
		}
		return node.getLeft();
	}

	public Position<E> right(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		BTNode<E> node = checkpositions(v);
		if (node.getRight() == null) {
			throw new BoundaryViolationException("EL nodo no tiene hijo derecho");
		}
		return node.getRight();
	}
	@Override
	public boolean hasLeft(Position<E> v) throws InvalidPositionException {
		BTNode<E> node = checkpositions(v);
		return node.getLeft() != null;
	}
	@Override
	public boolean hasRight(Position<E> v) throws InvalidPositionException {
		BTNode<E> node = checkpositions(v);
		return node.getRight() != null;
	}
	@Override
	public Position<E> addLeft(Position<E> v, E r) throws InvalidOperationException, InvalidPositionException {
		BTNode<E> node = checkpositions(v);
		if (node.getLeft() != null) {
			throw new InvalidOperationException("El nodo ya tiene un hizo izquierdo");
		}
		BTNode<E> newNode = new BTNode<>(r,node,null,null);
		node.setLeft(newNode);
		cantElems++;
		return newNode;
	}
	@Override
	public Position<E> addRight(Position<E> v, E r) throws InvalidOperationException, InvalidPositionException {
		BTNode<E> node = checkpositions(v);
		if (node.getRight() != null) {
			throw new InvalidOperationException("El nodo ya tiene un hizo derecho");
		}
		BTNode<E> newNode = new BTNode<>(r,node,null,null);
		node.setRight(newNode);
		cantElems++;
		return newNode;	}
	@Override
	public void attach(Position<E> r, BinaryTree<E> T1, BinaryTree<E> T2) throws InvalidPositionException {
		BTNode<E> node = checkpositions(r);
		if (isInternal(node)) {
			throw new InvalidPositionException("El nodo no es hoja");
		}
		try {
			BTNode<E> R1 = checkpositions(T1.root());
			node.setLeft(R1);
			
			BTNode<E> R2 = checkpositions(T2.root());
			node.setRight(R2);
		} catch (InvalidPositionException | EmptyTreeException e) {e.printStackTrace();}
	}
	
	public BinaryTree<E> BinaryTreeCopy(){
		LinkedBinaryTree<E> nuevoArbol = new LinkedBinaryTree<>();
		if (!isEmpty()) {
			nuevoArbol.root = copiarNodo(root, null);
			nuevoArbol.cantElems = cantElems;
			
		}
		return nuevoArbol;
	}
	private BTNode<E> copiarNodo(BTNode<E> nodoOriginal, BTNode<E> padreNuevo) {
		BTNode<E> nuevoNodo = new BTNode<>(nodoOriginal.element(), padreNuevo, null, null);
		
		if (nodoOriginal.getLeft() != null) {
			nuevoNodo.setLeft(copiarNodo(nodoOriginal.getLeft(), nuevoNodo));
		}
		if (nodoOriginal.getRight() != null) {
			nuevoNodo.setRight(copiarNodo(nodoOriginal.getRight(), nuevoNodo));
		}
		return nuevoNodo;
	}
	
}
