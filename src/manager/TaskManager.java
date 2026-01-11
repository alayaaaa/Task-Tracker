package manager;

import task.Task;
import task.TaskStatus;
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

        if(tasks.isEmpty()) {

            System.out.println("Your task list is empty. Use 'add <description>' to add a task.");
            return;

        }

        int index = findTask(id);

        if (index == -1) {

            System.out.println("Task ID " + id + " not found.");
            return;

        }

        tasks.get(index).setDescription(newDescription);
        tasks.get(index).setUpdatedAt();

        persistence.saveTasks(tasks);

    }

    public void deleteTask(int id) {

        if(tasks.isEmpty()) {

            System.out.println("Your task list is empty. Use 'add <description>' to add a task.");
            return;

        }

        int index = findTask(id);

        if (index == -1) {

            System.out.println("Task ID " + id + " not found.");
            return;

        }

        tasks.remove(index);
        
        persistence.saveTasks(tasks);

    }

    public void listTask() {
    
        if(tasks.isEmpty()) {

            System.out.println("Your task list is empty. Use 'add <description>' to add a task.");
            return;

        }

        System.out.println("---------------------------Your Tasks---------------------------");

        for(Task task : tasks) {

            System.out.println(task);

        }

        System.out.println("----------------------------------------------------------------");

    }

    public void listTask(String listType) {
    
        if(tasks.isEmpty()) {

            System.out.println("Your task list is empty. Use 'add <description>' to add a task.");
            return;

        }
        
        TaskStatus statusCheck;
        try {

            statusCheck = TaskStatus.valueOf(listType.toUpperCase());

        }catch(IllegalArgumentException e) {

            System.out.println("Invalid status. " + e.getMessage());
            return;

        }

        String statusPrint = null;

        switch(statusCheck) {

            case TaskStatus.TODO: 
                statusPrint = "To Do";
                break;
            
            case TaskStatus.IN_PROGRESS:
                statusPrint = "In Progress";
                break;

            case TaskStatus.DONE:
                statusPrint = "Done";
                break;

        }


        
        System.out.printf("---------------------------Your %s Tasks---------------------------\n", statusPrint);

        for(Task task : tasks) {

            if(task.getStatus() == statusCheck) {

                System.out.println(task);

            }
            
        }

        System.out.println("----------------------------------------------------------------");

    }

    public void markInProgress(int id) {

        int index = findTask(id);

        if (index == -1) {

            System.out.println("Task ID " + id + " not found.");
            return;

        }

        tasks.get(index).setStatus(TaskStatus.IN_PROGRESS);

        persistence.saveTasks(tasks);

    }

    public void markDone(int id) {

        int index = findTask(id);

        if (index == -1) {

            System.out.println("Task ID " + id + " not found.");
            return;

        }

        tasks.get(index).setStatus(TaskStatus.DONE);
        persistence.saveTasks(tasks);

    }
}