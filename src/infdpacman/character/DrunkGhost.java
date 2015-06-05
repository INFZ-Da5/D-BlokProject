package infdpacman.character;

import infdpacman.Direction;
import infdpacman.EmptyCell;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author CVD
 */
public class DrunkGhost extends Character {
    ImageIcon normalGhost = new ImageIcon("Plaatjes/drunkghost.png");
    ImageIcon fleeGhost = new ImageIcon("Plaatjes/fleeghost.png");    
    ImageIcon currentImage = normalGhost;

    public DrunkGhost() {
        super(new ImageIcon("Plaatjes/drunkghost.png"));
    }
    
    @Override
    public void bewegen(EmptyCell directionVakje, Direction direction) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
