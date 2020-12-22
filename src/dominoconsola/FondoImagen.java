/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominoconsola;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Dami
 */
public class FondoImagen extends JPanel{
    public FondoImagen(){
        this.setSize(100,10);
    }
    
    @Override
    public void paintComponent(Graphics g){
        Dimension tamanio = getSize();
        ImageIcon imagenFondo = new ImageIcon(getClass().getResource("./fondoInicio.jpeg"));
        g.drawImage(imagenFondo.getImage(), 0 , 0 , tamanio.width, tamanio.height, null);
        setOpaque(false);
        super.paintComponent(g);
    }
}


