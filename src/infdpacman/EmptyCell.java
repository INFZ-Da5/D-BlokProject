package infdpacman;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 * @author Lenovo
 */
public class EmptyCell extends Cell{
    private LinkedList inhoud = new LinkedList(); 
    private Map<EmptyCell, Direction> neighbors = new HashMap<>();
    private ArrayList<GameElement> gameElements = new ArrayList();
    
    public EmptyCell(){
        this.setBackground(Color.BLACK);
    }
    
    //draw the object, not the arraylist --> cant with typecasting
    @Override
    public void draw(Graphics g){
        for(GameElement elem: gameElements){
            elem.draw(g,this.getWidth(),this.getHeight());
        }
    }
    
    public LinkedList getInhoud() {
        return inhoud;
    }

    public void setInhoud(LinkedList inhoud) {
        this.inhoud = inhoud;
    } 
    
    public void vulVakje(){
        gameElements.clear();
        for(Object obj : inhoud){
            gameElements.add((GameElement)obj);
        }
    }
}
