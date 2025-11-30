package repository;

import java.util.List;
import task.Task;

public interface TaskDAO {

    List<Task> loadTasks();
    void saveTasks(List<Task> tasks);

}