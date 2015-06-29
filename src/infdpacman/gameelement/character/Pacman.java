package infdpacman.gameelement.character;

import infdpacman.cell.Cell;
import infdpacman.cell.EmptyCell;
import infdpacman.enums.Direction;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;

/**
 *
 * @author Lenovo
 */
public class Pacman extends GameCharacter implements KeyListener {
    private int lives;
    private final int invincibleTimeInMs;
    private boolean keys = true;
    private boolean invincible;
    private boolean stopTimer;
    private boolean firstMove;
    //private final Timer timer;
    private Cell cell;
    private final Cell spawnCell;
    private final Map<Direction, ImageIcon> images;

    private final ImageIcon westImage;
    private final ImageIcon eastImage;
    private final ImageIcon northImage;
    private final ImageIcon southImage;
    private ImageIcon currentImage;
    private Direction d;
    
    public Pacman(Cell cell){
        super(new ImageIcon("Plaatjes/pacmanleft.png"));
        
        westImage = new ImageIcon("Plaatjes/pacmanleft.png");
        eastImage = new ImageIcon("Plaatjes/pacmanright.png");
        southImage = new ImageIcon("Plaatjes/pacmandown.png");
        northImage = new ImageIcon("Plaatjes/pacmanup.png");
        images = new HashMap<>();
        
        images.put(Direction.WEST, westImage);
        images.put(Direction.EAST, eastImage);
        images.put(Direction.NORTH, northImage);
        images.put(Direction.SOUTH, southImage);
        this.cell = cell;
        lives = 3;
        invincible = false;
        firstMove = true;
        currentImage = westImage;
        //timer = new Timer();
        stopTimer = false;
        invincibleTimeInMs = 10000;
        this.spawnCell = cell;
    }

    public void setKeys(boolean keys){
        this.keys = keys;
    }

    public int getInvincibleTimeInMs() {
        return invincibleTimeInMs;
    }
    
    public boolean isStopTimer() {
        return stopTimer;
    }

    public void setStopTimer(boolean stopTimer) {
        this.stopTimer = stopTimer;
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
    public void keyTyped(KeyEvent ke) {}

    @Override
    public void keyPressed(KeyEvent ke) {
        if(keys)
        switch (ke.getKeyCode()){
            case KeyEvent.VK_DOWN:
                d = Direction.SOUTH;
                break;
            case KeyEvent.VK_UP:
                d = Direction.NORTH;
                break;
            case KeyEvent.VK_RIGHT:
                d = Direction.EAST;
                break;
            case KeyEvent.VK_LEFT:
                d = Direction.WEST; 
               break;
        }
        else{
        switch (ke.getKeyCode()){
            case KeyEvent.VK_S:
                d = Direction.SOUTH;
                break;
            case KeyEvent.VK_W:
                d = Direction.NORTH;
                break;
            case KeyEvent.VK_D:
                d = Direction.EAST;
                break;
            case KeyEvent.VK_A:
                d = Direction.WEST; 
               break;
        }
        }
        if(firstMove){
            firstMove = false;
            moveTimer(new Timer());
        }
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public boolean isInvincible() {
        return invincible;
    }

    public void setInvincible(boolean invincible) {
        this.invincible = invincible;
    }
    
    @Override
    public void keyReleased(KeyEvent ke) {}

    public void moveTimer(Timer timer){
        TimerTask task = new TimerTask(){ 
            public void run(){
                if(stopTimer){
                    timer.cancel();
                }
                else{
                    currentImage = images.get(d);
                    ((EmptyCell)cell).checkCollision();
                    movePacman(d);
                    ((EmptyCell)cell).checkCollision();
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 400);
    }
    
    public void movePacman(Direction d){
        move(d, this);
    }

    public void setFirstMove(boolean firstMove) {
        this.firstMove = firstMove;
    }
    
    
    
    @Override
    public void draw(Graphics g,int width, int height) {
        ImageIcon i = currentImage;
        Image img = i.getImage();
        g.drawImage(img, 0,0, width,height, null);    
    }

    public void invincibleTimer(Timer t) {
        invincible = true;
        TimerTask task = new TimerTask(){
            public void run(){
                t.cancel();
                invincible = false;
            }
        };
        t.scheduleAtFixedRate(task, invincibleTimeInMs, 1);
    }

    @Override
    public Cell getSpawnCell() {
        return spawnCell;
    }    
}
