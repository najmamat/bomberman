package cz.cvut.bomberman.utils;

import cz.cvut.bomberman.game.Game;
import cz.cvut.bomberman.game.Handler;
import cz.cvut.bomberman.game.map.GameMap;
import cz.cvut.bomberman.ui.MapEditor;
import cz.cvut.bomberman.utils.test.EditorGrid;
import cz.cvut.bomberman.utils.test.LabelColor;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.JLabel;

/**
 * MyMouseListener is a class to add Mouse user input to EditorGrid.
 */
public class MyMouseListener extends MouseAdapter {
    private EditorGrid editorGrid;
    private MapEditor mapEditor;
    private WriteFile fileWriter;
    private Handler handler;
    private String path = "resources/maps/loadMap.txt";
    private int numberOfPlayers = 2;
    private int playerHealth = 0;

    public MyMouseListener(EditorGrid editorGrid) {
        this.editorGrid = editorGrid;
    }

    /**
     * Method mousePressed works as actionListener. If statements distinguish between 3 Buttons on the mouse.
     * It also utilizes fileWrite to create .txt file in our format to load up a map.
     * @param e
     */
    @Override
    public void mousePressed(MouseEvent e) {

        // Left-Click: Change colours
        if (e.getButton() == MouseEvent.BUTTON1) {
            editorGrid.labelPressed((JLabel)e.getSource());

        // Right-Click: Create .txt file and change path, so our game loads from our new file.
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            fileWriter = new WriteFile(path);

            try {
                fileWriter.writeToFile("16 11" + "\n");
                fileWriter = new WriteFile(path, true);
                fileWriter.writeToFile("100 100" + "\n");
                fileWriter.writeToFile("0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0" + "\n");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

            LabelColor[][] labelColors = editorGrid.getMyColors();
            for (int row = 0; row < labelColors.length; row++) {
                try {
                    fileWriter.writeToFile("0 ");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                for (int col = 0; col < labelColors[row].length; col++) {
                    try {
                        fileWriter.writeToFile(labelColors[row][col] + " ");
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
                try {
                    fileWriter.writeToFile("0" + "\n");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            try {
                fileWriter.writeToFile("0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        //MouseWheel-Click: Creates a new Game with our edited map.
        } else if (e.getButton() == MouseEvent.BUTTON2){
            mapEditor.frame.setVisible(false);
            mapEditor.frame.dispose();
            GameMap newMap = new GameMap(handler, path, numberOfPlayers, playerHealth);

            Game game = new Game("Bomberman",1200, 950, path, numberOfPlayers, playerHealth);
        }
    }
}