package infdpacman;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author CVD
 */
public class Muur {
    public void draw(Graphics g, int width, int height) {
        ImageIcon i = new ImageIcon("Plaatjes/wall.png");
        Image img = i.getImage();
        g.drawImage(img, 0,0, width,height, null);    
    }
}
