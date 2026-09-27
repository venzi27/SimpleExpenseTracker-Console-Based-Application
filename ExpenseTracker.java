package SimpleExpenseTracker;

import java.util.Collections;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ExpenseTracker {
    public static void main(String[] args) throws InterruptedException {

        String[] option = { "Add List", "Remove", "View", "Exit" };
        Scanner scan = new Scanner(System.in);
        HashMap<String, Double> listExpense = new HashMap<>();

        while (true) {
            System.out.println();
            System.out.println("====EXPENSE TRACKER====");

            for (int i = 0; i < option.length; i++) {
                System.out.printf("|%d. %-18s |  ", i + 1, option[i]);
                System.out.println();
            }

            System.out.println("========================");
            System.out.print("|Select: ");
            int choice = 0;
            try {
                choice = scan.nextInt();
            } catch (InputMismatchException e) {
                System.out.println(">> Invalid Option <<");
                scan.nextLine();
            }

            switch (choice) {
                case 1 -> UiAddListLoop(scan, listExpense);
                case 2 -> removeElementinList(listExpense, scan);
                case 3 -> viewDashboard(listExpense);
                case 4 -> {
                    System.out.println("Exiting.......");
                    Thread.sleep(1000);
                    System.exit(0);
                }
                default -> System.out.printf(">> %d doesn't exist. <<", choice);

            }
        }
    }

    public static void UiAddListLoop(Scanner scan, HashMap<String, Double> listExpense) {

        boolean addAnother = true;

        while (addAnother) {
            System.out.print("> Enter Expense Name: ");
            String expense = scan.next().trim();
            scan.nextLine();

            System.out.print("> Enter a value: ");

            while (!scan.hasNextDouble()) {
                System.out.print(">> Invalid amount. Enter a numeric value: ");
                scan.next();
            }
            double value = scan.nextDouble();
            scan.nextLine();

            if (listExpense.containsKey(expense)) {
                System.out.println(">> Note: Updated existing expense price. <<");
            }

            listExpense.put(expense, value);
            System.out.println(">> Successfully Added! <<");

            String choice = "";
            while (true) {
                System.out.print("> Add another? (Y/N): ");
                choice = scan.nextLine().trim();

                if (choice.equalsIgnoreCase("Y")) {
                    break;
                } else if (choice.equalsIgnoreCase("n")) {
                    addAnother = false;
                    break;
                } else {
                    System.out.println(">> Invalid Choice. <<");
                }
            }
        }

    }

    public static void removeElementinList(HashMap<String, Double> listExpense, Scanner scan) {

        String expenseName = "";

        if (listExpense.isEmpty()) {
            System.out.println(">>List is Empty<<");
            return;
        }

        while (expenseName.isBlank()) {
            System.out.print("> Enter Expense Name: ");
            expenseName = scan.next().trim();
        }

        if (listExpense.containsKey(expenseName)) {
            listExpense.remove(expenseName);
            System.out.println(">>Successfully Removed!<<");
        } else {
            System.out.printf(">> %s is not in List << ", expenseName);
        }

    }

    public static void viewDashboard(HashMap<String, Double> listExpense) {
        System.out.println();

        if (!listExpense.isEmpty()) {
            System.out.println("==========DASHBOARD===========");

            listExpense.entrySet()
                    .stream()
                    .sorted(Map.Entry.<String, Double>comparingByValue())
                    .forEach(entry -> System.out.printf(" %s = %.2f \n", entry.getKey(), entry.getValue()));

            System.out.println("==============================");
        } else {
            System.out.println("No list of expense");
        }
    }
}
