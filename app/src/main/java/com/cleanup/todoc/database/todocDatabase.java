package com.cleanup.todoc.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.cleanup.todoc.database.dao.ProjectDao;
import com.cleanup.todoc.database.dao.TaskDao;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

@Database(entities = {Project.class, Task.class}, version = 1, exportSchema = false)
public abstract class todocDatabase extends RoomDatabase {

    // --- SINGLETON ---
    private static volatile todocDatabase INSTANCE;

    // --- DAO ---
    public abstract ProjectDao mProjectDao();
    public abstract TaskDao mTaskDao();

    // --- INSTANCE ---
    public static todocDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (todocDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            todocDatabase.class, "todocDatabase.db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
