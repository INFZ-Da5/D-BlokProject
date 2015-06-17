package infdpacman.gameelement.character;

import infdpacman.cell.Cell;
import infdpacman.cell.EmptyCell;
import infdpacman.cell.Wall;
import infdpacman.enums.Direction;
import infdpacman.gameelement.GameElement;
import javax.swing.ImageIcon;

/**
 *
 * @author Lenovo
 */

public abstract class GameCharacter extends GameElement{    

    public GameCharacter(ImageIcon i) {
        super(i);
    }
    
    public abstract Cell getCell();
    public abstract void setCell(Cell cell);
    public abstract Cell getSpawnCell();
    
    public void move(Direction direction, GameCharacter character){
        Cell cell = character.getCell();
        if(cell.getNeighbors().containsKey(direction)){
            Cell nextCell = cell.getNeighbors().get(direction);
            if(!(nextCell instanceof Wall)){
                ((EmptyCell)cell).getInhoud().remove(this);
                ((EmptyCell)nextCell).getInhoud().add(this);
                character.setCell(nextCell);
            }
        }
    }
    
    public void moveGhost(Ghost character, Cell nextCell){
        Cell cell = character.getCell();
        if(cell != nextCell){
            ((EmptyCell)cell).getInhoud().remove(this);
            ((EmptyCell)nextCell).getInhoud().add(this);
            character.setCell(nextCell);
        }
    }
}
