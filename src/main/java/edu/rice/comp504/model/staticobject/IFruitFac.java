package edu.rice.comp504.model.staticobject;

import edu.rice.comp504.model.movingobject.Ghost;

public interface IFruitFac {

    /**
     * make a ghost according to the type.
     * @return A Ghost
     */
    Fruit make();

}
