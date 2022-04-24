package edu.rice.comp504.model.cmd;

import edu.rice.comp504.model.movingobject.Pacman;

public interface ICmd {

    /**
     * Execute the command.
     * @param context The receiver on which the command is executed.
     */
    public void execute(Pacman context);
}
