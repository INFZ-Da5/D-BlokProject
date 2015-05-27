package infdpacman;

import javax.swing.JPanel;

/**
 *
 * @author Lenovo
 */
public class Speelbord extends JPanel {
    int height;
    int length;
    Vakje[][] vakjes;
    
    
    
    public Speelbord(){
    vakjes = new Vakje[3][3]; 
    }
}
