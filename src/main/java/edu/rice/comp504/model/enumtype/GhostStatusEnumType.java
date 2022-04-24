package edu.rice.comp504.model.enumtype;

/**
 * status of ghost.
 *
 * @Author ChaoWang
 * @Date 2021/11/13 8:07
 */
public enum GhostStatusEnumType {
    //normal status
    NORMAL(0),
    //can be eaten by pacman. switch to this status after pacman eats big dot
    BLUE(1),
    //have been eaten by pacman, travel quickly to square box
    TWO_EYES(2),
    //after become blue for a period time, the ghost switches to this flashing status
    DIZZY(3);
    private final int value;

    GhostStatusEnumType(int value) {
        this.value = value;
    }
}
