package com.cleanup.todoc.repositories;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.database.dao.ProjectDao;
import com.cleanup.todoc.model.Project;

import java.util.List;

public class ProjectRepository {

    private final ProjectDao mProjectDao;

    private LiveData<List<Project>> projects;

    public ProjectRepository(ProjectDao projectDao) {
        mProjectDao = projectDao;
        projects = projectDao.getProjects();
    }

    public LiveData<List<Project>> getProjects() {
        return mProjectDao.getProjects();
    }
}
