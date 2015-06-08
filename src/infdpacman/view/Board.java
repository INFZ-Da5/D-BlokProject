package infdpacman.view;

import infdpacman.cell.Cell;
import infdpacman.enums.Direction;
import infdpacman.cell.EmptyCell;
import infdpacman.cell.Wall;
import infdpacman.gameelement.character.DrunkGhost;
import infdpacman.gameelement.character.Ghost;
import infdpacman.gameelement.character.Pacman;
import infdpacman.gameelement.item.Banana;
import infdpacman.gameelement.item.Pill;
import infdpacman.gameelement.item.SuperPill;
import java.util.LinkedList;
import java.util.Map;
import javax.swing.JPanel;

/**
 *
 * @author Lenovo
 */
public abstract class Board extends JPanel  {
    Pacman pacman = new Pacman();
    Cell[][] cellgrid;
    
    public Board(){
        this.requestFocusInWindow();
        this.addKeyListener(pacman);
        this.repaint();
    }
        
    public void fillCells(int [][] grid){
        cellgrid = new Cell[grid.length][grid[0].length];
        
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if(grid[row][col] == 0){
                cellgrid[row][col] = new Wall();}
                
                if(grid[row][col] != 0){
                    LinkedList inhoud = new LinkedList();
                    EmptyCell cell = new EmptyCell();
                    cellgrid[row][col] = cell;
                    cell.setInhoud(inhoud);

                    switch(grid[row][col]){
                        case 1: inhoud.add(pacman); break;
                        case 2: inhoud.add(new Ghost()); break;
                        case 3: inhoud.add(new DrunkGhost()); break;
                        case 4: inhoud.add(new Pill()); break;
                        case 5: inhoud.add(new SuperPill()); break;
                        case 6: inhoud.add(new Banana()); break;

                    }
                }
                this.add(cellgrid[row][col]);
            }
        }
        setNeigbors();
    }  

    public void setNeigbors() {
        for(int row = 0; row < cellgrid.length; row++){
            for(int col = 0; col < cellgrid[0].length; col++){
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
