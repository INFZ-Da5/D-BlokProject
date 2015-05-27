package infdpacman.poppetje;

import infdpacman.Direction;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author Lenovo
 */
public abstract class Poppetje {
    Image image;
    
    public abstract void bewegen(Direction direction);
    
    public abstract void draw(Graphics g);
}
