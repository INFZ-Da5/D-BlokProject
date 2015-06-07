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
public abstract class Board extends JPanel  {
    int height;
    int width;
    Pacman p = new Pacman(this);
    
    public Board(){
        this.requestFocusInWindow();
        this.addKeyListener(p);
        this.repaint();
    }
    
    public abstract void initLevel();
    
    public abstract void fillVakjes();
}
