package repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import task.Task;

public class TaskDAOImpl implements TaskDAO {

    private final String FILE_PATH = "tasks.json";
    private Gson gson;

    public TaskDAOImpl() {

        gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).setPrettyPrinting().create();

    }

    public List<Task> loadTasks() {

        File file = new File(FILE_PATH);
        List<Task> tasks = new ArrayList<>();

        if(!file.exists() || file.length() == 0) {

            tasks = new ArrayList<>();

        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            Type type = new TypeToken<ArrayList<Task>>(){}.getType();

            List<Task> temp = gson.fromJson(br, type);

            if(temp != null) {

                tasks = temp;

            }

        }catch(IOException e) {

            System.err.println("Error reading file. " + e.getMessage());

        }catch(JsonSyntaxException e) {

            System.err.println("File contains invalid JSON syntax. " + e.getMessage());

        }

        return tasks;

    }

    public void saveTasks(List<Task> tasks) {

        String jsonString = gson.toJson(tasks);

        try(FileWriter fw = new FileWriter(FILE_PATH)) {

            fw.write(jsonString);
            
        }catch(IOException e) {

            System.err.println("An error occurred while saving the file: " + e.getMessage());

        }

    }

}