package infdpacman.gameelement.character;

import infdpacman.enums.Direction;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author CVD
 */
public class DrunkGhost extends Character implements Runnable {
    ImageIcon normalGhost = new ImageIcon("Plaatjes/drunkghost.png");
    ImageIcon fleeGhost = new ImageIcon("Plaatjes/fleeghost.png");    
    ImageIcon currentImage = normalGhost;
    Direction lastDirection;

    public DrunkGhost() {
        super(new ImageIcon("Plaatjes/drunkghost.png"));
<<<<<<< HEAD
       // moveTimer();
=======
        //new Thread(this).start();
>>>>>>> origin/vakjes-versie
    }
    
    public void moveGhost(){
        for(Direction d : Direction.values()){
            if(lastDirection != d){
                move(d, this);
                lastDirection = d;
            }
        }
    }

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(2000);
            } catch(InterruptedException ie) {}
            moveGhost();
        }
    }
}
