package infdpacman.poppetje;

import infdpacman.Direction;
import java.awt.Graphics;

/**
 *
 * @author Lenovo
 */
public abstract class Poppetje {    
    public abstract void bewegen(Direction direction);

    public abstract void draw(Graphics g);
}
