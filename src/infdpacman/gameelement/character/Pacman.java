package infdpacman.gameelement.character;

import infdpacman.cell.Cell;
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
public class Pacman extends GameCharacter implements KeyListener {
    public int lives = 3;
    public boolean invincible;
    private long lastPressProcessed = 0;
    private Cell cell;
    private Direction lastDirection;

    private ImageIcon leftImg = new ImageIcon("Plaatjes/pacmanleft.png");
    private ImageIcon rightImg = new ImageIcon("Plaatjes/pacmanright.png");
    private ImageIcon upImg = new ImageIcon("Plaatjes/pacmanup.png");
    private ImageIcon downImg = new ImageIcon("Plaatjes/pacmandown.png");
    private ImageIcon currentImage = leftImg;
    
    public Pacman(Cell cell){
        super(new ImageIcon("Plaatjes/pacmanleft.png"));
        this.cell = cell;
        invincible = false;
    }
     
    public boolean getOnverslaanbaar(){
        return invincible;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }
       
    @Override
    public void keyTyped(KeyEvent ke) {}

    @Override
    public void keyPressed(KeyEvent ke) {
        if(System.currentTimeMillis() - lastPressProcessed > 250) {
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
