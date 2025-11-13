package ar.edu.uns.cs.ed.tdas.tdaarbol;
import ar.edu.uns.cs.ed.tdas.*;
import ar.edu.uns.cs.ed.tdas.tdamapeo.*;

public class ej {
    
public Map<Character,Integer> ContarVocales(Tree<Character> arbol) {
    Map<Character,Integer> mapavocales=new MapeoConHash<>();
    posorden(arbol.root(),mapavocales,arbol);


    return mapavocales;
}

private void posorden(Position<Character> nodo, Map<Character,Integer> mapavocales,Tree<Character> arbol) {
    if(nodo!=null){
        if(arbol.isInternal(nodo)){
    
            for (Position<Character> hijo : arbol.children(nodo)) {
                posorden(hijo,mapavocales,arbol);
            }
            if(nodo.element()=='a' || nodo.element()=='e' || nodo.element()=='i' || nodo.element()=='o' || nodo.element()=='u'){
                Integer contador = mapavocales.get(nodo.element());
                if(contador==null){
                    mapavocales.put(nodo.element(), 1);        
            }
            else{
                mapavocales.put(nodo.element(), contador+1);
            }
            
        }
    }    


}
}

