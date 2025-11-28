package manager;

import task.Task;

import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {

    private final String FILE_PATH = "tasks.json";
    private Gson gson;
    private List<Task> tasks;
    private int id = 0;

    public TaskManager() {

        gson = new Gson();
        loadTask();

    }

    private void loadTask() {

        File file = new File(FILE_PATH);
        this.tasks = new ArrayList<>();

        if(!file.exists() || file.length() == 0) {

            return;

        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            Type type = new TypeToken<ArrayList<Task>>(){}.getType();
            
            List<Task> temp = gson.fromJson(br, type);

            if(temp != null) {

                this.tasks = temp;

            }

        }catch(IOException e) {

            System.err.println("Error reading file. " + e.getMessage());

        }catch(JsonSyntaxException e) {

            System.err.println("File contains invalid JSON syntax. " + e.getMessage());

        }

    }

    public void saveTask() {

        gson = new GsonBuilder().setPrettyPrinting().create();

        String jsonString = gson.toJson(tasks);

        try(FileWriter fw = new FileWriter(FILE_PATH)) {

            fw.write(jsonString);
            
        }catch(IOException e) {

            System.out.println("An error occurred while saving the file: " + e.getMessage());

        }

    }

    public int addTask(String description) {

        Task task = new Task(id, description);
        tasks.add(task);

        return id++;

    }

}