package infdpacman;

import java.util.LinkedList;
import infdpacman.character.Pacman;
import infdpacman.character.Ghost;
import infdpacman.character.DrunkGhost;
import infdpacman.item.*;

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
    Object [][] grid = new Object[][]{
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
        System.out.println("test");
    }

    @Override
    public void fillVakjes() {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < length; col++) {
                if((int)grid[row][col] == 0){
                grid[row][col] = new Wall();}
                
                if((int)grid[row][col] > 0){
                    LinkedList inhoud = new LinkedList();
                    EmptyCell cell = new EmptyCell();
                    grid[row][col] = cell;
                    cell.setInhoud(inhoud);

                    switch((int)grid[row][col]){
                        case 1: inhoud.add(new Pacman(this));
                        case 2: inhoud.add(new Ghost());
                        case 3: inhoud.add(new DrunkGhost());
                        case 4: inhoud.add(new Pill());
                        case 5: inhoud.add(new SuperPill());
                        case 6: inhoud.add(new Banana());

                    }
                }
            }
        }
    } 
}
