import java.util.Arrays;
import java.util.Comparator;

public class LibraryTest {
    public static void main(String[] args) {
        Book[] books = {
            new Book(1, "The Pragmatic Programmer", "David Thomas"),
            new Book(2, "Clean Code", "Robert Martin"),
            new Book(3, "Effective Java", "Joshua Bloch"),
            new Book(4, "Introduction to Algorithms", "Thomas Cormen"),
            new Book(5, "Design Patterns", "Erich Gamma")
        };

        System.out.println("Linear Search for 'Effective Java':");
        Book linearResult = LibrarySearch.linearSearchByTitle(books, "Effective Java");
        System.out.println(linearResult != null ? linearResult : "Book not found");

        Book[] sortedBooks = Arrays.copyOf(books, books.length);
        Arrays.sort(sortedBooks, Comparator.comparing(Book::getTitle));

        System.out.println("\nSorted Books for Binary Search:");
        for (Book book : sortedBooks) {
            System.out.println(book);
        }

        System.out.println("\nBinary Search for 'Design Patterns':");
        Book binaryResult = LibrarySearch.binarySearchByTitle(sortedBooks, "Design Patterns");
        System.out.println(binaryResult != null ? binaryResult : "Book not found");

        System.out.println("\nBinary Search for 'Unknown Book' (not present):");
        Book notFoundResult = LibrarySearch.binarySearchByTitle(sortedBooks, "Unknown Book");
        System.out.println(notFoundResult != null ? notFoundResult : "Book not found");
    }
}
