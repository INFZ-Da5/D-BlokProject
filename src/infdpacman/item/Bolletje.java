package infdpacman.item;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Lenovo
 */
public class Bolletje implements Item{
    
    @Override
    public void draw(Graphics g, int width, int height) {
        ImageIcon i = new ImageIcon("Plaatjes/bolletje.png");
        Image img = i.getImage();
        g.drawImage(img, 0, 0, width, height, null);    
    }
}
