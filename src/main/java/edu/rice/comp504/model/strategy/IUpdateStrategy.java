package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.movingobject.AMovingObject;

/**
 * An interface for ball strategies that determine the ball behavior.
 */
public interface IUpdateStrategy {
    /**
     * The name of the strategy.
     *
     * @return strategy name
     */
    String getName();

    /**
     * Update the state of the ghost.
     *
     * @param pacman  The pacman
     * @param context The ghost.
     */
    void updateState(AMovingObject pacman, AMovingObject context);


}
