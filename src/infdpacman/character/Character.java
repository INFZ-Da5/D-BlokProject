package infdpacman.character;

import infdpacman.Direction;
import infdpacman.GameElement;
import infdpacman.EmptyCell;
import java.awt.Graphics;
import javax.swing.ImageIcon;

/**
 *
 * @author Lenovo
 */
public abstract class Character extends GameElement {    

    public Character(ImageIcon i) {
        super(i);
    }
    public abstract void bewegen(EmptyCell directionVakje, Direction direction);

    
}
