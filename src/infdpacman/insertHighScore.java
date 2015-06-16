
package infdpacman;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 *
 * @author Lenovo
 */
public class insertHighScore implements ActionListener {
    public ArrayList hscore;
    public int score;
    public JTextField TFname;
    
    public insertHighScore(ArrayList hscore, int score){
    
    this.hscore = hscore;
    this.score = score;
    }
    
    
    public void init(){
    
    
    JFrame submitscore = new JFrame();
    
    TFname = new JTextField();
    JButton submit = new JButton();
    
    submitscore.add(TFname);
    submitscore.add(submit);
    
    
    
    
    }
    
    
   
    
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
       hscore.add(score + "" + TFname.getText() );
       Collections.sort(hscore);
    }
    
}
