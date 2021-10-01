package com.cleanup.todoc.dao;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.cleanup.todoc.LiveDataTestUtils;
import com.cleanup.todoc.database.TodocDatabase;
import com.cleanup.todoc.database.dao.TaskDao;
import com.cleanup.todoc.model.Task;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

import static com.cleanup.todoc.dao.dataDaoTest.*;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class TaskDaoTest {

    private TaskDao taskDao;
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
        taskDao = db.mTaskDao();
    }

    @After
    public void closeDb() throws Exception {
        db.close();
    }

    @Test
    public void insertAndGetTasks() throws InterruptedException {
//        //Given : We insert three projects then four tasks
//        insertProject();
//        insertTask();
//
//        //When: we get the list of tasks
//        List<Task> tasks = LiveDataTestUtils.getOrAwaitValue(taskDao.getTasks());
//
//        List<Task> expectedTask = Arrays.asList(TASK_1, TASK_2, TASK_3, TASK_4);
//
//        //Then : The list contains four tasks
//        assertTrue(tasks.equals(expectedTask));
    }

    private void insertProject() {
        db.mProjectDao().insert(PROJECT_TARTAMPION);
        db.mProjectDao().insert(PROJECT_CIRCUS);
        db.mProjectDao().insert(PROJECT_LUCIDIA);
    }

    private void insertTask() {
        taskDao.insertTask(TASK_1);
        taskDao.insertTask(TASK_2);
        taskDao.insertTask(TASK_3);
        taskDao.insertTask(TASK_4);
    }
}
