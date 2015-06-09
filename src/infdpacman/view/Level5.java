package infdpacman.view;

import java.awt.GridLayout;

/**
 *
 * @author CVD
 */
public class Level5 extends Board{
    int [][] grid = new int[][] {
        { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        { 0, 7, 7, 7, 7, 7, 7, 7, 0 },
        { 0, 7, 7, 7, 7, 7, 7, 7, 0 },
        { 0, 7, 7, 7, 7, 7, 7, 7, 0 },
        { 0, 7, 7, 7, 3, 7, 7, 7, 0 },
        { 0, 7, 7, 7, 7, 7, 7, 7, 0 },
        { 0, 7, 7, 7, 7, 7, 7, 7, 0 },
        { 0, 7, 7, 7, 7, 7, 7, 1, 0 },
        { 0, 0, 0, 0, 0, 0, 0, 0, 0 }
    };
    
    public Level5() {
        this.setLayout(new GridLayout(grid.length,grid[0].length));
         fillCells(grid);
    }
}
