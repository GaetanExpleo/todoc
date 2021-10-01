package com.cleanup.todoc.dao;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.cleanup.todoc.LiveDataTestUtils;
import com.cleanup.todoc.database.TodocDatabase;
import com.cleanup.todoc.database.dao.ProjectDao;
import com.cleanup.todoc.model.Project;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

import static com.cleanup.todoc.dao.dataDaoTest.PROJECT_CIRCUS;
import static com.cleanup.todoc.dao.dataDaoTest.PROJECT_LUCIDIA;
import static com.cleanup.todoc.dao.dataDaoTest.PROJECT_TARTAMPION;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class ProjectDaoTest {

    private ProjectDao projectDao;
    private TodocDatabase db;

    @Rule
    public InstantTaskExecutorRule mInstantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void initDb() throws Exception {
        db = Room
                .inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(),
                        TodocDatabase.class)
                .allowMainThreadQueries()
                .build();
        projectDao = db.mProjectDao();
    }

    @After
    public void closeDb() throws Exception {
        db.close();
    }

    @Test
    public void getProjectsWhenNoProjectInserted() throws InterruptedException {
        //Given : no project inserted in database
        //When : we get the list of projects
        List<Project> projects = LiveDataTestUtils.getOrAwaitValue(projectDao.getProjects());
        //Then : the list is empty
        assertTrue(projects.isEmpty());
    }

    @Test
    public void insertAndGetProjects() throws InterruptedException {
        //Given : we insert three projects
        insertProject();

        //When : we get the list of projects
        List<Project> projects = LiveDataTestUtils.getOrAwaitValue(projectDao.getProjects());

        List<Project> expectedProjects = Arrays.asList(
                PROJECT_LUCIDIA,
                PROJECT_CIRCUS,
                PROJECT_TARTAMPION
        );

        //Then : the list of projects contains the three projects
        assertTrue(projects.equals(expectedProjects));
    }

    private void insertProject() {
        projectDao.insert(PROJECT_CIRCUS);
        projectDao.insert(PROJECT_LUCIDIA);
        projectDao.insert(PROJECT_TARTAMPION);
    }
}
