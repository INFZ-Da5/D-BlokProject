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

public abstract class GameCharacter extends GameElement{    
    static boolean firstMove = true;
    Cell lastCell;
    
    public GameCharacter(ImageIcon i) {
        super(i);
    }

    public static void setFirstMove(boolean firstMove) {
        GameCharacter.firstMove = firstMove;
    }
       
    public void move(Direction direction, GameCharacter character){
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
