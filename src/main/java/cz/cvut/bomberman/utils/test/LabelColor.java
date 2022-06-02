package cz.cvut.bomberman.utils.test;

import java.awt.Color;

/**
 * Class LabelColor is for creating colour options in our EditorGrid. Each color has its name and shortName(id). ShortName
 * is then used to generate a text file in our format. Then Maps path is changed to the generated .txt file.2
 */
public enum LabelColor {
    RED(Color.red, "brick", "0"), GRAY(Color.gray, "stone", "1"),
    YELLOW(Color.yellow, "planks", "2");
    private Color img;
    private String name;
    private String shortName;

    private LabelColor(Color color, String name, String shortName) {
        this.img = color;
        this.name = name;
        this.shortName = shortName;
    }

    public LabelColor next() {
        int index = 0;
        for (int i = 0; i < LabelColor.values().length; i++) {
            if (LabelColor.values()[i] == this) {
                index = (i + 1) % LabelColor.values().length;
            }
        }
        return LabelColor.values()[index];
    }

    public Color getImg() {
        return img;
    }

    @Override
    public String toString() {
        return shortName;
    }

}