package com.codepath.simpletodo.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.codepath.simpletodo.database.Scheme.TableTask;

/**
 * Created by weslly on 01/03/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String NAME_DB = "SimpleTodo";
    private static final int VERSION = 1;
    private static  DatabaseHelper sInstance;

    //Singleton Pattern to avoid leaks of memory
    public static synchronized DatabaseHelper getInstance(Context context){
        if(sInstance == null)
            sInstance = new DatabaseHelper(context.getApplicationContext());

        return sInstance;
    }

    private DatabaseHelper(Context context) {
        super(context,NAME_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table_task = "CREATE TABLE "+ TableTask.NAME +" ( "+
                    TableTask.Cols.ID+" INTEGER PRIMARY KEY, "+
                    TableTask.Cols.TASK_NAME +" TEXT, "+
                    TableTask.Cols.TASK_DESCRIPTION+" TEXT, "+
                    TableTask.Cols.ARCHIVED +" INTEGER, "+
                    TableTask.Cols.STATUS+" INTEGER, "+
                    TableTask.Cols.PRIORITY +" INTEGER, "+
                    TableTask.Cols.TIMESTAMP+" INTEGER " +
                ")";
        db.execSQL(create_table_task);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if(oldVersion != newVersion){
            db.execSQL("DROP TABLE IF EXISTS "+TableTask.NAME);
            onCreate(db);
        }

    }
}
