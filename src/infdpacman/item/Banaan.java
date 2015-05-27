package infdpacman.item;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Lenovo
 */
public class Banaan implements Item {
    @Override
    public void draw(Graphics g) {
        ImageIcon i = new ImageIcon("Plaatjes/banana.png");
        Image img = i.getImage();
        g.drawImage(img, 20,20,null);    
    }
}
