package cz.cvut.bomberman.ui;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.*;

/**
 * Class to set up our JFrame and Canvas to display our game on.
 */
public class Display {
    private final int DEFAULT_FRAME_WIDTH = 1200;
    private final int DEFAULT_FRAME_HEIGHT = 825;
    private JFrame frame;
    private Canvas canvas;

    private String title;
    private int width, height;

    public Display(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;

        CreateDisplay();
    }

    private void CreateDisplay(){
        frame = new JFrame(title);
        frame.setSize(width,height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        frame.setSize(this.DEFAULT_FRAME_WIDTH,this.DEFAULT_FRAME_HEIGHT);
        frame.setPreferredSize(new Dimension(this.DEFAULT_FRAME_WIDTH, this.DEFAULT_FRAME_HEIGHT));
        //frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height)); // canvas zustane porad stejne velky jako okno
        canvas.setFocusable(false);

        frame.add(canvas);
        frame.pack();
    }

    public Canvas getCanvas(){
        return canvas;
    }

    public JFrame getJFrame(){
        return frame;
    }

}
