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

/*
Vanuit cell moet de cell waar pacman, ghost, drunkghost in staat gestuurd worden naar character
Dit moet dus verwijderd worden uit board.
*/
public abstract class Character extends GameElement {    
    
    public Character(ImageIcon i) {
        super(i);
    }
    
    public void move(Direction direction, Character character){
        //moet niet alleen pacman worden
        Cell c = Board.getCellOfCharacter(character);

        /*if(character instanceof Pacman){
            c = Board.getPacmanCell();
        }
        else if(character instanceof Ghost){
            c = Board.getPacmanCell();
        }
        else{
            c = Board.getPacmanCell();           
        }*/
        
        if(c.getNeighbors().containsKey(direction)){
            Cell c1 = c.getNeighbors().get(direction);
            if(!(c1 instanceof Wall)){
                ((EmptyCell)c).getInhoud().remove(this);
                ((EmptyCell)c1).getInhoud().add(this);
                c.getNeighbors().replace(direction, c1);
                Board.setPacmanCell(c1);
            }
        }
    }
}
