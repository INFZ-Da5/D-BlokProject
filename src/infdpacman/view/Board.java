package infdpacman.view;

import infdpacman.Cell;
import infdpacman.Direction;
import infdpacman.EmptyCell;
import infdpacman.character.Pacman;
import java.util.Map;
import javax.swing.JPanel;

/**
 *
 * @author Lenovo
 */
public abstract class Board extends JPanel  {
    Pacman pacman = new Pacman();
    
    public Board(){
        this.requestFocusInWindow();
        this.addKeyListener(pacman);
        this.repaint();
    }
        
    public abstract void fillVakjes();

    public void setNeigbors(Cell[][] cellgrid) {
        for(int row = 0; row < cellgrid[0].length; row++){
            for(int col = 0; col < cellgrid.length; col++){
                if(cellgrid[row][col] instanceof EmptyCell){
                    Map<Direction, Cell> neighbors = ((EmptyCell)cellgrid[row][col]).getNeighbors();
                    neighbors.put(Direction.NORTH, cellgrid[row][col-1]);
                    neighbors.put(Direction.SOUTH, cellgrid[row][col+1]);
                    neighbors.put(Direction.EAST, cellgrid[row+1][col]);
                    neighbors.put(Direction.WEST, cellgrid[row-1][col]);
                    ((EmptyCell)cellgrid[row][col]).setNeighbors(neighbors);  
                }
            }
        }
    }
    
}
