 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import business.ArbolBusiness;
import java.io.FileNotFoundException;

/**
 *
 * @author hanse
 */
public class ArbolAVL {

    public NodosAVL raiz;
    private int posicion;

    public ArbolAVL() {
        this.raiz = null;
        this.posicion = 0;

    }//constructor

    
    public int getFactorDeBalance(NodosAVL x) {
        if (x == null) {
            return -1;
        } else {
            return x.balance;
        }
    }//getFactorDeBalance

    //****************** Rotaciones simples*******************************************
    public NodosAVL rotacionIzquierda(NodosAVL c) {
        NodosAVL aux = c.hijoDerecho;
        c.hijoDerecho = aux.hijoIzquierdo;
        aux.hijoIzquierdo = c;
        c.balance = Math.max(getFactorDeBalance(c.hijoIzquierdo), getFactorDeBalance(c.hijoDerecho)) + 1;
        aux.balance = Math.max(getFactorDeBalance(aux.hijoIzquierdo), getFactorDeBalance(aux.hijoDerecho));

        return aux;
    }// rotacionIzquierda

    public NodosAVL rotacionDerecha(NodosAVL c) {
        NodosAVL aux = c.hijoIzquierdo;
        c.hijoIzquierdo = aux.hijoDerecho;
        aux.hijoDerecho = c;

        c.balance = Math.max(getFactorDeBalance(c.hijoIzquierdo), getFactorDeBalance(c.hijoDerecho));
        aux.balance = Math.max(getFactorDeBalance(aux.hijoIzquierdo), getFactorDeBalance(aux.hijoDerecho));

        return aux;
    }//rotacionDerecha

    //********************************rotaciones dobles**************************************
    public NodosAVL rotacion_Izq_Der(NodosAVL c) {
        NodosAVL temp;
        c.hijoIzquierdo = rotacionIzquierda(c.hijoIzquierdo);
        temp = rotacionDerecha(c);

        return temp;

    }// rotacion_Izq_Der

    public NodosAVL rotacion_Der_Izq(NodosAVL c) {
        NodosAVL temp;
        c.hijoDerecho = rotacionDerecha(c.hijoDerecho);
        temp = rotacionIzquierda(c);

        return temp;

    }//rotacion_Der_Izq

    //***************************************metodo insercion*****************************************
    public NodosAVL insertarAVL(NodosAVL nuevo, NodosAVL subArbol) {
        NodosAVL newParent = subArbol;

        if (nuevo.getCadena().compareTo(subArbol.getCadena()) < 0) {
            if (subArbol.hijoIzquierdo == null) {
                subArbol.hijoIzquierdo = nuevo;
                subArbol.hijoIzquierdo.setCantPosiciones(subArbol.hijoIzquierdo.getCantPosiciones() + this.posicion + "-");
                this.posicion++;
            } else {
                subArbol.hijoIzquierdo = insertarAVL(nuevo, subArbol.hijoIzquierdo);

                if ((getFactorDeBalance(subArbol.hijoIzquierdo) - getFactorDeBalance(subArbol.hijoDerecho) == 2)) {
                    if (nuevo.getCadena().compareTo(subArbol.hijoIzquierdo.getCadena()) < 0) {
                        newParent = rotacionDerecha(subArbol);
                    } else {
                        newParent = rotacion_Izq_Der(subArbol);
                    }
                }
            }
        } else if (nuevo.getCadena().compareTo(subArbol.getCadena()) > 0) {
            if (subArbol.hijoDerecho == null) {
                subArbol.hijoDerecho = nuevo;
                subArbol.hijoDerecho.setCantPosiciones(subArbol.hijoDerecho.getCantPosiciones() + this.posicion + "-");
                this.posicion++;
            } else {
                subArbol.hijoDerecho = insertarAVL(nuevo, subArbol.hijoDerecho);

                if ((getFactorDeBalance(subArbol.hijoIzquierdo) - getFactorDeBalance(subArbol.hijoDerecho) == -2)) {
                    if (nuevo.getCadena().compareTo(subArbol.hijoDerecho.getCadena()) > 0) {
                        newParent = rotacionIzquierda(subArbol);
                    } else {
                        newParent = rotacion_Der_Izq(subArbol);
                    }
                }
            }
        } else {
            subArbol.setCantPosiciones(subArbol.getCantPosiciones() + this.posicion + "-");
            this.posicion++;
        }
        //actualizar factor de balance
        if ((subArbol.hijoIzquierdo == null) && (subArbol.hijoDerecho != null)) {
            subArbol.balance = subArbol.hijoDerecho.balance + 1;
        } else if ((subArbol.hijoDerecho == null) && (subArbol.hijoIzquierdo != null)) {
            subArbol.balance = subArbol.hijoIzquierdo.balance + 1;
        } else {
            subArbol.balance = Math.max(getFactorDeBalance(subArbol.hijoIzquierdo), getFactorDeBalance(subArbol.hijoDerecho)) + 1;
        }

        return newParent;
    }

    public void insert(String s) {
        NodosAVL nuevoNodoAvl = new NodosAVL(s);
        if (this.raiz == null) {
            this.raiz = nuevoNodoAvl;
            this.raiz.setCantPosiciones(this.raiz.getCantPosiciones() + this.posicion + "-");
            this.posicion++;
        } else {
            this.raiz = insertarAVL(nuevoNodoAvl, raiz);

        }
    }//insertarAVL

    public void guardarNodos(NodosAVL nod, String ruta) throws FileNotFoundException {
        ArbolBusiness treeBusiness = new ArbolBusiness(ruta);
        if (nod != null) {
            treeBusiness.guardarArbol(nod);
            guardarNodos(nod.hijoIzquierdo, ruta);
            guardarNodos(nod.hijoDerecho, ruta);
        }
    }//preOrden

    public NodosAVL buscar(NodosAVL node, String cadena) {
        while (node != null) {
            if (node.getCadena().equals(cadena)) {
                return node;
            } else if (node.getCadena().compareTo(cadena) > 0) {
                node = node.hijoIzquierdo;
            } else {
                node = node.hijoDerecho;
            }
        }//while

        return null;

    }//buscar

}//class
