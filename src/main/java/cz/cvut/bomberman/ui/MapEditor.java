package cz.cvut.bomberman.ui;

import cz.cvut.bomberman.utils.test.EditorGrid;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Class MapEditor opens a window with a grid where user with clicking can customize his map. Rows and Collumns number is 1 less then actual
 * number of Columns and Rows, to secure Players cannot run outside our map.
 */
public class MapEditor {
    public static JFrame frame;

    private static void createAndShowGui() {
        int rows = 9;
        int cols = 14;
        int cellWidth = 40;
        EditorGrid mainPanel = new EditorGrid(rows, cols, cellWidth);

        frame = new JFrame("Bomberman map editor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGui();
            }
        });
    }


}
