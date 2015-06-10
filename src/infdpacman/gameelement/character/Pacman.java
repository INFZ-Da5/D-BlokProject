package infdpacman.gameelement.character;

import infdpacman.enums.Direction;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;

/**
 *
 * @author Lenovo
 */
public class Pacman extends Character implements KeyListener {
    public int lives = 3;
    public boolean onverslaanbaar;
    private long lastPressProcessed = 0;
    Direction lastDirection;

    ImageIcon leftImg = new ImageIcon("Plaatjes/pacmanleft.png");
    ImageIcon rightImg = new ImageIcon("Plaatjes/pacmanright.png");
    ImageIcon upImg = new ImageIcon("Plaatjes/pacmanup.png");
    ImageIcon downImg = new ImageIcon("Plaatjes/pacmandown.png");
    ImageIcon currentImage = leftImg;
    
    public Pacman(){
        super(new ImageIcon("Plaatjes/pacmanleft.png"));
        onverslaanbaar = false;
    
    
    //lastPressProcessed = System.currentTimeMillis();
       // moveGhost();
    }
    
    public void moveGhost(){
        while(true){
            if(System.currentTimeMillis() - lastPressProcessed > 1500) {
                for(Direction d : Direction.values()){
                    if(lastDirection != d){
                        move(d, this);
                        lastDirection = d;
                    }
                }
                lastPressProcessed = System.currentTimeMillis();
            }
        }
    }
    
     public static void setFirstMove(boolean firstMove) {
        Character.firstMove = firstMove;
    }
     
     
     public boolean getOnverslaanbaar(){
     
     return onverslaanbaar;
     }
    
    
    @Override
    public void keyTyped(KeyEvent ke) {}

    @Override
    public void keyPressed(KeyEvent ke) {
        if(System.currentTimeMillis() - lastPressProcessed > 500) {
            switch (ke.getKeyCode())
            {
                case KeyEvent.VK_DOWN:
                    move(Direction.SOUTH, this);
                    currentImage = downImg; //method maken
                    break;
                case KeyEvent.VK_UP:
                    move(Direction.NORTH, this);
                    currentImage = upImg;
                    break;
                case KeyEvent.VK_RIGHT:
                    move(Direction.EAST, this);
                    currentImage = rightImg;
                    break;
                case KeyEvent.VK_LEFT:
                    move(Direction.WEST, this);
                    currentImage = leftImg;
                   break;
            }
        lastPressProcessed = System.currentTimeMillis();
        } 
    }

    @Override
    public void keyReleased(KeyEvent ke) {}


    @Override
    public void draw(Graphics g,int width, int height) {
        ImageIcon i = currentImage;
        Image img = i.getImage();
        g.drawImage(img, 0,0, width,height, null);    
    }
}
