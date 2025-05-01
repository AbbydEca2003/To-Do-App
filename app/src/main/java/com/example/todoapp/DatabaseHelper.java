package com.example.todoapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "toDoList.db";
    public static final int DB_VERSION = 1;
    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE toDoList("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "name String,"
                + "description String,"
                + "priority String,"
                + "dueDate timestamp,"
                + "isCompleted INTEGER DEFAULT 0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS toDoList");
        onCreate(db);
    }

    public long insertTask(){
        SQLiteDatabase db = this.getWritableDatabase();
        return 1;
    }

    public ArrayList<Task> getAllTask(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM toDoList ORDER BY id DESC", null);
        ArrayList<Task> taskList =new ArrayList<>();
        if(cursor.moveToFirst()){
//            do{
//                Task taskItem =new Task(
//                        cursor.getString(1),
//                        cursor.getString(2),
//                        cursor.getString(3),
//                        cursor.getString(4),
//                        cursor.getString(5)
//                );
//                taskItem.add(taskItem);
//            }
        }
    }
}
