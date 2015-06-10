package infdpacman.gameelement.character;

import infdpacman.enums.Direction;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Lenovo
 */
public class Ghost extends GameCharacter {
    ImageIcon normalGhost = new ImageIcon("Plaatjes/ghost.png");
    ImageIcon fleeGhost = new ImageIcon("Plaatjes/fleeghost.png");    
    ImageIcon currentImage = normalGhost;

    public Ghost() {
        super(new ImageIcon("Plaatjes/ghost.png"));
    }
    
    public void setImage(ImageIcon img){
    
    currentImage = img;
    }
    
    
    
    
    public void moveGhost(){
        Direction d = Direction.getRandom();
        move(d, this);
    }
    
    @Override
    public void draw(Graphics g,int width, int height) {
        ImageIcon i = currentImage;
        Image img = i.getImage();
        g.drawImage(img, 0,0, width,height, null);    
    }
}
