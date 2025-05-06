package com.example.todoapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "toDoList.db";
    public static final int DB_VERSION = 3;

    public static final String TABLE_NAME = "toDoList";
    public static final String COL_ID = "id";
    public static final String COL_NAME = "name";
    public static final String COL_DESCRIPTION = "description";
    public static final String COL_PRIORITY = "priority";
    public static final String COL_DUE_DATE = "dueDate";
    public static final String COL_COMPLETED = "isCompleted";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "("
                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_NAME + " TEXT, "
                + COL_DESCRIPTION + " TEXT, "
                + COL_PRIORITY + " TEXT, "
                + COL_DUE_DATE + " TEXT, "
                + COL_COMPLETED + " INTEGER DEFAULT 0)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long insertTask(Task taskItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NAME, taskItem.getName());
        values.put(COL_DESCRIPTION, taskItem.getDescription());
        values.put(COL_PRIORITY, taskItem.getPriority());
        values.put(COL_DUE_DATE, taskItem.getDuedate());
        values.put(COL_COMPLETED, taskItem.isCompleted() ? 1 : 0);

        long id = db.insert(TABLE_NAME, null, values);
        db.close();
        return id;
    }

    public ArrayList<Task> getAllTask() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " ORDER BY " + COL_ID + " DESC", null);
        ArrayList<Task> taskList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String description = cursor.getString(2);
                String priority = cursor.getString(3);
                String dueDate = cursor.getString(4);
                boolean isCompleted = cursor.getInt(5) == 1;

                Task taskItem = new Task(name, description, priority, dueDate);
                taskItem.setId(id);
                taskItem.setCompleted(isCompleted);

                taskList.add(taskItem);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return taskList;
    }
}
