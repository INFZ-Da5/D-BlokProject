package infdpacman.cell;

import infdpacman.enums.Direction;
import infdpacman.gameelement.character.Pacman;
import infdpacman.gameelement.item.Item;
import infdpacman.gameelement.item.Pill;
import infdpacman.gameelement.item.SuperPill;
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
    private int amountOfPills = 0;
    
 
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
    
    //nu stopt hij dus als hij een pill heeft. Hij moet iedere pill vinden.
    //alleen als je iedere keer neighbors checkt dan is het oneindig
    public void countPills(){
        if((!(this instanceof Wall))&&FindClassType.containsInstance(((EmptyCell)this).getInhoud(), Item.class)){
            amountOfPills++;
        }
        else{
            for (Map.Entry<Direction, Cell> entry : neighbors.entrySet()) {
                Cell value = entry.getValue();
                if((!(value instanceof Wall))&&FindClassType.containsInstance(((EmptyCell)value).getInhoud(), Item.class)){
                        amountOfPills++;
                    }
                else{
                    value.countPills();
                }
            }
        }  System.out.println(amountOfPills);
    }
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
        this.repaint();
    }
    abstract void draw(Graphics g);
}
