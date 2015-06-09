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
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Lenovo
 */
public class Game implements ActionListener {
    
    public ArrayList highscore;
    public JFrame frame;
    public Board currentSb;
    public Player player;
    public JPanel menu;
    public JPanel gameInfo;
    public JLabel scoreLabel;
    public JLabel lifeLabel;
    public JLabel timeLabel;
    private int seconds = 0;
    Timer timer;
    
    public Game(){
        //NextLevel();
    }
    
    public void start(){
        
        frame = new JFrame();
        frame.setSize(1280,720);
        frame.setLayout(new BorderLayout());
        player = new Player();
        
        menu = new JPanel();
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
        
        gameInfo = new JPanel();
        lifeLabel = new JLabel();
        scoreLabel = new JLabel();
        timeLabel = new JLabel();
        
        gameInfo.add(scoreLabel);
        gameInfo.add(lifeLabel);
        gameInfo.add(timeLabel);
        gameInfo.setBorder(new TitledBorder(new EtchedBorder()));
        
        JPanel both = new JPanel();
        both.add(menu);
        both.add(gameInfo);
        frame.add(both, BorderLayout.NORTH);
        frame.setVisible(true); 
        
    }
    
   

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getActionCommand().equals(Actions.START.name())) {
            if(currentSb == null){
            Board level1 = new Level2();
            currentSb = level1;
            currentSb.countPills();
            player.setLevel(currentSb);
            //currentlives = currentSb.getPacman().lives;
            //j.setText("" + currentSb.getPacman().lives);
            frame.add(level1,BorderLayout.CENTER);      
            level1.requestFocus();
            startLevel();
            frame.validate();
            
            }
        }else if(e.getActionCommand().equals(Actions.STOP.name())){
            
            frame.remove(currentSb);
            currentSb = null;
            timer.cancel();
            timer.purge();
            
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
    
    
    
public void setLives(){
    if(currentSb != null){
        lifeLabel.setText("Lives: " + currentSb.getPacman().lives);
    }
}
    
    public void NextLevel(){
        if(currentSb.total == 0 && currentSb != null){
            Board level1 = new Level2();
            currentSb = level1;
            player.setLevel(currentSb);
            lifeLabel.setText("Lives: " +currentSb.getPacman().lives);
            frame.add(level1,BorderLayout.CENTER);      
            level1.requestFocus();
            frame.validate();
        }
    }

    private void startTimer() {
        timer = new Timer();
        TimerTask task = new TimerTask(){
            public void run(){
               seconds++; 
               timeLabel.setText("Time: " + seconds);
            }
        };
        timer.scheduleAtFixedRate(task, 0, 1000);
    }

    private void startLevel() {
        startTimer();
        setLives();
        scoreLabel.setText("score: " + player.getScore());
    }
    
}
