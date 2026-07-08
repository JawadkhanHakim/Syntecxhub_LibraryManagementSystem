package com.library;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LibraryDAO dao = new LibraryDAO();
        boolean running = true;

        System.out.println("📚 Welcome to Library Management System 📚");

        while (running) {
            System.out.println("\n----- MENU -----");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Search Book by Title");
            System.out.println("4. View All Books");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    String title = readNonEmpty(sc, "Enter title: ");
                    String author = readNonEmpty(sc, "Enter author: ");
                    String isbn = readNonEmpty(sc, "Enter ISBN: ");
                    int quantity = readNonNegativeInt(sc, "Enter quantity: ");
                    dao.addBook(new Book(title, author, isbn, quantity));
                    break;

                case "2":
                    String removeIsbn = readNonEmpty(sc, "Enter ISBN of book to remove: ");
                    dao.removeBook(removeIsbn);
                    break;

                case "3":
                    String searchTitle = readNonEmpty(sc, "Enter title to search: ");
                    List<Book> results = dao.searchByTitle(searchTitle);
                    if (results.isEmpty()) {
                        System.out.println("No books found.");
                    } else {
                        results.forEach(System.out::println);
                    }
                    break;

                case "4":
                    List<Book> allBooks = dao.getAllBooks();
                    if (allBooks.isEmpty()) {
                        System.out.println("No books in library yet.");
                    } else {
                        allBooks.forEach(System.out::println);
                    }
                    break;

                case "5":
                    running = false;
                    System.out.println("Goodbye! 👋");
                    break;

                default:
                    System.out.println("❌ Invalid choice, try again.");
            }
        }

        sc.close();
    }

    // Keeps asking until user enters non-empty text
    private static String readNonEmpty(Scanner sc, String prompt) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = sc.nextLine().trim();
            if (!input.isEmpty()) break;
            System.out.println("❌ This field cannot be empty. Try again.");
        }
        return input;
    }

    // Keeps asking until user enters a valid, non-negative number
    private static int readNonNegativeInt(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();
            try {
                int value = Integer.parseInt(input);
                if (value < 0) {
                    System.out.println("❌ Value cannot be negative. Try again.");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid number. Try again.");
            }
        }
    }
}