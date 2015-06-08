package infdpacman;

import infdpacman.gameelement.character.Pacman;
import infdpacman.view.Board;

/**
 *
 * @author CVD
 */
public class Player {
    private int score = 0;
    Board currentSb;
    
    
    public Player(){

}
 
    public void setLevel(Board level){
    
    currentSb = level;
    
    }
    public int calcScore(){
    
 Pacman pacman = currentSb.getPacman();
 score = score + pacman.score;
 
 return score;
        }
    
    
    
    
    public int getScore(){
    
    return score;
    }
    
    
}
