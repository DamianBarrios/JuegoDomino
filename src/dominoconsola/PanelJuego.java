package dominoconsola;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Dami
 */
public class PanelJuego extends JPanel{
    public PanelJuego(){
        this.setSize(800,600);
    }
    
    @Override
    public void paintComponent(Graphics g){
        Dimension tamanio = getSize();
        ImageIcon imagenFondo = new ImageIcon(getClass().getResource("./fverde.jpg"));
        g.drawImage(imagenFondo.getImage(), 0 , 0 , tamanio.width, tamanio.height, null);
        setOpaque(false);
        super.paintComponent(g);
    }
}
