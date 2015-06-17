package infdpacman.gameelement.character;

import infdpacman.cell.Cell;
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
    private boolean invincible;
    private boolean stopTimer;
    private boolean firstMove;
    private Timer timer;
    private Cell cell;
    private Map<Direction, ImageIcon> images;

    private ImageIcon westImage;
    private ImageIcon eastImage;
    private ImageIcon northImage;
    private ImageIcon southImage;
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
        invincible = false;
        lives = 3;
        firstMove = true;
        currentImage = westImage;
        timer = new Timer();
        stopTimer = false;
    }

    public boolean isStopTimer() {
        return stopTimer;
    }

    public void setStopTimer(boolean stopTimer) {
        this.stopTimer = stopTimer;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }
       
    @Override
    public void keyTyped(KeyEvent ke) {}

    @Override
    public void keyPressed(KeyEvent ke) {
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
        if(firstMove){
            moveTimer();
            firstMove = false;
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

    public void moveTimer(){
        TimerTask task;
        task = new TimerTask(){ 
            public void run(){
                if(stopTimer){
                    timer.cancel();
                }
                else{
                    currentImage = images.get(d);
                    movePacman(d);
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 400);
    }
    
    public void movePacman(Direction d){
        move(d, this);
    }
    
    @Override
    public void draw(Graphics g,int width, int height) {
        ImageIcon i = currentImage;
        Image img = i.getImage();
        g.drawImage(img, 0,0, width,height, null);    
    }
}
