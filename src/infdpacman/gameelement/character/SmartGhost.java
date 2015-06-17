package infdpacman.gameelement.character;

import infdpacman.cell.Cell;
import infdpacman.utilities.dijkstra.DijkstraAlgorithm;
import infdpacman.view.Board;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 *
 * @author Lenovo
 */
public class SmartGhost extends Ghost {
    private Cell cell;
    private Random r;
   
    public SmartGhost(Cell cell, Board board) {
        super(new ImageIcon("Plaatjes/ghost.png"), board);
        normalGhost = new ImageIcon("Plaatjes/ghost.png");
        currentImage = normalGhost;
        this.cell = cell;
        this.board = board;
        flee = false;
        r = new Random();
        firstStep = true;
    }

    @Override
    public void attack(){
        currentImage = normalGhost;
        flee = false;
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
    public void moveGhost() {
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
