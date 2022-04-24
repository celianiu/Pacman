package edu.rice.comp504.model.movingobject;

/**
 * ghost factory.
 *
 * @Author ChaoWang
 * @Date 2021/11/12 20:41
 */
public class GhostFac implements IGhostFac {
    private static GhostFac singleton;

    @Override
    public Ghost make(String type) {
        return null;
    }

    /**
     * get the single instance of GhostFac.
     *
     * @return a single instance of GhostFac
     */
    public static GhostFac getInstance() {
        if (singleton == null) {
            singleton = new GhostFac();
        }
        return singleton;
    }
}
