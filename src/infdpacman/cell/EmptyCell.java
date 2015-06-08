package infdpacman.cell;

import infdpacman.enums.Direction;
import infdpacman.gameelement.GameElement;
import infdpacman.gameelement.character.Character;
import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Lenovo
 */
public class EmptyCell extends Cell{
    private List<GameElement> inhoud = new LinkedList<>(); 
    private Map<Direction, Cell> neighbors = new HashMap<>();
    
    public EmptyCell(){
        this.setBackground(Color.BLACK);
    }
    
    @Override
    public void draw(Graphics g){
        for (GameElement inhoud1 : inhoud) {
            //character: instanceof werkt niet en isInstance geeft altijd true
            inhoud1.draw(g, this.getWidth(), this.getHeight());
        }
    }

    public List<GameElement> getInhoud() {
        return inhoud;
    }

    public Map<Direction, Cell> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(Map<Direction, Cell> neighbors) {
        this.neighbors = neighbors;
    }
        
    public void setInhoud(LinkedList inhoud) {
        this.inhoud = inhoud;
    } 
}
