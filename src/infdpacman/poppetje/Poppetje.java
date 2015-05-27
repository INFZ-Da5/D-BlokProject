package infdpacman.poppetje;

import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author Lenovo
 */
public abstract class Poppetje {
    Image image;
    
    public abstract void bewegen();
    
    public abstract void draw(Graphics g);
}
