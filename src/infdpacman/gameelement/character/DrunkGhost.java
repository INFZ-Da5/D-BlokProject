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
    private boolean firstFleePath;
    private Cell spawnCell;
    
    public DrunkGhost(Cell cell, Board board) {
        super(new ImageIcon("Plaatjes/drunkghost.png"), board);
        normalGhost = new ImageIcon("Plaatjes/drunkghost.png");
        currentImage = normalGhost;
        this.cell = cell;
        firstFleePath = true;
        this.spawnCell = cell;
    }
    
    @Override
    public Cell getSpawnCell(){
        return spawnCell;
    }
     
    @Override
    public void moveGhost(){
        if(!flee){
            Direction d = Direction.getRandom();
            move(d, this);
        }
        else{
            if(!flee){
                path = calculateRoute(cell);
                firstFleePath = true;
            }
            else{
                if(firstFleePath){
                    path = calculateRoute(cell);  
                    firstFleePath = false;
                }
            }
            if(path != null){
                if(!path.isEmpty()){
                    moveGhost(this, path.getFirst());
                    path.removeFirst();
                }
                else{
                    path = calculateRoute(cell);
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
