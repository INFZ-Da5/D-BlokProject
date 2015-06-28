package infdpacman.gameelement.character;

import infdpacman.cell.Cell;
import infdpacman.utilities.dijkstra.DijkstraAlgorithm;
import infdpacman.view.Board;
import java.util.LinkedList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;

/**
 *
 * @author CVD
 */
public abstract class Ghost extends GameCharacter{
    protected ImageIcon normalGhost;
    private final ImageIcon fleeGhost;    
    protected ImageIcon currentImage;
    private final int points;
    protected boolean flee;
    protected boolean firstStep;
    private boolean stopTimer;
    private boolean eaten;
    protected Board board;
    protected LinkedList<Cell> path;
    protected DijkstraAlgorithm dijkstra;
    protected final Random r;
    private int ghostTimerMs;
    
    public Ghost(ImageIcon i, Board board) {
        super(i);
        this.board = board;
        fleeGhost = new ImageIcon("Plaatjes/fleeghost.png");
        flee = false;
        eaten = false;
        firstStep = true;
        r = new Random();
        points = 200;
        ghostTimerMs = 600;
    }

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
    
    public boolean isStopTimer() {
        return stopTimer;
    }

    public void setStopTimer(boolean stopTimer) {
        this.stopTimer = stopTimer;
    }
    
    //nullpointer met invincible
    public void ghostTimer(Timer t) {
        TimerTask task = new TimerTask(){
            public void run(){ 
               if(stopTimer){
                    t.cancel();
                }
                else{
                    if(!eaten){
                        moveGhost();
                    }
                }
            }
        };
        t.scheduleAtFixedRate(task, 0, ghostTimerMs);
    }

    public int getGhostTimerMs() {
        return ghostTimerMs;
    }

    public void setGhostTimerMs(int ghostTimerMs) {
        this.ghostTimerMs = ghostTimerMs;
    }

    public void setEaten(boolean eaten) {
        this.eaten = eaten;
    }
}
