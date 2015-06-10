package infdpacman.cell;

import infdpacman.Player;
import infdpacman.gameelement.GameElement;
import infdpacman.gameelement.character.DrunkGhost;
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
            if(FindClassType.containsInstance(inhoud, Item.class)){
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
        checkItemCollision();
        checkGhostCollision();
    }

    private void checkGhostCollision() {
        if(FindClassType.containsInstance(inhoud, Pacman.class) 
            && (FindClassType.containsInstance(inhoud, Ghost.class) || FindClassType.containsInstance(inhoud, DrunkGhost.class)) ){
            for (GameElement inhoud1 : inhoud) {
                if(inhoud1 instanceof Pacman){
                    if(((Pacman)inhoud1).onverslaanbaar == false){
                        inhoud.remove(inhoud1);
                        ((Pacman)inhoud1).lives -= 1;
                        ((EmptyCell)board.getPacmanCell()).getInhoud().add(board.getPacman());
                        board.getPacman().setFirstMove(true);
                    }
                    else{
                        for(GameElement inhoud2: inhoud){
                            if(inhoud2 instanceof Ghost || inhoud2 instanceof DrunkGhost){
                                inhoud.remove(inhoud2);
                                //board.total--;
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
                    board.player.setScore(board.player.getScore() + ((Item)inhoud1).points );
 ;
                    //((Item)inhoud1).points; -->score moet aan player toegevoegd worden
                    inhoud.remove(inhoud1);
                    board.amountofPills--;
                    if(inhoud1 instanceof SuperPill){
                    
                    board.getPacman().onverslaanbaar = true;
                        System.out.println(board.getPacman().getOnverslaanbaar());
                    }
                }
            }
        }
    }
}
