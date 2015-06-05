package infdpacman;

import infdpacman.Wall;
import infdpacman.item.Item;
import infdpacman.poppetje.Character;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import javax.swing.JPanel;

/**
 *
 * @author Lenovo
 */
public class EmptyCell extends JPanel{
    private LinkedList inhoud = new LinkedList(); 
    private Map<EmptyCell, Direction> neighbors = new HashMap<EmptyCell, Direction>();
    ArrayList<GameElement> gameElements = new ArrayList();
    
    public EmptyCell(){
        this.setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
    
    private void draw(Graphics g){
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
            gameElements.add((Character)obj);
        }
    }
}
