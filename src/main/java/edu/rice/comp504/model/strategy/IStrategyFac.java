package edu.rice.comp504.model.strategy;

/**
 * A factory that makes strategies.
 */
public interface IStrategyFac {

    /**
     * Makes a strategy.
     *
     * @return A strategy
     */
    IUpdateStrategy make();
}
