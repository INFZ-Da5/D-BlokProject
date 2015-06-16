
package infdpacman;

import java.awt.GridLayout;
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
    private ArrayList hscore;
    private int score;
    private JTextField TFname;
    private JFrame submitscore;
    
    public insertHighScore(ArrayList hscore, int score){
    
    this.hscore = hscore;
    this.score = score;
    }
    
    
    public void init(){
    
    
    submitscore = new JFrame();
    submitscore.setLayout(new GridLayout(0,2));
    TFname = new JTextField();
    JButton submit = new JButton("opslaan");
    submit.addActionListener(this);
    
    submitscore.add(TFname);
    submitscore.add(submit);
    
    
    submitscore.setVisible(true);
    
    }
    
    
   
    
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
       hscore.add(score + "" + TFname.getText() );
       Collections.sort(hscore);
       submitscore.dispose();
    }
    
}
