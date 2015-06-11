package infdpacman.gameelement.character;

import infdpacman.cell.Cell;
import infdpacman.enums.Direction;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Lenovo
 */
public class Ghost extends GameCharacter {
    private ImageIcon normalGhost = new ImageIcon("Plaatjes/ghost.png");
    private ImageIcon fleeGhost = new ImageIcon("Plaatjes/fleeghost.png");    
    private ImageIcon currentImage = normalGhost;
    public int points = 200;
    private Cell cell;
   
    public Ghost(Cell cell) {
        super(new ImageIcon("Plaatjes/ghost.png"));
        this.cell = cell;
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
