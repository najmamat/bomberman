package cz.cvut.bomberman.objects;

import cz.cvut.bomberman.game.Handler;
import cz.cvut.bomberman.objects.statics.Bomb;

import java.awt.*;
import java.util.ArrayList;

/**
 * ObjectManager is a class for managing our objects, that are rendered on the screen. It gets ArrayList of Players in
 * constructor. It has a list of all objects that extend MainObject a takes care of rendering and updating them.
 */
public class ObjectManager {

    private ArrayList<MainObject> objects;
    private Handler handler;
    private Bomb bomb;
    private ArrayList<Player> playerArrayList;

    /* RENDER ORDER ready
    private Comparator<MainObject> renderOrder = new Comparator<MainObject>() {
        @Override
        public int compare(MainObject o1, MainObject o2) {
            if(o1.getY() + o1.getHeight() < o2.getY() + o2.getHeight()){
                return -1;
            } else {
                return 1;
            }
        }
    };

     */


    public ObjectManager(ArrayList<Player> playerArrayList){
        this.playerArrayList = playerArrayList;
        objects = new ArrayList<MainObject>();
        for (Player p : playerArrayList) {
            addObject(p);
        }
    }


    public void update(){
        for (int i = 0; i < objects.size(); i++){
            MainObject object = objects.get(i);
            object.update();
        }
    }

    public void render(Graphics graphics){
        for (MainObject object : objects){
            object.render(graphics);
        }
    }

    public void addObject(MainObject object){
        objects.add(object);
    }

    //gettery a settery
    public ArrayList<MainObject> getObjects() {
        return objects;
    }

    public void setObjects(ArrayList<MainObject> objects) {
        this.objects = objects;
    }
}
