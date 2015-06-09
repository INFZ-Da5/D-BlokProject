package infdpacman.gameelement.character;

import infdpacman.enums.Direction;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author CVD
 */
public class DrunkGhost extends Character {
    ImageIcon normalGhost = new ImageIcon("Plaatjes/drunkghost.png");
    ImageIcon fleeGhost = new ImageIcon("Plaatjes/fleeghost.png");    
    ImageIcon currentImage = normalGhost;
    Direction lastDirection;

    public DrunkGhost() {
        super(new ImageIcon("Plaatjes/drunkghost.png"));
       // moveTimer();
    }
    
    public void moveGhost(){
        for(Direction d : Direction.values()){
            if(lastDirection != d){
                move(d, this);
                lastDirection = d;
            }
        }
    }

    private void moveTimer(){
        while(true) {
            long millis = System.currentTimeMillis();
            moveGhost();
            try {
                Thread.sleep(1000 - millis % 1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(DrunkGhost.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
