/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import domain.NodosAVL;
import domain.ArbolAVL;
import data.ArbolData;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author hanse
 */
public class ArbolBusiness {

    private ArbolData arbolData;

    public ArbolBusiness(String nombreFila) {
        this.arbolData = new ArbolData(nombreFila);
    }//constructor

    public void guardarArbol(NodosAVL nodoAvl) throws FileNotFoundException {
        this.arbolData.guardarArbol(nodoAvl);
    }//saveArbol

    public String getArbol() throws FileNotFoundException, IOException {
        
        return this.arbolData.getArbol();
        
    }//getArbol
    
      public String RegresarAlTexto() throws IOException {
          return this.arbolData.regresarTexto();
      }

}//class
