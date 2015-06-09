package infdpacman;

import infdpacman.enums.Actions;
import infdpacman.view.Board;
import infdpacman.view.Level1;
import infdpacman.view.Level2;
import infdpacman.view.Level5;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Lenovo
 */
public class Game implements ActionListener {
    
    public ArrayList highscore;
    public JFrame frame;
    public Board currentSb;
    public Player p1 = new Player();
    public JLabel s = new JLabel();
    public JLabel j = new JLabel();
    public int currentlives;
    
    public void start(){
    
        frame = new JFrame();
        frame.setSize(1280,720);
        frame.setLayout(new BorderLayout());
        
        JPanel menu = new JPanel();
        JButton start = new JButton("start");
            start.setActionCommand(Actions.START.name());
            start.addActionListener(this);
        JButton stop = new JButton("stop");
            stop.setActionCommand(Actions.STOP.name());
            stop.addActionListener(this);
        JButton pauze = new JButton("pauze");
            pauze.setActionCommand(Actions.PAUZE.name());
            pauze.addActionListener(this);
        JButton reset = new JButton("opnieuw");
            reset.setActionCommand(Actions.RESET.name());
            reset.addActionListener(this);
        
        s.setText("" + p1.getScore());
        menu.add(start);
        menu.add(stop);
        menu.add(pauze);
        menu.add(reset);
        menu.add (s);
        menu.add(j);
        frame.add(menu, BorderLayout.NORTH);
        frame.setVisible(true);  
        
    }
    
   

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getActionCommand().equals(Actions.START.name())) {
            if(currentSb == null){
            Board level1 = new Level2();
            currentSb = level1;
            p1.setLevel(currentSb);
            //currentlives = currentSb.getPacman().lives;
            //j.setText("" + currentSb.getPacman().lives);
            frame.add(level1,BorderLayout.CENTER);      
            level1.requestFocus();
            frame.validate();
            }
        }else if(e.getActionCommand().equals(Actions.STOP.name())){
            
            frame.remove(currentSb);
            currentSb = null;
            frame.validate();
            frame.repaint();
            
        } else if(e.getActionCommand().equals(Actions.PAUZE.name())){

        }else if(e.getActionCommand().equals(Actions.RESET.name())){
            frame.remove(currentSb);
            Board level1 = new Level2();
            currentSb = level1;          
            frame.add(level1,BorderLayout.CENTER);      
            frame.validate();
        }
    }
    
    
    
 /*  
public void Lives(){

if(currentlives != currentSb.getPacman().lives){
            currentlives = currentSb.getPacman().lives;
            j.setText("" + currentSb.getPacman().lives);
}

}
    
   */ 
    
    
}
