package ar.edu.uns.cs.ed.tdas.tdaarbolbinario;
import ar.edu.uns.cs.ed.tdas.*;

public class BTNode<E> implements Position<E> {
	
	private E element;
	private BTNode<E> father,left,right;
	
	public BTNode(E element, BTNode<E> father, BTNode<E> left, BTNode<E> right) {
		this.element = element;
		this.father = father;
		this.left = left;
		this.right = right; 
	}
	
	public BTNode(E element) {
		this(element,null,null,null);
	}
	
	public E element() {
		return element;
	}
	
	public void setElement(E element) {
		this.element = element;
	}

	public BTNode<E> getFather() {
		return father;
	}

	public void setFather(BTNode<E> father) {
		this.father = father;
	}

	public BTNode<E> getLeft() {
		return left;
	}

	public void setLeft(BTNode<E> left) {
		this.left = left;
	}

	public BTNode<E> getRight() {
		return right;
	}

	public void setRight(BTNode<E> right) {
		this.right = right;
	}
}
