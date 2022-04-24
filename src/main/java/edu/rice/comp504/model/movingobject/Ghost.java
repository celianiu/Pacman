package edu.rice.comp504.model.movingobject;

import edu.rice.comp504.model.enumtype.DirectionEnumType;
import edu.rice.comp504.model.enumtype.GhostStatusEnumType;
import edu.rice.comp504.model.map.MapSetting;
import edu.rice.comp504.model.strategy.ChaseStrategy;
import edu.rice.comp504.model.strategy.IUpdateStrategy;
import edu.rice.comp504.model.strategy.RandomMoveStrategy;

import java.awt.*;
import java.beans.PropertyChangeEvent;

/**
 * Ghost class.
 *
 * @Author ChaoWang
 * @Date 2021/11/12 20:33
 */
public class Ghost extends AMovingObject {
    private GhostStatusEnumType status;
    private IUpdateStrategy updateStrategy;
    private int index;

    /**
     * Get the object name.
     *
     * @return object name
     */
    public String getName() {
        return "ghost";
    }

    /**
     * Ghost Constructor.
     *
     * @param location          current location
     * @param vel               current velocity
     * @param color             current color
     * @param strategy          current strategy
     * @param directionEnumType current direction
     */
    public Ghost(Point location, Point vel, int color, IUpdateStrategy strategy, DirectionEnumType directionEnumType) {
        this.location = location;
        this.velocity = vel;
        this.color = color;
        this.status = GhostStatusEnumType.NORMAL;
        this.updateStrategy = strategy;
        this.directionEnumType = directionEnumType;
        this.index = 0;
    }

    /**
     * judge the status of ghost and update its position.
     *
     * @param evt event to update the ghost
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        reachHome();
        updateStrategy.updateState((AMovingObject) evt.getNewValue(), this);
        this.setFlash(index);
        index = index == 0 ? 1 : 0;
    }

    /**
     * Get ghost strategy.
     *
     * @return updateStrategy of the ghost
     */
    public IUpdateStrategy getUpdateStrategy() {
        return updateStrategy;
    }

    /**
     * Get ghost strategy.
     *
     * @return updateStrategy of the ghost
     */
    public void setUpdateStrategy(IUpdateStrategy strategy) {
        this.updateStrategy = strategy;
    }

    public GhostStatusEnumType getStatus() {
        return status;
    }

    public void setStatus(GhostStatusEnumType status) {
        this.status = status;
    }


    /**
     * Check if the backHome ghost reach home.
     *
     * @return updateStrategy of the ghost
     */
    public boolean reachHome() {
        // if the ghost reach home
        int x = location.x / MapSetting.gridSize;
        int y = location.y / MapSetting.gridSize;
        //return to the center box
        if (this.status == GhostStatusEnumType.TWO_EYES && x == 11 && y == 7) {
            if (color == 0 || color == 1) {
                setUpdateStrategy(ChaseStrategy.make());
                setStatus(GhostStatusEnumType.NORMAL);
            } else {
                setUpdateStrategy(RandomMoveStrategy.make());
                setStatus(GhostStatusEnumType.NORMAL);
            }
            return true;
        }
        return false;
    }


}
