package infdpacman.cell;

import infdpacman.Game;
import infdpacman.gameelement.GameElement;
import infdpacman.gameelement.character.DrunkGhost;
import infdpacman.gameelement.character.GameCharacter;
import infdpacman.gameelement.character.Ghost;
import infdpacman.gameelement.character.Pacman;
import infdpacman.gameelement.item.Item;
import infdpacman.gameelement.item.SuperPill;
import infdpacman.utilities.FindClassType;
import infdpacman.view.Board;
import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class EmptyCell extends Cell{
    private List<GameElement> inhoud = new LinkedList<>(); 
    Board board;
    
    public EmptyCell(Board board){
        this.setBackground(Color.BLACK);
        this.board = board;
    }
    
    @Override
    public void draw(Graphics g){
        checkCollision();
        for (GameElement inhoud1 : inhoud) {
            if(inhoud1 instanceof Item){
                inhoud1.draw(g, this.getWidth()/2, this.getHeight()/2);
            }
            else{
                inhoud1.draw(g, this.getWidth(), this.getHeight());
            }
        }
    }

    public List<GameElement> getInhoud() {
        return inhoud;
    }
        
    public void setInhoud(LinkedList inhoud) {
        this.inhoud = inhoud;
    } 

    private void checkCollision() {
        
        
        if(board.getPacman().onverslaanbaar = true){
            onverslaanbaarCollision();
        }else{
        checkGhostCollision();
        }
        checkItemCollision();
    }

    private void checkGhostCollision() {
        if(FindClassType.containsInstance(inhoud, Pacman.class) 
            && (FindClassType.containsInstance(inhoud, Ghost.class) || FindClassType.containsInstance(inhoud, DrunkGhost.class)) ){
            for (GameElement inhoud1 : inhoud) {
                if(inhoud1 instanceof Pacman){
<<<<<<< HEAD
                    if(((Pacman)inhoud1).invincible == false){
=======
                   
>>>>>>> origin/vakjes-versie
                        ((Pacman)inhoud1).lives -= 1;
                        inhoud.remove((Pacman)inhoud1);
                       
                        ((EmptyCell)board.getPacmanRespawnCell()).getInhoud().add(board.getPacman());
                        board.getPacman().setCell((EmptyCell)board.getPacmanRespawnCell());
                    
                   
                }
            }
        }
    }
    
    
    
    private void onverslaanbaarCollision(){


        if(FindClassType.containsInstance(inhoud, Pacman.class) 
           && (FindClassType.containsInstance(inhoud, Ghost.class) || FindClassType.containsInstance(inhoud, DrunkGhost.class)) ){
            for (GameElement inhoud1 : inhoud) {
                if(inhoud1 instanceof Pacman){
                    for(GameElement inhoud2: inhoud){
                            if(inhoud2 instanceof Ghost || inhoud2 instanceof DrunkGhost){
                                inhoud.remove(inhoud2);
                                ((EmptyCell)board.getGhostRespawnCell()).getInhoud().add(inhoud2);
                                
                                if(inhoud2 instanceof Ghost){
                                    ((Ghost)inhoud2).setCell((EmptyCell)board.getGhostRespawnCell());
                                    Game.getPlayer().setScore(Game.getPlayer().getScore() + ((Ghost)inhoud2).points);
                                }
                                else if(inhoud2 instanceof DrunkGhost){
                                    ((DrunkGhost)inhoud2).setCell((EmptyCell)board.getGhostRespawnCell());
                                    Game.getPlayer().setScore(Game.getPlayer().getScore() + ((DrunkGhost)inhoud2).points);
                                }
                            }
                    }
                }
            }
        }
    }
    
    
    
    
    
    private void checkItemCollision() {
        if(FindClassType.containsInstance(inhoud, Pacman.class) && FindClassType.containsInstance(inhoud, Item.class)){
            for (GameElement inhoud1 : inhoud) {
                if(inhoud1 instanceof Item){
                    Game.getPlayer().setScore(Game.getPlayer().getScore() + ((Item)inhoud1).points );
                    inhoud.remove(inhoud1);
                    board.setAmountOfPills(board.getAmountOfPills()-1);
                    if(inhoud1 instanceof SuperPill){
                        board.getPacman().invincible = true;
                        for(GameCharacter gc: board.getGhosts()){
                            if(gc instanceof DrunkGhost){
                                ((DrunkGhost)gc).flee();
                            }
                            if(gc instanceof Ghost){
                                ((Ghost)gc).flee();
                            }
                        }
                    }
                }
            }
        }
    }
}
