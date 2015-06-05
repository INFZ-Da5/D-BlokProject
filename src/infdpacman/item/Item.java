package infdpacman.item;

import java.awt.Graphics;

/**
 *
 * @author Lenovo
 */
public abstract class Item {
    public int points;
    public abstract void draw(Graphics g, int width, int height);
            
            
public Item(int points){

this.points = points;

}            
            
            
            
}
