package infdpacman;

import infdpacman.enums.Actions;
import infdpacman.gameelement.character.DrunkGhost;
import infdpacman.gameelement.character.GameCharacter;
import infdpacman.gameelement.character.Ghost;
import infdpacman.view.Board;
import infdpacman.view.Level4;
import infdpacman.view.Level1;
import infdpacman.view.Level2;
import infdpacman.view.Level3;
import infdpacman.view.Level5;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Lenovo
 */
public class Game implements ActionListener {
    
    private ArrayList highscore;
    private ArrayList<Board> levels;
    private JFrame frame;
    private Board board;
    private static  Player player;
    private JPanel menu;
    private JPanel gameInfo;
    private JLabel scoreLabel;
    private JLabel lifeLabel;
    private JLabel timeLabel;
    private double seconds = 0;
    private boolean cherrySpawned;
    private boolean timerStarted = false;
    private Timer timer;
    
    public Game(){
        levels = new ArrayList();
        fillLevelList();
    }
    
    public static Player getPlayer(){
        return player;
    }
    
    public void init(){
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
            setLevel();
        }else if(e.getActionCommand().equals(Actions.STOP.name())){
            frame.remove(board);
            board = null;
            timer.cancel();
            timer.purge();
            frame.validate();
            frame.repaint();
        } else if(e.getActionCommand().equals(Actions.PAUZE.name())){

        }else if(e.getActionCommand().equals(Actions.RESET.name())){
            frame.remove(board);
            Board level1 = new Level1();
            board = level1;          
            frame.add(level1,BorderLayout.CENTER);      
            frame.validate();
        }
    }
    
    public void setLevel(){
        if(board == null){
            board = levels.get(0);
            startTimer();
            OnverslaanbaarTimer();
            gameOver();
            timerStarted = true;
        }
        else{
            for(Board level : levels){
                if(board == level){
                    if(levels.indexOf(level)+1 < levels.size()){
                        frame.remove(board);
                        board = levels.get(levels.indexOf(level)+1);
                        break;
                    }
                    //else: you win bla bla highscore shizzle
                }
            }
        }
        startLevel();
    }

    private void startTimer() {
        timer = new Timer();
        TimerTask task = new TimerTask(){
            public void run(){
                timeLabel.setText("Time: " + seconds);
                lifeLabel.setText("Lives: " + board.getPacman().lives);
                scoreLabel.setText("score: " + player.getScore());
                checkForCherrySpawn();
                seconds+=0.5; 
                checkIfLevelCompleted();
            }
        };
        timer.scheduleAtFixedRate(task, 0, 500);
    } 
    
    
    private void OnverslaanbaarTimer() {
        timer = new Timer();
        TimerTask task = new TimerTask(){
            public void run(){
              board.getPacman().onverslaanbaar = false;
              for(GameCharacter gc: board.getGhosts()){
              if(gc instanceof Ghost){
                ((Ghost)gc).normal();
              }
              else if(gc instanceof DrunkGhost){
                ((DrunkGhost)gc).normal();
              }}
              
            }
        };
        timer.scheduleAtFixedRate(task, 0, 5000);
    }

    private void ghostTimer(int ms) {
        timer = new Timer();
        TimerTask task = new TimerTask(){
            public void run(){
                timeLabel.setText("Time: " + seconds);
                lifeLabel.setText("Lives: " + board.getPacman().lives);
                scoreLabel.setText("score: " + player.getScore());
                checkForCherrySpawn();
                seconds+=0.5; 
                checkIfLevelCompleted();
            }
        };
        timer.scheduleAtFixedRate(task, 0, ms);
    }
    
    private void startLevel() {
        if(!timerStarted){
            startTimer();
            OnverslaanbaarTimer();
            gameOver();
        }
        frame.add(board,BorderLayout.CENTER);      
        board.requestFocus();
        frame.validate();
        cherrySpawned = false;
    }
    
    
    private void moveGhosts(){
        for(GameCharacter g : (ArrayList<GameCharacter>)board.getGhosts()){
            if(g instanceof DrunkGhost){
                ((DrunkGhost)g).moveGhost();
            }
            else{
                ((Ghost)g).moveGhost();
            }
        }
    }
    
    private void checkForCherrySpawn() {
        if(board.getAmountOfPills() == (board.getAmountofPillsInGame()/2) && !cherrySpawned){
            cherrySpawned = board.spawnCherry();
        }
    }
    
    private void checkIfLevelCompleted(){
        if(board.getAmountOfPills() == 0){
            setLevel();
        }
    }

    private void fillLevelList() {
        levels.add(new Level1());
        levels.add(new Level2());
        levels.add(new Level3());
        levels.add(new Level5());
        levels.add(new Level4());
    }
    
     private void gameOver(){
            timer = new Timer();
        TimerTask task = new TimerTask(){
            public void run(){
                if(board.getPacman().lives == 0){
                JOptionPane.showMessageDialog(null, "Game Over!", "gameover", JOptionPane.ERROR_MESSAGE);
                frame.remove(board);
                board = null;
                timer.cancel();
                timer.purge();
             frame.validate();
            frame.repaint();
                
                }
            }
        }; timer.scheduleAtFixedRate(task, 0, 1000);
                
                }
        
}
