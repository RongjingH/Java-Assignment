import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Book extends Item{

    private static Set<String> usedIsbns = new HashSet<>();
    private String isbn;

    public Book(String title, List<String> authors) {
        super(title, authors);
        this.isbn = generateIsbn();
    }

    public String getIsbn() {
        return isbn;
    }

    @Override
    public void displayAttributes() {
        System.out.println("Here are the details of the book.");
        System.out.println("Book ID: " + getId());
        System.out.println("Title: " + getTitle());
        System.out.println("Authors: " + getAuthors());
        System.out.println("ISBN: " + getIsbn());
    }

    //Method to generate unique ISBN
    private String generateIsbn() {
        //ISBN is 13 digits right now
        StringBuilder isbnBuilder = new StringBuilder();
        Random random = new Random();
        while (true) {
            for (int i = 0; i < 13; i++) {
                isbnBuilder.append(random.nextInt(10));
            }
            String newIsbn = isbnBuilder.toString();
            if (!usedIsbns.contains(newIsbn)) {
                usedIsbns.add(newIsbn);
                return newIsbn;
            }
            isbnBuilder.setLength(0);
        }
    }
}
