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
     
    public Cell getCellOfCharacter(infdpacman.gameelement.character.Character character) {
        if((!(this instanceof Wall))&&((EmptyCell)this).getInhoud().contains(character)){
                return this;
        }
        else{
            for (Map.Entry<Direction, Cell> entry : neighbors.entrySet()) {
                Cell value = entry.getValue();
                if(!(value instanceof Wall)){
                    if(((EmptyCell)value).getInhoud().contains(character)){
                        return value;
                    }
                }
                else{
                    return value.getCellOfCharacter(character);
                }
            }
        }
        return null;
    }
    
    //oneindig: degene die 
    public void countPills(){
        if((!(this instanceof Wall))&&FindClassType.containsInstance(((EmptyCell)this).getInhoud(), Item.class)){
            amountOfPills++;
        }
        else{
            for (Map.Entry<Direction, Cell> entry : neighbors.entrySet()) {
                entry.getValue().countPills();
            }
        }  System.out.println(amountOfPills);
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
