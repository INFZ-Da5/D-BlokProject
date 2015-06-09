package infdpacman.gameelement.character;

import infdpacman.enums.Direction;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author CVD
 */
public class DrunkGhost extends Character{
    private long lastPressProcessed = 0;
    ImageIcon normalGhost = new ImageIcon("Plaatjes/drunkghost.png");
    ImageIcon fleeGhost = new ImageIcon("Plaatjes/fleeghost.png");    
    ImageIcon currentImage = normalGhost;
    Direction lastDirection;

    public DrunkGhost() {
        super(new ImageIcon("Plaatjes/drunkghost.png"));
        //moveGhost();
    }
    
    public void moveGhost(){
        if(System.currentTimeMillis() - lastPressProcessed > 500) {
            for(Direction d : Direction.values()){
                if(lastDirection != d){
                    move(d, this);
                    lastDirection = d;
                }
            }
            lastPressProcessed = System.currentTimeMillis();
        }
    }

    @Override
    public void draw(Graphics g,int width, int height) {
        ImageIcon i = currentImage;
        Image img = i.getImage();
        g.drawImage(img, 0,0, width,height, null);    
    }
}
