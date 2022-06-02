package cz.cvut.bomberman.utils.test;

import cz.cvut.bomberman.utils.MyMouseListener;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.*;

public class EditorGrid extends JPanel {
    private LabelColor[][] labelColors;
    private JLabel[][] myLabels;

    /**
     * Class EditorGrid is for adding grid to our Editor window. It contains Mouse Listener to check for clicks etc.
     * @param rows
     * @param cols
     * @param cellWidth
     */
    public EditorGrid(int rows, int cols, int cellWidth) {
        labelColors = new LabelColor[rows][cols];
        myLabels = new JLabel[rows][cols];

        MyMouseListener myListener = new MyMouseListener(this);
        Dimension labelPrefSize = new Dimension(cellWidth, cellWidth);
        setLayout(new GridLayout(rows, cols));
        for (int row = 0; row < myLabels.length; row++) {
            for (int col = 0; col < myLabels[row].length; col++) {
                JLabel myLabel = new JLabel();
                myLabel = new JLabel();
                myLabel.setOpaque(true);
                LabelColor labelColor = LabelColor.RED;
                labelColors[row][col] = labelColor;
                myLabel.setBackground(labelColor.getImg());
                myLabel.addMouseListener(myListener);
                myLabel.setPreferredSize(labelPrefSize);
                add(myLabel);
                myLabels[row][col] = myLabel;
            }
        }
    }

    /**
     * Getter for Label Colours.
     * @return
     */
    public LabelColor[][] getMyColors() {
        return labelColors;
    }

    /**
     * Method to change tiles colours.
     * @param label
     */
    public void labelPressed(JLabel label) {
        for (int row = 0; row < myLabels.length; row++) {
            for (int col = 0; col < myLabels[row].length; col++) {
                if (label == myLabels[row][col]) {
                    LabelColor labelColor = labelColors[row][col].next();
                    labelColors[row][col] = labelColor;
                    myLabels[row][col].setBackground(labelColor.getImg());
                }
            }
        }
    }
}