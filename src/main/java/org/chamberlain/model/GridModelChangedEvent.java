package org.chamberlain.model;

import java.util.EventObject;

public class GridModelChangedEvent extends EventObject {

    private GridModel model;

    public GridModelChangedEvent(Object source) {
        super(source);
        if (source instanceof GridModel) {
            this.model = (GridModel) source;
        }
    }

    public GridModel getModel() {
        return this.model;
    }
}