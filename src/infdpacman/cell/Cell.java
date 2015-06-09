package infdpacman.cell;

import infdpacman.enums.Direction;
import infdpacman.gameelement.character.Pacman;
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
    
    public Map<Direction, Cell> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(Map<Direction, Cell> neighbors) {
        this.neighbors = neighbors;
    }
     
    //geen inhoud van neighbors wat
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
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
        this.repaint();
    }
    abstract void draw(Graphics g);
}
