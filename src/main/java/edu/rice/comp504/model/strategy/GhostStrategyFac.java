package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.movingobject.Ghost;

import java.util.Random;

/**
 * Factory that creates line strategies.
 */
public class GhostStrategyFac implements IStrategyFac {
    private IUpdateStrategy[] children;
    Random random = new Random();

    /**
     * Constructor.
     */
    public GhostStrategyFac() {

    }

    @Override
    public IUpdateStrategy make() {
        //randomly select initial strategy
        return ChaseStrategy.make();

    }

    /**
     * get requested strategy.
     *
     * @param strat requested strategy.
     * @return The strategy requested
     */

    public IUpdateStrategy getStrategy(String strat) {
        switch (strat) {
            case ("Chase"):
                return ChaseStrategy.make();
            case ("BackHome"):
                return BackHomeStrategy.make();
            case ("RandomMove"):
                return RandomMoveStrategy.make();
            default:
                return ChaseStrategy.make();
        }


    }


    /**
     * Switch the strategy.
     *
     * @param ghost The ghost.
     * @return The new strategy for the ghost
     */
    public IUpdateStrategy switchStrategy(Ghost ghost) {
        String name = ghost.getUpdateStrategy().getName();
        switch (name) {
            case "Chase":
                //return StraightColorChangingStrategy.make();
                return getStrategy("BackHome");
            case "RandomMove":
                //return StraightSizeChangingStrategy.make();
                return getStrategy("BackHome");
            default:
                return getStrategy("BackHome");
        }

    }
}


