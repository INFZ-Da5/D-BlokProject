
package infdpacman.view;

import java.awt.GridLayout;

/**
 *
 * @author Lenovo
 */
public class Level1 extends Board {
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
        {0, 0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 0, 0, 0, 0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 0, 0}
    };
     
     public Level1() {
        this.setLayout(new GridLayout(grid.length,grid[0].length));
        fillCells(grid);
    }
}
