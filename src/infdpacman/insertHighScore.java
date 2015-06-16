
package infdpacman;

import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class insertHighScore {
    public ArrayList hscore;
    public int score;
    
    public insertHighScore(ArrayList hscore, int score){
    
    this.hscore = hscore;
    this.score = score;
    }
    
    
    public void insert(){
    
    
    hscore.add(score);
    
    
    
    
    }
    
}
