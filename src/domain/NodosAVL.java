/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author hanse
 */
public class NodosAVL {
    
    int balance;
    private String cadena;
    private String cantPosiciones;
    public NodosAVL hijoIzquierdo;
    public NodosAVL hijoDerecho;

    public NodosAVL(String chain) {
        this.balance=0;
        this.hijoIzquierdo= null;
        this.hijoDerecho=null;
        this.cadena=chain;
        this.cantPosiciones="";
    }//constructor

    /**
     * @return the chain
     */
    public String getCadena() {
        return cadena;
    }

    /**
     * @param cadena the chain to set
     */
    public void setCadena(String cadena) {
        this.cadena = cadena;
    }

    /**
     * @return the quantityPositions
     */
    public String getCantPosiciones() {
        return cantPosiciones;
    }

    /**
     * @param cantPosiciones the quantityPositions to set
     */
    public void setCantPosiciones(String cantPosiciones) {
        this.cantPosiciones = cantPosiciones;
    }

}//class
