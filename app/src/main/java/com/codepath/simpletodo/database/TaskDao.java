package com.codepath.simpletodo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.codepath.simpletodo.database.Scheme.TableTask;
import com.codepath.simpletodo.model.Task;
import com.codepath.simpletodo.util.Formatter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weslly on 01/03/17.
 */

public class TaskDao {
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
        CursorTaskWrapper wrapper = queryTask(TableTask.Cols.ID + " = ?", new String[]{String.valueOf(id)});
        Task task = null;
        try {
            wrapper.moveToFirst();
            task = wrapper.getTask();
        } finally {
            wrapper.close();
        }

        return task;
    }

    public List<Task> getAllTask() {
        return getList(null, null);
    }

    public List<Task> getAllTaskArchived() {

        return getList(TableTask.Cols.ARCHIVED + " = ?",
                new String[]{String.valueOf(1)});
    }

    public List<Task> getAllTaskUnarchived() {
        return getList(TableTask.Cols.ARCHIVED + "= ?",
                new String[]{String.valueOf(0)});
    }

    public List<Task> getAllTaskofDate(long date) {
        return getList(TableTask.Cols.TIMESTAMP + "= ?",
                new String[]{String.valueOf(date)});
    }


    private List<Task> getList(String whereClause, String[] whereArgs) {
        CursorTaskWrapper wrapper = queryTask(whereClause, whereArgs);
        List<Task> tasks = new ArrayList<>();
        try {
            wrapper.moveToFirst();
            while (!wrapper.isAfterLast()) {
                tasks.add(wrapper.getTask());
                wrapper.moveToNext();
            }
        } finally {
            wrapper.close();
        }
        return tasks;
    }

    private CursorTaskWrapper queryTask(String whereClause, String[] whereArgs) {
        SQLiteDatabase reader = database.getReadableDatabase();
        Cursor cursor = reader.query(TableTask.NAME, null, whereClause, whereArgs, null, null, null, null);
        return new CursorTaskWrapper(cursor);
    }

    public ContentValues wraperTask(Task task) {
        ContentValues content = new ContentValues();
        content.put(TableTask.Cols.ID, task.getId());
        content.put(TableTask.Cols.TASK_NAME, task.getTaskName());
        content.put(TableTask.Cols.TASK_DESCRIPTION, task.getId());
        content.put(TableTask.Cols.ARCHIVED, Formatter.handleBooleans(task.isArchived()));
        content.put(TableTask.Cols.STATUS, Formatter.handleBooleans(task.isStatus()));
        content.put(TableTask.Cols.PRIORITY,task.getPriority());
        if (task.getTimestamp() != null)
            content.put(TableTask.Cols.TIMESTAMP, Formatter.handleDates(task.getTimestamp().getTime()));
        return content;
    }


}
