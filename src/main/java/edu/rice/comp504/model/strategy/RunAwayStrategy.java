package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.enumtype.DirectionEnumType;
import edu.rice.comp504.model.map.MapSetting;
import edu.rice.comp504.model.movingobject.AMovingObject;
import edu.rice.comp504.model.movingobject.Ghost;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import static edu.rice.comp504.model.enumtype.DirectionEnumType.*;


public class RunAwayStrategy implements IUpdateStrategy {
    private static IUpdateStrategy ONLY;
    private ArrayList<DirectionEnumType> dirChoice;
    private ArrayList<DirectionEnumType> openDir;
    private DirectionEnumType[] dirs = {LEFT, RIGHT, UP, DOWN};
    private Random random = new Random();


    /**
     * Constructor.
     */
    private RunAwayStrategy() {
    }

    /**
     * Only makes 1 runAway strategy.
     *
     * @return The runAway strategy
     */
    public static IUpdateStrategy make() {
        if (ONLY == null) {
            ONLY = new RunAwayStrategy();
        }
        return ONLY;
    }

    /**
     * Get the strategy name.
     *
     * @return strategy name
     */
    public String getName() {
        return "RunAway";
    }

    /**
     * Check which direction ghost should move to run away from pacman.
     *
     * @param pacLoc   pacman Location
     * @param ghostLoc ghost Location
     * @return a point shows direction that ghost to move (0 for above and left,1 for bottom and right, 2 for ghost already align with the pacman horizontally or vertically )
     */
    public Point moveDirection(Point pacLoc, Point ghostLoc) {
        Point direction = new Point();
        if (pacLoc.x < ghostLoc.x) {
            direction.x = 1;
        } else {
            direction.x = 0;
        }

        if (pacLoc.y < ghostLoc.y) {
            direction.y = 1;
        } else {
            direction.y = 0;
        }
        return direction;
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
        openDir = new ArrayList<>();
        for (DirectionEnumType dir : dirs) {
            if (!ghost.detectWall(dir)) {
                openDir.add(dir);
            }
        }
        return openDir;
    }

    /**
     * Get the priority choice direction.
     *
     * @param direction the x and y direction ghost should move to reach pacman
     * @param openDir   direction that available to switch
     * @param xPrior    if gonna move horizontally first
     * @return priority choice direction.
     */
    public DirectionEnumType getPriorChoice(Point direction, ArrayList<DirectionEnumType> openDir, boolean xPrior) {
        dirChoice = new ArrayList<>();
        if (xPrior) {
            if (direction.x == 0) {
                dirChoice.add(LEFT);
                if (direction.y == 0) {
                    dirChoice.add(UP);
                    dirChoice.add(DOWN);
                } else {
                    dirChoice.add(DOWN);
                    dirChoice.add(UP);
                }
                dirChoice.add(RIGHT);
            } else {
                dirChoice.add(RIGHT);
                if (direction.y == 0) {
                    dirChoice.add(UP);
                    dirChoice.add(DOWN);
                } else {
                    dirChoice.add(DOWN);
                    dirChoice.add(UP);
                }
                dirChoice.add(LEFT);
            }
        } else {
            if (direction.y == 0) {
                dirChoice.add(UP);
                if (direction.x == 0) {
                    dirChoice.add(LEFT);
                    dirChoice.add(RIGHT);
                } else {
                    dirChoice.add(RIGHT);
                    dirChoice.add(LEFT);
                }
                dirChoice.add(DOWN);
            } else {
                dirChoice.add(DOWN);
                if (direction.x == 0) {
                    dirChoice.add(LEFT);
                    dirChoice.add(RIGHT);
                } else {
                    dirChoice.add(RIGHT);
                    dirChoice.add(LEFT);
                }
                dirChoice.add(UP);
            }
        }

        for (DirectionEnumType dir : dirs) {

            if (!openDir.contains(dir)) {
                dirChoice.remove(dir);
            }
        }
        if (dirChoice.size() >= 2) {
            return dirChoice.get(random.nextInt(2));
        }
        return dirChoice.get(0);
    }

    /**
     * Update the ghost state to let it chase the pacman.
     *
     * @param ghost The pacman to chase
     * @param ghost The ghost to update
     */
    @Override
    public void updateState(AMovingObject pacman, AMovingObject ghost) {
        Point direction = moveDirection(pacman.getLocation(), ghost.getLocation());
        // Calculate vertical and horizontal distance between pacman and ghost, move on larger distance first
        int xDistance = Math.abs(ghost.getLocation().x - pacman.getLocation().x);
        int yDistance = Math.abs(ghost.getLocation().y - pacman.getLocation().y);
        if (ghost.detectCollideWithWall()) {
            openDir = getOpenDir((Ghost) ghost);
            if (openDir.size() == 1) {
                if (ghost.getLocation().x % MapSetting.gridSize == 25 && ghost.getLocation().y % MapSetting.gridSize == 25) {
                    ghost.setDirection(openDir.get(0));
                }
            } else if (openDir.size() > 1) {
                switch (ghost.getDirection()) {
                    case UP:
                        openDir.remove(DOWN);
                        break;
                    case DOWN:
                        openDir.remove(UP);
                        break;
                    case LEFT:
                        openDir.remove(RIGHT);
                        break;
                    case RIGHT:
                        openDir.remove(LEFT);
                        break;
                    default:
                        break;
                }
                if (ghost.getLocation().x % MapSetting.gridSize == 25 && ghost.getLocation().y % MapSetting.gridSize == 25) {
                    ghost.setDirection(openDir.get(0));
                }
            }
        }

        //only change dir if ghost reach wall or reach intersection
        if (checkIntersection((Ghost) ghost)) {
            openDir = getOpenDir((Ghost) ghost);
            System.out.println("ghost color: " + ghost.getColor() + " ghost openDir: " + openDir);
            if (ghost.getLocation().x % MapSetting.gridSize == 25 && ghost.getLocation().y % MapSetting.gridSize == 25) {
                ghost.setDirection(getPriorChoice(direction, openDir, random.nextBoolean()));
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