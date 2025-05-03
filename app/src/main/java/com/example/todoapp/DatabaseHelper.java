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
    public static final int DB_VERSION = 2;
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE toDoList("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "name TEXT,"
                + "description TEXT,"
                + "priority TEXT,"
                + "dueDate TEXT,"
                + "isCompleted INTEGER DEFAULT 0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS toDoList");
        onCreate(db);
    }

    public long insertTask(Task taskItem){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", taskItem.getName());
        values.put("description", taskItem.getDescription());
        values.put("priority", taskItem.getPriority());
        values.put("dueDate", taskItem.getDuedate());

        long id = db.insert("toDoList", null, values);
        db.close();
        return id;

    }

    public ArrayList<Task> getAllTask(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM toDoList ORDER BY id DESC", null);
        ArrayList<Task> taskList =new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                Task taskItem =new Task(
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)
                );
                taskList.add(taskItem);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return taskList;
    }
}
