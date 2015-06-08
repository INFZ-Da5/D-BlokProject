package infdpacman.cell;

import infdpacman.gameelement.GameElement;
import infdpacman.gameelement.character.Pacman;
import infdpacman.gameelement.item.Item;
import infdpacman.gameelement.item.Pill;
import infdpacman.utilities.FindClassType;
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
    
    public EmptyCell(){
        this.setBackground(Color.BLACK);
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
            && (FindClassType.containsInstance(inhoud, Item.class) || FindClassType.containsInstance(inhoud, Item.class)) ){
                    //-leven
            for (GameElement inhoud1 : inhoud) {
                if(inhoud1 instanceof Pacman){
                    //speler punten ((Item)inhoud1).points;
                    ((Pacman)inhoud1).lives -= 1;
                }
            }
        }
    }
    
    private void checkItemCollision() {
        if(FindClassType.containsInstance(inhoud, Pacman.class) && FindClassType.containsInstance(inhoud, Item.class)){
            for (GameElement inhoud1 : inhoud) {
                if(inhoud1 instanceof Item){
                    //speler punten ((Item)inhoud1).points;
                    inhoud.remove(inhoud1);
                }
            }
        }
    }
}
