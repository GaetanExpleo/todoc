package com.cleanup.todoc.dao;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import java.util.Date;

public class dataDaoTest {

    private static final long PROJECT_CIRCUS_ID = 1;
    private static final String PROJECT_CIRCUS_NAME = "Project Circus";
    private static final int PROJECT_CIRCUS_COLOR = 0xFFEADAD1;
    public static final Project PROJECT_CIRCUS = new Project(PROJECT_CIRCUS_ID, PROJECT_CIRCUS_NAME, PROJECT_CIRCUS_COLOR);

    private static final long PROJECT_LUCIDIA_ID = 2;
    private static final String PROJECT_LUCIDIA_NAME = "Project Luciada";
    private static final int PROJECT_LUCIDIA_COLOR = 0xFFEADAD1;
    public static final Project PROJECT_LUCIDIA = new Project(PROJECT_LUCIDIA_ID, PROJECT_LUCIDIA_NAME, PROJECT_LUCIDIA_COLOR);

    private static final long PROJECT_TARTAMPION_ID = 3;
    private static final String PROJECT_TARTAMPION_NAME = "Project Tartampion";
    private static final int PROJECT_TARTAMPION_COLOR = 0xFFEADAD1;
    public static final Project PROJECT_TARTAMPION = new Project(PROJECT_TARTAMPION_ID, PROJECT_TARTAMPION_NAME, PROJECT_TARTAMPION_COLOR);

    public static final String TASK_1_NAME = "Tache 1";
    public static final long TASK_1_TIMESTAMP  = new Date().getTime();
    public static final Task TASK_1 = new Task(PROJECT_CIRCUS_ID, TASK_1_NAME, TASK_1_TIMESTAMP);

    public static final String TASK_2_NAME = "Tache 2";
    public static final long TASK_2_TIMESTAMP  = new Date().getTime();
    public static final Task TASK_2 = new Task(PROJECT_CIRCUS_ID, TASK_2_NAME, TASK_2_TIMESTAMP);

    public static final String TASK_3_NAME = "Tache 3";
    public static final long TASK_3_TIMESTAMP  = new Date().getTime();
    public static final Task TASK_3 = new Task(PROJECT_CIRCUS_ID, TASK_3_NAME, TASK_3_TIMESTAMP);

    public static final String TASK_4_NAME = "Tache 4";
    public static final long TASK_4_TIMESTAMP  = new Date().getTime();
    public static final Task TASK_4 = new Task(PROJECT_CIRCUS_ID, TASK_4_NAME, TASK_4_TIMESTAMP);
}
