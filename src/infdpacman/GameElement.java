package infdpacman;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author CVD
 */
public abstract class GameElement {
    ImageIcon i;
    public GameElement(ImageIcon i){
        i = this.i;
    }
    public void draw(Graphics g,int width, int height) {
        Image img = i.getImage();
        g.drawImage(img, 0,0, width,height, null);    
    }
}
