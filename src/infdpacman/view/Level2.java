
package infdpacman.view;

import infdpacman.Cell;
import infdpacman.EmptyCell;
import infdpacman.Wall;
import infdpacman.character.DrunkGhost;
import infdpacman.character.Ghost;
import infdpacman.item.Banana;
import infdpacman.item.Pill;
import infdpacman.item.SuperPill;
import java.awt.GridLayout;
import java.util.LinkedList;

/**
 *
 * @author Lenovo
 */
public class Level2 extends Board {
     int [][] grid = new int[][] {
    {0, 0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 0, 0, 0, 0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 0, 0},
    {0, 4,  4,  4,  4,  4,  4,  4,  4,  4,  4,  4, 4, 0, 0, 4,  4,  4,  4,  4,  4,  4,  4,  4,  4,  4, 4, 0},
    {0, 4,  0,  0,  0,  0,  4,  0,  0,  0,  0,  0, 4, 0, 0, 4,  0,  0,  0,  0,  0,  4,  0,  0,  0,  0, 4, 0},
    {0, 5,  0,  0,  0,  0,  4,  0,  0,  0,  0,  0, 4, 0, 0, 4,  0,  0,  0,  0,  0,  4,  0,  0,  0,  0, 5, 0},
    {0, 4,  0,  0,  0,  0,  4,  0,  0,  0,  0,  0, 4, 0, 0, 4,  0,  0,  0,  0,  0,  4,  0,  0,  0,  0, 4, 0},
    {0, 4,  4,  4,  4,  4,  4,  4,  4,  4,  4,  4, 4, 4, 4, 4,  4,  4,  4,  4,  4,  4,  4,  4,  4,  4, 4, 0},
    {0, 4,  0,  0,  0,  0,  4,  0,  0,  4,  0,  0, 0, 0, 0, 0,  0,  0,  4,  0,  0,  4,  0,  0,  0,  0, 4, 0},
    {0, 4,  0,  0,  0,  0,  4,  0,  0,  4,  0,  0, 0, 0, 0, 0,  0,  0,  4,  0,  0,  4,  0,  0,  0,  0, 4, 0},
    {0, 4,  4,  4,  4,  4,  4,  0,  0,  4,  4,  4, 4, 0, 0, 4,  4,  4,  4,  0,  0,  4,  4,  4,  4,  4, 4, 0},
    {0, 0,  0,  0,  0,  0,  4,  0,  0,  0,  0,  0, 7, 0, 0, 7,  0,  0,  0,  0,  0,  4,  0,  0,  0,  0, 0, 0},
    {0, 0,  0,  0,  0,  0,  4,  0,  0,  0,  0,  0, 7, 0, 0, 7,  0,  0,  0,  0,  0,  4,  0,  0,  0,  0, 0, 0},
    {0, 0,  0,  0,  0,  0,  4,  0,  0,  7,  7,  7, 7, 7, 7, 7,  7,  7,  7,  0,  0,  4,  0,  0,  0,  0, 0, 0},
    {0, 0,  0,  0,  0,  0,  4,  0,  0,  7,  0,  0, 0, 7, 7, 0,  0,  0,  7,  0,  0,  4,  0,  0,  0,  0, 0, 0},
    {0, 0,  0,  0,  0,  0,  4,  0,  0,  7,  0,  0, 0, 7, 7, 0,  0,  0,  7,  0,  0,  4,  0,  0,  0,  0, 0, 0},
    {0, 0,  0,  0,  0,  0,  4,  7,  7,  7,  0,  0, 0, 2, 3, 0,  0,  0,  7,  7,  7,  4,  0,  0,  0,  0, 0, 0},
    {0, 0,  0,  0,  0,  0,  4,  0,  0,  7,  0,  0, 0, 3, 2, 0,  0,  0,  7,  0,  0,  4,  0,  0,  0,  0, 0, 0},
    {0, 0,  0,  0,  0,  0,  4,  0,  0,  7,  0,  0, 0, 0, 0, 0,  0,  0,  7,  0,  0,  4,  0,  0,  0,  0, 0, 0},
    {0, 0,  0,  0,  0,  0,  4,  0,  0,  7,  7,  7, 7, 7, 7, 7,  7,  7,  7,  0,  0,  4,  0,  0,  0,  0, 0, 0},
    {0, 0,  0,  0,  0,  0,  4,  0,  0,  7,  0,  0, 0, 0, 0, 0,  0,  0,  7,  0,  0,  4,  0,  0,  0,  0, 0, 0},
    {0, 0,  0,  0,  0,  0,  4,  0,  0,  7,  0,  0, 0, 0, 0, 0,  0,  0,  7,  0,  0,  4,  0,  0,  0,  0, 0, 0},
    {0, 4,  4,  4,  4,  4,  4,  4,  4,  4,  4,  4, 4, 0, 0, 4,  4,  4,  4,  4,  4,  4,  4,  4,  4,  4, 4, 0},
    {0, 4,  0,  0,  0,  0,  4,  0,  0,  0,  0,  0, 4, 0, 0, 4,  0,  0,  0,  0,  0,  4,  0,  0,  0,  0, 4, 0},
    {0, 4,  0,  0,  0,  0,  4,  0,  0,  0,  0,  0, 4, 0, 0, 4,  0,  0,  0,  0,  0,  4,  0,  0,  0,  0, 4, 0},
    {0, 5,  4,  4,  0,  0,  4,  4,  4,  4,  4,  4, 4, 4, 1, 4,  4,  4,  4,  4,  4,  4,  0,  0,  4,  4, 5, 0},
    {0, 0,  0,  4,  0,  0,  4,  0,  0,  4,  0,  0, 0, 0, 0, 0,  0,  0,  4,  0,  0,  4,  0,  0,  4,  0, 0, 0},
    {0, 0,  0,  4,  0,  0,  4,  0,  0,  4,  0,  0, 0, 0, 0, 0,  0,  0,  4,  0,  0,  4,  0,  0,  4,  0, 0, 0},
    {0, 4,  4,  4,  4,  4,  4,  0,  0,  4,  4,  4, 4, 0, 0, 4,  4,  4,  4,  0,  0,  4,  4,  4,  4,  4, 4, 0},
    {0, 4,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 4, 0, 0, 4,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 4, 0},
    {0, 4,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 4, 0, 0, 4,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 4, 0},
    {0, 4,  4,  4,  4,  4,  4,  4,  4,  4,  4,  4, 4, 4, 4, 4,  4,  4,  4,  4,  4,  4,  4,  4,  4,  4, 4, 0},
    {0, 0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 0, 0, 0, 0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 0, 0},
    
    };
     
     public Level2() {
        this.setLayout(new GridLayout(grid.length,grid[0].length));
        fillVakjes();
    }


    @Override
    public void fillVakjes() {
            Cell[][] cellgrid = new Cell[grid.length][grid[0].length];
        
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
        super.setNeigbors(cellgrid);
    }  
}
