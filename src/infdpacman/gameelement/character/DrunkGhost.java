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
    private ImageIcon normalGhost;
    private ImageIcon fleeGhost;    
    private ImageIcon currentImage;
    
    private int points = 200;
    private Cell cell;
    
    public DrunkGhost(Cell cell) {
        super(new ImageIcon("Plaatjes/drunkghost.png"));
        fleeGhost = new ImageIcon("Plaatjes/fleeghost.png");
        normalGhost = new ImageIcon("Plaatjes/drunkghost.png");
        currentImage = normalGhost;
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

    @Override
    public void draw(Graphics g,int width, int height) {
        ImageIcon i = currentImage;
        Image img = i.getImage();
        g.drawImage(img, 0,0, width,height, null);    
    }
}
