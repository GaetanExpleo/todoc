package com.cleanup.todoc;

import android.content.Context;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.cleanup.todoc.database.TodocDatabase;
import com.cleanup.todoc.database.dao.ProjectDao;
import com.cleanup.todoc.database.dao.TaskDao;
import com.cleanup.todoc.injection.Injection;
import com.cleanup.todoc.injection.ViewModelFactory;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.repositories.TaskRepository;
import com.cleanup.todoc.ui.MainActivity;
import com.cleanup.todoc.ui.TaskViewModel;

import java.util.Date;

public class falseTask {

    public static void generateFalseTasks(Context context, ViewModelStoreOwner owner) {
        ViewModelFactory viewModelFactory = Injection.provideViewModelFactory(context);
        TaskViewModel taskViewModel = new ViewModelProvider(owner, viewModelFactory).get(TaskViewModel.class);
        taskViewModel.createTask(new Task(1, "Faire le ménage", new Date().getTime()));
        taskViewModel.createTask(new Task(2, "Faire à manger", new Date().getTime()));
        taskViewModel.createTask(new Task(3, "Ranger la chambre", new Date().getTime()));
        taskViewModel.createTask(new Task(1, "Réparer le vase", new Date().getTime()));
        taskViewModel.createTask(new Task(1, "Acheter des pommes", new Date().getTime()));
    }
}
