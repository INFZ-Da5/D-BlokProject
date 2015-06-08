package infdpacman.gameelement.character;

import infdpacman.enums.Direction;
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
}
