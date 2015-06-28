package infdpacman;

import infdpacman.cell.Cell;
import infdpacman.cell.EmptyCell;
import infdpacman.view.Menu;
import infdpacman.enums.Actions;
import infdpacman.gameelement.GameElement;
import infdpacman.gameelement.character.GameCharacter;
import infdpacman.gameelement.character.Ghost;
import infdpacman.view.Board;
import infdpacman.view.levels.Level4;
import infdpacman.view.levels.Level1;
import infdpacman.view.levels.Level2;
import infdpacman.view.levels.Level3;
import infdpacman.view.levels.Level5;
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
    
    private boolean timerStarted;
    private boolean stopTimers;
    private boolean gameStarted;
    private boolean gameWon;
    private boolean startedFromFirstLevel;
    private boolean keys;

    private double seconds;
    private final int extraPoints;
    private int prefLives;
   
    private JFrame frame;
    private JPanel inGameMenu;
    private JPanel gameInfo;
    private JLabel scoreLabel;
    private JLabel lifeLabel;
    private JLabel timeLabel;
    private Menu menu;

    public Game(Menu menu){
        player = new Player();
        extraPoints = 100000;
        fillLevelList();
        hScores = new HashMap();
        this.menu = menu;
        startedFromFirstLevel = false;
        timerStarted = false;
        stopTimers = false;
        gameStarted = false;
        gameWon = false;
        prefLives = 3;
        keys = true;
        seconds = 0;
    }
    public void setKeys(boolean keys){
    
    this.keys = keys;
    
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
            if(stopTimers){
                stopTimers = false;
                startTimers();
            }
            frame.validate();
            frame.repaint();
        }else if(e.getActionCommand().equals(Actions.STOP.name())){            
            if(board != null){
                frame.remove(board);
                stopGameFunctionality();
                seconds = 0;
                player.setScore(0);
                frame.validate();
                frame.repaint();
            }else{ 
                frame.dispose();
                menu.init();
            }
            gameWon = false;
        } else if(e.getActionCommand().equals(Actions.PAUZE.name())){
            stopTimers = true;
            setTimerState();
            board = null;
            gameStarted = false;
        }else if(e.getActionCommand().equals(Actions.RESET.name())){
            if(board != null){
                stopGameFunctionality();
                seconds = 0;
                player.setScore(0);
                setLevel();
                frame.validate();
                frame.repaint();
            }
        }
    }
    
    public void setTimerState(){
        board.getPacman().setStopTimer(stopTimers);
        for(GameCharacter g : (ArrayList<GameCharacter>)board.getGhosts()){
            ((Ghost)g).setStopTimer(stopTimers);
        }
        board.setStopTimer(stopTimers);
    }
    
    public void stopGameFunctionality(){
        stopTimers = true;
        setTimerState();
        levels.clear();
        gameStarted = false;
        fillLevelList(); //reset levels else the pills that you ate won't come back.
        board = null;          
    }
    
    public void setLevel(){
        if(stopTimers){
            stopTimers = false;
        }
        if(board == null){
            board = levels.get(0);
            startTimers();
            timerStarted = true;
            startedFromFirstLevel = true;
            board.getPacman().setLives(prefLives);
            board.getPacman().setKeys(keys);
        }
        else{
            for(Board level : levels){
                if(board == level){
                    //level + 1, for next level
                    if(levels.indexOf(level)+1 < levels.size()){
                        frame.remove(board);
                        board = levels.get(levels.indexOf(level)+1);
                        board.getPacman().setLives(prefLives);
                        board.getPacman().setKeys(keys);
                        for(GameCharacter g : (ArrayList<GameCharacter>)board.getGhosts()){
                            ((Ghost)g).setGhostTimerMs(((Ghost)g).getGhostTimerMs() - 75);
                        }
                        break;
                    }
                    else{
                        gameWon = true;
                        frame.remove(board);
                        if(startedFromFirstLevel){
                            player.setScore(player.getScore() + (extraPoints / (int)seconds));
                        }
                        stopGameFunctionality();
                        frame.add(new EndGame(hScores, player.getScore(), gameWon), BorderLayout.CENTER);
                        seconds = 0;
                        player.setScore(0);
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
            ((Ghost)g).ghostTimer(new Timer());
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
        stopTimers = false;
        setTimerState();
        gameTimer(new Timer());
        gameOverTimer(new Timer());
        moveGhosts();
    }
    
    private void gameTimer(Timer t) {
        TimerTask task = new TimerTask(){
            public void run(){
                if(stopTimers){
                    t.cancel();
                }
                else{
                    //((EmptyCell)board.getPacman().getCell()).checkCollision();
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
        
    private void gameOverTimer(Timer t){
        TimerTask task = new TimerTask(){
            public void run(){
                if(stopTimers){
                    t.cancel();
                }
                else{
                    if(board.getPacman().getLives() < 1){
                        stopTimers = true;
                        frame.remove(board);
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