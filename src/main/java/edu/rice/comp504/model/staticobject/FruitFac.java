package edu.rice.comp504.model.staticobject;

public class FruitFac implements IFruitFac{
    private static FruitFac singleton;

    @Override
    public Fruit make() {
        return null;
    }

    /**
     * get the single instance of FruitFac.
     * @return a single instance of FruitFac
     */
    public static FruitFac getInstance() {
        if (singleton == null) {
            singleton = new FruitFac();
        }
        return singleton;
    }


}
