package com.codepath.simpletodo.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.codepath.simpletodo.database.Scheme.TableTask;
import com.codepath.simpletodo.model.Task;
import com.codepath.simpletodo.util.Formatter;

import java.util.Calendar;

/**
 * Created by weslly on 01/03/17.
 */

public class CursorTaskWrapper extends CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public CursorTaskWrapper(Cursor cursor) {
        super(cursor);
    }

    public Task getTask() {
        int id = getInt(getColumnIndex(TableTask.Cols.ID));
        String taskName = getString(getColumnIndex(TableTask.Cols.TASK_NAME));
        String taskDescription  = getString(getColumnIndex(TableTask.Cols.TASK_DESCRIPTION));
        int taskStatus = getInt(getColumnIndex(TableTask.Cols.STATUS));
        int taskPriority = getInt(getColumnIndex(TableTask.Cols.PRIORITY));
        int taskArchived = getInt(getColumnIndex(TableTask.Cols.ARCHIVED));
        long taskDate  = getLong(getColumnIndex(TableTask.Cols.TIMESTAMP));

        Task task = new Task();
        task.setId(id);
        task.setTaskName(taskName);
        task.setTaskDescription(taskDescription);
        task.setArchived(Formatter.handleBooleans(taskArchived));
        task.setStatus(Formatter.handleBooleans(taskStatus));
        task.setPriority(taskPriority);

        Calendar timestamp = Calendar.getInstance();
        timestamp.setTime(Formatter.handleDates(taskDate));
        task.setTimestamp(timestamp);

        return task;
    }
}
