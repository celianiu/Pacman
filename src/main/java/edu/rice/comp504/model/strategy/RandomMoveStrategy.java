package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.enumtype.DirectionEnumType;
import edu.rice.comp504.model.map.MapSetting;
import edu.rice.comp504.model.movingobject.AMovingObject;
import edu.rice.comp504.model.movingobject.Ghost;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import static edu.rice.comp504.model.enumtype.DirectionEnumType.*;


public class RandomMoveStrategy implements IUpdateStrategy {
    private static IUpdateStrategy ONLY;
    private ArrayList<DirectionEnumType> openDir;
    private DirectionEnumType[] dirs = {LEFT, RIGHT, UP, DOWN};
    private Random random = new Random();

    /**
     * Constructor.
     */
    private RandomMoveStrategy() {
    }

    /**
     * Only makes 1 runAway strategy.
     *
     * @return The runAway strategy
     */
    public static IUpdateStrategy make() {
        if (ONLY == null) {
            ONLY = new RandomMoveStrategy();
        }
        return ONLY;
    }

    /**
     * Get the strategy name.
     *
     * @return strategy name
     */
    public String getName() {
        return "RandomMove";
    }

    /**
     * Check if the ghost currently at an intersection. Ghost only change direction at intersection.
     *
     * @param ghost the ghost
     * @return A boolean of if the ghost at an intersection
     */
    public boolean checkIntersection(Ghost ghost) {
//        if (ghost.getDirection() == LEFT || ghost.getDirection() == DirectionEnumType.RIGHT) {
//            return ghost.detectWall(DirectionEnumType.UP) || ghost.detectWall(DirectionEnumType.DOWN);
//        } else {
//            return ghost.detectWall(LEFT) || ghost.detectWall(DirectionEnumType.RIGHT);
//        }
        int count = 0;
        for (DirectionEnumType dir : dirs) {
            if (!ghost.detectWall(dir)) {
                count++;
            }
        }
        return count >= 3;
    }

    /**
     * Check All open direction.
     *
     * @param ghost the ghost
     * @return a list of openDir
     */
    public ArrayList<DirectionEnumType> getOpenDir(Ghost ghost) {
        ArrayList<DirectionEnumType> openDir = new ArrayList<>();
        for (DirectionEnumType dir : dirs) {
            if (!ghost.detectWall(dir)) {
                openDir.add(dir);
            }
        }
        return openDir;
    }

    /**
     * Update the ghost state to let it chase the pacman.
     *
     * @param ghost The pacman to chase
     * @param ghost The ghost to update
     */
    @Override
    public void updateState(AMovingObject pacman, AMovingObject ghost) {

        //if ghost not reach wall or ghost reach an intersection
        if (ghost.detectCollideWithWall()) {
            openDir = getOpenDir((Ghost) ghost);
            if (openDir.size() > 1) {
                openDir.remove(ghost.getDirection());
            }

            int dirChoice = random.nextInt(openDir.size());
            if (ghost.getLocation().x % MapSetting.gridSize == 25 && ghost.getLocation().y % MapSetting.gridSize == 25) {
                ghost.setDirection(openDir.get(dirChoice));
            }


        }
        // move the ghost on it direction
        switch (ghost.getDirection()) {
            case LEFT:
                ghost.setLocation(new Point(ghost.getLocation().x - ghost.getVelocity().x, ghost.getLocation().y));
                break;
            case RIGHT:
                ghost.setLocation(new Point(ghost.getLocation().x + ghost.getVelocity().x, ghost.getLocation().y));
                break;
            case UP:
                ghost.setLocation(new Point(ghost.getLocation().x, ghost.getLocation().y - ghost.getVelocity().y));
                break;
            case DOWN:
                ghost.setLocation(new Point(ghost.getLocation().x, ghost.getLocation().y + ghost.getVelocity().y));
                break;
            default:
                break;
        }

    }
}