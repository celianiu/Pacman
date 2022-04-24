package edu.rice.comp504.model.movingobject;

/**
 * interface to make a ghost according to the specified type.
 *
 * @Author ChaoWang
 * @Date 2021/11/12 20:41
 */
public interface IGhostFac {
    /**
     * make a ghost according to the type.
     * @param type the type of ghost
     * @return A Ghost
     */
    Ghost make(String type);
}
