import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {
    private Main game;
    private JButton[][] buttons;

    @BeforeEach //set up for each test
    public void setUp() {
        game = new Main();
        int gameTableSize = game.tablesize();
        buttons = new JButton[gameTableSize][gameTableSize];
        for (int row = 0; row < gameTableSize; row++) {
            for (int col = 0; col < gameTableSize; col++) {
                buttons[row][col] = new JButton();
            }
        }
        game.resetGame(System.currentTimeMillis());
    }

    @Test
    public void testBoardInitialization() {
        // Check if the board size is correct
        int expectedSize = 8;
        assertEquals(expectedSize, game.tablesize(), "Board size 8x8.");
    }

    @Test
    public void testInitialPlayerTurn() {
        // Verify that Player 1 starts the game
        assertTrue(game.isPlayerOneTurn(), "The game should start when Player 1's turn.");
    }

    @Test  //validates turn-switching functionality
    public void testPlayerMoveAndTurnSwitching() {
        game.checkHit(0, 0);
        assertFalse(game.isPlayerOneTurn(), "Switch to turn Player 2 after a miss by Player 1.");

        game.checkHit(1, 1);
        assertTrue(game.isPlayerOneTurn(), "Turn back to Player 1 after a miss by Player 2.");
    }

    @Test //simulates finding the ship and achieving hit
    public void testWinningMove() {
        for (int row = 0; row < game.tablesize(); row++) {
            for (int col = 0; col < game.tablesize(); col++) {
                if (game.checkHit(row, col)) {
                    assertEquals(true, game.checkHit(row, col), "Winning is 'Hit!'");
                    return;
                }
            }
        }
        fail("Winning move not found on the board.");
    }

    @Test // verifies game resets to initial states and player 1 turn starting
    public void testResetGame() {
        game.checkHit(0, 0);

        game.resetGame(System.currentTimeMillis());
        assertTrue(game.isPlayerOneTurn(), "Game should reset to Player 1's turn after reset.");
    }
}
