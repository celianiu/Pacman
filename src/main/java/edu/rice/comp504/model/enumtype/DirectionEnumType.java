package edu.rice.comp504.model.enumtype;

/**
 * describe moving direction.
 *
 * @Author ChaoWang
 * @Date 2021/11/13 7:55
 */
public enum DirectionEnumType {
    LEFT(0),
    UP(1),
    RIGHT(2),
    DOWN(3);
    private final int value;

    DirectionEnumType(int value) {
        this.value = value;
    }
}
