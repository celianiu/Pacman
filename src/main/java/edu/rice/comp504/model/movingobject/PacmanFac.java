package edu.rice.comp504.model.movingobject;

/**
 * pacman factory class.
 *
 * @Author ChaoWang
 * @Date 2021/11/12 20:40
 */
public class PacmanFac implements IPacmanFac {
    private static PacmanFac singleton;

    @Override
    public Pacman make() {
        return null;
    }

    /**
     * get the single instance of pacmanFac.
     * @return a single instance of pacmanFac
     */
    public static PacmanFac getInstance() {
        if (singleton == null) {
            singleton = new PacmanFac();
        }
        return singleton;
    }
}
