package edu.rice.comp504.model.movingobject;

/**
 * A factory interface that makes a new pacman.
 *
 * @Author ChaoWang
 * @Date 2021/11/12 20:42
 */
public interface IPacmanFac {
    /**
     * Make a new pacman.
     * @return A pacman
     */
    Pacman make();
}
