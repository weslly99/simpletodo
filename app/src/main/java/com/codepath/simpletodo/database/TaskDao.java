package com.codepath.simpletodo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.codepath.simpletodo.Constants;
import com.codepath.simpletodo.database.Scheme.TableTask;
import com.codepath.simpletodo.model.Task;

import java.util.Date;
import java.util.List;

/**
 * Created by weslly on 01/03/17.
 */

public class TaskDao {
    private final String[] priorits = {Constants.PRIORITY_LOW, Constants.PRIORITY_MEDIUM, Constants.PRIORITY_HIGHT};
    private DatabaseHelper database;

    public TaskDao(Context context) {
        database = DatabaseHelper.getInstance(context);
    }

    public void addTask(Task task) {
        SQLiteDatabase writer = database.getWritableDatabase();
        writer.beginTransaction();
        try {
            ContentValues content = wraperTask(task);
            writer.insertOrThrow(TableTask.NAME, null, content);
            writer.setTransactionSuccessful();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            writer.endTransaction();
        }
    }

    public void updateTask(Task task) {
        SQLiteDatabase writer = database.getWritableDatabase();
        ContentValues content = wraperTask(task);
        writer.update(TableTask.NAME, content, TableTask.Cols.ID + "= ?",
                new String[]{String.valueOf(task.getId())});
    }

    public int deleteTask(int id) {
        SQLiteDatabase writer = database.getWritableDatabase();
        writer.beginTransaction();
        try {
            writer.delete(TableTask.NAME, TableTask.Cols.ID + " = ?",
                    new String[]{String.valueOf(id)});
            writer.setTransactionSuccessful();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            writer.endTransaction();
        }
        return id;
    }


    public Task getTask(int id) {
        SQLiteDatabase reader = database.getReadableDatabase();


        return null;
    }

    public List<Task> getAllTask() {
        return null;
    }

    public List<Task> getAllTaskArchived() {
        return null;
    }

    public List<Task> getAllTaskUnarchived() {
        return null;
    }

    public List<Task> getAllTaskofDate(long date) {
        return null;
    }


    public ContentValues wraperTask(Task task) {
        ContentValues content = new ContentValues();
        content.put(TableTask.Cols.ID, task.getId());
        content.put(TableTask.Cols.TASK_NAME, task.getTaskName());
        content.put(TableTask.Cols.TASK_DESCRIPTION, task.getId());
        content.put(TableTask.Cols.ARCHIVED, handleBooleans(task.isArchived()));
        content.put(TableTask.Cols.STATUS, handleBooleans(task.isStatus()));
        content.put(TableTask.Cols.PRIORITY, handlePrioritys(task.getPriority()));
        content.put(TableTask.Cols.TIMESTAMP, handleDates(task.getTimestamp().getTime()));
        return content;
    }

    public boolean handleBooleans(int val) {
        return val == 1 ? true : false;
    }

    public int handleBooleans(boolean val) {
        return val ? 1 : 0;
    }

    public String handlePrioritys(int prior) {
        return priorits[prior];
    }

    public int handlePrioritys(String prior) {
        for (int i = 0; i < priorits.length; i++) {
            if (prior.equals(priorits[i])) {
                return i;
            }
        }
        return 1; //default medium
    }

    public long handleDates(Date date) {
        return date.getTime();
    }

    public Date handleDates(long date) {
        return new Date(date);
    }
}
