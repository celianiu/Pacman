package edu.rice.comp504.model.cmd;

import edu.rice.comp504.adapter.DispatchAdapter;
import edu.rice.comp504.model.enumtype.DirectionEnumType;
import edu.rice.comp504.model.enumtype.GhostStatusEnumType;
import edu.rice.comp504.model.map.GameMap;
import edu.rice.comp504.model.map.MapSetting;
import edu.rice.comp504.model.movingobject.Ghost;
import edu.rice.comp504.model.movingobject.Pacman;
import edu.rice.comp504.model.staticobject.Fruit;
import edu.rice.comp504.model.strategy.BackHomeStrategy;
import edu.rice.comp504.model.strategy.ChaseStrategy;
import edu.rice.comp504.model.strategy.RunAwayStrategy;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class UpdateScoreCmd implements ICmd {

    DispatchAdapter dispatchAdapter;
    GameMap map;
    Set<Point> dotsList;
    Set<Point> bigDotsList;
    Set<Fruit> fruits;
    MapSetting mapSetting;
    List<Ghost> ghostList;
    int score;
    boolean collideGhost = false;

    public UpdateScoreCmd(GameMap gameMap, DispatchAdapter dispatchAdapter) {
        this.dispatchAdapter = dispatchAdapter;
        this.map = gameMap;
        mapSetting = gameMap.getMapSetting();
        this.dotsList = mapSetting.getDotsList();
        this.bigDotsList = mapSetting.getBigDotsList();
        this.fruits = mapSetting.getFruitList();
        this.ghostList = gameMap.getGhosts();
        this.score = gameMap.getScore();
    }

    @Override
    public void execute(Pacman context) {
        if (!collideGhost) {
            for (Ghost ghost : ghostList) {
                ghost.setVelocity(new Point(10, 10));
            }
        }
        for (Iterator<Point> it = dotsList.iterator(); it.hasNext(); ) {
            Point dot = it.next();
            if (eat(context, dot)) {
                map.setScore(10);
                it.remove();
            }
        }

        for (Iterator<Point> it = bigDotsList.iterator(); it.hasNext(); ) {
            Point bigDot = it.next();
            if (eat(context, bigDot)) {
                context.setStartTime(context.getTimeAfterEatBigDots());
                map.setScore(50);
                context.setEatBigDots(true);
                it.remove();
            }
        }

        for (Fruit fruit : fruits) {
            if (eat(context, fruit.getLocation())) {
                map.setScore(150);
                fruit.setLocation(new Point(-20, -20));
            }
        }

        if (context.getEatBigDots()) {
            context.increaseTimeBigDots();
        }

        for (Ghost ghost : ghostList) {
            if (eatGhost(context, ghost)) {
                System.out.println(ghost.getStatus());
                if (ghost.getStatus().equals(GhostStatusEnumType.BLUE) || ghost.getStatus().equals(GhostStatusEnumType.DIZZY)) {
                    context.increaseEatenGhost();
                    map.getGhostScore(context.getEatenGhost());
                    ghost.setStatus(GhostStatusEnumType.TWO_EYES);
                    ghost.setUpdateStrategy(BackHomeStrategy.make());
                } else if (ghost.getStatus().equals(GhostStatusEnumType.NORMAL)) {
                    context.loseLife();
                    context.setVelocity(new Point(0, 0));
                    collideGhost = true;
                    Point location = new Point(11 * MapSetting.gridSize + MapSetting.gridSize / 2,
                            12 * MapSetting.gridSize + MapSetting.gridSize / 2);
                    context.setLocation(location);
                    context.setVelocity(new Point(0, 0));
                    context.setDirection(DirectionEnumType.LEFT);
                    map.setCollideGhost(collideGhost);
                }
            }

        }
        if (collideGhost) {
            MapSetting.resetGhostList(MapSetting.gameLevel);
            map.setGhosts(new ArrayList<>());
            for (int i = 0; i < ghostList.size(); i++) {
                map.getGhosts().add(MapSetting.ghostList.get(i));
            }
            for (Ghost ghost : map.getGhosts()) {
                dispatchAdapter.getPcs().addPropertyChangeListener(DispatchAdapter.GHOST, ghost);
            }
        }
        map.setCollideGhost(collideGhost);
        collideGhost = false;
        setGhostStatus(context);
        map.setLife(context.getLife());
        map.setHighestScore(score);
    }

    /**
     *  Check if the pacman can eat dot/bigDot/fruit.
     * @param pacman pacman
     * @param point point
     * @return status.
     */
    private boolean eat(Pacman pacman, Point point) {
        return Math.abs(pacman.getLocation().x + pacman.getVelocity().x - (point.x)) < 0.1
                && Math.abs(pacman.getLocation().y + pacman.getVelocity().y - (point.getLocation().y)) < 0.1;
    }

    /**
     *  Check if the pacman can eat ghost.
     * @param pacman pacman
     * @param ghost ghost
     * @return status.
     */
    private boolean eatGhost(Pacman pacman, Ghost ghost) {
        return Math.abs(pacman.getLocation().x + pacman.getVelocity().x - (ghost.getLocation().x + ghost.getVelocity().x)) <= 20
                && Math.abs(pacman.getLocation().y + pacman.getVelocity().y - (ghost.getLocation().y + ghost.getVelocity().y)) <= 20;
    }

    /**
     *  When pacman eat a big dot, the normal ghost will become blue in the first 20 seconds. Between the 20th to 25th seconds,
     *  the ghost will become dizzy.
     * @param context pacman
     */
    public void setGhostStatus(Pacman context) {
        if (context.getEatBigDots() && context.getTimeAfterEatBigDots() <= context.getStartTime() + 200) {
            for (Ghost ghost : ghostList) {
                if (ghost.getStatus().equals(GhostStatusEnumType.NORMAL)) {
                    ghost.setStatus(GhostStatusEnumType.BLUE);
                    ghost.setUpdateStrategy(RunAwayStrategy.make());
                }
            }
        } else if (context.getEatBigDots() && context.getTimeAfterEatBigDots() <= context.getStartTime() + 250 && context.getTimeAfterEatBigDots() > context.getStartTime() + 200) {
            for (Ghost ghost : ghostList) {
                if (ghost.getStatus().equals(GhostStatusEnumType.BLUE)) {
                    ghost.setStatus(GhostStatusEnumType.DIZZY);
                } else if (ghost.getStatus().equals(GhostStatusEnumType.DIZZY)) {
                    ghost.setStatus(GhostStatusEnumType.BLUE);
                }
            }
        } else if (context.getEatBigDots() && context.getTimeAfterEatBigDots() > context.getStartTime() + 250) {
            context.setTimeAfterEatBigDots();
            context.setEatBigDots(false);
            for (Ghost ghost : ghostList) {
                if (ghost.getStatus().equals(GhostStatusEnumType.DIZZY) || ghost.getStatus().equals(GhostStatusEnumType.BLUE)) {
                    ghost.setStatus(GhostStatusEnumType.NORMAL);
                    ghost.setUpdateStrategy(ChaseStrategy.make());
                }
            }
        }
    }

}
