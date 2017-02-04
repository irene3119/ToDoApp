package com.simpleapp.irene.simpletodo;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by Irene on 2017/2/4.
 */

@Database(name = TodoItemDatabase.NAME, version = TodoItemDatabase.VERSION)
public class TodoItemDatabase {

        public static final String NAME = "TodoDB";

        public static final int VERSION = 2;

}
