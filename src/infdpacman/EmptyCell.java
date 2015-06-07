package infdpacman;

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
    private List<GameElement> inhoud = new LinkedList<GameElement>(); 
    private Map<Direction, EmptyCell> neighbors = new HashMap<>();
    
    public EmptyCell(){
        this.setBackground(Color.BLACK);
    }
    
    @Override
    public void draw(Graphics g){
        for (GameElement inhoud1 : inhoud) {
            inhoud1.draw(g, this.getWidth(), this.getHeight());
        }
    }
    
    public void setInhoud(LinkedList inhoud) {
        this.inhoud = inhoud;
    } 
}
