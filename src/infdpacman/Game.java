package infdpacman;

import infdpacman.enums.Actions;
import infdpacman.gameelement.character.DrunkGhost;
import infdpacman.gameelement.character.GameCharacter;
import infdpacman.gameelement.character.Ghost;
import infdpacman.view.Board;
import infdpacman.view.Level5;
import infdpacman.view.Level1;
import infdpacman.view.Level2;
import infdpacman.view.Level3;
import infdpacman.view.Level4;
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
    private Timer timer;
    
    public Game(){
        levels = new ArrayList();
        fillLevelList();
        board = levels.get(3);
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
        }
        else{
            for(Board level : levels){
                if(board == level){
                    if(levels.size() < levels.indexOf(level)+1){
                        frame.remove(board);
                        board = levels.get(levels.indexOf(level)+1);
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
                checkIfLevelCompleted();
                timeLabel.setText("Time: " + seconds);
                lifeLabel.setText("Lives: " +board.getPacman().lives);
                scoreLabel.setText("score: " + player.getScore());
                //checkForCherrySpawn();
                moveGhosts();
                seconds+=0.5; 
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
              ((Ghost)gc).setImage(new ImageIcon("Plaatjes/ghost.png"));
              }
              
              if(gc instanceof DrunkGhost){
           ((DrunkGhost)gc).setImage(new ImageIcon("Plaatjes/drunkghost.png"));
              }}
              
            }
        };
        timer.scheduleAtFixedRate(task, 0, 5000);
    }

    private void startLevel() {
        frame.add(board,BorderLayout.CENTER);      
        board.requestFocus();
        frame.validate();
        cherrySpawned = false;
        startTimer();
        OnverslaanbaarTimer(); 
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
        if(board.getAmountofPills() == (board.getAmountofPillsInGame()/2) && !cherrySpawned){
            cherrySpawned = board.spawnCherry();
        }
    }
    
    private void checkIfLevelCompleted(){
        if(board.getAmountofPills() == 0){
            setLevel();
        }
    }

    private void fillLevelList() {
        levels.add(new Level1());
        levels.add(new Level2());
        levels.add(new Level3());
        levels.add(new Level4());
        levels.add(new Level5());
    }
        
}
