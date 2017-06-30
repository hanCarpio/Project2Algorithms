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
public class RegresarTexto {
    
    private String nombre;
    private int posicion;

    public RegresarTexto(String nombre, int posicion) {
        this.nombre = nombre;
        this.posicion = posicion;
    }//constructor

    public RegresarTexto() {
        this.nombre="";
        this.posicion=0;
    }//default

    /**
     * @return the name
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the name to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the position
     */
    public int getPosicion() {
        return posicion;
    }

    /**
     * @param posicion the position to set
     */
    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }
    

    
}//class
