package edu.jsu.mcis.cs408.lab5a;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Memo {
    private int id;
    private String memo;

    public Memo (int id, String memo) {
        this.id = id;
        this.memo= memo;

    }
    public Memo (String memo) {

        this.memo= memo;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getMemo() {
        return memo;
    }
    public void setMemo(String memo) {
        this.memo = memo;
    }


    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("#").append(this.id).append(':').append(" ");
        s.append("Memo: ").append(memo).append("\n");
        return s.toString();
    }


}
