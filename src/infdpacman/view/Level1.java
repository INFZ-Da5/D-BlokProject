package infdpacman.view;

import infdpacman.gameelement.item.SuperPill;
import infdpacman.gameelement.item.Pill;
import infdpacman.gameelement.item.Banana;
import infdpacman.cell.Cell;
import infdpacman.cell.EmptyCell;
import infdpacman.cell.Wall;
import infdpacman.gameelement.character.DrunkGhost;
import infdpacman.gameelement.character.Ghost;
import infdpacman.gameelement.character.Pacman;
import java.awt.GridLayout;
import java.util.LinkedList;


/**
 *
 * @author CVD
 */
public class Level1 extends Board {

    /*
        0 = muur
        1 = pacman
        2 = smartghost
        3 = drunkghost
        4 = pill
        5 = superpill
        6 = banana
    */
    int [][] grid = new int[][] {
        { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        { 0, 4, 4, 4, 0, 4, 4, 4, 0 },
        { 0, 5, 4, 4, 3, 4, 4, 5, 0 },
        { 0, 4, 4, 0, 2, 0, 4, 4, 0 },
        { 0, 0, 4, 0, 2, 0, 4, 0, 0 },
        { 0, 4, 4, 0, 0, 0, 4, 4, 0 },
        { 0, 4, 4, 4, 1, 4, 4, 4, 0 },
        { 0, 4, 4, 4, 0, 4, 4, 6, 0 },
        { 0, 0, 0, 0, 0, 0, 0, 0, 0 }
    };
    
    
    
    public Level1() {
        this.setLayout(new GridLayout(grid.length,grid[0].length));
        fillCells(grid);
    }
}
