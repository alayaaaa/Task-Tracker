package cli;

import manager.TaskManager;
import repository.TaskDAOImpl;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class TaskCLI {

    TaskManager manager = new TaskManager(new TaskDAOImpl());

    public void run() {

        Scanner scan = new Scanner(System.in);

        welcomeMessage();
        String input;

        while(!((input = scan.next().toLowerCase()).equals("exit"))) {

            String inputTrim = scan.nextLine();

            switch(input) {

                case "add":
                    String description = inputTrim.trim();
                    int id = add(description);
                    System.out.printf("Task added successfully (ID: %s)", id);
                    break;

                case "update":
                    System.out.println("Task updated successfully.");
                    break;

                case "delete":
                    System.out.println("Task deleted successfully.");
                    break;

                case "mark-in-progress":
                    System.out.println("Task updated successfully.");
                    break;

                case "mark-done":
                    System.out.println("Task updated successfully.");
                    break;
                    
                case "list":

                    System.out.println("Task updated successfully.");
                    break;

                case "list-done":
                    break;

                case "list-todo":
                    break;

                case "list-in-progress":
                    break;

                case "help":
                    System.out.println("add [description] : Add a new task");
                    System.out.println("update [id] [description]: Update an existing task");
                    System.out.println("delete [id] [description] : Delete a new task");
                    System.out.println("list : List all tasks");
                    System.out.println("list-done : List all finished tasks");
                    System.out.println("list-todo : List all to-do tasks");
                    System.out.println("list-in-progress : List all in-progress tasks");
                    System.out.println("exit : Terminate the program");
                    break;

                default:
                    System.out.println("Invalid input. Type 'help' for commands.");
                    break;

            }

        }

    }

    private static void welcomeMessage() {

        System.out.println("Welcome to the Task Tracker!");
        System.out.println("add [description] : Add a new task");
        System.out.println("update [id] [description]: Update an existing task");
        System.out.println("delete [id] [description] : Delete a new task");
        System.out.println("mark-in-progress : Mark a task as in-progress");
        System.out.println("mark-done : Mark a task as done");
        System.out.println("list : List all tasks");
        System.out.println("list-done : List all finished tasks");
        System.out.println("list-todo : List all to-do tasks");
        System.out.println("list-in-progress : List all in-progress tasks");
        System.out.println("exit : Terminate the program");
        System.out.println("Type 'help' for commands.");

    }

    private int add(String description) {

        return manager.addTask(description);

    }

}