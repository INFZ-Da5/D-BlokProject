package infdpacman;

import infdpacman.character.DrunkGhost;
import infdpacman.character.Ghost;
import infdpacman.character.Pacman;
import infdpacman.item.*;
import java.awt.GridLayout;
import java.util.LinkedList;


/**
 *
 * @author CVD
 */
class Level1 extends Board {

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
        initLevel();
        fillVakjes();
    }

    @Override
    public void initLevel() {
        width = grid[0].length;
        height = grid.length;
        this.setLayout(new GridLayout(width,height));
    }

    @Override
    public void fillVakjes() {
        Cell[][] cellgrid = new Cell[width][height];
        
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if(grid[row][col] == 0){
                cellgrid[row][col] = new Wall();}
                
                if(grid[row][col] != 0){
                    LinkedList inhoud = new LinkedList();
                    EmptyCell cell = new EmptyCell();
                    cellgrid[row][col] = cell;
                    cell.setInhoud(inhoud);

                  /*  switch(grid[row][col]){
                        case 1: inhoud.add(new Pacman(this));
                        case 2: inhoud.add(new Ghost());
                        case 3: inhoud.add(new DrunkGhost());
                        case 4: inhoud.add(new Pill());
                        case 5: inhoud.add(new SuperPill());
                        case 6: inhoud.add(new Banana());

                    }*/
                }
            }
        }
    } 
}
