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
import java.util.Date;
import java.util.List;

import static com.cleanup.todoc.dao.dataDaoTest.PROJECT_CIRCUS;
import static com.cleanup.todoc.dao.dataDaoTest.PROJECT_LUCIDIA;
import static com.cleanup.todoc.dao.dataDaoTest.PROJECT_TARTAMPION;
import static com.cleanup.todoc.dao.dataDaoTest.TASK_1;
import static com.cleanup.todoc.dao.dataDaoTest.TASK_1_NAME;
import static com.cleanup.todoc.dao.dataDaoTest.TASK_2;
import static com.cleanup.todoc.dao.dataDaoTest.TASK_2_NAME;
import static com.cleanup.todoc.dao.dataDaoTest.TASK_2_TIMESTAMP;
import static com.cleanup.todoc.dao.dataDaoTest.TASK_3;
import static com.cleanup.todoc.dao.dataDaoTest.TASK_3_NAME;
import static com.cleanup.todoc.dao.dataDaoTest.TASK_4;
import static com.cleanup.todoc.dao.dataDaoTest.TASK_4_NAME;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class TaskDaoTest {

    private static final int FIRST_POSITION = 0;
    private static final int SECOND_POSITION = 1;
    private static final int THIRD_POSITION = 2;
    private static final int FORTH_POSITION = 3;
    private static final long TASK_1_ID = 1;
    private static final long TASK_2_ID = 2;
    private static final long TASK_3_ID = 3;
    private static final long TASK_4_ID = 4;
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
        //Given : We insert three projects then four tasks
        insertProject();
        insertTask();

        //When: we get the list of tasks
        List<Task> tasks = LiveDataTestUtils.getOrAwaitValue(taskDao.getTasks());

        List<Task> expectedTask = Arrays.asList(TASK_1, TASK_2, TASK_3, TASK_4);

        //Then : The list contains four tasks
        assertEquals(tasks.size(), expectedTask.size());
        assertEquals(tasks.get(0).getName(), TASK_1_NAME);
        assertEquals(tasks.get(1).getName(), TASK_2_NAME);
        assertEquals(tasks.get(2).getName(), TASK_3_NAME);
        assertEquals(tasks.get(3).getName(), TASK_4_NAME);
    }

    @Test
    public void insertTasksUpdateOneAndGetAll() throws InterruptedException {
        //Given :
        insertProject();
        insertTask();
        setTasksIds();

        Task TASK_UPDATED = new Task(
                TASK_1_ID,
                TASK_1.getProjectId(),
                "Task updated",
                new Date().getTime()
        );

        taskDao.updateTask(TASK_UPDATED);

        //When :
        List<Task> tasks = LiveDataTestUtils.getOrAwaitValue(taskDao.getTasks());

        //Then :
        assertEquals(4, tasks.size());


        assertEquals(TASK_UPDATED.getName(), tasks.get(FIRST_POSITION).getName());
        assertEquals(TASK_2.getName(), tasks.get(SECOND_POSITION).getName());
        assertEquals(TASK_3.getName(), tasks.get(THIRD_POSITION).getName());
        assertEquals(TASK_4.getName(), tasks.get(FORTH_POSITION).getName());


    }

    @Test
    public void insertAndDeleteOneTask() throws InterruptedException {
        //Given :
        insertProject();
        insertTask();

        taskDao.deleteTask(new Task(TASK_2_ID, TASK_2.getProjectId(), TASK_2_NAME, TASK_2_TIMESTAMP));

        //When:
        List<Task> tasks = LiveDataTestUtils.getOrAwaitValue(taskDao.getTasks());

        //Then:
        assertEquals(3, tasks.size());

        assertEquals(TASK_1.getName(), tasks.get(FIRST_POSITION).getName());
        assertEquals(TASK_3.getName(), tasks.get(SECOND_POSITION).getName());
        assertEquals(TASK_4.getName(), tasks.get(THIRD_POSITION).getName());
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

    private void setTasksIds() {
        TASK_1.setId(TASK_1_ID);
        TASK_2.setId(TASK_2_ID);
        TASK_3.setId(TASK_3_ID);
        TASK_4.setId(TASK_4_ID);
    }
}
