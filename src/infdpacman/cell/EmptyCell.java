package infdpacman.cell;

import infdpacman.gameelement.GameElement;
import infdpacman.gameelement.character.DrunkGhost;
import infdpacman.gameelement.character.Ghost;
import infdpacman.gameelement.character.Pacman;
import infdpacman.gameelement.item.Item;
import infdpacman.gameelement.item.Pill;
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
            //character: instanceof werkt niet en isInstance geeft altijd true
            inhoud1.draw(g, this.getWidth(), this.getHeight());
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
                    //-leven
            for (GameElement inhoud1 : inhoud) {
                if(inhoud1 instanceof Pacman){
                    //speler punten ((Item)inhoud1).points;
                    if(((Pacman)inhoud1).onverslaanbaar == false){
                    ((Pacman)inhoud1).lives -= 1;
                  System.out.println(((Pacman)inhoud1).lives);
                 inhoud.remove(inhoud1);
                  
                }else{
                        for(GameElement inhoud2: inhoud){
                    
                            if(inhoud2 instanceof Ghost || inhoud2 instanceof DrunkGhost){
                    
                                inhoud.remove(inhoud2);
                                board.total--;
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
                    for(GameElement inhoud2: inhoud){
                        if(inhoud2 instanceof Pacman){
                            
                    ((Pacman)inhoud2).score = ((Pacman)inhoud2).score + ((Item)inhoud1).points;
                      
                        }  
                    }
                    inhoud.remove(inhoud1);
                }
            }
        }
    }
}
