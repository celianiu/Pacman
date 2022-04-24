package edu.rice.comp504.model.map;

import edu.rice.comp504.model.movingobject.Ghost;
import edu.rice.comp504.model.movingobject.Pacman;

import java.util.List;

/**
 * game map class to store all game objects.
 *
 * @Author ChaoWang
 * @Date 2021/11/12 23:42
 */
public class GameMap {

    private MapSetting mapSetting;
    private int score;
    //private  int highestScore;
    private Pacman pacman;
    private List<Ghost> ghosts;
    private boolean isGameOver = false;
    private boolean isGameWin = false;
    public static int eatenGhost;
    public int life;
    public int highestScore = 0;
    public boolean collideGhost = false;

    public GameMap() {
    }

    /**
     * start a new game, by passing the map setting.
     *
     * @param mapSetting map setting when beginning a new game
     */
    public GameMap(MapSetting mapSetting) {
        this.mapSetting = mapSetting;
        this.score = 0;
    }

    public MapSetting getMapSetting() {
        return mapSetting;
    }

    public void setMapSetting(MapSetting mapSetting) {
        this.mapSetting = mapSetting;
    }

    public List<Ghost> getGhosts() {
        return ghosts;
    }

    public Pacman getPacman() {
        return pacman;
    }

    public void setPacman(Pacman pacman) {
        this.pacman = pacman;
    }

    public void setGhosts(List<Ghost> ghosts) {
        this.ghosts = ghosts;
    }

    public void setScore(int newScore) {
        score += newScore;
    }


    public void getGhostScore(int eatenGhost) {
        score += Math.pow(2, Math.max(eatenGhost, 4)) * 100;
    }

    public int getScore() {
        return score;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }

    public boolean isGameWin() {
        return isGameWin;
    }

    public void setGameWin(boolean gameWin) {
        isGameWin = gameWin;
    }

    public void increaseEatenGhost() {
        eatenGhost++;
    }

    public void decreaseEatenGhost() {
        eatenGhost--;
    }

    public int getEatenGhost() {
        return eatenGhost;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void setHighestScore(int score) {
        this.highestScore = Math.max(highestScore, score);
    }

    public void setCollideGhost(boolean status) {
        this.collideGhost = status;
    }
}
