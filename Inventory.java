import java.util.LinkedList;
import java.util.Scanner;
import java.util.Queue;

public class Inventory {

    private static LinkedList<Book> bookList = new LinkedList<>();
    private static Queue<String> orderQueue = new LinkedList<>();

    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Bookstore Inventory Management System");

        while(running) {
            System.out.println("\n---------------------");
            System.out.println("Select an option:");
            System.out.println("1. Add a new book");
            System.out.println("2. Display all books");
            System.out.println("3. Sort books by title");
            System.out.println("4. Search for a book by title");
            System.out.println("5. Add a customer order to the queue");
            System.out.println("6. Process the next customer order");
            System.out.println("7. Exit");
            System.out.print("Enter a choice: ");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        addBook(scanner);
                        break;
                    case 2:
                        displayAllBooks();
                        break;
                    case 3:
                        sortBooksByTitle();
                        break;
                    case 4:
                        searchBookByTitle(scanner);
                        break;
                    case 5:
                        addOrderToQueue(scanner);
                        break;
                    case 6:
                        processNextOrder();
                        break;
                    case 7:
                        System.out.println("Exiting application. Have a great day!");
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again");
                }
            } else {
                System.out.println("Invalid input. Please enter the number.");
                scanner.next();
            }
        }
        scanner.close();
    }

public static void addBook(Scanner scanner) {
    System.out.println("\n--- Add New Book---");

    System.out.print("Enter Title: ");
    String title = scanner.nextLine();

    System.out.print("Enter Author: ");
    String author = scanner.nextLine();

    System.out.print("Enter ISBN: ");
    String isbn = scanner.nextLine();

    System.out.print("Enter Book Price: ");
    double price = 0.0;
    if (scanner.hasNextDouble()) {
        price = scanner.nextDouble();
        scanner.nextLine();
    } else {
        System.out.println("Invalid price. Defaulting to 0.0");
        scanner.nextLine();
    }

    Book newBook = new Book(title, author, isbn, price);
    bookList.add(newBook);
    System.out.println("Book added successfully!");
}

    public static void displayAllBooks() {
        System.out.println("\n--- All Books in Inventory ---");
        if (bookList.isEmpty()) {
            System.out.println("The inventory is empty.");
        } else {
            for (Book b : bookList) {
                System.out.println(b.toString());
            }
        }
        System.out.println("------------------------------");
    }

    public static void sortBooksByTitle() {
        if (bookList.isEmpty() || bookList.size() == 1) {
            System.out.println("Not enough books to sort.");
            return;
        }

        System.out.println("Sorting books by title...");
        int n = bookList.size();

        //Bubble Sort Algorithm
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                Book b1 = bookList.get(j);
                Book b2 = bookList.get(j + 1);

                if (b1.getTitle().compareToIgnoreCase(b2.getTitle()) > 0) {
                    bookList.set(j, b2);
                    bookList.set(j + 1, b1);
                }
            }
        }
        System.out.println("Books sorted successfully!");
        displayAllBooks();
    }


    //Linear Search
    public static void searchBookByTitle(Scanner scanner) {
        System.out.print("Enter the title of the book to search for: ");
        String searchTitle = scanner.nextLine();
        boolean found = false;

        for (Book b : bookList) {

            if (b.getTitle().equalsIgnoreCase(searchTitle)) {
                System.out.println("Book found...4 ");
                System.out.println(b.toString());
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Book not found in inventory.");
        }
    }

    public static void addOrderToQueue(Scanner scanner) {
        System.out.print("Enter the title of the book to order: ");
        String title = scanner.nextLine();

        orderQueue.add(title);
        System.out.println("Order for \"" + title +" \" has been added to the queue.");
    }

    public static void processNextOrder() {
        System.out.println("Processing next order...");

        if (orderQueue.isEmpty()) {
            System.out.println("No orders in the queue to process.");
        } else {

            String processedBook = orderQueue.poll();
            System.out.println("Processed order for: " + processedBook);
        }
    }
}

