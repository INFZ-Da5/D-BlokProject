package infdpacman.item;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Lenovo
 */
public class SuperBolletje extends Item {
    
    @Override
    public void draw(Graphics g, int width, int height) {
        ImageIcon i = new ImageIcon("Plaatjes/superbol.png");
        Image img = i.getImage();
        g.drawImage(img, 0, 0, width, height, null);    
    }
    
    public SuperBolletje(){ 
        super(50);
    }
    
}
