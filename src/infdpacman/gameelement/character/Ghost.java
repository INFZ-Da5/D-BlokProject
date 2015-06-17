package infdpacman.gameelement.character;

import infdpacman.cell.Cell;
import infdpacman.utilities.dijkstra.DijkstraAlgorithm;
import infdpacman.view.Board;
import java.util.LinkedList;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 *
 * @author CVD
 */
public abstract class Ghost extends GameCharacter{
    protected ImageIcon normalGhost;
    private final ImageIcon fleeGhost;    
    protected ImageIcon currentImage;
    private final int points = 200;
    protected boolean flee;
    protected boolean firstStep;
    protected Board board;
    protected LinkedList<Cell> path;
    protected DijkstraAlgorithm dijkstra;
    protected final Random r;
    
    public Ghost(ImageIcon i, Board board) {
        super(i);
        this.board = board;
        fleeGhost = new ImageIcon("Plaatjes/fleeghost.png");
        flee = false;
        firstStep = true;
        r = new Random();
    }

    @Override
    public abstract Cell getCell();
    @Override
    public abstract void setCell(Cell cell);

    public int getPoints() {
        return points;
    }
        
    public void flee(){
        flee = true;
        currentImage = fleeGhost;
    }
    
    public abstract void moveGhost();
            
    public LinkedList<Cell> calculateRoute(Cell cell){
        if(firstStep){
            dijkstra = new DijkstraAlgorithm(board); //init nodes & edges
            firstStep = false;
        }
        dijkstra.execute(cell);
        if(flee){
            if(board.getNodes().indexOf(board.getPacman().getCell()) > board.getNodes().size()/2){
                int randomNumber = r.nextInt((board.getNodes().size()/2));
                path = dijkstra.getPath(board.getNodes().get(randomNumber));
            }
            else{
                int randomNumber = r.nextInt((board.getNodes().size() - board.getNodes().size()/2)+1)+board.getNodes().size()/2 -1;
                path = dijkstra.getPath(board.getNodes().get(randomNumber));
            }  
        }
        else{
            path = dijkstra.getPath(board.getPacman().getCell());
        }
        return path;
    }
    
    public abstract void attack();
}
