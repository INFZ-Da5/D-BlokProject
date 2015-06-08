package infdpacman.gameelement.character;

import infdpacman.enums.Direction;
import infdpacman.view.Board;
import infdpacman.cell.EmptyCell;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author Lenovo
 */
public class Pacman extends Character implements KeyListener {
    public int lives;
    private ArrayList<EmptyCell> neighbors = new ArrayList();
    ImageIcon leftImg = new ImageIcon("Plaatjes/pacmanleft.png");
    ImageIcon rightImg = new ImageIcon("Plaatjes/pacmanright.png");
    ImageIcon upImg = new ImageIcon("Plaatjes/pacmanup.png");
    ImageIcon downImg = new ImageIcon("Plaatjes/pacmandown.png");

    ImageIcon currentImage = leftImg;
    
    public Pacman(){
        super(new ImageIcon("Plaatjes/pacmanleft.png"));
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {}

    @Override
    public void keyPressed(KeyEvent ke) {
        //movementCheck();
        switch (ke.getKeyCode())
        {
            case KeyEvent.VK_DOWN:
                move(Direction.SOUTH);
                currentImage = downImg; //method maken 
                break;
            case KeyEvent.VK_UP:
                move(Direction.NORTH);
                currentImage = upImg;
                break;
            case KeyEvent.VK_RIGHT:
                move(Direction.EAST);
                currentImage = rightImg;
                break;
            case KeyEvent.VK_LEFT:
                move(Direction.WEST);
                currentImage = leftImg;
               break;
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
