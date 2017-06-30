/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import domain.NodosAVL;
import domain.RegresarTexto;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

/**
 *
 * @author hanse
 */
public class ArbolData {

    private String nombreFila;

    public ArbolData(String nombreFila) {
        this.nombreFila = nombreFila;
    }//constructor

    public void guardarArbol(NodosAVL nodoAvl) throws FileNotFoundException {

        File file = new File(this.nombreFila);
        FileOutputStream fileOutputStream = new FileOutputStream(file, true);
        PrintStream printStream = new PrintStream(fileOutputStream);
        printStream.println(nodoAvl.getCadena() + "_" + nodoAvl.getCantPosiciones());
       
    }//guardarArbol

    public String getArbol() throws FileNotFoundException, IOException {
        String resultado = "";
        File file = new File(this.nombreFila);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String linea;

        while ((linea = bufferedReader.readLine()) != null) {

            resultado += linea + "&";

        }//while
        resultado = resultado.substring(0, resultado.length() - 1);

        return resultado;
    }//getArbol

    public String regresarTexto() throws IOException {
        String splitBar = this.getArbol();
        String[] splitBars = splitBar.split("&");
        ArrayList<RegresarTexto> regresarTextos = new ArrayList<RegresarTexto>();

        for (int i = 0; i < splitBars.length; i++) {
            String[] temp = splitBars[i].split("_");
            String tempPalabra = temp[0], tempNumero = temp[1];
            String[] tempNumeros = tempNumero.split("-");

            for (int j = 0; j < tempNumeros.length; j++) {
                regresarTextos.add(new RegresarTexto(tempPalabra, Integer.parseInt(tempNumeros[j])));
            }//forTempNumero

        }//forSplitBar
        
        RegresarTexto[] resultadoFinal= new RegresarTexto[regresarTextos.size()];
        
        for (int i = 0; i < resultadoFinal.length; i++) {
           resultadoFinal[i]=regresarTextos.get(i);
        }//for
        
        return insercion(resultadoFinal);

    }//regresarTexto
    
    public String insercion(RegresarTexto[] regresarTxt) {
        String resultado = "";
        int x, j;
        RegresarTexto aux;
        for (x = 1; x < regresarTxt.length; x++) {
            aux = regresarTxt[x];
            j = x - 1;
            while ((j >= 0) && (aux.getPosicion() < regresarTxt[j].getPosicion())) {

                regresarTxt[j + 1] = regresarTxt[j];
                j--;
            }
            regresarTxt[j + 1] = aux;
        }//for i

        for (int i = 0; i < regresarTxt.length; i++) {
            resultado += regresarTxt[i].getNombre() + " ";
        }//forConcatena

        return resultado;

    }//insercion

}//class
