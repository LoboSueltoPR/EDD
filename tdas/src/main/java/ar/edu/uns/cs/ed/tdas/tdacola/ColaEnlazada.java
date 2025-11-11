package ar.edu.uns.cs.ed.tdas.tdacola;

import ar.edu.uns.cs.ed.tdas.excepciones.EmptyQueueException;

public class ColaEnlazada<E> implements Queue<E> {

    private static class Nodo<T> {
        T elem;
        Nodo<T> sig;
        Nodo(T e) { this.elem = e; }
    }

    private Nodo<E> frente;
    private Nodo<E> fondo;
    private int cantidad;

    public ColaEnlazada() {
        frente = null;
        fondo = null;
        cantidad = 0;
    }

    @Override
    public int size() {
        return cantidad;
    }

    @Override
    public boolean isEmpty() {
        return cantidad == 0;
    }

    @Override
    public E front() {
        if (isEmpty()) {
            throw new EmptyQueueException("cola vacia");
        }
        return frente.elem;
    }

    @Override
    public void enqueue(E element) {
        Nodo<E> nuevo = new Nodo<>(element);
        if (isEmpty()) {
            frente = nuevo;
            fondo = nuevo;
        } else {
            fondo.sig = nuevo;
            fondo = nuevo;
        }
        cantidad++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new EmptyQueueException("cola vacia");
        }
        E toRet = frente.elem;
        frente = frente.sig;
        if (frente == null) {
            fondo = null;
        }
        cantidad--;
        return toRet;
    }
}

