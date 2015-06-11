package infdpacman.view;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author CVD
 */
public class Winner extends JPanel {
        
        public Winner(){
            JLabel label = new JLabel("You win!");
            label.setSize(this.getWidth(), this.getHeight());
            this.add(label);
        }
}
