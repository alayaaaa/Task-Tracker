package cli;

import manager.TaskManager;
import repository.TaskDAOImpl;

import java.util.Scanner;

public class TaskCLI {

    TaskManager manager = new TaskManager(new TaskDAOImpl());
    Scanner scanner = new Scanner(System.in);

    public void run() {

        welcomeMessage();
        String input;

        while(!((input = scanner.next().toLowerCase()).equals("exit"))) {

            switch(input) {

                case "add":
                    String description = scanner.nextLine().trim();
                    add(description);
                    break;

                case "update":
                    if(scanner.hasNextInt()) {

                        int newId = scanner.nextInt();
                        String newDescription = scanner.nextLine().trim();
                        update(newId, newDescription);

                    }else {

                        System.out.println("Error: ID must be a number.");
                        scanner.nextLine();

                    }

                    break;

                case "delete":
                    intHandling(this::delete);
                    break;

                case "mark-in-progress":
                    intHandling(this::markInProgress);
                    break;

                case "mark-done":
                    intHandling(this::markDone);
                    break;
                    
                case "list":
                    list();
                    break;

                case "list-done":
                    listDone();
                    break;

                case "list-todo":
                    listTODO();
                    break;

                case "list-in-progress":
                    listInProgress();
                    break;

                case "help":
                    System.out.println("add [description] : Add a new task");
                    System.out.println("update [id] [description] : Update an existing task");
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

        scanner.close();

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

    private void add(String description) {

        manager.addTask(description);

    }

    private void update(int id, String description) {

        manager.updateTask(id, description);

    }

    private void delete(int id) {

        manager.deleteTask(id);

    }

    private void list() {

        manager.listTask();

    }

    private void listTODO() {

        manager.listTask("TODO");

    }

    private void listInProgress() {

        manager.listTask("IN_PROGRESS");

    }

    private void listDone() {

        manager.listTask("DONE");

    }

    private void markInProgress(int id) {

        manager.markInProgress(id);

    }

    private void markDone(int id) {

        manager.markDone(id);

    }

    // A helper method to handle commands that require an ID.
    private void intHandling(java.util.function.IntConsumer action) {

        if (scanner.hasNextInt()) {

            int id = scanner.nextInt();
            action.accept(id);
            scanner.nextLine(); 

        } else {

            System.out.println("Error: Invalid ID provided. Please enter a number.");
            scanner.nextLine();

        }

    }

}