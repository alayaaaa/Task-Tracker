package repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.core.type.TypeReference;

import task.Task;

public class TaskDAOImpl implements TaskDAO {

    private final String FILE_PATH = "tasks.json";
    private ObjectMapper mapper;

    /**
     * Support Java 8 date/time types {@code LocalDateTime} via {@code JavaTimeModule}
     * Enables pretty printing so the JSON file is human-readable
     * Enables access to private fields directly to bypass public getters/setters
     */
    public TaskDAOImpl() {

        mapper = new ObjectMapper().registerModule(new JavaTimeModule())
                .enable(SerializationFeature.INDENT_OUTPUT)
                .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

    }

    /**
     * Loads the list of tasks from the JSON file
     *
     * @return a list of Task objects, or an empty list if the file is missing/empty
     */
    public List<Task> loadTasks() {

        File file = new File(FILE_PATH);
        List<Task> tasks = new ArrayList<>();

        // Check if the file exist and has content
        // If true, return an empty list immediately
        if(!file.exists() || file.length() == 0) {

            return tasks;

        }

        try {

            // Deserialize JSON to List<Task>
            // Use TypeReference to tell Jackson what generic type we need
            // because java erases generic types at runtime.
            List<Task> temp = mapper.readValue(file, new TypeReference<List<Task>>() {});

            // Ensures we don't return null if the file contains nothing
            if(temp != null) {

                tasks = temp;

            }

        }catch(IOException e) {

            System.err.println("Error reading file. " + e.getMessage());

        }

        return tasks;

    }

    /**
     * Serializes the list of tasks and saves them to the JSON file.
     * This method uses Jackson to handle the entire file writing process,
     * including opening the file, converting the Java objects to JSON,
     * and closing the resource safely.
     *
     * @param tasks The list of Task objects to save.
     */
    public void saveTasks(List<Task> tasks) {

        try {

            mapper.writeValue(new File(FILE_PATH), tasks);
            
        }catch(IOException e) {

            System.err.println("An error occurred while attempting to save the file: " + e.getMessage());

        }

    }

}