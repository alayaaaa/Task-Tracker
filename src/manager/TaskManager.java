package manager;

import task.Task;
import repository.TaskDAO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {

    private List<Task> tasks;
    private int nextId = 1;
    private final TaskDAO persistence;

    public TaskManager(TaskDAO persistence) {

        this.persistence = persistence;
        this.tasks = persistence.loadTasks();
        findNextId();

    }

    private void findNextId() {

        int currentId = 0;
        for(Task task : tasks) {

            if(task.getId() > currentId) {

                currentId = task.getId();

            }

        }

        this.nextId = currentId + 1;

    }

    public int addTask(String description) {

        Task task = new Task(nextId, description);
        tasks.add(task);

        persistence.saveTasks(tasks);

        return nextId++;

    }

}