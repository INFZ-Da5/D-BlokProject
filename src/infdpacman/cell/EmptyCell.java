package infdpacman.cell;

import infdpacman.Game;
import infdpacman.gameelement.GameElement;
import infdpacman.gameelement.character.DrunkGhost;
import infdpacman.gameelement.character.GameCharacter;
import infdpacman.gameelement.character.Ghost;
import infdpacman.gameelement.character.SmartGhost;
import infdpacman.gameelement.character.Pacman;
import infdpacman.gameelement.item.Item;
import infdpacman.gameelement.item.SuperPill;
import infdpacman.utilities.FindClassTypeFromList;
import infdpacman.view.Board;
import java.awt.Color;
import java.awt.Graphics;
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
    private List<GameElement> content = new LinkedList<>(); 
    private Board board;
    
    public EmptyCell(Board board){
        this.setBackground(Color.BLACK);
        this.board = board;
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
                    ((EmptyCell)board.getPacmanRespawnCell()).getInhoud().add(board.getPacman());
                    board.getPacman().setCell((EmptyCell)board.getPacmanRespawnCell());
                    break;
                }
            }
        }
    }
        
    private void invincibleCollision(){
        if(FindClassTypeFromList.containsInstance(content, Pacman.class) 
           && (FindClassTypeFromList.containsInstance(content, Ghost.class)) ){
            GameElement[] a = content.toArray(new GameElement[content.size()]); //make an array to prevent ConcurrentModificationException
            content.clear();
            for (GameElement c : a) {
                if(c instanceof Ghost){
                    board.getGhosts().remove((GameCharacter)c);
                    a = ArrayUtils.removeElement(a, c);
                    Game.getPlayer().setScore(Game.getPlayer().getScore() + ((Ghost)c).getPoints());
                    ghostRespawnTimer(new Timer(), c);
                }
            }
            for(int i = 0; i < a.length; i++){
                content.add(i, a[i]);
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
                        board.getPacman().setInvincible(true);
                        invincibleTimer(new Timer());
                    }
                    break;
                }
            }
        }
    }
    
    private void invincibleTimer(Timer t) {
        setGhostState();
        TimerTask task = new TimerTask(){
            public void run(){
                t.cancel();
                board.getPacman().setInvincible(false);
                setGhostState();
            }
        };
        t.scheduleAtFixedRate(task, 10000, 1);

    }
    
    private void ghostRespawnTimer(Timer t, GameElement g) {
        TimerTask task = new TimerTask(){
            public void run(){
                t.cancel();
                //optie 1:
                ((Ghost)g).attack();
                ((Ghost)g).setCell((EmptyCell)board.getGhostRespawnCell());
                board.getGhosts().add((GameCharacter)g);
                
                //optie 2:
                /*
                if(g instanceof SmartGhost){
                    Ghost ghost = new SmartGhost(board.getGhostRespawnCell(), board);
                    board.getGhosts().add(ghost);
                }
                else{
                    Ghost ghost = new DrunkGhost(board.getGhostRespawnCell(), board);   
                    board.getGhosts().add(ghost);
                }*/
            }
        };
        t.scheduleAtFixedRate(task, 5000, 1);
    }
    
    private void setGhostState(){
        if(board.getPacman().isInvincible()){
            for(GameCharacter gc: board.getGhosts()){
                ((Ghost)gc).flee();
            }
        }
        else{
            for(GameCharacter gc: board.getGhosts()){
                ((Ghost)gc).attack();
            }
        }
    }
}
