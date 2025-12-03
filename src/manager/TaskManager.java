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

    private int findTask(int id) {

        for(int i = 0; i < tasks.size(); i++) {

            Task task = tasks.get(i);
            
            if(task.getId() == id) {

                return i;

            }

        }

        return -1;


    }

    public void updateTask(int id, String newDescription) {

        int index = findTask(id);

        tasks.get(index).setDescription(newDescription);
        tasks.get(index).setUpdatedAt();

        persistence.saveTasks(tasks);

    }


}