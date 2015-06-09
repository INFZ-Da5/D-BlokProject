package infdpacman.gameelement.character;

import infdpacman.cell.Cell;
import infdpacman.cell.EmptyCell;
import infdpacman.cell.Wall;
import infdpacman.enums.Direction;
import infdpacman.gameelement.GameElement;
import infdpacman.view.Board;
import javax.swing.ImageIcon;

/**
 *
 * @author Lenovo
 */

public abstract class Character extends GameElement{    
    static boolean firstMove = true;
    Cell lastCell;
    
    public Character(ImageIcon i) {
        super(i);
    }
    
    public void move(Direction direction, Character character){
        //moet niet alleen pacman worden
        Cell c;
        if(firstMove){
            c = Board.getPacmanCell().getCellOfCharacter(character);
        }
        else{
            c = lastCell.getCellOfCharacter(character);            
        }
        if(c.getNeighbors().containsKey(direction)){
            Cell c1 = c.getNeighbors().get(direction);
            if(!(c1 instanceof Wall)){
                ((EmptyCell)c).getInhoud().remove(this);
                ((EmptyCell)c1).getInhoud().add(this);
                firstMove = false;
                lastCell = c1;
            }
        }
    }
}
