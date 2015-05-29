package infdpacman.poppetje;

import infdpacman.Direction;
import infdpacman.Vakje;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author CVD
 */
public class DrunkGhost extends Poppetje {
    ImageIcon normalGhost = new ImageIcon("Plaatjes/drunkghost.png");
    ImageIcon fleeGhost = new ImageIcon("Plaatjes/fleeghost.png");    
    ImageIcon currentImage = normalGhost;
    
    @Override
    public void bewegen(Vakje directionVakje, Direction direction) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void draw(Graphics g,int width, int height) {
        ImageIcon i = currentImage;
        Image img = i.getImage();

        img.getScaledInstance(width,height,Image.SCALE_SMOOTH);
        g.drawImage(img, 0,0, width,height, null);   
    }
}
