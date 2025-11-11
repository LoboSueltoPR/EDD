package ar.edu.uns.cs.ed.tdas.tdalista;
import java.util.Iterator;
import ar.edu.uns.cs.ed.tdas.*;
public class ej<E> {
	public PositionList<E> listaintercalada(PositionList<E> l1,PositionList<E> l2){
		PositionList<E> toRet=new lista();
		Iterator<E> it1=l1.iterator();
		Iterator<E> it2=l2.iterator();
		
		while(it1.hasNext()||it2.hasNext()) {
			if(it1.hasNext())toRet.addLast(it1.next());
			if(it2.hasNext())toRet.addLast(it2.next());
			
			
		}
		return toRet;	
	}
	
	public Iterable<E> Eliminarl2elemsl1(PositionList<E>l1,PositionList<E>l2){
		PositionList<E> toRet = new lista();
		Iterator<Position<E>> it1=l1.positions().iterator();
		Iterator<Position<E>> it2=l2.positions().iterator();
		
		
		
		return toRet;
		
	}
	
}
