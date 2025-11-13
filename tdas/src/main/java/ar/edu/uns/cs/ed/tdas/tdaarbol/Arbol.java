package ar.edu.uns.cs.ed.tdas.tdaarbol;
import java.util.Iterator;
import ar.edu.uns.cs.ed.tdas.excepciones.*;
import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.tdalista.*;
import javafx.geometry.Pos;
public class Arbol<E> implements Tree<E>{
	
	//Atributos de isntancia
	protected int cantElem;
	protected Tnodo<E> Raiz;
	
	
	public Arbol() {
		cantElem=0;
		Raiz=null;
	}
	

	private Tnodo<E> CheckPositions(Position<E> p)throws InvalidPositionException{
		if(p==null||cantElem==0)throw new InvalidPositionException("Posicion invalida");
		try {
			Tnodo<E> check =(Tnodo<E>) p; 
			return check;
		}catch (ClassCastException h){
			throw new InvalidPositionException("Posicion invalida");
		}
	}
	
	public Iterable<E> convertirAhoja(Position<E> p) throws InvalidPositionException{
		Tnodo <E> nodo= CheckPositions(p);
		PositionList<E> toRet=new lista<>();
		posor(toRet,nodo);
		return toRet;
	}

	public void posor(PositionList<E> list, Tnodo<E> nodo) {
		if(nodo != null) {
			// Primero agregamos el elemento actual a la lista
			// Luego eliminamos todos los hijos del nodo actual
			PositionList<Tnodo<E>> listaHijos = nodo.getListahijos();
			
			
			// Recursivamente procesamos todos los hijos
			for (Tnodo<E> child : listaHijos) {
				posor(list, child);	
			}
			
			while(!listaHijos.isEmpty()) {
				Position<Tnodo<E>> first = listaHijos.first();
				listaHijos.remove(first);
				cantElem--;
				list.addLast(nodo.element());
			}
		}
	}
	
	@Override
	public int size() {
		return cantElem;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return cantElem==0;
	}

	private void preOrder(Tnodo<E> p, PositionList<E> list) {
		list.addLast(p.element());
		for (Tnodo<E> child : p.getListahijos()) {
			preOrder(child,list);
		}
	}
	@Override
	public Iterator<E> iterator() {
		PositionList<E> toRet = new lista<>();
		if(!isEmpty()) {
			preOrder(Raiz,toRet);
		}
		return toRet.iterator();
		// TODO Auto-generated method stub
	}
	private void preOrderPos(Tnodo<E> p, PositionList<Position<E>> list) {
		list.addLast(p);
		for (Tnodo<E> child : p.getListahijos()) {
			preOrderPos(child,list);
		}
	}
	@Override
	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> toRet=new lista<>();
		if(!isEmpty()) {
			preOrderPos(Raiz,toRet);
		}
		return toRet;
	}

	@Override
	public E replace(Position<E> v, E e) throws InvalidPositionException{
		if(v==null)throw new InvalidPositionException("posicion nula");
		Tnodo<E> nodo= CheckPositions(v);
		E toRet= nodo.element();
		nodo.setElem(e);
		// TODO Auto-generated method stub
		return toRet;
	}

	@Override
	public Position<E> root()throws EmptyTreeException {
		if(Raiz==null)throw new EmptyTreeException("raiz nula");
		return Raiz;
	}

	@Override
	public Position<E> parent(Position<E> v)throws InvalidPositionException,BoundaryViolationException {
		Tnodo<E> nodo=CheckPositions(v);
		if(nodo==Raiz)throw new BoundaryViolationException("no tiene padres la Raiz");
		return nodo.getPadre();
	}

	@Override
	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException {
		Tnodo<E> nodo=CheckPositions(v);
		PositionList<Position<E>> toRet=new lista<>();
		
		for(Tnodo<E> hijo: nodo.getListahijos()) {
			toRet.addLast(hijo);
		}
		return toRet;
	}

	@Override
	public boolean isInternal(Position<E> v) throws InvalidPositionException {
		Tnodo<E> nodo = CheckPositions(v);
		return !nodo.getListahijos().isEmpty();
	}

	@Override
	public boolean isExternal(Position<E> v) throws InvalidPositionException {
		Tnodo<E> nodo = CheckPositions(v);
		return nodo.getListahijos().isEmpty();
	}

	@Override
	public boolean isRoot(Position<E> v) throws InvalidPositionException {
		Tnodo<E> nodo = CheckPositions(v);
		return nodo.getPadre() == null;
	}

	@Override
	public void createRoot(E e) throws InvalidOperationException {
		if (Raiz != null) {
			throw new InvalidOperationException("El arbol ya tiene raiz!");
		}
		Raiz = new Tnodo<>(null,e);
		cantElem++;
	}
	@Override
	public Position<E> addFirstChild(Position<E> p, E e) throws InvalidPositionException{
		Tnodo<E> nodo = CheckPositions(p);
		Tnodo<E> nodonuevo= new Tnodo<>(nodo,e);
		nodo.getListahijos().addFirst(nodonuevo);
		cantElem++;
		return nodonuevo;
	}

	@Override
	public Position<E> addLastChild(Position<E> p, E e) throws InvalidPositionException{
		Tnodo<E> nodo = CheckPositions(p);
		Tnodo<E> nodonuevo= new Tnodo<>(nodo,e);
		nodo.getListahijos().addLast(nodonuevo);
		cantElem++;
		return nodonuevo;
	}

	@Override
	public Position<E> addBefore(Position<E> p, Position<E> rb, E e) {
		Tnodo<E> nodo = CheckPositions(p);
		Tnodo<E> nodeRB = CheckPositions(rb);
		if (nodeRB.getPadre() != nodo) {
			throw new InvalidPositionException("El hermano derecho no pertenece al padre");
		}
		Tnodo<E> nodonuevo = new Tnodo<>(nodo,e);
		PositionList<Tnodo<E>> listahijos = nodo.getListahijos();
		Iterable<Position<Tnodo<E>>> positions = listahijos.positions();
		Iterator<Position<Tnodo<E>>> it = positions.iterator();
		boolean added = false;
		
		while(it.hasNext()&& !added) {
			Position<Tnodo<E>> pos = it.next();
			if(pos.element()==nodeRB) {
				added=true;
				listahijos.addBefore(pos, nodonuevo);
				cantElem++;
			}
		}
		return nodonuevo;
	}


	public Position<E> addAfter(Position<E> p, Position<E> lb, E e) throws InvalidPositionException {
		Tnodo<E> node = CheckPositions(p);
		Tnodo<E> nodeLB = CheckPositions(lb);
		if (nodeLB.getPadre() != node) {
			throw new InvalidPositionException("El hermano izquierdo no pertenece al padre");
		}
		Tnodo<E> newNode = new Tnodo<>(node,e);
		PositionList<Tnodo<E>> childrens = node.getListahijos();
		Iterable<Position<Tnodo<E>>> positions = childrens.positions();
		Iterator<Position<Tnodo<E>>> it = positions.iterator();
		boolean added = false;
		while (it.hasNext() && !added) {
			Position<Tnodo<E>> pos = it.next();
			if (pos.element() == nodeLB) {
				added = true;
				childrens.addAfter(pos, newNode);
				cantElem++;
			}
		}
		return newNode;
	}

	public void removeExternalNode(Position<E> p) throws InvalidPositionException {
		Tnodo<E> node = CheckPositions(p);
		if (!isExternal(node)) {
			throw new InvalidPositionException("El nodo no es externo");
		}
		if (node == Raiz) {
			Raiz = null;
			cantElem = 0;
		} else {
			PositionList<Tnodo<E>> childrens = node.getPadre().getListahijos();
			Iterable<Position<Tnodo<E>>> positions = childrens.positions();
			Iterator<Position<Tnodo<E>>> it = positions.iterator();
			boolean removed = false;
			while (it.hasNext() && !removed) {
				Position<Tnodo<E>> pos = it.next();
				if (pos.element() == node) {
					removed = true;
					childrens.remove(pos);
					node.setElem(null);
					node.setPadre(null);
					cantElem--;
				}
			}
			if (!removed) {
				throw new InvalidPositionException("La posicion no pertenece al arbol");
			}
		}
	}

	public void removeInternalNode(Position<E> p) throws InvalidPositionException {
		Tnodo<E> node = CheckPositions(p);
		if (!isInternal(node)) {
			throw new InvalidPositionException("El nodo no es interno");
		}
		if (node == Raiz) {
			if (node.getListahijos().size() > 1) {
				throw new InvalidPositionException("La posicion a elminiar es la raiz y tiene mas de un hijo");
			}
			try {
				Position<Tnodo<E>> posChild = node.getListahijos().first();
				Raiz = posChild.element();
				node.setElem(null);
				node.getListahijos().remove(posChild);
				Raiz.setPadre(null);
				cantElem--;
			} catch (EmptyListException e) {e.printStackTrace();}
		} else {
			Tnodo<E> father = node.getPadre();
			PositionList<Tnodo<E>> childrensF = father.getListahijos();
			Iterator<Position<Tnodo<E>>> it = childrensF.positions().iterator();
			boolean found = false;
			Position<Tnodo<E>> pos = null;
			while (it.hasNext() && !found) {
				pos = it.next();
				if (pos.element() == node) {
					found = true;
				}
			}
			if (!found) {
				throw new InvalidPositionException("El nodo no figura como hijo de su padre");
			}
			PositionList<Tnodo<E>> childrens = node.getListahijos();
			it = childrens.positions().iterator();
			while (it.hasNext()) {
				Tnodo<E> posC = it.next().element();
				posC.setPadre(father);
				childrensF.addBefore(pos, posC);
			}
			childrensF.remove(pos);
			node.setElem(null);
			node.setPadre(null);
			node.setHijo(null);
			cantElem--;
		}
	}

	public void removeNode(Position<E> p) throws InvalidPositionException {
		if (isInternal(p)) {
			removeInternalNode(p);
		} else {
			removeExternalNode(p);
		}
	}
	
	public void porNivelConLineas() {
	    if (Raiz == null) return;

	    PositionList<Tnodo<E>> cola = new lista<>();
	    cola.addLast(Raiz);

	    while (!cola.isEmpty()) {
	        int nivel = 0;
	        // contamos cuántos nodos hay actualmente en la cola (nivel vigente)
	        for (Tnodo<E> ignore : cola) nivel++;

	        // procesamos exactamente 'nivel' nodos
	        for (int i = 0; i < nivel; i++) {
	            Position<Tnodo<E>> p = cola.first();
	            Tnodo<E> nodo = p.element();
	            cola.remove(p);

	            System.out.print(nodo.element() + " ");

	            for (Tnodo<E> h : nodo.getListahijos()) {
	                cola.addLast(h);
	            }
	        }
	        System.out.println(); // fin de nivel
	    }
	}

	public void eliminarUltimoHijo(Position<E> p)throws  InvalidOperationException,InvalidPositionException{
		Tnodo<E> nodo=CheckPositions(p);
		if(nodo==Raiz)throw new InvalidOperationException("nodo igual a raiz");
		if(nodo==null)throw new InvalidPositionException("nodo igual a nulo");
		Tnodo<E> padre=nodo.getPadre();
		PositionList<Tnodo<E>> list=padre.getListahijos();
		PositionList<Tnodo<E>> listahijosnodo=nodo.getListahijos();
		if(list.last()==p) {
			list.remove(list.last());
			if (!listahijosnodo.isEmpty()) {
				for(Tnodo<E> e:listahijosnodo) {
					e.setPadre(padre);
				}
			nodo.setPadre(null);
			}
			
		}	
	}
	public Iterator<Position<E>> covertirAHoja(Position<E> p)throws InvalidPositionException{
        try {
            PositionList<Position<E>> l =new lista();
            Tnodo<E> tnod = CheckPositions(p);
            if(!tnod.getListahijos().isEmpty()) {
                posOr(tnod,l);
            }
            return l.iterator();
        }catch(InvalidPositionException a ) {
            System.out.print("Posicion Invalida");
            return null;
        }

    }
    private void posOr(Tnodo<E> a, PositionList<Position<E>> l ){
            Iterator<Position<Tnodo<E>>> b = a.getListahijos().positions().iterator();
            while(b.hasNext()) {
                Position<Tnodo<E>> rem = b.next();
                posOr(rem.element(),l);
                l.addLast(rem.element());

                a.getListahijos().remove(rem);
                a.setPadre(null);
                cantElem--;
            }
    }

public int podarSubarbol(Position<E> p) throws InvalidPositionException{
	Tnodo<E> nodo=CheckPositions(p);
	return podarposor(nodo);
}

private int podarposor(Tnodo<E> nodo){
	int contador=0;
	if(nodo!=null){
		while(!nodo.getListahijos().isEmpty()){
			Position<Tnodo<E>> hijo=nodo.getListahijos().first();
			contador+=podarposor(hijo.element());
			nodo.getListahijos().remove(hijo);
			contador++;
			cantElem--;
		}
	}
	return contador;
}



}
	
	
	

