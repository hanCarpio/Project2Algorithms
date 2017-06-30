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
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author hanse
 */
public class JPanelPaint extends JPanel {

    private ArbolAVL myTree;
    private NodosAVL myNode;
    private Graphics2D graphics2D;
    private BufferedImage bufferedImage;
    private int x;
    private int y;
    private boolean next;

    public JPanelPaint(ArbolAVL myTree) {
        super();
        this.myTree = myTree;
        this.myNode = this.myTree.raiz;
        try {
            bufferedImage = ImageIO.read(getClass().getResourceAsStream("/assets/rec.png"));
        } catch (IOException ex) {
            Logger.getLogger(JPanelPaint.class.getName()).log(Level.SEVERE, null, ex);
        }
        graphics2D = (Graphics2D) this.bufferedImage.getGraphics();
        this.next = true;
    }//constructor

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(bufferedImage, 0, 0, 3000, 3000, null);
        paintTree(g, myNode, 1500, 20, 110, 40, 0);
    }//paintComponent

    private void paintTree(Graphics g, NodosAVL node, int x, int y, int xoff, int yoff, int nivel) {
        if (node == null) {
            return;
        }
        g.setColor(Color.BLUE);

            if (node.hijoIzquierdo != null) {
                g.drawLine(x + 10, y + 20, x - xoff + (nivel * 2) - 30, (y + yoff) + 100);
            }
            if (node.hijoDerecho != null) {
                g.drawLine(x + 10, y + 20, x + xoff - (nivel * 2) + 30, (y + yoff) + 100);
            }

            g.fillOval(x - 50, y - 10, 130, 50);


        g.setColor(Color.BLACK);
        g.setFont(new Font("Bold", Font.BOLD, 11));
        g.drawString(node.getCadena() + " [" + node.getCantPosiciones() + "]", x - 25, y + 15);
        
        

        paintTree(g, node.hijoIzquierdo, (int) (x - xoff) - 30, ((y + yoff) + 100)-10, xoff + nivel * 2, yoff, nivel + 1);
        paintTree(g, node.hijoDerecho, (int) (x + xoff) + 30, (y + yoff) + 100, xoff - nivel * 2, yoff, nivel + 1);

    }//paintTree

    /**
     * @return the myTree
     */
    public ArbolAVL getMyTree() {
        return myTree;
    }

    
    public void setMyTree(ArbolAVL myTree) {
        this.myTree = myTree;
    }

}//class
