package infdpacman.poppetje;

import infdpacman.Direction;
import infdpacman.Vakje;
import java.awt.Graphics;

/**
 *
 * @author Lenovo
 */
public abstract class Poppetje {    
    public abstract void bewegen(Vakje directionVakje, Direction direction);

    public abstract void draw(Graphics g, int width, int height);
}
