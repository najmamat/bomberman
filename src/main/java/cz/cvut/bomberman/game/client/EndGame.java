package cz.cvut.bomberman.game.client;

import cz.cvut.bomberman.game.Game;
import cz.cvut.bomberman.ui.MapEditor;
import cz.cvut.bomberman.utils.CustomButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
//import cz.cvut.bomberman.game.map.MapEditor;

public class EndGame extends JFrame {


    public EndGame(int width, int height, int bombsPlaced, String winner, int totalDamageDealt){

        setSize(width,height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new EndGameWindow(bombsPlaced, winner, totalDamageDealt));
        setVisible(true);
    }

    public class EndGameWindow extends JPanel{
        private int bombsPlaced;
        private String winner;
        private int totalDamageDealt;

        public EndGameWindow(int bombsPlaced, String winner, int totalDamageDealt){
            this.bombsPlaced = bombsPlaced;
            this.winner = winner;
            this.totalDamageDealt = totalDamageDealt;

            setBorder(new EmptyBorder(10, 10, 10, 10));
            setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.anchor = GridBagConstraints.NORTH;

            add(new JLabel("<html><h1><strong>Bomberman</strong></h1></html>"), gbc);

            gbc.anchor = GridBagConstraints.CENTER;
            gbc.fill = GridBagConstraints.HORIZONTAL;

            JPanel buttons = new JPanel(new GridBagLayout());

            JLabel textField = new JLabel("<html>" +
                    "Winner: " + winner +"  <br>" +
                    "Total Bombs placed: "+ bombsPlaced +"  <br>" +
                    "Total Damage dealt: "+ totalDamageDealt +" <br>" +
                    "</html>");
            textField.setFont(new Font("Verdana", 1, 20));
            textField.setBounds(100, 80, 165, 25);


            CustomButton menuButton = new CustomButton("Menu");


            menuButton.addActionListener(e -> {
                new StartGame(1200, 950);;
                dispose();
            });

            //buttons.add(textField);

            buttons.add(menuButton, gbc);

            gbc.weighty = 1;
            add(textField);
            add(buttons, gbc);
        }
    }
}