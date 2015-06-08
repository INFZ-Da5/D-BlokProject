package infdpacman.gameelement.item;

import infdpacman.gameelement.GameElement;
import javax.swing.ImageIcon;

/**
 *
 * @author Lenovo
 */
public abstract class Item extends GameElement{
    public int points;
            
    public Item(ImageIcon i, int points){
        super(i);
        this.points = points;
    }                        
}
