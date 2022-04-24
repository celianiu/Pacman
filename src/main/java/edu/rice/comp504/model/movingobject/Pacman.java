package edu.rice.comp504.model.movingobject;

import edu.rice.comp504.model.cmd.ICmd;
import edu.rice.comp504.model.enumtype.PacmanStatusEnumType;

import java.awt.*;
import java.beans.PropertyChangeEvent;

/**
 * The pacman class.
 *
 * @Author ChaoWang
 * @Date 2021/11/12 20:33
 */
public class Pacman extends AMovingObject {
    private PacmanStatusEnumType status;
    private int life;
    private static int score;
    public int eatenGhost;
    public static int timeAfterEatBigDots = 0;
    public boolean eatBigDots;
    public static int startTime = 0;

    public Pacman(Point location, Point vel) {
        this.location = location;
        this.velocity = vel;
        this.status = PacmanStatusEnumType.LIVE;
        this.life = 3;
        this.eatenGhost = 0;
        this.eatBigDots = false;
    }

    /**
     * Get the object name.
     *
     * @return object name
     */
    public String getName() {
        return "pacman";
    }

    /**
     * judge the status of pacman and update the position.
     *
     * @param evt the event to update the status of pacman
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("pacman")) {
            ((ICmd) evt.getNewValue()).execute(this);
        }
    }

    /**
     * Update velocity based on direction.
     */
    public void updateVel() {
        switch (directionEnumType) {
            case DOWN:
                velocity.setLocation(0, 10);
                break;
            case UP:
                velocity.setLocation(0, -10);
                break;
            case LEFT:
                velocity.setLocation(-10, 0);
                break;
            case RIGHT:
                velocity.setLocation(10, 0);
                break;
            default:
                break;
        }
    }

    public void loseLife() {
        life--;
    }


    public void increaseEatenGhost() {
        eatenGhost++;
    }

    public void decreaseEatenGhost() {
        eatenGhost--;
    }

    public int getEatenGhost() {
        return eatenGhost;
    }

    public void increaseTimeBigDots() {
        timeAfterEatBigDots++;
    }

    public int getTimeAfterEatBigDots() {
        return timeAfterEatBigDots;
    }

    public void setTimeAfterEatBigDots() {
        timeAfterEatBigDots = 0;
    }

    public void setEatBigDots(boolean status) {
        eatBigDots = status;
    }

    public boolean getEatBigDots() {
        return eatBigDots;
    }

    public int getLife() {
        return life;
    }

    public void setStartTime(int time) {
        startTime += time;
    }

    public int getStartTime() {
        return startTime;
    }
}
