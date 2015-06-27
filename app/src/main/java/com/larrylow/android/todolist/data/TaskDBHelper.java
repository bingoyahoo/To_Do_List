package com.larrylow.android.todolist.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.larrylow.android.todolist.data.TaskContract.TaskEntry;

public class TaskDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "task.db";
    private static final int DATABASE_VERSION = 2;

    public TaskDBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_TASKS_TABLE =
                "CREATE TABLE " + TaskEntry.TABLE_NAME + " (" +
                        TaskEntry._ID + " INTEGER PRIMARY KEY," +
                        TaskEntry.COLUMN_TASK + " TEXT NOT NULL" +
                        " );";
        db.execSQL(SQL_CREATE_TASKS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TaskEntry.TABLE_NAME);
        onCreate(db);
    }
}
