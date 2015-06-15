package infdpacman.gameelement.character;

import infdpacman.cell.Cell;
import infdpacman.enums.Direction;
import infdpacman.utilities.dijkstra.DijkstraAlgorithm;
import infdpacman.view.Board;
import java.awt.Graphics;
import java.awt.Image;
import java.util.LinkedList;
import javax.swing.ImageIcon;

/**
 *
 * @author Lenovo
 */
public class Ghost extends GameCharacter {
    private ImageIcon normalGhost = new ImageIcon("Plaatjes/ghost.png");
    private ImageIcon fleeGhost = new ImageIcon("Plaatjes/fleeghost.png");    
    private ImageIcon currentImage = normalGhost;
    private int points = 200;
    private boolean flee;
    private Cell cell;
    private Board board;
    DijkstraAlgorithm dijkstra;
    LinkedList<Cell> path;
   
    public Ghost(Cell cell, Board board) {
        super(new ImageIcon("Plaatjes/ghost.png"));
        this.cell = cell;
        this.board = board;
        flee = false;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
    
    public void flee(){
        currentImage = fleeGhost;
        flee = true;
    }
    
    public void normal(){
        currentImage = normalGhost;
        flee = false;
    }

    public void moveGhost(){
        calculateRoute();
        moveGhost(this, path.getFirst());
        path.removeFirst();
    }

    public void calculateRoute(){
        dijkstra = new DijkstraAlgorithm(board);
        dijkstra.execute(cell);
        if(flee){
            path = dijkstra.getPath(cell);
        }
        else{
            path = dijkstra.getPath(board.getPacman().getCell());
        }
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
