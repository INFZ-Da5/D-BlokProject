package infdpacman;

import infdpacman.item.Bolletje;
import infdpacman.poppetje.Pacman;
import infdpacman.poppetje.Poppetje;
import infdpacman.poppetje.Spook;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Lenovo
 */
public class Speelbord extends JPanel implements KeyListener {
    int height = 8;
    int length = 8;
    ArrayList<Poppetje> poppetjes = new ArrayList();
    Pacman p = new Pacman();
    Vakje[][] vakjes = new Vakje[length][height];
    
    public Speelbord(){
        poppetjes.add(p);
        this.requestFocusInWindow();
        this.repaint();
        this.setBackground(Color.green);
        this.setLayout(new GridLayout(length,height));
        
        for (int row = 0; row < length; row++) {
            for (int col = 0; col < height; col++) {
                vakjes[row][col] = new Vakje();
                this.add(vakjes[row][col]);
            }
        }
        initLevel();
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {}

    @Override
    public void keyPressed(KeyEvent ke) {
        switch (ke.getKeyCode())
        {
            case KeyEvent.VK_DOWN:
                movePacman(Direction.SOUTH);
                break;
            case KeyEvent.VK_UP:
                movePacman(Direction.NORTH);
                break;
            case KeyEvent.VK_RIGHT:
                movePacman(Direction.EAST);
                break;
            case KeyEvent.VK_LEFT:
                movePacman(Direction.WEST);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {}
    
    private void movePacman(Direction direction){
        p.bewegen(direction);
        repaint();
    }

    private void initLevel() {
        Spook sp = new Spook();
        Muur m = new Muur();
        Bolletje b = new Bolletje();
        vakjes[2][4].getInhoud().add(p);
        vakjes[4][3].getInhoud().add(sp);
        vakjes[4][4].getInhoud().add(sp);
        vakjes[5][2].getInhoud().add(sp);
        vakjes[5][1].getInhoud().add(b);
        
        for (int row = 0; row < length; row++) {
            for (int col = 0; col < height; col++) {
                vakjes[row][0].getInhoud().add(m);
                vakjes[0][col].getInhoud().add(m);
                vakjes[row][length-1].getInhoud().add(m);
                vakjes[height-1][col].getInhoud().add(m);
            }
        }
        
        for (int row = 0; row < length; row++) {
            for (int col = 0; col < height; col++) {
                vakjes[row][col].vulVakje();
            }
        }
        
    }
}
