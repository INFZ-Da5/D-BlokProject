package infdpacman.character;

import infdpacman.Direction;
import infdpacman.EmptyCell;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Lenovo
 */
public class Ghost extends Character {
    ImageIcon normalGhost = new ImageIcon("Plaatjes/ghost.png");
    ImageIcon fleeGhost = new ImageIcon("Plaatjes/fleeghost.png");    
    ImageIcon currentImage = normalGhost;

    public Ghost() {
        super(new ImageIcon("Plaatjes/ghost.png"));
    }
    
    @Override
    public void move(EmptyCell directionVakje, Direction direction) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
