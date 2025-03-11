package edu.jsu.mcis.cs408.lab5a;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "mydatabase.db";
    private static final String TABLE_MEMOS = "memos";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_MEMO = "memo";



    public DatabaseHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MEMOS_TABLE = "CREATE TABLE memos ( _id INTEGER PRIMARY KEY AUTOINCREMENT, memo TEXT NOT NULL)";
        db.execSQL(CREATE_MEMOS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEMOS);
        onCreate(db);
    }

    public void addContact(Contact c) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_MEMO, c.getMemo());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_CONTACTS, null, values);
        db.close();

    }

    public Contact getContact(int id) {

        String query = "SELECT * FROM " + TABLE_CONTACTS + " WHERE " + COLUMN_ID + " = " + id;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Contact c = null;

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            int newId = cursor.getInt(0);
            String newName = cursor.getString(1);
            String newAddress = cursor.getString(2);
            cursor.close();
            c = new Contact(newId, newName, newAddress);
        }

        db.close();
        return c;

    }

    public String getAllContacts() {

        String query = "SELECT * FROM " + TABLE_CONTACTS;

        StringBuilder s = new StringBuilder();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            do {
                int id = cursor.getInt(0);
                s.append(getContact(id)).append("\n");
            }
            while ( cursor.moveToNext() );
        }

        db.close();
        return s.toString();

    }

    public ArrayList<Contact> getAllContactsAsList() {

        String query = "SELECT * FROM " + TABLE_CONTACTS;

        ArrayList<Contact> allContacts = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            do {
                int newId = cursor.getInt(0);
                String newName = cursor.getString(1);
                String newAddress = cursor.getString(2);
                allContacts.add( new Contact(newId, newName, newAddress) );
            }
            while ( cursor.moveToNext() );
        }

        db.close();
        return allContacts;

    }
}
