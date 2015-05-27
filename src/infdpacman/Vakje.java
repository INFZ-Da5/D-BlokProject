package infdpacman;

import java.awt.Color;
import java.util.LinkedList;
import javax.swing.JPanel;

/**
 *
 * @author Lenovo
 */
public class Vakje extends JPanel{
    private LinkedList inhoud = new LinkedList();      
    private final static Color[] COLORS = { Color.red, Color.orange,
         Color.yellow, Color.green, Color.blue, Color.cyan, Color.darkGray,
         Color.magenta, Color.pink };
   private int colorIndex = (int) (Math.random() * COLORS.length);
   
    public Vakje(){
        this.setBackground(COLORS[colorIndex]);
    }

    public LinkedList getInhoud() {
        return inhoud;
    }

    public void setInhoud(LinkedList inhoud) {
        this.inhoud = inhoud;
    }
    
    public void addToList(Object o){
        inhoud.add(o);
    }
    
}
