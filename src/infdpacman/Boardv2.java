package infdpacman;

import infdpacman.item.Pill;
import infdpacman.character.DrunkGhost;
import infdpacman.character.Pacman;
import infdpacman.character.Character;
import infdpacman.character.Ghost;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Lenovo
 */
public abstract class Boardv2 extends JPanel  {
    int height = 8;
    int length = 8;
    Cell[][] vakjes = new Cell[length][height];
    Pacman p = new Pacman(this);
    
    public Boardv2(){
        this.requestFocusInWindow();
        this.addKeyListener(p);
        this.repaint();
        this.setLayout(new GridLayout(length,height));
        
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < length; col++) {
                vakjes[row][col] = new EmptyCell();
                this.add(vakjes[row][col]);
            }
        }
    }
    
    public abstract void initLevel();
    
    public abstract void fillVakjes();
}
