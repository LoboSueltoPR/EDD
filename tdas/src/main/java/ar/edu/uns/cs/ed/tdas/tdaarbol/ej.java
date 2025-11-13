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
public Map<Character, Integer> cantidadRepeticiones(Tree<Character> t){
    Map<Character, Integer> mapa=new MapeoConHash<>();
    contarreps(t.root(),mapa,t);
    return mapa;
	}

// private void contarreps(Tnodo<Character> nodo,Map<Character,Integer> mapa,Tree<Character> t){
//     Integer num=mapa.get(t.root().element());
//     if(num==null){
//         mapa.put(t.root().element(), 1);
//     }else{
//         mapa.put(t.root().element(),num+1);
//     }

//     if(!nodo.getListahijos().isEmpty()){
//         for(Tnodo<Character> hijos:nodo.getListahijos()){
//             contarreps(hijos,mapa,t);    
//         }
//     }
// }
private void contarreps(Position<Character> nodo,Map<Character,Integer> mapa,Tree<Character> t){
    Integer num=mapa.get(nodo.element());
    if(num==null){
        mapa.put(nodo.element(), 1);
    }else{
        mapa.put(nodo.element(),num+1);
    }

    if(t.isInternal(nodo)){
        for(Position<Character> hijos:t.children(nodo)){
            contarreps(hijos,mapa,t);    
        }
    }
}


