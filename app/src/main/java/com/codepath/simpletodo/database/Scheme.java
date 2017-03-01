package com.codepath.simpletodo.database;

/**
 * Created by weslly on 01/03/17.
 */

public interface Scheme {

    interface TableTask {
        String NAME = "TASK_TABLE";

        interface Cols {
            String ID = "ID";
            String TASK_NAME = "TASK_NAME";
            String STATUS = "STATUS";
            String ARCHIVED = "ARCHIVED";
            String TIMESTAMP = "TIMESTAMP";
            String TASK_DESCRIPTION = "TASK_DESCRIPTION";
            String PRIORITY = "PRIORITY";
        }


    }
}
