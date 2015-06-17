package infdpacman.gameelement.character;

import infdpacman.cell.Cell;
import infdpacman.enums.Direction;
import infdpacman.view.Board;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
/**
 *
 * @author CVD
 */
public class DrunkGhost extends Ghost{
    private Cell cell;
    
    public DrunkGhost(Cell cell, Board board) {
        super(new ImageIcon("Plaatjes/drunkghost.png"), board);
        normalGhost = new ImageIcon("Plaatjes/drunkghost.png");
        currentImage = normalGhost;
        this.cell = cell;
        
    }

    @Override
    public void moveGhost(){
        if(!flee){
            Direction d = Direction.getRandom();
            move(d, this);
        }
        else{
            path = calculateRoute(cell);
            if(path != null){
                if(!path.isEmpty()){
                    moveGhost(this, path.getFirst());
                    path.removeFirst();
                }
            }
            else{
                path = calculateRoute(cell);
            } 
        }
    }
    
    @Override
    public Cell getCell() {
        return cell;
    }

    @Override
    public void setCell(Cell cell) {
        this.cell = cell;
    }

    @Override
    public void draw(Graphics g,int width, int height) {
        ImageIcon i = currentImage;
        Image img = i.getImage();
        g.drawImage(img, 0,0, width,height, null);    
    }

    @Override
    public void attack() {
        currentImage = normalGhost;
        flee = false;
    }
}
