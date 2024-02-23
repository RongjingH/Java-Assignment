import java.util.List;

public abstract class Item {
    private static int nextId = 1; // Static variable to keep track of next ID
    private String id;
    private String title;
    private List<String> authors;

    public Item(String title, List<String> authors) {
        this.id = "I" + nextId++;
        this.title = title;
        this.authors = authors;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public abstract void displayAttributes();
}
