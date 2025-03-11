package edu.jsu.mcis.cs408.lab5a;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class MemoPadController implements PropertyChangeListener {





    private Model model;
    private ArrayList<Model> models;

    private MainActivity view;



    public MemoPadController(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {

        this.model = new Model(context, name, factory, version);
        models = new ArrayList<>();
        addModel(model);



    }

    public void addModel(Model model) {


        model.addPropertyChangeListener(this);
        models.add(model);

    }

    public void removeModel(Model model) {


        model.removePropertyChangeListener(this);
        models.remove(model);

    }

    public void addView(MainActivity view) {


      view = view;

    }

    public void removeView(AbstractView view) {

       view = null;

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {




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
    protected void establishConnection(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {

        try {
            model = new Model(context, name, factory, version);

        }

        catch (Exception e) {
            e.printStackTrace();
        }

    }
    protected void addMemo(Memo m){
        model.addMemo(m);

    }
    protected ArrayList<Memo> getMemos(){
        return model.getAllMemosAsList();
    }
}
