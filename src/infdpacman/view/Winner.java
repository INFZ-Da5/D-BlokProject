package infdpacman.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author CVD
 */
public class Winner extends JPanel {
        
        public Winner(){
            JLabel winLabel = new JLabel("You win!");
            winLabel.setFont(new Font("Helvetica", Font.PLAIN, 88));
            FontMetrics fm = winLabel.getFontMetrics(winLabel.getFont());
            TextLayout layout = new TextLayout(winLabel.getText(), winLabel.getFont(), fm.getFontRenderContext());
            Rectangle2D bounds = layout.getBounds();
            Dimension d = winLabel.getPreferredSize();
            winLabel.setSize(this.getWidth(), this.getHeight());
            this.add(winLabel);
        }
}
