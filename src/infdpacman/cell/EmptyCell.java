package infdpacman.cell;

import infdpacman.enums.Direction;
import infdpacman.gameelement.GameElement;
import infdpacman.gameelement.character.Character;
import infdpacman.gameelement.character.Pacman;
import infdpacman.gameelement.item.Pill;
import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
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
        
    }
}
