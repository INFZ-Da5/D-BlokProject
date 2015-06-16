package infdpacman.gameelement.character;

import infdpacman.cell.Cell;
import infdpacman.cell.EmptyCell;
import infdpacman.cell.Wall;
import infdpacman.utilities.dijkstra.DijkstraAlgorithm;
import infdpacman.view.Board;
import java.awt.Graphics;
import java.awt.Image;
import java.util.LinkedList;
import java.util.Random;
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
    private boolean firstStep;
    private Cell cell;
    private Board board;
    private DijkstraAlgorithm dijkstra;
    private LinkedList<Cell> path;
    private Random r;

    
   
    public Ghost(Cell cell, Board board) {
        super(new ImageIcon("Plaatjes/ghost.png"));
        this.cell = cell;
        this.board = board;
        flee = false;
        r = new Random();
        firstStep = true;
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
    
    public void attack(){
        currentImage = normalGhost;
        flee = false;
    }

    public void moveGhost(){
        calculateRoute();
        if(path != null){
            if(!path.isEmpty()){
                moveGhost(this, path.getFirst());
                path.removeFirst();
            }
        }
        else{
            calculateRoute();
        }
    }

    public void calculateRoute(){
        //dijkstra geeft de error
        if(firstStep){
            dijkstra = new DijkstraAlgorithm(board);
            firstStep = false;
        }
        dijkstra.execute(cell);
        if(flee){
            if(board.getNodes().indexOf(board.getPacman().getCell()) > board.getNodes().size()/2){
                int randomNumber = r.nextInt((board.getNodes().size()/2)+1);
                while(board.getNodes().get(randomNumber) instanceof Wall){
                    if(board.getNodes().get(randomNumber) instanceof EmptyCell){
                        path = dijkstra.getPath(board.getNodes().get(randomNumber));
                    }
                    randomNumber = r.nextInt((board.getNodes().size()/2)+1);
                }
            }
            else{
                int randomNumber = r.nextInt(((board.getNodes().size() - board.getNodes().size()/2))+board.getNodes().size()/2);
                while(board.getNodes().get(randomNumber) instanceof Wall){
                    if(board.getNodes().get(randomNumber) instanceof EmptyCell){
                        path = dijkstra.getPath(board.getNodes().get(randomNumber));
                    }
                    randomNumber = r.nextInt(((board.getNodes().size() - board.getNodes().size()/2))+board.getNodes().size()/2);
                }
            }
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
