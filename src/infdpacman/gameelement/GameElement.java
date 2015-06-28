package infdpacman.gameelement;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author CVD
 */
public abstract class GameElement {
    private final ImageIcon i;
    public GameElement(ImageIcon i){
        this.i = i;
    }
    public void draw(Graphics g,int width, int height) {
        Image img = i.getImage();
        g.drawImage(img, width/2,height/2, width,height, null);    
    }
}
