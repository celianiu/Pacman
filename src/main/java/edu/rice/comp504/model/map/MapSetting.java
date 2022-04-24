package edu.rice.comp504.model.map;

import edu.rice.comp504.model.enumtype.DirectionEnumType;
import edu.rice.comp504.model.movingobject.Ghost;
import edu.rice.comp504.model.staticobject.Fruit;
import edu.rice.comp504.model.strategy.LeaveHomeStrategy;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MapSetting {
    public static final int gridSize = 50;
    public static final int gridNumX = 26;
    public static final int gridNumY = 13;
    public Set<Point> dotsList;
    public Set<Point> bigDotsList;
    public Set<Fruit> fruitList;
    public static List<Ghost> ghostList = new ArrayList<>();
    public static int gameLevel;

    public static void resetGhostList(int gameLevel) {
        MapSetting.gameLevel = gameLevel;
        ghostList = new ArrayList<>();
        //red
        Point location1 = new Point(9 * MapSetting.gridSize + MapSetting.gridSize / 2, 7 * MapSetting.gridSize + MapSetting.gridSize / 2);
        //green
        Point location2 = new Point(13 * MapSetting.gridSize + MapSetting.gridSize / 2, 7 * MapSetting.gridSize + MapSetting.gridSize / 2);
        //pink
        Point location3 = new Point(9 * MapSetting.gridSize + MapSetting.gridSize / 2, 8 * MapSetting.gridSize + MapSetting.gridSize / 2);
        //yellow
        Point location4 = new Point(13 * MapSetting.gridSize + MapSetting.gridSize / 2, 8 * MapSetting.gridSize + MapSetting.gridSize / 2);
        //Point velGhost = new Point(10, 0);
        ghostList.add(new Ghost(location1, new Point(5, 5), 0, LeaveHomeStrategy.make(), DirectionEnumType.RIGHT));
        ghostList.add(new Ghost(location2, new Point(10, 10), 1, LeaveHomeStrategy.make(), DirectionEnumType.LEFT));
        ghostList.add(new Ghost(location3, new Point(5, 5), 2, LeaveHomeStrategy.make(), DirectionEnumType.RIGHT));
        ghostList.add(new Ghost(location4, new Point(10, 10), 3, LeaveHomeStrategy.make(), DirectionEnumType.LEFT));
        Ghost ghost1 = MapSetting.ghostList.get(0);
        Ghost ghost2 = MapSetting.ghostList.get(2);
        ghost1.setVelocity(new Point(10, 10));
        ghost2.setVelocity(new Point(10, 10));
    }


    //0 is wall,1 is dot,2 is big dot,3 is fruit,4 is path
    // width 26, height 13
    public static final int[][] matrix = new int[][]{
            {0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 2},
            {2, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0},
            {0, 3, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1},
            {0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1},
            {0, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1},
            {1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 1},
            {0, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 0, 0, 0, 1, 1, 1},
            {0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1},
            {0, 2, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 1},
            {0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 2}
    };

    // list of wall lines
    java.util.List<Point[]> wallLineList;
    // list of exit lines
    java.util.List<Point[]> exitLineList;
    // list of base line
    java.util.List<Point[]> baseLineList;
    // list of base exit
    List<Point[]> baseExitLineList;
    // list of dots

    /**
     * add a new fruit periodically.
     *
     * @return new fruit
     */
    public Set<Fruit> getFruitList() {
        return fruitList;
    }

    public Set<Point> getDotsList() {
        return dotsList;
    }

    public Set<Point> getBigDotsList() {
        return bigDotsList;
    }

    /**
     * Constructor of mapSetting.
     */
    public MapSetting() {

        wallLineList = new ArrayList<>();
        exitLineList = new ArrayList<>();
        baseLineList = new ArrayList<>();
        baseExitLineList = new ArrayList<>();

        dotsList = new HashSet<>();
        bigDotsList = new HashSet<>();


        //add wall lines
        wallLineList.add(addOneLine(0, 0, 50, 0));
        wallLineList.add(addOneLine(50, 0, 50, 50));
        wallLineList.add(addOneLine(50, 50, 0, 50));
        wallLineList.add(addOneLine(0, 0, 0, 50));

        wallLineList.add(addOneLine(100, 50, 150, 50));
        wallLineList.add(addOneLine(150, 50, 150, 100));
        wallLineList.add(addOneLine(150, 100, 100, 100));
        wallLineList.add(addOneLine(100, 100, 100, 50));

        wallLineList.add(addOneLine(0, 100, 50, 100));
        wallLineList.add(addOneLine(50, 100, 50, 150));
        wallLineList.add(addOneLine(50, 150, 100, 150));
        wallLineList.add(addOneLine(100, 150, 100, 200));
        wallLineList.add(addOneLine(100, 200, 50, 200));
        wallLineList.add(addOneLine(50, 200, 50, 250));
        wallLineList.add(addOneLine(50, 250, 0, 250));
        wallLineList.add(addOneLine(0, 100, 0, 250));

        wallLineList.add(addOneLine(200, 50, 250, 50));
        wallLineList.add(addOneLine(250, 50, 250, 100));
        wallLineList.add(addOneLine(250, 100, 200, 100));
        wallLineList.add(addOneLine(200, 50, 200, 100));

        wallLineList.add(addOneLine(300, 0, 350, 0));
        wallLineList.add(addOneLine(350, 0, 350, 50));
        wallLineList.add(addOneLine(350, 50, 450, 50));
        wallLineList.add(addOneLine(450, 50, 450, 100));
        wallLineList.add(addOneLine(450, 100, 350, 100));
        wallLineList.add(addOneLine(350, 100, 350, 150));
        wallLineList.add(addOneLine(350, 150, 450, 150));
        wallLineList.add(addOneLine(450, 150, 450, 200));
        wallLineList.add(addOneLine(450, 200, 300, 200));
        wallLineList.add(addOneLine(300, 0, 300, 200));

        wallLineList.add(addOneLine(150, 150, 250, 150));
        wallLineList.add(addOneLine(250, 150, 250, 250));
        wallLineList.add(addOneLine(250, 250, 200, 250));
        wallLineList.add(addOneLine(200, 250, 200, 300));
        wallLineList.add(addOneLine(200, 300, 150, 300));
        wallLineList.add(addOneLine(150, 150, 150, 300));

        wallLineList.add(addOneLine(0, 450, 0, 650));
        wallLineList.add(addOneLine(0, 450, 50, 450));
        wallLineList.add(addOneLine(50, 450, 50, 350));
        wallLineList.add(addOneLine(50, 350, 100, 350));
        wallLineList.add(addOneLine(100, 350, 100, 550));
        wallLineList.add(addOneLine(100, 550, 50, 550));
        wallLineList.add(addOneLine(50, 550, 50, 600));
        wallLineList.add(addOneLine(50, 600, 200, 600));
        wallLineList.add(addOneLine(200, 600, 200, 650));
        wallLineList.add(addOneLine(200, 650, 0, 650));

        wallLineList.add(addOneLine(150, 350, 200, 350));
        wallLineList.add(addOneLine(200, 350, 200, 500));
        wallLineList.add(addOneLine(200, 500, 150, 500));
        wallLineList.add(addOneLine(150, 500, 150, 350));

        wallLineList.add(addOneLine(250, 350, 300, 350));
        wallLineList.add(addOneLine(300, 300, 300, 250));
        wallLineList.add(addOneLine(300, 250, 500, 250));
        wallLineList.add(addOneLine(500, 250, 500, 300));
        wallLineList.add(addOneLine(500, 300, 300, 300));
        wallLineList.add(addOneLine(300, 350, 300, 400));
        wallLineList.add(addOneLine(300, 400, 350, 400));
        wallLineList.add(addOneLine(350, 400, 350, 350));
        wallLineList.add(addOneLine(350, 350, 400, 350));
        wallLineList.add(addOneLine(400, 350, 400, 550));
        wallLineList.add(addOneLine(400, 550, 350, 550));
        wallLineList.add(addOneLine(350, 550, 350, 450));
        wallLineList.add(addOneLine(350, 450, 300, 450));
        wallLineList.add(addOneLine(300, 450, 300, 550));
        wallLineList.add(addOneLine(300, 550, 250, 550));
        wallLineList.add(addOneLine(250, 550, 250, 350));

        wallLineList.add(addOneLine(450, 500, 700, 500));
        wallLineList.add(addOneLine(700, 500, 700, 550));
        wallLineList.add(addOneLine(700, 550, 450, 550));
        wallLineList.add(addOneLine(450, 550, 450, 500));

        wallLineList.add(addOneLine(500, 50, 650, 50));
        wallLineList.add(addOneLine(650, 50, 650, 100));
        wallLineList.add(addOneLine(650, 100, 600, 100));
        wallLineList.add(addOneLine(600, 100, 600, 150));
        wallLineList.add(addOneLine(600, 150, 700, 150));
        wallLineList.add(addOneLine(700, 150, 700, 300));
        wallLineList.add(addOneLine(700, 300, 650, 300));
        wallLineList.add(addOneLine(650, 300, 650, 200));
        wallLineList.add(addOneLine(650, 200, 600, 200));
        wallLineList.add(addOneLine(600, 200, 600, 300));
        wallLineList.add(addOneLine(600, 300, 550, 300));
        wallLineList.add(addOneLine(550, 300, 550, 200));
        wallLineList.add(addOneLine(550, 200, 500, 200));
        wallLineList.add(addOneLine(500, 200, 500, 150));
        wallLineList.add(addOneLine(500, 150, 550, 150));
        wallLineList.add(addOneLine(550, 150, 550, 100));
        wallLineList.add(addOneLine(550, 100, 500, 100));
        wallLineList.add(addOneLine(500, 100, 500, 50));

        wallLineList.add(addOneLine(700, 50, 750, 50));
        wallLineList.add(addOneLine(750, 50, 750, 0));
        wallLineList.add(addOneLine(750, 0, 800, 0));
        wallLineList.add(addOneLine(800, 0, 800, 200));
        wallLineList.add(addOneLine(800, 200, 750, 200));
        wallLineList.add(addOneLine(750, 200, 750, 100));
        wallLineList.add(addOneLine(750, 100, 700, 100));
        wallLineList.add(addOneLine(700, 100, 700, 50));

        wallLineList.add(addOneLine(750, 250, 800, 250));
        wallLineList.add(addOneLine(800, 250, 800, 300));
        wallLineList.add(addOneLine(800, 300, 750, 300));
        wallLineList.add(addOneLine(750, 300, 750, 250));

        wallLineList.add(addOneLine(750, 350, 800, 350));
        wallLineList.add(addOneLine(800, 350, 800, 400));
        wallLineList.add(addOneLine(800, 400, 850, 400));
        wallLineList.add(addOneLine(850, 400, 850, 350));
        wallLineList.add(addOneLine(850, 350, 900, 350));
        wallLineList.add(addOneLine(900, 350, 900, 550));
        wallLineList.add(addOneLine(900, 550, 850, 550));
        wallLineList.add(addOneLine(850, 550, 850, 450));
        wallLineList.add(addOneLine(850, 450, 800, 450));
        wallLineList.add(addOneLine(800, 450, 800, 550));
        wallLineList.add(addOneLine(800, 550, 750, 550));
        wallLineList.add(addOneLine(750, 550, 750, 350));

        wallLineList.add(addOneLine(850, 50, 950, 50));
        wallLineList.add(addOneLine(950, 50, 950, 100));
        wallLineList.add(addOneLine(950, 100, 1000, 100));
        wallLineList.add(addOneLine(1000, 100, 1000, 50));
        wallLineList.add(addOneLine(1000, 50, 1100, 50));
        wallLineList.add(addOneLine(1100, 50, 1100, 200));
        wallLineList.add(addOneLine(1100, 200, 1050, 200));
        wallLineList.add(addOneLine(1050, 200, 1050, 100));
        wallLineList.add(addOneLine(1050, 100, 1000, 100));
        wallLineList.add(addOneLine(1000, 100, 1000, 200));
        wallLineList.add(addOneLine(1000, 200, 950, 200));
        wallLineList.add(addOneLine(950, 200, 950, 100));
        wallLineList.add(addOneLine(950, 100, 900, 100));
        wallLineList.add(addOneLine(900, 100, 900, 200));
        wallLineList.add(addOneLine(900, 200, 850, 200));
        wallLineList.add(addOneLine(850, 200, 850, 50));

        wallLineList.add(addOneLine(1150, 0, 1200, 0));
        wallLineList.add(addOneLine(1200, 0, 1200, 150));
        wallLineList.add(addOneLine(1200, 150, 1150, 150));
        wallLineList.add(addOneLine(1150, 150, 1150, 0));

        wallLineList.add(addOneLine(1100, 200, 1150, 200));
        wallLineList.add(addOneLine(1150, 200, 1150, 300));
        wallLineList.add(addOneLine(1150, 300, 1100, 300));
        wallLineList.add(addOneLine(1100, 300, 1100, 200));

        wallLineList.add(addOneLine(850, 250, 950, 250));
        wallLineList.add(addOneLine(950, 250, 950, 300));
        wallLineList.add(addOneLine(950, 300, 850, 300));
        wallLineList.add(addOneLine(850, 300, 850, 250));

        wallLineList.add(addOneLine(1000, 250, 1050, 250));
        wallLineList.add(addOneLine(1050, 250, 1050, 350));
        wallLineList.add(addOneLine(1050, 350, 1150, 350));
        wallLineList.add(addOneLine(1150, 350, 1150, 400));
        wallLineList.add(addOneLine(1150, 400, 1050, 400));
        wallLineList.add(addOneLine(1050, 400, 1050, 450));
        wallLineList.add(addOneLine(1050, 450, 1150, 450));
        wallLineList.add(addOneLine(1150, 450, 1150, 500));
        wallLineList.add(addOneLine(1150, 500, 950, 500));
        wallLineList.add(addOneLine(950, 500, 950, 450));
        wallLineList.add(addOneLine(950, 450, 1000, 450));
        wallLineList.add(addOneLine(1000, 450, 1000, 400));
        wallLineList.add(addOneLine(1000, 400, 950, 400));
        wallLineList.add(addOneLine(950, 400, 950, 350));
        wallLineList.add(addOneLine(950, 350, 1000, 350));
        wallLineList.add(addOneLine(1000, 350, 1000, 250));

        wallLineList.add(addOneLine(1000, 550, 1150, 550));
        wallLineList.add(addOneLine(1150, 550, 1150, 600));
        wallLineList.add(addOneLine(1150, 600, 1000, 600));
        wallLineList.add(addOneLine(1000, 600, 1000, 550));

        wallLineList.add(addOneLine(1250, 50, 1300, 50));
        wallLineList.add(addOneLine(1250, 100, 1300, 100));
        wallLineList.add(addOneLine(1250, 50, 1250, 100));

        wallLineList.add(addOneLine(1200, 250, 1250, 250));
        wallLineList.add(addOneLine(1250, 250, 1250, 450));
        wallLineList.add(addOneLine(1250, 450, 1200, 450));
        wallLineList.add(addOneLine(1200, 450, 1200, 250));

        wallLineList.add(addOneLine(1200, 500, 1250, 500));
        wallLineList.add(addOneLine(1250, 500, 1250, 600));
        wallLineList.add(addOneLine(1250, 600, 1200, 600));
        wallLineList.add(addOneLine(1200, 600, 1200, 500));

        wallLineList.add(addOneLine(900, 550, 950, 550));
        wallLineList.add(addOneLine(950, 550, 950, 600));
        wallLineList.add(addOneLine(950, 600, 900, 600));
        wallLineList.add(addOneLine(900, 600, 900, 550));

        wallLineList.add(addOneLine(700, 600, 850, 600));
        wallLineList.add(addOneLine(850, 600, 850, 650));
        wallLineList.add(addOneLine(700, 600, 700, 650));

        wallLineList.add(addOneLine(500, 550, 500, 600));
        wallLineList.add(addOneLine(500, 600, 650, 600));
        wallLineList.add(addOneLine(650, 600, 650, 550));

        wallLineList.add(addOneLine(300, 600, 450, 600));
        wallLineList.add(addOneLine(450, 600, 450, 650));
        wallLineList.add(addOneLine(300, 600, 300, 650));

        baseLineList.add(addOneLine(450, 350, 550, 350));
        baseExitLineList.add(addOneLine(550, 350, 600, 350));
        baseLineList.add(addOneLine(600, 350, 700, 350));
        baseLineList.add(addOneLine(700, 350, 700, 450));
        baseLineList.add(addOneLine(700, 450, 450, 450));
        baseLineList.add(addOneLine(450, 450, 450, 350));


        //add exit lines
        exitLineList.add(addOneLine(0, 300, 0, 350));
        exitLineList.add(addOneLine(1300, 300, 1300, 350));


        //dots location
        int col;
        int row;
        for (col = 0; col < 26; col++) {
            for (row = 0; row < 13; row++) {
                dotsList.add(new Point(col * 50 + 25, row * 50 + 25));
            }
        }
        // add 4 big dots
        bigDotsList.add(new Point(25 * 50 + 25, 25));
        bigDotsList.add(new Point(25 * 50 + 25, 12 * 50 + 25));
        bigDotsList.add(new Point(75, 11 * 50 + 25));
        bigDotsList.add(new Point(25, 75));

        // remove for big dot
        dotsList.remove(new Point(25 * 50 + 25, 25));
        dotsList.remove(new Point(25 * 50 + 25, 12 * 50 + 25));
        dotsList.remove(new Point(75, 11 * 50 + 25));

        dotsList.remove(new Point(25, 25));
        dotsList.remove(new Point(2 * 50 + 25, 50 + 25));
        dotsList.remove(new Point(4 * 50 + 25, 50 + 25));
        dotsList.remove(new Point(25, 2 * 50 + 25));
        dotsList.remove(new Point(25, 3 * 50 + 25));
        dotsList.remove(new Point(25, 4 * 50 + 25));
        dotsList.remove(new Point(50 + 25, 3 * 50 + 25));
        dotsList.remove(new Point(25, 12 * 50 + 25));
        dotsList.remove(new Point(25, 11 * 50 + 25));
        dotsList.remove(new Point(25, 10 * 50 + 25));
        dotsList.remove(new Point(25, 9 * 50 + 25));
        dotsList.remove(new Point(75, 12 * 50 + 25));
        dotsList.remove(new Point(75, 10 * 50 + 25));
        dotsList.remove(new Point(75, 9 * 50 + 25));
        dotsList.remove(new Point(75, 8 * 50 + 25));
        dotsList.remove(new Point(75, 7 * 50 + 25));
        dotsList.remove(new Point(2 * 50 + 25, 12 * 50 + 25));
        dotsList.remove(new Point(3 * 50 + 25, 12 * 50 + 25));
        dotsList.remove(new Point(3 * 50 + 25, 9 * 50 + 25));
        dotsList.remove(new Point(3 * 50 + 25, 8 * 50 + 25));
        dotsList.remove(new Point(3 * 50 + 25, 7 * 50 + 25));
        dotsList.remove(new Point(3 * 50 + 25, 5 * 50 + 25));
        dotsList.remove(new Point(3 * 50 + 25, 4 * 50 + 25));
        dotsList.remove(new Point(3 * 50 + 25, 3 * 50 + 25));
        dotsList.remove(new Point(4 * 50 + 25, 3 * 50 + 25));
        dotsList.remove(new Point(4 * 50 + 25, 4 * 50 + 25));
        dotsList.remove(new Point(6 * 50 + 25, 25));
        dotsList.remove(new Point(6 * 50 + 25, 50 + 25));
        dotsList.remove(new Point(6 * 50 + 25, 2 * 50 + 25));
        dotsList.remove(new Point(6 * 50 + 25, 3 * 50 + 25));
        dotsList.remove(new Point(7 * 50 + 25, 50 + 25));
        dotsList.remove(new Point(8 * 50 + 25, 50 + 25));
        dotsList.remove(new Point(7 * 50 + 25, 3 * 50 + 25));
        dotsList.remove(new Point(8 * 50 + 25, 3 * 50 + 25));
        dotsList.remove(new Point(6 * 50 + 25, 5 * 50 + 25));
        dotsList.remove(new Point(7 * 50 + 25, 5 * 50 + 25));
        dotsList.remove(new Point(8 * 50 + 25, 5 * 50 + 25));
        dotsList.remove(new Point(9 * 50 + 25, 5 * 50 + 25));
//        dotsList.remove(new Point(5 * 50 + 25, 6 * 50 + 25));
        dotsList.remove(new Point(5 * 50 + 25, 7 * 50 + 25));
        dotsList.remove(new Point(5 * 50 + 25, 8 * 50 + 25));
        dotsList.remove(new Point(5 * 50 + 25, 9 * 50 + 25));
        dotsList.remove(new Point(5 * 50 + 25, 10 * 50 + 25));
        dotsList.remove(new Point(6 * 50 + 25, 8 * 50 + 25));
        dotsList.remove(new Point(7 * 50 + 25, 8 * 50 + 25));
        dotsList.remove(new Point(7 * 50 + 25, 7 * 50 + 25));
        dotsList.remove(new Point(7 * 50 + 25, 9 * 50 + 25));
        dotsList.remove(new Point(7 * 50 + 25, 10 * 50 + 25));
        dotsList.remove(new Point(6 * 50 + 25, 12 * 50 + 25));
        dotsList.remove(new Point(7 * 50 + 25, 12 * 50 + 25));
        dotsList.remove(new Point(8 * 50 + 25, 12 * 50 + 25));
        dotsList.remove(new Point(9 * 50 + 25, 10 * 50 + 25));
        dotsList.remove(new Point(10 * 50 + 25, 10 * 50 + 25));
        dotsList.remove(new Point(11 * 50 + 25, 10 * 50 + 25));
        dotsList.remove(new Point(12 * 50 + 25, 10 * 50 + 25));
        dotsList.remove(new Point(13 * 50 + 25, 10 * 50 + 25));
        dotsList.remove(new Point(10 * 50 + 25, 11 * 50 + 25));
        dotsList.remove(new Point(11 * 50 + 25, 11 * 50 + 25));
        dotsList.remove(new Point(12 * 50 + 25, 11 * 50 + 25));
        dotsList.remove(new Point(9 * 50 + 25, 7 * 50 + 25));
        dotsList.remove(new Point(10 * 50 + 25, 7 * 50 + 25));
        dotsList.remove(new Point(11 * 50 + 25, 7 * 50 + 25));
        dotsList.remove(new Point(12 * 50 + 25, 7 * 50 + 25));
        dotsList.remove(new Point(13 * 50 + 25, 7 * 50 + 25));

        dotsList.remove(new Point(9 * 50 + 25, 8 * 50 + 25));
        dotsList.remove(new Point(10 * 50 + 25, 8 * 50 + 25));
        dotsList.remove(new Point(11 * 50 + 25, 8 * 50 + 25));
        dotsList.remove(new Point(12 * 50 + 25, 8 * 50 + 25));
        dotsList.remove(new Point(13 * 50 + 25, 8 * 50 + 25));

        dotsList.remove(new Point(10 * 50 + 25, 50 + 25));
        dotsList.remove(new Point(11 * 50 + 25, 50 + 25));
        dotsList.remove(new Point(12 * 50 + 25, 50 + 25));
        dotsList.remove(new Point(11 * 50 + 25, 2 * 50 + 25));
        dotsList.remove(new Point(11 * 50 + 25, 3 * 50 + 25));
        dotsList.remove(new Point(11 * 50 + 25, 4 * 50 + 25));
        dotsList.remove(new Point(11 * 50 + 25, 5 * 50 + 25));
        dotsList.remove(new Point(12 * 50 + 25, 3 * 50 + 25));
        dotsList.remove(new Point(10 * 50 + 25, 3 * 50 + 25));
        dotsList.remove(new Point(13 * 50 + 25, 3 * 50 + 25));
        dotsList.remove(new Point(13 * 50 + 25, 4 * 50 + 25));
        dotsList.remove(new Point(13 * 50 + 25, 5 * 50 + 25));

        dotsList.remove(new Point(14 * 50 + 25, 50 + 25));
        dotsList.remove(new Point(15 * 50 + 25, 50 + 25));
        dotsList.remove(new Point(15 * 50 + 25, 25));
        dotsList.remove(new Point(15 * 50 + 25, 2 * 50 + 25));
        dotsList.remove(new Point(15 * 50 + 25, 3 * 50 + 25));

        dotsList.remove(new Point(15 * 50 + 25, 5 * 50 + 25));
        dotsList.remove(new Point(15 * 50 + 25, 7 * 50 + 25));
        dotsList.remove(new Point(15 * 50 + 25, 8 * 50 + 25));
        dotsList.remove(new Point(15 * 50 + 25, 9 * 50 + 25));
        dotsList.remove(new Point(15 * 50 + 25, 10 * 50 + 25));
        dotsList.remove(new Point(16 * 50 + 25, 8 * 50 + 25));
        dotsList.remove(new Point(17 * 50 + 25, 8 * 50 + 25));
        dotsList.remove(new Point(17 * 50 + 25, 7 * 50 + 25));
        dotsList.remove(new Point(17 * 50 + 25, 9 * 50 + 25));
        dotsList.remove(new Point(17 * 50 + 25, 10 * 50 + 25));
        dotsList.remove(new Point(18 * 50 + 25, 11 * 50 + 25));

        dotsList.remove(new Point(15 * 50 + 25, 12 * 50 + 25));
        dotsList.remove(new Point(14 * 50 + 25, 12 * 50 + 25));
        dotsList.remove(new Point(16 * 50 + 25, 12 * 50 + 25));

        dotsList.remove(new Point(22 * 50 + 25, 11 * 50 + 25));
        dotsList.remove(new Point(21 * 50 + 25, 11 * 50 + 25));
        dotsList.remove(new Point(20 * 50 + 25, 11 * 50 + 25));

        dotsList.remove(new Point(24 * 50 + 25, 11 * 50 + 25));
        dotsList.remove(new Point(24 * 50 + 25, 10 * 50 + 25));
        dotsList.remove(new Point(24 * 50 + 25, 8 * 50 + 25));
        dotsList.remove(new Point(24 * 50 + 25, 7 * 50 + 25));
        dotsList.remove(new Point(24 * 50 + 25, 6 * 50 + 25));
        dotsList.remove(new Point(24 * 50 + 25, 5 * 50 + 25));

        //add
        dotsList.remove(new Point(22 * 50 + 25, 4 * 50 + 25));
        dotsList.remove(new Point(22 * 50 + 25, 5 * 50 + 25));

        dotsList.remove(new Point(22 * 50 + 25, 9 * 50 + 25));
        dotsList.remove(new Point(21 * 50 + 25, 9 * 50 + 25));
        dotsList.remove(new Point(20 * 50 + 25, 9 * 50 + 25));
        dotsList.remove(new Point(19 * 50 + 25, 9 * 50 + 25));
        dotsList.remove(new Point(20 * 50 + 25, 8 * 50 + 25));
        dotsList.remove(new Point(20 * 50 + 25, 7 * 50 + 25));
        dotsList.remove(new Point(19 * 50 + 25, 7 * 50 + 25));
        dotsList.remove(new Point(21 * 50 + 25, 7 * 50 + 25));
        dotsList.remove(new Point(22 * 50 + 25, 7 * 50 + 25));
        dotsList.remove(new Point(20 * 50 + 25, 6 * 50 + 25));
        dotsList.remove(new Point(20 * 50 + 25, 5 * 50 + 25));
        dotsList.remove(new Point(18 * 50 + 25, 5 * 50 + 25));
        dotsList.remove(new Point(17 * 50 + 25, 5 * 50 + 25));

        dotsList.remove(new Point(17 * 50 + 25, 3 * 50 + 25));
        dotsList.remove(new Point(17 * 50 + 25, 2 * 50 + 25));
        dotsList.remove(new Point(17 * 50 + 25, 50 + 25));
        dotsList.remove(new Point(18 * 50 + 25, 50 + 25));
        dotsList.remove(new Point(20 * 50 + 25, 50 + 25));
        dotsList.remove(new Point(21 * 50 + 25, 50 + 25));
        dotsList.remove(new Point(21 * 50 + 25, 2 * 50 + 25));
        dotsList.remove(new Point(21 * 50 + 25, 3 * 50 + 25));
        dotsList.remove(new Point(19 * 50 + 25, 2 * 50 + 25));
        dotsList.remove(new Point(19 * 50 + 25, 3 * 50 + 25));

        dotsList.remove(new Point(23 * 50 + 25, 25));
        dotsList.remove(new Point(23 * 50 + 25, 50 + 25));
        dotsList.remove(new Point(23 * 50 + 25, 2 * 50 + 25));
        dotsList.remove(new Point(25 * 50 + 25, 50 + 25));

        fruitList = new HashSet<>();
        Fruit fruit = new Fruit(this);
        fruitList.add(fruit);

    }

    /**
     * add one line to line list.
     *
     * @param x1 x of first point
     * @param y1 y of first point
     * @param x2 x of second point
     * @param y2 y of second point
     * @return the line
     */
    public Point[] addOneLine(int x1, int y1, int x2, int y2) {
        Point startPoint = new Point(x1, y1);
        Point endPoint = new Point(x2, y2);
        return new Point[]{startPoint, endPoint};
    }

}

