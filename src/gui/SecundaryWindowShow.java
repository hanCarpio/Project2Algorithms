/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import business.ArbolBusiness;
import java.awt.Font;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author hanse
 */
public class SecundaryWindowShow extends JDialog{

    private String text;
    private JTextArea txtArea;
    private JScrollPane jscrollPane;
    private ArbolBusiness business;
    

    public SecundaryWindowShow(String text) {
        this.setTitle("Back to text");
        this.setSize(716, 537);
        this.setLocation(250, 60);
        this.setLayout(null);
        this.text = text;
        startComponents();
    }//constructor

    private void startComponents() {

        this.business = new ArbolBusiness(text);
        this.txtArea = new JTextArea();
        this.txtArea.setEditable(false);
        this.txtArea.setFont(new Font("Monospace", Font.PLAIN, 14));
        this.jscrollPane = new JScrollPane();
        this.jscrollPane.setViewportView(this.txtArea);
        this.jscrollPane.setBounds(1, 0, 700, 500);
        this.txtArea.setLineWrap(true);
        this.txtArea.setWrapStyleWord(true);

        this.add(this.jscrollPane);

        convertText();

    }//startComponents

    public void convertText() {
        try {
            String n = this.business.RegresarAlTexto();

            this.txtArea.setText(n);
        } catch (IOException ex) {
            Logger.getLogger(SecundaryWindowShow.class.getName()).log(Level.SEVERE, null, ex);
        }//try-catch

    }//convertText

}//class
