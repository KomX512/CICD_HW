package main.grp.services;

import main.grp.models.Task;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class TaskService {
    private final List<Task> tasksList = new ArrayList<>();
    private final AtomicInteger idCounter = new AtomicInteger(1);

    public List<Task> getAllTasks() {
        return new ArrayList<>(tasksList);
    }

    public Optional<Task> getTaskById(Integer id) {
        return tasksList.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst();
    }

    public Task createTask(Task task) {
        Task newTask = new Task(
                idCounter.getAndIncrement(),
                task.getTitle(),
                false,
                LocalDate.now()
        );
        tasksList.add(newTask);
        return newTask;
    }

    public Optional<Task> updateTask(Integer id, Task taskDetails) {
        Optional<Task> existingTask = getTaskById(id);

        if (existingTask.isPresent()) {
            Task task = existingTask.get();
            task.setTitle(taskDetails.getTitle());
            task.setCompleted(taskDetails.isCompleted());
            return Optional.of(task);
        }

        return Optional.empty();
    }

    public boolean deleteTask(Integer id) {
        return tasksList.removeIf(task -> task.getId().equals(id));
    }
}