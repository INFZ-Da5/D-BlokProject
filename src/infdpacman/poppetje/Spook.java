package infdpacman.poppetje;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Lenovo
 */
public class Spook extends Poppetje {

    @Override
    public void bewegen() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void draw(Graphics g) {
        ImageIcon i = new ImageIcon("Plaatjes/ghost.png");
        Image img = i.getImage();
        g.drawImage(img, 20,20,null);    
    }
    
}
