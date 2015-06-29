package infdpacman.cell;

import infdpacman.Game;
import infdpacman.Player;
import infdpacman.gameelement.GameElement;
import infdpacman.gameelement.character.Pacman;
import infdpacman.gameelement.item.Pill;
import infdpacman.view.Board;
import infdpacman.view.Menu;
import infdpacman.view.levels.Level5;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author CVD
 */
public class EmptyCellTest {
    
    Menu menu;
    Game game;
    Board board;
    Pill pill;
    Cell c;
    Pacman pacman;
    int scoreBefore;

    @Before
    public void setUp() {
        menu = new Menu();
        game = new Game(menu);
        board = new Level5();
        pill = new Pill();
        c = new EmptyCell(board);
        pacman = new Pacman(c);
        scoreBefore = Game.getPlayer().getScore();
    }

    /**
     * checkCollision: Test if score from pill is added to the players score.
     */
    @Test
    public void testCheckCollision() {
        System.out.println("checkCollision: Test if score from pill is added to the players score.");
        LinkedList<GameElement> ja = new LinkedList();
        ja.add(pill);
        ja.add(pacman);
        
        ((EmptyCell)c).setInhoud(ja);
        ((EmptyCell)c).checkCollision();
        
        assertEquals(0, scoreBefore);
        assertEquals(scoreBefore+pill.points, Game.getPlayer().getScore());
    }
}
