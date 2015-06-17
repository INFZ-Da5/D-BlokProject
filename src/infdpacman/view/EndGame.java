package infdpacman.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author CVD
 */
public class EndGame extends JPanel implements ActionListener{
    private Map<String, Integer> hScore;
    private int score;
    private JTextField TFname;
    private JButton submit;
    
    public EndGame(Map hScore, int score, boolean winner){
        this.hScore = hScore;
        this.score = score;
        createBigLabel(winner);

        TFname = new JTextField();
        submit = new JButton("opslaan");
        submit.addActionListener(this);

        this.add(TFname);
        this.add(submit);

        
        this.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        hScore.put(TFname.getText(), score);
        this.remove(TFname);
        this.remove(submit);
        
        sortByValue(hScore);
        JTextArea ta = new JTextArea();
        String message = "Highscores:\n";
        for ( Map.Entry<String, Integer> entry : hScore.entrySet()) {
            String name = entry.getKey();
            Integer score = entry.getValue();
            message = message + name + score + "\n";
        }
        ta.append(message);
        this.add(ta);
    }

    private void createBigLabel(boolean winner) {
        JLabel winLabel;
        if(winner){
            winLabel = new JLabel("You win!");
        }
        else{        
            winLabel = new JLabel("You lose!");
        }
        winLabel.setFont(new Font("Helvetica", Font.PLAIN, 88));
        FontMetrics fm = winLabel.getFontMetrics(winLabel.getFont());
        TextLayout layout = new TextLayout(winLabel.getText(), winLabel.getFont(), fm.getFontRenderContext());
        Rectangle2D bounds = layout.getBounds();
        Dimension d = winLabel.getPreferredSize();
        winLabel.setSize(this.getWidth(), this.getHeight());
        this.add(winLabel);
    }
    
    public static <K, V extends Comparable<? super V>> Map<K, V> 
    sortByValue( Map<K, V> map )
    {
        List<Map.Entry<K, V>> list =
            new LinkedList<>( map.entrySet() );
        Collections.sort( list, new Comparator<Map.Entry<K, V>>()
        {
            @Override
            public int compare( Map.Entry<K, V> o1, Map.Entry<K, V> o2 )
            {
                return (o1.getValue()).compareTo( o2.getValue() );
            }
        } );

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list)
        {
            result.put( entry.getKey(), entry.getValue() );
        }
        return result;
    }
}
