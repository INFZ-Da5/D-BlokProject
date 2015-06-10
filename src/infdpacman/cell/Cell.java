package infdpacman.cell;

import infdpacman.enums.Direction;
import infdpacman.gameelement.item.Item;
import infdpacman.utilities.FindClassType;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;

/**
 *
 * @author CVD
 */
public abstract class Cell extends JPanel{
    private Map<Direction, Cell> neighbors = new HashMap<>();
    private static int amountOfPills = 0;
    
 
    public Map<Direction, Cell> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(Map<Direction, Cell> neighbors) {
        this.neighbors = neighbors;
    }
     
    public Cell getCellOfCharacter(infdpacman.gameelement.character.GameCharacter character) {
        if((!(this instanceof Wall))&&((EmptyCell)this).getInhoud().contains(character)){
            return this;
        }
        else{
            Cell value = null;
            for (Map.Entry<Direction, Cell> entry : this.neighbors.entrySet()) {
                value = entry.getValue();
                if((!(value instanceof Wall))&&((EmptyCell)value).getInhoud().contains(character)){
                    return value;
                }
            }
            for (Map.Entry<Direction, Cell> entry : value.neighbors.entrySet()) {
                Cell v = entry.getValue();
                if((!(v instanceof Wall))&&((EmptyCell)v).getInhoud().contains(character)){
                    return v;
                }
            }
            /*
            for (Map.Entry<Direction, Cell> entry : this.neighbors.entrySet()) {
                value = entry.getValue();
                return value.getCellOfCharacter(character);
            }*/
        }
        return null;
    }

    public int getAmountOfPills() {
        return amountOfPills;
    }
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
        this.repaint();
    }
    abstract void draw(Graphics g);
}
