package com.cleanup.todoc.injection;

import android.content.Context;

import com.cleanup.todoc.database.TodocDatabase;
import com.cleanup.todoc.repositories.ProjectRepository;
import com.cleanup.todoc.repositories.TaskRepository;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Injection {

    public static TaskRepository provideTaskRepository(Context context){
        TodocDatabase database = TodocDatabase.getInstance(context);
        return new TaskRepository(database.mTaskDao());
    }

    public static ProjectRepository provideProjectRepository(Context context){
        TodocDatabase database = TodocDatabase.getInstance(context);
        return new ProjectRepository(database.mProjectDao());
    }

    public static Executor provideExecutor() {return Executors.newSingleThreadExecutor();}

    public static ViewModelFactory provideViewModelFactory(Context context){
        ProjectRepository projectRepository = provideProjectRepository(context);
        TaskRepository taskRepository = provideTaskRepository(context);
        Executor executor = provideExecutor();
        return new ViewModelFactory(projectRepository, taskRepository, executor);
    }
}
