package cz.cvut.bomberman.game.client;

import cz.cvut.bomberman.game.Game;
import cz.cvut.bomberman.utils.CustomButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Settings class is another part of our UI. It contains 2 textFields to setup our game.
 * <li>First textField decides how many players will be playing against each other.</li>
 * <li>Second textField decides how much health will players have.</li>
 *
 * <p>Method checkFromValues is for checking user input in out textFields.</p>
 */
public class Settings extends JFrame {
    public Settings(int width, int height){
        setSize(width,height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new SettingsWindow());
        setVisible(true);
    }

    public class SettingsWindow extends JPanel{
        private JTextField textFieldNumberOfPlayers;
        private JTextField textFieldPlayerHealth;
        private boolean isValid = true;
        public JFrame frame;

        public SettingsWindow(){
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

            //label1
            JLabel numberOfPlayers = new JLabel("Number of Players: ");
            numberOfPlayers.setBounds(10, 80, 80, 25);
            add(numberOfPlayers);
            //textfield1
            textFieldNumberOfPlayers = new JTextField(20);
            textFieldNumberOfPlayers.setBounds(100, 80, 165, 25);
            add(textFieldNumberOfPlayers);

            //label2
            JLabel playerHealth = new JLabel("Player Health: 1 + ");
            playerHealth.setBounds(10, 80, 80, 25);
            add(playerHealth);
            //textfield2
            textFieldPlayerHealth = new JTextField(20);
            textFieldPlayerHealth.setBounds(100, 80, 165, 25);
            add(textFieldPlayerHealth);

            CustomButton startGameButton = new CustomButton("Start Game");
            CustomButton menuButton = new CustomButton("Menu");

            startGameButton.addActionListener(e -> {
                checkFormValues();
                if (isValid){
                    Integer nop = Integer.parseInt(textFieldNumberOfPlayers.getText());
                    Integer ph = Integer.parseInt(textFieldPlayerHealth.getText());
                    new Game("Bomberman", 800, 800, "resources/maps/map1.txt", nop, ph);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(frame,"There has to be 2-4 players and player has to have more then 1 health");
                    new Settings(1200, 950);
                    dispose();
                }

            });

            menuButton.addActionListener(e -> {
                new StartGame(1200, 950);
                dispose();
            });


            buttons.add(startGameButton, gbc);
            buttons.add(menuButton, gbc);

            gbc.weighty = 1;
            add(buttons, gbc);
        }

        private void checkFormValues(){
            try {
                Integer.parseInt(textFieldNumberOfPlayers.getText());
            } catch (NumberFormatException e){
                System.out.println("You have to put Integer in between 2-4");
            }
            try {
                Integer.parseInt(textFieldPlayerHealth.getText());
            } catch (NumberFormatException e){
                System.out.println("You have to put Integer in between 2-4");
            }
            if (Integer.parseInt(textFieldNumberOfPlayers.getText()) < 2
                    || Integer.parseInt(textFieldNumberOfPlayers.getText()) > 4) {
                isValid = false;
            }
            if (Integer.parseInt(textFieldPlayerHealth.getText()) - 1 < -1){
                isValid = false;
            }
        }
    }
}