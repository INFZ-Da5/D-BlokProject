package infdpacman;

import infdpacman.enums.Actions;
import infdpacman.gameelement.character.GameCharacter;
import infdpacman.gameelement.character.Ghost;
import infdpacman.view.Board;
import infdpacman.view.Level4;
import infdpacman.view.Level1;
import infdpacman.view.Level2;
import infdpacman.view.Level3;
import infdpacman.view.Level5;
import infdpacman.view.EndGame;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
    public ArrayList<Board> levels;
    private Board board;
    private static Player player;
     private Map<String, Integer> hScores;
    
    private double seconds = 0;
    private boolean timerStarted = false;
    private boolean stopTimers = false;
    private boolean gameStarted = false;
    private boolean gameWon = false;
    private int ghostTimerMs;
    private int extraPoints;
    private int prefLives = 3;
   
    private JFrame frame;
    private JPanel inGameMenu;
    private JPanel gameInfo;
    private JLabel scoreLabel;
    private JLabel lifeLabel;
    private JLabel timeLabel;
    private Menu menu;

    public Game(Menu menu){
        player = new Player();
        ghostTimerMs = 600;
        extraPoints = 100000;
        fillLevelList();
        hScores = new HashMap();
        this.menu = menu;

        //board = levels.get(3);
    }
    
    public static Player getPlayer(){
        return player;
    }

    public Map<String, Integer> gethScores() {
        return hScores;
    }

    public void sethScores(Map<String, Integer> hScores) {
        this.hScores = hScores;
    }
    
    public int getPrefLives(){
    
    return prefLives;
    }
    
    public void setPrefLives(int lives){
    
    prefLives = lives;
    }
    
    public void initFrame(){
        
        frame = new JFrame();
        frame.setSize(1280,720);
        frame.setLayout(new BorderLayout());

        inGameMenu = new JPanel();

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
        
        inGameMenu.add(start);
        inGameMenu.add(stop);
        inGameMenu.add(pauze);
        inGameMenu.add(reset);
        
        gameInfo = new JPanel();
        lifeLabel = new JLabel();
        scoreLabel = new JLabel();
        timeLabel = new JLabel();
        
        gameInfo.add(scoreLabel);
        gameInfo.add(lifeLabel);
        gameInfo.add(timeLabel);
        gameInfo.setBorder(new TitledBorder(new EtchedBorder()));
        
        JPanel both = new JPanel();
        both.add(inGameMenu);
        both.add(gameInfo);
        frame.add(both, BorderLayout.NORTH);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true); 
    }
    
    public void setBoard(Board board){
        this.board = board;
    }
    
    public Board getBoard(){
        return board;
    }
    
    public ArrayList<Board> getLevels(){
        return levels;
    }
   
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(Actions.START.name())) {
            if(!gameStarted){
                gameWon = false;
                setLevel();
                gameStarted = true;
            }
            frame.validate();
            frame.repaint();
        }else if(e.getActionCommand().equals(Actions.STOP.name())){            
            if(board != null){
                frame.remove(board);
                stopGameFunctionality();
                frame.validate();
                frame.repaint();
            }else{ 
                frame.dispose();
                menu.init();
            }
            gameWon = false;
        } else if(e.getActionCommand().equals(Actions.PAUZE.name())){
            stopTimers = true;
            board.getPacman().setStopTimer(stopTimers);
            board.setStopTimer(stopTimers);
            board = null;
            gameStarted = false;
        }else if(e.getActionCommand().equals(Actions.RESET.name())){
            if(board != null){
                stopGameFunctionality();
                setLevel();
                frame.validate();
                frame.repaint();
            }
        }
    }
    
    public void stopGameFunctionality(){
        stopTimers = true;
        board.getPacman().setLives(3);
        board.getPacman().setStopTimer(stopTimers);
        board.setStopTimer(stopTimers);
        levels.clear();
        gameStarted = false;
        fillLevelList(); //reset levels else the pills that you ate won't come back.
        board = null;          
        seconds = 0;
        player.setScore(0);
    }
    
    public void setLevel(){
        if(stopTimers){
            stopTimers = false;
        }
        if(board == null){
            board = levels.get(0);
            startTimers();
            timerStarted = true;
        }
        else{
            for(Board level : levels){
                if(board == level){
                    //level + 1, for next level
                    if(levels.indexOf(level)+1 < levels.size()){
                        frame.remove(board);
                        board = levels.get(levels.indexOf(level)+1);
                         board.getPacman().setLives(prefLives);
                        ghostTimerMs -= 75;
                        break;
                    }
                    else{
                       gameWon = true;
                       frame.remove(board);
                        player.setScore(player.getScore() + (extraPoints / (int)seconds));
                       frame.add(new EndGame(hScores, player.getScore(), gameWon), BorderLayout.CENTER);
                       stopGameFunctionality();
                    }
                }
            }
        }
        if(!gameWon){
            startLevel();
        }
    }
    
    private void startLevel() {
        if(!timerStarted){
            startTimers();
        }
        frame.add(board,BorderLayout.CENTER);      
        board.requestFocus();
        frame.validate();
        board.setCherrySpawned(false);
        board.setBananaSpawned(false);
    }
    
    
    private void moveGhosts(){
        for(GameCharacter g : (ArrayList<GameCharacter>)board.getGhosts()){
            ((Ghost)g).moveGhost();
        }
    }    
    
    private void checkIfLevelCompleted(){
        if(board.getAmountOfPills() == 0){
            setLevel();
        }
    }

    private void fillLevelList() {
        levels = new ArrayList();
        levels.add(new Level1());
        levels.add(new Level2());
        levels.add(new Level3());
        levels.add(new Level4());
        levels.add(new Level5());
    }

    private void startTimers() {
        gameTimer(new Timer());
        ghostTimer(new Timer());
        gameOverTimer(new Timer());
    }
    
    private void gameTimer(Timer t) {
        TimerTask task = new TimerTask(){
            public void run(){
                if(stopTimers){
                    t.cancel();
                }
                else{
                    timeLabel.setText("Time: " + seconds);
                    lifeLabel.setText("Lives: " + board.getPacman().getLives());
                    scoreLabel.setText("score: " + player.getScore());
                    board.checkForExtraItemSpawn();
                    seconds+=0.5; 
                    checkIfLevelCompleted();
                }
            }
        };
        t.scheduleAtFixedRate(task, 0, 500);
    } 
    
    
    private void ghostTimer(Timer t) {
        TimerTask task = new TimerTask(){
            public void run(){ 
                if(stopTimers){
                    t.cancel();
                }
                else{
                    moveGhosts();
                }
            }
        };
        t.scheduleAtFixedRate(task, 0, ghostTimerMs);
    }
        
    private void gameOverTimer(Timer t){
        TimerTask task = new TimerTask(){
            public void run(){
                if(stopTimers){
                    t.cancel();
                }
                else{
                    if(board.getPacman().getLives() == 0){
                        stopTimers = true;
                        stopGameFunctionality();
                        frame.add(new EndGame(hScores, player.getScore(), gameWon), BorderLayout.CENTER);
                        frame.validate();
                        frame.repaint();
                        t.cancel();
                        t.purge();
                    }
                }
            }
        }; 
        t.scheduleAtFixedRate(task, 0, 1000);
    }
}
