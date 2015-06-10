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
    ImageIcon normalGhost = new ImageIcon("Plaatjes/ghost.png");
    ImageIcon fleeGhost = new ImageIcon("Plaatjes/fleeghost.png");    
    ImageIcon currentImage = normalGhost;
    private Cell cell;
   
    public Ghost(Cell cell) {
        super(new ImageIcon("Plaatjes/ghost.png"));
        this.cell = cell;
    }
    
    public void setImage(ImageIcon img){
        currentImage = img;
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
