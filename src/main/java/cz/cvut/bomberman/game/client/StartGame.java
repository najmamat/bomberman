package cz.cvut.bomberman.game.client;

import cz.cvut.bomberman.ui.MapEditor;
import cz.cvut.bomberman.utils.CustomButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * StartGame is a class, that makes first UI and menu.It extends a JFrame.
 * <p>It has an inside class GameMenu that extends JPanel. This class contains basic buttons to control our game.</p>
 */
public class StartGame extends JFrame {
    public StartGame(int width, int height){
        setSize(width,height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new GameMenu());
        setVisible(true);
    }

    public class GameMenu extends JPanel{
        public GameMenu(){
            setBorder(new EmptyBorder(10, 10, 10, 10));
            setLayout(new GridBagLayout());
//            setBackground(Color.black);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.anchor = GridBagConstraints.NORTH;

            add(new JLabel("<html><h1><strong>Bomberman</strong></h1></html>"), gbc);

            gbc.anchor = GridBagConstraints.CENTER;
            gbc.fill = GridBagConstraints.HORIZONTAL;

            JPanel buttons = new JPanel(new GridBagLayout());

            CustomButton singlePlButton = new CustomButton("Start Game");
            CustomButton editorButton = new CustomButton("Map editor");
            CustomButton howToPlayButton = new CustomButton("How to play");
            CustomButton exitButton = new CustomButton("Exit");

            singlePlButton.addActionListener(e -> {
                new Settings(1200, 950);
                dispose();
            });

            editorButton.addActionListener(e -> {
                String[] arguments = new String[] {""};
                MapEditor.main(arguments);
                dispose();
            });

            exitButton.addActionListener(e -> {
                System.exit(0);
                dispose();
            });

            buttons.add(singlePlButton, gbc);
            buttons.add(editorButton, gbc);
            buttons.add(howToPlayButton, gbc);
            buttons.add(exitButton, gbc);

            gbc.weighty = 1;
            add(buttons, gbc);
        }
    }
}
