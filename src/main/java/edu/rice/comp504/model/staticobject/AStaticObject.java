package edu.rice.comp504.model.staticobject;

import edu.rice.comp504.model.enumtype.DirectionEnumType;

import java.awt.*;

public abstract class AStaticObject {
    protected Point location;

    //been eatten by pacman or not been eatten
    int status;

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }



}
