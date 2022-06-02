package cz.cvut.bomberman.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

/**
 * Class for determining looks of our custom Buttons, that are used in GUI.
 */
public class CustomButton extends JLabel implements MouseListener {

    ActionListener actionListener;

    public CustomButton(String str){

        super(str);
        this.setForeground(Color.red);
        this.setOpaque(false);
        this.addMouseListener(this);
        this.setFont(new Font("Helvetica", Font.BOLD, 30));
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setBackground(Color.black);
    }

    public void addActionListener(ActionListener actionListener){
        this.actionListener = actionListener;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        actionListener.actionPerformed(new ActionEvent(this,501,""));
    }

    @Override
    public void mousePressed(MouseEvent e) {    }

    @Override
    public void mouseReleased(MouseEvent e) {    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.setForeground(Color.orange);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        this.setForeground(Color.red);
    }
}
