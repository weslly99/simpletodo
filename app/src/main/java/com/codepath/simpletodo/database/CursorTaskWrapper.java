package com.codepath.simpletodo.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.codepath.simpletodo.database.Scheme.TableTask;
import com.codepath.simpletodo.model.Task;

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
        int 
    }
}
