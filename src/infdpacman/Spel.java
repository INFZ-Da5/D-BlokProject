package infdpacman;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Lenovo
 */
public class Spel implements ActionListener {
    
    public ArrayList highscore;
    public JFrame frame;
    public Speelbord currentSb;
    
    public void start(){
    
        frame = new JFrame();
        frame.setSize(1280,720);
        frame.setLayout(new BorderLayout());
        currentSb = new Speelbord();

        
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
        
        menu.add(start);
        menu.add(stop);
        menu.add(pauze);
        menu.add(reset);
        frame.add(menu, BorderLayout.NORTH);
        frame.add(currentSb,BorderLayout.CENTER);
        frame.setVisible(true);      
    }
    
   

    @Override
    public void actionPerformed(ActionEvent e) {
        Speelbord level1 = new Speelbord();
        if (e.getActionCommand().equals(Actions.START.name())) {
            currentSb = level1;
            frame.add(level1,BorderLayout.CENTER);      
            frame.validate();
        }else if(e.getActionCommand().equals(Actions.STOP.name())){
            frame.remove(currentSb);
        } else if(e.getActionCommand().equals(Actions.PAUZE.name())){

        }else if(e.getActionCommand().equals(Actions.RESET.name())){

        }
    }
    
    
    
   
    
    
    
    
    
}
