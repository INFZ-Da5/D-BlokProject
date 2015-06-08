package infdpacman.cell;

import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author CVD
 */
public abstract class Cell extends JPanel{
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }
    abstract void draw(Graphics g);
}
