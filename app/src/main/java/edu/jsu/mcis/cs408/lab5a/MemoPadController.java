package edu.jsu.mcis.cs408.lab5a;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class MemoPadController implements PropertyChangeListener {



    private String display = "display";


    private Model model;

    public static String DISPLAYTAG = "display";

    private ArrayList<AbstractView> views;



    public MemoPadController(Model model) {

        this.model = model;
        addModel(model);

    }

    public void addModel(Model model) {


        model.addPropertyChangeListener(this);

    }

    public void removeModel(Model model) {


        model.removePropertyChangeListener(this);

    }

    public void addView(AbstractView view) {


        views.add(view);

    }

    public void removeView(AbstractView view) {

        views.remove(view);

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {


        for (AbstractView view : views) {
            view.modelPropertyChange(evt);
        }

    }

    protected void setModelProperty(String propertyName, Object newValue) {

        try {

            Method method = model.getClass().getMethod("set" + propertyName, newValue.getClass());
            method.invoke(model, newValue);

        }

        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
