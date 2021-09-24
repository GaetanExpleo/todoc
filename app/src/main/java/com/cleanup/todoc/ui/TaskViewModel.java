package com.cleanup.todoc.ui;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.repositories.ProjectRepository;
import com.cleanup.todoc.repositories.TaskRepository;

import java.util.List;
import java.util.concurrent.Executor;

public class TaskViewModel extends ViewModel {

    // --- REPOSITORIES ---
    private final ProjectRepository mProjectRepository;
    private final TaskRepository mTaskRepository;
    private final Executor mExecutor;

    // --- DATA ---
    @Nullable
    private LiveData<List<Project>> mProjects;

    public TaskViewModel(ProjectRepository projectRepository, TaskRepository taskRepository, Executor executor) {
        mProjectRepository = projectRepository;
        mTaskRepository = taskRepository;
        mExecutor = executor;
        mProjects = projectRepository.getProjects();
    }

//     --- PROJECT ---
    public LiveData<List<Project>> getProjects() {
        return mProjects;
    }

    // --- TASK ---
    public LiveData<List<Task>> getTasks() {
        return mTaskRepository.getTasks();
    }

    public void createTask(Task task) {
        mExecutor.execute(() -> mTaskRepository.createTask(task));
    }

    public void deleteTask(Task task) {
        mExecutor.execute(() -> mTaskRepository.deleteTask(task));
    }

    public void updateTask(Task task) {
        mExecutor.execute(() -> mTaskRepository.updateTask(task));
    }
}
