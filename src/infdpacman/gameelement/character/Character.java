package infdpacman.gameelement.character;

import infdpacman.enums.Direction;
import infdpacman.gameelement.GameElement;
import javax.swing.ImageIcon;

/**
 *
 * @author Lenovo
 */
public abstract class Character extends GameElement {    

    public Character(ImageIcon i) {
        super(i);
    }
    
    public void move(Direction direction){
        
    }
}
