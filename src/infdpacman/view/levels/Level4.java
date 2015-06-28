package infdpacman.view.levels;

import infdpacman.view.Board;
import java.awt.GridLayout;


/**
 *
 * @author CVD
 */
public class Level4 extends Board {
    /*
        0 = wall
        1 = pacman
        2 = smartghost
        3 = drunkghost
        4 = pill
        5 = superpill
    */
    private final int [][] grid = new int[][] {
        { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        { 0, 4, 4, 4, 0, 4, 4, 4, 0 },
        { 0, 5, 4, 4, 3, 4, 4, 5, 0 },
        { 0, 4, 4, 0, 3, 0, 4, 4, 0 },
        { 0, 0, 4, 0, 3, 0, 4, 0, 0 },
        { 0, 4, 4, 0, 0, 0, 4, 4, 0 },
        { 0, 4, 4, 4, 1, 4, 4, 4, 0 },
        { 0, 4, 4, 4, 0, 4, 4, 6, 0 },
        { 0, 0, 0, 0, 0, 0, 0, 0, 0 }
    };    
    
    public Level4() {
        this.setLayout(new GridLayout(grid.length,grid[0].length));
        fillCells(grid);
    }
}
