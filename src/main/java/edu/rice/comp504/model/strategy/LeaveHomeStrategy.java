package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.enumtype.DirectionEnumType;
import edu.rice.comp504.model.map.MapSetting;
import edu.rice.comp504.model.movingobject.AMovingObject;
import edu.rice.comp504.model.movingobject.Ghost;

import java.awt.*;

public class LeaveHomeStrategy implements IUpdateStrategy {
    private static IUpdateStrategy ONLY;
    Point leaveHomePoint = new Point(11, 7);

    @Override
    public String getName() {
        return "LeaveHome";
    }

    /**
     * Constructor.
     */
    private LeaveHomeStrategy() {
    }

    /**
     * Only makes 1 Chase strategy.
     *
     * @return The Chase strategy
     */
    public static IUpdateStrategy make() {
        if (ONLY == null) {
            ONLY = new LeaveHomeStrategy();
        }
        return ONLY;
    }

    @Override
    public void updateState(AMovingObject pacman, AMovingObject ghost) {
        int x = ghost.getLocation().x / MapSetting.gridSize;
        int y = ghost.getLocation().y / MapSetting.gridSize;
        // move the ghost on it direction

        switch (ghost.getDirection()) {
            case LEFT:
                Point newLoc = new Point(ghost.getLocation().x - ghost.getVelocity().x, ghost.getLocation().y);
                ghost.setLocation(newLoc);
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

        if (x == leaveHomePoint.x && y == leaveHomePoint.y) {
            if (ghost.getLocation().x % MapSetting.gridSize == 25 && ghost.getLocation().y % MapSetting.gridSize == 25) {
                if (ghost.getColor() == 1 || ghost.getColor() == 0) {
                    ((Ghost) ghost).setUpdateStrategy(ChaseStrategy.make());
                } else {
                    ((Ghost) ghost).setUpdateStrategy(RandomMoveStrategy.make());
                }
            }
        } else if (x == 11 && y == 8) {
            if (ghost.getLocation().x % MapSetting.gridSize == 25 && ghost.getLocation().y % MapSetting.gridSize == 25) {
                ghost.setDirection(DirectionEnumType.UP);
            }
        }

    }
}
