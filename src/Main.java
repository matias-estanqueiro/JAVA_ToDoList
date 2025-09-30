import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        int option;
        Scanner sc = new Scanner(System.in);

        do {
            showMenu();
            System.out.println("Select an option from the menu: ");
            while(!sc.hasNextInt()) {
                System.out.println("Invalid entry, Only numbers are allowed.");
                sc.next();
                System.out.println("Select an option from the menu: ");
            }
            option = sc.nextInt();
            sc.nextLine();

            switch(option) {
                case 1:
                    System.out.println("Task Description: ");
                    String task = sc.nextLine();
                    addTask(tasks, task);
                    break;
                case 2:
                    if(tasks.isEmpty()) {
                        System.out.println("The task list is empty.");
                    } else {
                        showTasks(tasks);
                        System.out.println("Number of the task to delete: ");
                        if(sc.hasNextInt()) {
                            int taskToDelete = sc.nextInt();
                            sc.nextLine();
                            deleteTask(tasks, taskToDelete);
                        } else {
                            System.out.println("Invalid number entered.");
                            sc.next();
                        }
                    }
                    break;
                case 3:
                    System.out.println("Number of the task to show: ");
                    if(sc.hasNextInt()) {
                        int taskToShow = sc.nextInt();
                        sc.nextLine();
                        showTask(tasks, taskToShow);
                    } else {
                        System.out.println("Invalid number entered.");
                    }

                    break;
                case 4:
                    if(tasks.isEmpty()) {
                        System.out.println("The task list is empty.");
                    } else {
                        showTasks(tasks);
                    }
                    break;
                case 5:
                    System.out.println("Number of the task to complete/incomplete: ");
                    if(sc.hasNextInt()){
                        int statusToChange = sc.nextInt();
                        sc.nextLine();
                        changeTaskStatus(tasks, statusToChange);
                    } else {
                        System.out.println("Invalid number entered.");
                    }
                    break;
                default:
                    System.out.println("The option you entered is not correct. Please re-enter: ");

            }
        } while (option != 0);
        sc.close();
        System.out.println("GoodBye!...");
    }

    /**
     * Displays the application's menu options on the console.
     */
    public static void showMenu() {
        System.out.println("---------------------------");
        System.out.println("TO DO LIST");
        System.out.println("---------------------------");
        System.out.println("1 - ADD TASK");
        System.out.println("2 - DELETE TASK");
        System.out.println("3 - SHOW TASK");
        System.out.println("4 - SHOW TASK LIST");
        System.out.println("5 - COMPLETE/INCOMPLETE TASK");
        System.out.println("0 - EXIT");
        System.out.println("---------------------------");
    }

    /**
     * Adds a new task to the list.
     *
     * @param tasks The list of tasks to which the new task will be added.
     * @param task  The description of the new task.
     */
    public static void addTask(ArrayList<Task> tasks, String task) {
        Task newTask = new Task(task);
        tasks.add(newTask);
        System.out.println("The task was added successfully!");
    }

    /**
     * Deletes a task from the list, validating that it exists.
     *
     * @param tasks        The list of tasks.
     * @param taskToDelete The number of the task to delete (based on a 1-based index).
     */
    public static void deleteTask(ArrayList<Task> tasks, int taskToDelete) {
        if(taskValidate(tasks, taskToDelete)) {
            System.out.println("The task entered does not exist, please enter a valid number.");
        } else {
            tasks.remove(taskToDelete - 1);
            System.out.println("the task was deleted successfully!");
        }
    }

    /**
     * Displays the details of a specific task, validating its existence.
     *
     * @param tasks      The list of tasks.
     * @param taskToShow The number of the task to show (based on a 1-based index).
     */
    public static void showTask(ArrayList<Task> tasks, int taskToShow) {
        if(taskValidate(tasks, taskToShow)) {
            System.out.println("The task entered does not exist, please enter a valid number.");
        } else {
            Task task = tasks.get(taskToShow - 1);
            System.out.println("---------------------------");
            System.out.println("Task description: " + task.getDescription());
            System.out.println("Task Complete?: " + (task.isCompleted()? "YES" : "NO"));
            System.out.println("---------------------------");
        }
    }

    /**
     * Toggles a task's status between completed and incomplete, validating its existence.
     *
     * @param tasks        The list of tasks.
     * @param taskToChange The number of the task to modify (based on a 1-based index).
     */
    public static void changeTaskStatus(ArrayList<Task> tasks, int taskToChange) {
        if(taskValidate(tasks, taskToChange)) {
            System.out.println("The task entered does not exist, please enter a valid number.");
        } else {
            Task task = tasks.get(taskToChange - 1);
            task.setStatus(!task.isCompleted());
            System.out.println("The task status was updated successfully!");
        }
    }

    /**
     * Displays all tasks in the list on the console.
     *
     * @param tasks The list of tasks to display.
     */
    public static void showTasks(ArrayList<Task> tasks) {
        int id = 1;
        for(Task task : tasks) {
            System.out.println(id + "- " + task.getDescription());
            id++;
        }
    }

    /**
     * Validates if a task number is within the valid range for the list.
     * Returns 'true' if the number is invalid, and 'false' if it's valid.
     *
     * @param tasks The list of tasks to check.
     * @param task  The task number to validate.
     * @return boolean Returns 'true' if the number is out of bounds, 'false' otherwise.
     */
    public static boolean taskValidate(ArrayList<Task> tasks, int task) {
        return (task < 1 || task > tasks.size());
    }
}
