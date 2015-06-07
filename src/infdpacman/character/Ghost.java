package infdpacman.character;

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
    
}
