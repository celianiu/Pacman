package edu.rice.comp504.model.cmd;

import edu.rice.comp504.model.enumtype.DirectionEnumType;
import edu.rice.comp504.model.enumtype.GhostStatusEnumType;
import edu.rice.comp504.model.map.MapSetting;
import edu.rice.comp504.model.movingobject.Ghost;
import edu.rice.comp504.model.movingobject.Pacman;

import java.awt.*;
import java.util.List;

/**
 * Update pacman position.
 *
 * @Author ChaoWang
 * @Date 2021/11/28 14:58
 */
public class PacmanUpdateCmd implements ICmd {
    List<Ghost> ghostList;
    //new direction
    DirectionEnumType direction;

    public PacmanUpdateCmd(DirectionEnumType direction, List<Ghost> ghostList) {
        this.direction = direction;
        this.ghostList = ghostList;
    }

    @Override
    public void execute(Pacman context) {
        if (direction == null) {
            return;
        }
        if (context.goToLeftExit(direction) || context.goToRightExit(direction)) {
            return;
        }
        //judge if collide with ghost
//        detectCollideWithGhost(context);
        Point location = context.getLocation();
        Point velocity = context.getVelocity();
        //if the new direction can go
        if (!context.detectWall(direction)) {
            if (location.x % MapSetting.gridSize == 25 && location.y % MapSetting.gridSize == 25) {
                context.setDirection(direction);
                context.updateVel();
            }
        }
        if (!context.detectCollideWithWall()) {
            location.setLocation(location.x + velocity.x, location.y + velocity.y);
        }
        //System.out.println("pacman info " + context.getDirection().toString() + " " + context.getLocation().toString());
    }


    /**
     * Lose one life when collide with a ghost.
     */
    private void detectCollideWithGhost(Pacman pacman) {
        for (Ghost ghost : ghostList) {
            if (Math.abs(pacman.getLocation().x - ghost.getLocation().x) < 1 || Math.abs(pacman.getLocation().y - ghost.getLocation().y) < 1) {
                if (ghost.getStatus().name().equals("BLUE")) {
                    pacman.increaseEatenGhost();
                    ghost.setStatus(GhostStatusEnumType.TWO_EYES);
                }
                if (ghost.getStatus().name().equals("NORMAL")) {
                    pacman.loseLife();
                }
            }
        }
    }
}
