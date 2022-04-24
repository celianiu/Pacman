package edu.rice.comp504.model.staticobject;

import edu.rice.comp504.model.map.MapSetting;

import java.awt.*;
import java.util.Random;

public class Fruit extends AStaticObject {
    public int type;

    /**
     * Constructor for fruit.
     */
    private Fruit() {
        Random r = new Random();
        type = r.nextInt(5) + 1;
        location = new Point();
    }

    /**
     * Fruit Constructor.
     * @param mapSetting input mapSetting
     */
    public Fruit(MapSetting mapSetting) {
        this();
        addNewFruit(mapSetting);

    }


    /**
     * Generate a valid position for fruits.
     *
     */

    public void addNewFruit(MapSetting mapSetting) {
        int col1;
        int row1;
        Random r = new Random();
        Random c = new Random();
        row1 = r.nextInt(13) * 50 + 25;
        col1 = c.nextInt(26) * 50 + 25;
        Point l = new Point(row1, col1);
        while (!mapSetting.dotsList.contains(l)) {
            row1 = r.nextInt(13) * 50 + 25;
            col1 = c.nextInt(26) * 50 + 25;
            l = new Point(row1, col1);
        }
        mapSetting.dotsList.remove(l);
        location.x = row1;
        location.y = col1;
    }
}
