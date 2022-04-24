package edu.rice.comp504.adapter;

import edu.rice.comp504.model.cmd.PacmanUpdateCmd;
import edu.rice.comp504.model.cmd.UpdateScoreCmd;
import edu.rice.comp504.model.enumtype.DirectionEnumType;
import edu.rice.comp504.model.map.GameMap;
import edu.rice.comp504.model.map.MapSetting;
import edu.rice.comp504.model.movingobject.Ghost;
import edu.rice.comp504.model.movingobject.Pacman;

import java.awt.*;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class DispatchAdapter {
    /**
     * The whole map of the game.
     */
    private GameMap mGameMap;
    private PropertyChangeSupport pcs;
    public static final String PACMAN = "pacman";
    public static final String GHOST = "ghost";
    private int index;

    /**
     * DispatchAdapter Constructor.
     */
    public DispatchAdapter() {
        MapSetting mapSetting = new MapSetting();
        mGameMap = new GameMap(mapSetting);
        pcs = new PropertyChangeSupport(this);
    }

    /**
     * a new move command to move Pacman.
     *
     * @param direction a direction to move
     * @return gameMap
     */
    public GameMap moveObject(DirectionEnumType direction) {
        //from update command to property change event.

        pcs.firePropertyChange(PACMAN, null, new PacmanUpdateCmd(direction, mGameMap.getGhosts()));
        pcs.firePropertyChange(PACMAN, null, new UpdateScoreCmd(mGameMap, this));
        pcs.firePropertyChange(GHOST, null, mGameMap.getPacman());

        if (mGameMap.getMapSetting().dotsList.isEmpty()) {
            mGameMap.setGameWin(true);
        }
        mGameMap.getPacman().setFlash(index);
        for (Ghost ghost : mGameMap.getGhosts()) {
            ghost.setFlash(index);
        }
        index = index == 1 ? 0 : 1;
//        System.out.println(mGameMap.highestScore);
        if (mGameMap.life <= 0) {
            mGameMap.setGameOver(true);
        }
        return mGameMap;
    }

    /**
     * Add a pacman that will listen for a property change (i.e. time elapsed).
     *
     * @param gameLevel the game level
     * @param ghostNum the number of ghost
     */
    public void addToMap(String gameLevel, String ghostNum) {
        //add pacman
        Point location = new Point(11 * MapSetting.gridSize + MapSetting.gridSize / 2,
                12 * MapSetting.gridSize + MapSetting.gridSize / 2);
        Point vel = new Point(10, 0);
        Pacman pacman = new Pacman(location, vel);
        pcs.addPropertyChangeListener(PACMAN, pacman);
        mGameMap.setPacman(pacman);
        MapSetting.resetGhostList(Integer.parseInt(gameLevel));

        //add four ghosts
        List<Ghost> ghostList = new ArrayList<>();
        switch (Integer.parseInt(ghostNum)) {
            case 1:
                ghostList.add(MapSetting.ghostList.get(0));
                break;
            case 2:
                ghostList.add(MapSetting.ghostList.get(0));
                ghostList.add(MapSetting.ghostList.get(1));
                break;
            case 3:
                ghostList.add(MapSetting.ghostList.get(0));
                ghostList.add(MapSetting.ghostList.get(1));
                ghostList.add(MapSetting.ghostList.get(2));
                break;
            case 4:
                ghostList.add(MapSetting.ghostList.get(0));
                ghostList.add(MapSetting.ghostList.get(1));
                ghostList.add(MapSetting.ghostList.get(2));
                ghostList.add(MapSetting.ghostList.get(3));
                break;
            default:
                break;
        }
        mGameMap.setGhosts(ghostList);
        for (Ghost ghost : ghostList) {
            pcs.addPropertyChangeListener(GHOST, ghost);
        }
    }

    /**
     * start a new game and reset a map.
     *
     * @param mapSetting a mapping setting
     * @return gameMap
     */
    public GameMap reset(MapSetting mapSetting) {
        mGameMap = new GameMap(mapSetting);
        pcs = new PropertyChangeSupport(this);
        return mGameMap;
    }

    /**
     * respawn Pacman after died.
     *
     * @return gameMap
     */
    public GameMap respawn() {
        GameMap gameMap = new GameMap();
        return gameMap;
    }

    public PropertyChangeSupport getPcs() {
        return pcs;
    }

    public void setPcs(PropertyChangeSupport pcs) {
        this.pcs = pcs;
    }
}
