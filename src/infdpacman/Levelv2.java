package infdpacman;

/**
 *
 * @author CVD
 */
public class Levelv2 {
    /*
        0 = muur
        1 = pacman
        2 = smartghost
        3 = drunkghost
        4 = pill
        5 = superpill
        6 = banana
    */
    int [][] grid = new int[][]{
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
    
    public Levelv2(){
        
    }
}
