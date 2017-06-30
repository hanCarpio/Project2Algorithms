/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domain.NodosAVL;
import domain.ArbolAVL;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javafx.scene.shape.Line;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author hanse
 */
public class JDialogSearchNodes extends JDialog implements ActionListener{
    
    private JLabel jlbBanner;
    private JScrollPane scroll;
    private JTextArea txtResult;
    private JTextField jtxtFind;
    private JButton jbtnFind;
    private ArbolAVL aVLtree;
    private String text;

    public JDialogSearchNodes(JFrame frame, boolean  b,ArbolAVL aVLtree) {
        super(frame, b);
        this.aVLtree = aVLtree;
        this.setTitle("Buscar Palabra");
        this.setLayout(null);
        this.setSize(500, 300);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        startComponents();
    }//constructor
    
    private void startComponents(){
        
        this.jlbBanner= new JLabel("Escriba la palabra: ");
        this.jlbBanner.setBounds(40, 20, 340, 30);
        this.jlbBanner.setFont(new Font("Bold", Font.BOLD, 16));
        
        this.txtResult= new JTextArea();
        this.txtResult.setBounds(new Rectangle(25, 120, 450, 130));
        this.txtResult.setEditable(false);
        this.txtResult.setLineWrap(true);
        this.txtResult.setWrapStyleWord(true);
        this.txtResult.setFont(new Font("Bold", Font.BOLD, 18));
        
        this.jtxtFind= new JTextField();
        this.jtxtFind.setBounds(320, 20, 120, 30);
        this.jtxtFind.setFont(new Font("Monospace", Font.PLAIN, 14));
        
        this.jbtnFind = new JButton("Buscar");
        this.jbtnFind.setBounds(320, 60, 120, 35);
        this.jbtnFind.addActionListener(this);

        
        this.add(this.jtxtFind);
        this.add(this.jlbBanner);
        this.add(this.txtResult);
        this.add(this.jbtnFind);
        
    }// startComponents
    

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == this.jbtnFind) {
            NodosAVL node = this.aVLtree.buscar(aVLtree.raiz, this.jtxtFind.getText());
            String node1 = "";
            node1 = node.getCantPosiciones();
            node1 = node1.substring(0, node1.length() - 1);
            if (node != null) {
                this.txtResult.setText("Node: " + node.getCadena() + " [" + node1 + "]");
            } else {
                this.txtResult.setText(" Not found!");
            }
            this.jtxtFind.setText("");

        }//this.jbtnFind
    
    }//action

    
}//class
