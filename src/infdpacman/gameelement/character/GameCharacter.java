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
    //static boolean firstMove = true;
    //Cell lastCell;
    
    public GameCharacter(ImageIcon i) {
        super(i);
    }
    public abstract Cell getCell();
    public abstract void setCell(Cell cell);
    
/*
    public static void setFirstMove(boolean firstMove) {
        GameCharacter.firstMove = firstMove;
    }*/
       
    public void move(Direction direction, GameCharacter character){
        Cell cell = character.getCell();
        //c = Board.getPacmanCell().getCellOfCharacter(character);
        if(cell.getNeighbors().containsKey(direction)){
            Cell nextCell = cell.getNeighbors().get(direction);
            if(!(nextCell instanceof Wall)){
                ((EmptyCell)cell).getInhoud().remove(this);
                ((EmptyCell)nextCell).getInhoud().add(this);
                character.setCell(nextCell);
                //lastCell = c1;
            }
        }
    }
}
