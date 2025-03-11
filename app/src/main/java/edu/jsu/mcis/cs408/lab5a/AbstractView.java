package edu.jsu.mcis.cs408.lab5a;

import java.beans.PropertyChangeEvent;

public interface AbstractView {
    public abstract void modelPropertyChange(final PropertyChangeEvent evt);
}
