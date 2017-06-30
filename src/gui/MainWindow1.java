/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import business.ArbolBusiness;
import domain.ArbolAVL;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author hanse
 */
public class MainWindow1 extends JFrame implements ActionListener {

    private JScrollPane scroll;
    private JPanelPaint jPanelPaint;
    private ArbolAVL aVLtree;
    private JFileChooser chooser;
    private JMenuBar jmbMenu;
    private JMenu jmOpciones;
    private JMenuItem jmiAbrirArchivo, jmiGuardarArchivo, jmiRegresarTexto, jmiBuscarPalabra;

    public MainWindow1() {

        super("Arboles");
        this.setSize(1000, 700);
        this.setResizable(false);
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        init();

    }//construtor

    private void init() {
        aVLtree = new ArbolAVL();

        this.jmbMenu = new JMenuBar();
        this.setJMenuBar(this.jmbMenu);

        this.jmOpciones = new JMenu("Menu");
        this.jmbMenu.add(this.jmOpciones);

        this.jmiAbrirArchivo = new JMenuItem("Abrir Archivo");
        this.jmiAbrirArchivo.addActionListener(this);
        this.jmOpciones.add(this.jmiAbrirArchivo);

        this.jmiGuardarArchivo = new JMenuItem("Guardar Archivo de arbol");
        this.jmiGuardarArchivo.addActionListener(this);
        this.jmOpciones.add(this.jmiGuardarArchivo);
        this.jmiGuardarArchivo.setEnabled(false);

        this.jmiRegresarTexto = new JMenuItem("Regresar al texto");
        this.jmiRegresarTexto.addActionListener(this);
        this.jmOpciones.add(this.jmiRegresarTexto);
        this.jmiRegresarTexto.setEnabled(false);

        this.jmiBuscarPalabra = new JMenuItem("Buscar Palabra");
        this.jmiBuscarPalabra.addActionListener(this);
        this.jmOpciones.add(this.jmiBuscarPalabra);
        this.jmiBuscarPalabra.setEnabled(false);

    }//init

    public void paint() {

        this.jPanelPaint = new JPanelPaint(this.aVLtree);
        this.scroll = new JScrollPane();
        this.scroll.setBounds(new Rectangle(5, 22, 984, 625));
        this.scroll.setViewportView(this.jPanelPaint);
        this.scroll.getViewport().setView(this.jPanelPaint);
        this.jPanelPaint.setPreferredSize(new Dimension(3000, 3000));
        this.jPanelPaint.repaint();
        this.jPanelPaint.revalidate();
        SwingUtilities.updateComponentTreeUI(this);
        this.add(this.scroll);

    }//paint

    public void createAndPaintTree(String message) {

        String[] createArray = message.split("[ \n]");

        for (int i = 0; i < createArray.length; i++) {
            this.aVLtree.insert(createArray[i]);
        }//for i

    }//createAndPaintTree

    public String openFile() throws IOException {
        String information = "";
        chooser = new JFileChooser();
        String rute;
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            FileReader fileReader = null;
            try {
                rute = chooser.getSelectedFile().getAbsolutePath();
                File file = new File(rute);

                fileReader = new FileReader(file);

                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    information += line + "\n";
                }//while
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MainWindow1.class.getName()).log(Level.SEVERE, null, ex);
            }//try-catch
        } else {

        }

        return information;
    }//openFIle

    public String openRute() throws IOException {

        chooser = new JFileChooser();
        String rute = "";
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

            rute += chooser.getSelectedFile().getAbsolutePath();

        } else {

        }

        return rute;
    }//openFIle

    private void saveFileChooser() {
        String rute;
        chooser = new JFileChooser();
        FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("txt", "TXT");
        chooser.setFileFilter(extensionFilter);
        try {
            if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                rute = chooser.getSelectedFile().getAbsolutePath();
                ArbolBusiness treeBusiness = new ArbolBusiness(rute);
                aVLtree.guardarNodos(aVLtree.raiz, rute);
            }//if
        } catch (Exception ex) {
        }//try-catch

    }//saveFileChooser

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.jmiAbrirArchivo) {

            try {
                createAndPaintTree(openFile());
                paint();
                repaint();
                revalidate();
            } catch (IOException ex) {
                Logger.getLogger(MainWindow1.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.jmiBuscarPalabra.setEnabled(true);
            this.jmiGuardarArchivo.setEnabled(true);
            this.jmiRegresarTexto.setEnabled(true);
        }//btnOPenFile

        if (e.getSource() == this.jmiGuardarArchivo) {
            saveFileChooser();
            JOptionPane.showMessageDialog(null, "Archivo guardado");
        }//btnSaveFile

        if (e.getSource() == this.jmiRegresarTexto) {
            try {

                SecundaryWindowShow secundaryWindowShow = new SecundaryWindowShow(openRute());
                secundaryWindowShow.setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(MainWindow1.class.getName()).log(Level.SEVERE, null, ex);
            }//try-catch

        }//jbtnBackToText

        if (e.getSource() == this.jmiBuscarPalabra) {
            JDialogSearchNodes jDialogSearchNodes = new JDialogSearchNodes(this, true, aVLtree);
            jDialogSearchNodes.setVisible(true);
        }//jbtnFind

    }//action

}//Class
