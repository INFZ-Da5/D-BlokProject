package infdpacman;

import infdpacman.poppetje.Pacman;
import infdpacman.poppetje.Poppetje;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Lenovo
 */
public class Speelbord extends JPanel implements KeyListener {
    int height;
    int length;
    ArrayList<Poppetje> poppetjes = new ArrayList();
    Pacman p = new Pacman(100,100);
    Vakje[][] vakjes;
    
    public Speelbord(){
        vakjes = new Vakje[5][5];
        poppetjes.add(p);
        this.requestFocusInWindow();
        this.repaint();
        this.setBackground(Color.green);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
    
    private void draw(Graphics g){
        for(Poppetje poppetje: poppetjes){
            poppetje.draw(g);
        }
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
}
