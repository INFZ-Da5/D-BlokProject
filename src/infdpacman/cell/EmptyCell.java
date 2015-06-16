package infdpacman.cell;

import infdpacman.Game;
import infdpacman.gameelement.GameElement;
import infdpacman.gameelement.character.DrunkGhost;
import infdpacman.gameelement.character.GameCharacter;
import infdpacman.gameelement.character.Ghost;
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
        if(board.getPacman().invincible){
            invincibleCollision();
        }else{
            checkGhostCollision();
        }
        checkItemCollision();
    }

    private void checkGhostCollision() {
        if(FindClassTypeFromList.containsInstance(content, Pacman.class) 
            && (FindClassTypeFromList.containsInstance(content, Ghost.class) || FindClassTypeFromList.containsInstance(content, DrunkGhost.class)) ){
            for (GameElement content1 : content) {
                if(content1 instanceof Pacman){
                    ((Pacman)content1).lives -= 1;
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
           && (FindClassTypeFromList.containsInstance(content, Ghost.class) || FindClassTypeFromList.containsInstance(content, DrunkGhost.class)) ){
            for (GameElement c : content) {
                if(c instanceof Ghost || c instanceof DrunkGhost){
                    content.remove(c);
                    ghostRespawnTimer(new Timer(), c);
                    if(c instanceof Ghost){
                        ((Ghost)c).setCell((EmptyCell)board.getGhostRespawnCell());
                        Game.getPlayer().setScore(Game.getPlayer().getScore() + ((Ghost)c).getPoints());
                    }
                    else if(c instanceof DrunkGhost){
                        ((DrunkGhost)c).setCell((EmptyCell)board.getGhostRespawnCell());
                        Game.getPlayer().setScore(Game.getPlayer().getScore() + ((DrunkGhost)c).getPoints());
                    }
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
                        board.getPacman().invincible = true;
                        invincibleTimer(new Timer());
                    }
                    break;
                }
            }
        }
    }
    
    private void invincibleTimer(Timer t) {
        setGhostImage();
        TimerTask task = new TimerTask(){
            public void run(){
                t.cancel();
                board.getPacman().invincible = false;
                setGhostImage();
            }
        };
        t.scheduleAtFixedRate(task, 20000, 1);

    }
    
    private void ghostRespawnTimer(Timer t, GameElement g) {
        TimerTask task = new TimerTask(){
            public void run(){
                t.cancel();
                //((EmptyCell)board.getGhostRespawnCell()).getInhoud().add(g); drunkghost respawned gwn?!?!
            }
        };
        t.scheduleAtFixedRate(task, 5000, 1);
    }
    
    private void setGhostImage(){
        if(board.getPacman().invincible){
            for(GameCharacter gc: board.getGhosts()){
                if(gc instanceof Ghost){
                  ((Ghost)gc).flee();
                }
                else if(gc instanceof DrunkGhost){
                  ((DrunkGhost)gc).flee();
                }
            }
        }
        else{
            for(GameCharacter gc: board.getGhosts()){
                if(gc instanceof Ghost){
                  ((Ghost)gc).attack();
                }
                else if(gc instanceof DrunkGhost){
                  ((DrunkGhost)gc).normal();
                }
            }
        }
    }
}
