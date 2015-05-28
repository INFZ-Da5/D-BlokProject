package infdpacman.item;

import java.awt.Graphics;

/**
 *
 * @author Lenovo
 */
public interface Item {
    int punten = 0;
    public abstract void draw(Graphics g, int width, int height);
}
