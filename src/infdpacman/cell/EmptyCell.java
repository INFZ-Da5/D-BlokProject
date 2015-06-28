package infdpacman.cell;

import infdpacman.Game;
import infdpacman.gameelement.GameElement;
import infdpacman.gameelement.character.GameCharacter;
import infdpacman.gameelement.character.Ghost;
import infdpacman.gameelement.character.Pacman;
import infdpacman.gameelement.item.Item;
import infdpacman.gameelement.item.SuperPill;
import infdpacman.utilities.FindClassTypeFromList;
import infdpacman.view.Board;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import org.apache.commons.lang3.ArrayUtils;

/**
 *
 * @author Lenovo
 */
public class EmptyCell extends Cell{
    private List<GameElement> content; 
    private final Board board;
    
    public EmptyCell(Board board){
        this.setBackground(Color.BLACK);
        this.board = board;
        content = new LinkedList<>();
    }
    
    @Override
    public void draw(Graphics g){
        checkCollision();
        for (GameElement content1 : content) {
            if(content1 instanceof Item){
                content1.draw(g, this.getWidth()/2, this.getHeight()/2);
            }
            else{
                content1.draw(g, this.getWidth(), this.getHeight());
            }
        }
    }

    public List<GameElement> getInhoud() {
        return content;
    }
        
    public void setInhoud(LinkedList content) {
        this.content = content;
    } 

    private void checkCollision() {
        if(board.getPacman().isInvincible()){
            invincibleCollision();
        }else{
            checkGhostCollision();
        }
        checkItemCollision();
    }

    private void checkGhostCollision() {
        if(FindClassTypeFromList.containsInstance(content, Pacman.class) 
            && (FindClassTypeFromList.containsInstance(content, Ghost.class))){
            for (GameElement content1 : content) {
                if(content1 instanceof Pacman){
                    ((Pacman)content1).setLives(((Pacman)content1).getLives() -1);
                    content.remove(content1);
                    ((EmptyCell)board.getPacman().getSpawnCell()).getInhoud().add(board.getPacman());
                    board.getPacman().setCell((EmptyCell)board.getPacman().getSpawnCell());
                    break;
                }
            }
        }
    }
        
    private void invincibleCollision(){
        if(FindClassTypeFromList.containsInstance(content, Pacman.class) 
           && (FindClassTypeFromList.containsInstance(content, Ghost.class)) ){
            Iterator<GameElement> it = content.iterator();
            while(it.hasNext()){
                GameElement g = it.next();
                if(g instanceof Ghost){
                    ((Ghost)g).setEaten(true);
                    board.getGhosts().remove((GameCharacter)g);
                    it.remove();
                    Game.getPlayer().setScore(Game.getPlayer().getScore() + ((Ghost)g).getPoints());
                    board.ghostRespawnTimer(new Timer(), g);
                }
            }
        }
    }
    
    private void checkItemCollision() {
        if(FindClassTypeFromList.containsInstance(content, Pacman.class) && FindClassTypeFromList.containsInstance(content, Item.class)){
            for (GameElement content1 : content) {
                if(content1 instanceof Item){
                    Game.getPlayer().setScore(Game.getPlayer().getScore() + ((Item)content1).points );
                    content.remove(content1);
                    board.setAmountOfPills(board.getAmountOfPills()-1);
                    if(content1 instanceof SuperPill){
                        board.getPacman().invincibleTimer(new Timer());
                        ghostState(new Timer(), board.getPacman().getInvincibleTimeInMs());
                        for(GameCharacter gc: board.getGhosts()){
                            ((Ghost)gc).flee();
                        }
                    }
                    break;
                }
            }
        }
    }
    
    public void ghostState(Timer t, int amountOfTime) {
        TimerTask task = new TimerTask(){
            public void run(){
                t.cancel();
                for(GameCharacter gc: board.getGhosts()){
                    ((Ghost)gc).attack();
                }
            }
        };
        t.scheduleAtFixedRate(task, amountOfTime, 1);
    }    
}

