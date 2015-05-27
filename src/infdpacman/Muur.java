package infdpacman;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author CVD
 */
public class Muur {
    public void draw(Graphics g) {
        ImageIcon i = new ImageIcon("Plaatjes/wall.png");
        Image img = i.getImage();
        g.drawImage(img, 20,20,null);    
    }
}
