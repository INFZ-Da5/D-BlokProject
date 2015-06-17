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
    private boolean firstStep;
    private Cell cell;
    private static Board board;
    protected DijkstraAlgorithm dijkstra;
    protected LinkedList<Cell> path;
    private final Random r;
    
    public Ghost(ImageIcon i, Board board) {
        super(i);
        this.board = board;
        fleeGhost = new ImageIcon("Plaatjes/fleeghost.png");
        flee = false;
        r = new Random();
        firstStep = true;
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
        if(firstStep){
            dijkstra = new DijkstraAlgorithm(board);
            firstStep = false;
        }
        dijkstra.execute(cell);
        if(flee){
            if(board.getNodes().indexOf(board.getPacman().getCell()) > board.getNodes().size()/2){
                int randomNumber = r.nextInt((board.getNodes().size()/2));
                path = dijkstra.getPath(board.getNodes().get(randomNumber));
            }
            else{
                int randomNumber = r.nextInt((board.getNodes().size() - board.getNodes().size()/2)+1)+board.getNodes().size()/2;
                path = dijkstra.getPath(board.getNodes().get(randomNumber));
            }  
        }
        else{
            path = dijkstra.getPath(board.getPacman().getCell());
        }
    }
    
    public abstract void attack();
}
