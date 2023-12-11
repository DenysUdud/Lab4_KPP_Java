import java.io.*;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CustomContainer<Product> productContainer;
        Scanner scanner = new Scanner(System.in);
        String reply = scanner.nextLine();

       // if ((args.length > 0 && args[0].equals("-auto")) || scanner.toString().equals("-auto")) {
        if (reply.toString().equals("-auto")) {
            // Автоматичний режим
            productContainer = generateTestData();
        } else {
            // Діалоговий режим
            productContainer = runInteractiveMode();
        }

        // Додаткові операції з контейнером або виведення результатів
        for (Product product : productContainer) {
            System.out.println(product);
        }

        // Збереження контейнера
        ContainerUtil.saveContainer(productContainer, "productContainer.dat");
    }

    private static CustomContainer<Product> generateTestData() {
        CustomContainer<Product> container = new CustomContainer<>();

        // Згенерувати тестові дані та додати до контейнера
        container.add(new Product("Item1", "kg", 10, 5, parseDate("2023-01-01"), "Description1"));
        container.add(new Product("Item2", "pcs", 20, 3, parseDate("2023-02-01"), "Description2"));
        container.add(new Product("Item3", "liters", 15, 8, parseDate("2023-03-01"), "Description3"));

        return container;
    }

    private static CustomContainer<Product> runInteractiveMode() {
        // Реалізація діалогового режиму
        CustomContainer<Product> container = new CustomContainer<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Interactive Mode Menu:");
            System.out.println("1. Add a product");
            System.out.println("2. Finish");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Очищення буфера

            switch (choice) {
                case 1:
                    System.out.print("Product name: ");
                    String name = scanner.nextLine();
                    System.out.print("Unit: ");
                    String unit = scanner.nextLine();
                    System.out.print("Quantity: ");
                    int quantity = scanner.nextInt();
                    System.out.print("Price: ");
                    int price = scanner.nextInt();
                    System.out.print("Arrival date (YYYY-MM-DD): ");
                    String dateStr = scanner.next();
                    Date arrivalDate = parseDate(dateStr);
                    scanner.nextLine(); // Очищення буфера
                    System.out.print("Description: ");
                    String description = scanner.nextLine();

                    Product newProduct = new Product(name, unit, quantity, price, arrivalDate, description);
                    container.add(newProduct);
                    System.out.println("Product added!");
                    break;

                case 2:
                    System.out.println("Finishing Interactive Mode.");
                    return container;

                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
        }
    }

    private static Date parseDate(String dateStr) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
        } catch (ParseException e) {
            System.err.println("Error parsing date. Using current date.");
            return new Date();
        }
    }
}
