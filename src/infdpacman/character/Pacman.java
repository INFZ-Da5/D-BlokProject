package infdpacman.character;

import infdpacman.Direction;
import infdpacman.FindClassType;
import infdpacman.Wall;
import infdpacman.Level;
import infdpacman.EmptyCell;
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
    private Level sb;
    ImageIcon leftImg = new ImageIcon("Plaatjes/pacmanleft.png");
    ImageIcon rightImg = new ImageIcon("Plaatjes/pacmanright.png");
    ImageIcon upImg = new ImageIcon("Plaatjes/pacmanup.png");
    ImageIcon downImg = new ImageIcon("Plaatjes/pacmandown.png");

    ImageIcon currentImage = leftImg;

    EmptyCell vWest;
    EmptyCell vEast;
    EmptyCell vSouth;
    EmptyCell vNorth;
    
    public Pacman(Level sb){
        super(new ImageIcon("Plaatjes/pacmanleft.png"));
        this.sb = sb;
    }
     
    public void movementCheck(){
        neighbors = sb.getNeighbors();
        //redundant -> always 4
        if(neighbors.size() == 4){
            vWest = neighbors.get(0);
            vEast = neighbors.get(1);
            vSouth = neighbors.get(2);
            vNorth = neighbors.get(3);
        }
    }
    
    @Override
    public void move(EmptyCell directionVakje, Direction direction) {
        Wall match = FindClassType.find(directionVakje.getInhoud(), Wall.class);
        if(match == null && sb.getCurrentVakje().getInhoud().contains(this)){
            sb.getCurrentVakje().getInhoud().remove(this);
            sb.setCurrentVakje(direction);
            sb.getCurrentVakje().getInhoud().add(this);
            sb.fillVakjes();
        }
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {}

    @Override
    public void keyPressed(KeyEvent ke) {
        movementCheck();
        switch (ke.getKeyCode())
        {
            case KeyEvent.VK_DOWN:
                move(vSouth, Direction.SOUTH);
                currentImage = downImg; //method maken 
                break;
            case KeyEvent.VK_UP:
                move(vNorth, Direction.NORTH);
                currentImage = upImg;
                break;
            case KeyEvent.VK_RIGHT:
                move(vEast, Direction.EAST);
                currentImage = rightImg;
                break;
            case KeyEvent.VK_LEFT:
                move(vWest, Direction.WEST);
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
