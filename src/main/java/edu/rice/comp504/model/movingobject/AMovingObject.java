package edu.rice.comp504.model.movingobject;

import edu.rice.comp504.model.enumtype.DirectionEnumType;
import edu.rice.comp504.model.map.MapSetting;

import java.awt.*;
import java.beans.PropertyChangeListener;

/**
 * An abstract class that includes all necessary fields to describe moving.
 *
 * @Author ChaoWang
 * @Date 2021/11/12 20:31
 */
public abstract class AMovingObject implements PropertyChangeListener {
    // current location of the object
    Point location;

    //current velocity of the object
    Point velocity;

    //object color
    int color;

    protected int flash;


    //object direction
    DirectionEnumType directionEnumType;

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public Point getVelocity() {
        return velocity;
    }

    public void setVelocity(Point velocity) {
        this.velocity = velocity;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public DirectionEnumType getDirection() {
        return directionEnumType;
    }

    public void setDirection(DirectionEnumType direction) {
        this.directionEnumType = direction;
    }


    /**
     * detect if the moveing object collides with wall(including border).
     *
     * @return true if collide with wall
     */
    public boolean detectCollideWithWall() {
        int x;
        int y;
        switch (directionEnumType) {
            case UP:
                x = getXInMatrix(location.x);
                y = getYInMatrix(location.y - MapSetting.gridSize / 2 - 1);
                break;
            case DOWN:
                x = getXInMatrix(location.x);
                y = getYInMatrix(location.y + MapSetting.gridSize / 2);
                break;
            case LEFT:
                x = getXInMatrix(location.x - MapSetting.gridSize / 2 - 1);
                y = getYInMatrix(location.y);
                break;
            case RIGHT:
                x = getXInMatrix(location.x + MapSetting.gridSize / 2);
                y = getYInMatrix(location.y);
                break;
            default:
                return true;
        }
        return outOfBorder(x, y) || MapSetting.matrix[y][x] == 0;
    }

    public boolean outOfBorder(int x, int y) {
        return x < 0 || x >= MapSetting.gridNumX || y < 0 || y >= MapSetting.gridNumY;
    }

    public int getXInMatrix(int x) {
        if (x < 0) {
            return -1;
        }
        return x / MapSetting.gridSize;
    }

    public int getYInMatrix(int y) {
        if (y < 0) {
            return -1;
        }
        return y / MapSetting.gridSize;
    }

    /**
     * judge if the pacman has enter into the left exit and update its position.
     */
    public boolean goToLeftExit(DirectionEnumType directionEnumType) {
        int x = getXInMatrix(location.x - MapSetting.gridSize / 2 - 1);
        int y = getYInMatrix(location.y);
        if (x < 0 && y == 6 && directionEnumType == DirectionEnumType.LEFT) {
            if (this.directionEnumType == DirectionEnumType.DOWN || this.directionEnumType == DirectionEnumType.UP) {
                location.x = location.x + MapSetting.gridNumX * MapSetting.gridSize - 50;
            } else {
                location.x = location.x + MapSetting.gridNumX * MapSetting.gridSize;
            }
            return true;
        }
        return false;
    }

    /**
     * judge if the pacman has enter into the right exit and update its position.
     */
    public boolean goToRightExit(DirectionEnumType directionEnumType) {
        int x = getXInMatrix(location.x + MapSetting.gridSize / 2);
        int y = getYInMatrix(location.y);
        if (x == 26 && y == 6 && directionEnumType == DirectionEnumType.RIGHT) {
            location.x = location.x - MapSetting.gridNumX * MapSetting.gridSize + 50;
            return true;
        }
        return false;
    }

    /**
     * detect if there's wall in certain direction.
     *
     * @return true if collide with wall
     */
    public boolean detectWall(DirectionEnumType direction) {
        int x;
        int y;
        switch (direction) {
            case UP:
                x = getXInMatrix(location.x);
                y = getYInMatrix(location.y - MapSetting.gridSize / 2 - 1);
                break;
            case DOWN:
                x = getXInMatrix(location.x);
                y = getYInMatrix(location.y + MapSetting.gridSize / 2);
                break;
            case LEFT:
                x = getXInMatrix(location.x - MapSetting.gridSize / 2 - 1);
                y = getYInMatrix(location.y);
                break;
            case RIGHT:
                x = getXInMatrix(location.x + MapSetting.gridSize / 2);
                y = getYInMatrix(location.y);
                break;
            default:
                return true;
        }
        return outOfBorder(x, y) || MapSetting.matrix[y][x] == 0;
    }

    public int getFlash() {
        return flash;
    }

    /**
     * Set flash status.
     * @param flash number shows status.
     */
    public void setFlash(int flash) {
        this.flash = flash;
    }

    /**
     * Get direction enumType.
     * @return directionEnumType.
     */
    public DirectionEnumType getDirectionEnumType() {
        return directionEnumType;
    }

}
