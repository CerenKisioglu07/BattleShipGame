import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    static {
        System.loadLibrary("BattleShip");  // Load Native library
    }

    // Native methods
    public native int tablesize();
    public native boolean checkHit(int row, int col);
    public native void resetGame(long seed);
    public native boolean isPlayerOneTurn();

    private static JLabel turnLabel;  // For shows gamers turn

    public static void main(String[] args) {
        Main game = new Main();
        int gametables = game.tablesize();
        JFrame frame = new JFrame("BattleShip");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        //For game table game.tablesize()xgame.tablesize() creating a panel
        JPanel panel = new JPanel(new GridLayout(gametables, gametables));
        JButton[][] buttons = new JButton[gametables][gametables];

        turnLabel = new JLabel("Next Move: Player 1");
        turnLabel.setHorizontalAlignment(SwingConstants.CENTER);

        long seed = System.currentTimeMillis();
        game.resetGame(seed);

        for (int row = 0; row < gametables; row++) {
            for (int col = 0; col < gametables; col++) {
                buttons[row][col] = new JButton();
                final int r = row;
                final int c = col;

                buttons[row][col].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (game.checkHit(r, c)) {
                            buttons[r][c].setText("Hit!");
                            JOptionPane.showMessageDialog(frame, (game.isPlayerOneTurn() ? "Player 1" : "Player 2") + " Found the ship! Won!");
                            disableBoard(buttons);
                            game.resetGame(System.currentTimeMillis());
                            updateTurnLabel(true, game);
                        } else {
                            buttons[r][c].setText("Miss");
                            updateTurnLabel(false, game);
                        }
                    }
                });
                panel.add(buttons[row][col]);
            }
        }

        // Add in Main Frame
        frame.setLayout(new BorderLayout());
        frame.add(turnLabel, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    // Update Gamer turn method
    private static void updateTurnLabel(boolean reset, Main game) {
        if (reset) {
            turnLabel.setText("Next Move: Player 1");
        } else {
            turnLabel.setText("Next Move: " + (game.isPlayerOneTurn() ? "Player 1" : "Player 2"));
        }
    }
    // After player win disabled all buttons so no more moves can be made
    private static void disableBoard(JButton[][] buttons) {
        for (int row = 0; row < buttons.length; row++) {
            for (int col = 0; col < buttons[row].length; col++) {
                buttons[row][col].setEnabled(false);
            }
        }
    }
}
