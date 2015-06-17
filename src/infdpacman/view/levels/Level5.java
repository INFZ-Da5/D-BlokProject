package infdpacman.view.levels;

import infdpacman.view.Board;
import java.awt.GridLayout;

/**
 *
 * @author CVD
 */
public class Level5 extends Board{
    private int [][] grid = new int[][] {
        { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        { 0, 4, 4, 4, 4, 4, 4, 4, 0 },
        { 0, 4, 4, 4, 4, 4, 4, 4, 0 },
        { 0, 4, 4, 4, 4, 4, 4, 4, 0 },
        { 0, 4, 4, 4, 3, 4, 4, 4, 0 },
        { 0, 4, 4, 4, 4, 4, 4, 4, 0 },
        { 0, 4, 4, 4, 4, 4, 4, 4, 0 },
        { 0, 4, 4, 4, 4, 4, 4, 1, 0 },
        { 0, 0, 0, 0, 0, 0, 0, 0, 0 }
    };
    
    public Level5() {
        this.setLayout(new GridLayout(grid.length,grid[0].length));
         fillCells(grid);
    }
}
