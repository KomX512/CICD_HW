package main.grp;

import main.grp.models.Task;
import main.grp.services.TaskService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        taskService = new TaskService();
    }

    @Test
    void testCreateTask() {
        Task task = new Task();
        task.setTitle("Test Task");

        Task created = taskService.createTask(task);

        assertNotNull(created);
        assertNotNull(created.getId());
        assertEquals("Test Task", created.getTitle());
        assertFalse(created.isCompleted());
        assertNotNull(created.getCreatedAt());
    }

    @Test
    void testGetAllTasks() {

        Task task1 = new Task();
        task1.setTitle("Task RAZ");
        taskService.createTask(task1);

        Task task2 = new Task();
        task2.setTitle("Task DVA");
        taskService.createTask(task2);

        List<Task> tasksList = taskService.getAllTasks();

        assertEquals(2, tasksList.size());
    }

    @Test
    void testGetTaskById() {
        Task task = new Task();
        task.setTitle("Test Task");
        Task created = taskService.createTask(task);

        Optional<Task> found = taskService.getTaskById(created.getId());

        assertTrue(found.isPresent());
        assertEquals(created.getId(), found.get().getId());
    }

    @Test
    void testUpdateTask() {
        Task task = new Task();
        task.setTitle("Original Task");
        Task created = taskService.createTask(task);

        Task updateDetails = new Task();
        updateDetails.setTitle("Updated Task");
        updateDetails.setCompleted(true);

        Optional<Task> updated = taskService.updateTask(created.getId(), updateDetails);

        assertTrue(updated.isPresent());
        assertEquals("Updated Task", updated.get().getTitle());
        assertTrue(updated.get().isCompleted());
    }

    @Test
    void testDeleteTask() {
        Task task = new Task();
        task.setTitle("Task to delete");
        Task created = taskService.createTask(task);

        boolean deleted = taskService.deleteTask(created.getId());

        assertTrue(deleted);
        assertTrue(taskService.getTaskById(created.getId()).isEmpty());
    }

    @AfterEach
    void testComplMsg(){
        System.out.println("Test complet!");
    }
}
