package infdpacman;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author CVD
 */
public class Wall extends Cell{
    public void draw(Graphics g) {
        ImageIcon i = new ImageIcon("Plaatjes/wall.png");
        Image img = i.getImage();
        g.drawImage(img, 0,0, this.getWidth(),this.getHeight(), null);    
    }
}
