package infdpacman.poppetje;

import infdpacman.Direction;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Lenovo
 */
public class Pacman extends Poppetje {
    public int levens;
    private final static int MOVEMENT = 20;//moet vakje worden niet pixels
    
    //private int xPos;
    //private int yPos;
    
    public Pacman(){
        //this.xPos = xPos;
        //this.yPos = yPos;
    }
    
    @Override
    public void bewegen(Direction direction) {
           /* switch (direction){
            case NORTH:
                yPos-=MOVEMENT;
                break;
            case SOUTH:
                yPos+=MOVEMENT;
                break;
            case WEST:
                xPos-=MOVEMENT;
                break;
            case EAST:
                xPos+=MOVEMENT;
                break;
        }*/
        
    }
    
    @Override
    public void draw(Graphics g) {
        ImageIcon i = new ImageIcon("Plaatjes/pacman.png");
        Image img = i.getImage();
        g.drawImage(img, 20,20,null);
    }   
}
