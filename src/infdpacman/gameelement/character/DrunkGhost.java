package infdpacman.gameelement.character;

import infdpacman.cell.Cell;
import infdpacman.enums.Direction;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author CVD
 */
public class DrunkGhost extends GameCharacter{
    private long lastPressProcessed = 0;
    private ImageIcon normalGhost = new ImageIcon("Plaatjes/drunkghost.png");
    private ImageIcon fleeGhost = new ImageIcon("Plaatjes/fleeghost.png");    
    private ImageIcon currentImage = normalGhost;
    private Direction lastDirection;
    private int points = 200;
    private Cell cell;
    
    public DrunkGhost(Cell cell) {
        super(new ImageIcon("Plaatjes/drunkghost.png"));
        this.cell = cell;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
    
    public void flee(){
        currentImage = fleeGhost;
    }
    
    public void normal(){
        currentImage = normalGhost;
    }
    
    public void moveGhost(){
        Direction d = Direction.getRandom();
        move(d, this);
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }
    
    /*
    public void moveGhost(){
        lastPressProcessed = System.currentTimeMillis();
        while(true){
            if(System.currentTimeMillis() - lastPressProcessed > 5000) {
                Direction d = Direction.getRandom();
                if(lastDirection != d){
                    move(d, this);
                    lastDirection = d;
                }
                lastPressProcessed = System.currentTimeMillis();
            }
        }
    }*/

    @Override
    public void draw(Graphics g,int width, int height) {
        ImageIcon i = currentImage;
        Image img = i.getImage();
        g.drawImage(img, 0,0, width,height, null);    
    }
}
